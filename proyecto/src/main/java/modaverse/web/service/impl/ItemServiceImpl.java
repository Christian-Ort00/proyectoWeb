/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modaverse.web.service.impl;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import modaverse.web.dao.FacturaDao;
import modaverse.web.dao.ProductoDao;
import modaverse.web.dao.UsuarioDao;
import modaverse.web.dao.VentaDao;

import modaverse.web.domain.Factura;
import modaverse.web.domain.Item;
import modaverse.web.domain.Usuario;
import modaverse.web.domain.Venta;

import modaverse.web.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private HttpSession session;

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> gets() {
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        return (listaItems != null) ? listaItems : new ArrayList<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Item get(Item item) {
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        if (listaItems != null && item != null && item.getProductoID() != null) {
            for (Item i : listaItems) {
                if (Objects.equals(i.getProductoID(), item.getProductoID())) {
                    return i;
                }
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(Item item) {
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        if (listaItems != null && item != null && item.getProductoID() != null) {
            int posicion = -1;
            boolean existe = false;
            for (Item i : listaItems) {
                posicion++;
                if (Objects.equals(i.getProductoID(), item.getProductoID())) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                listaItems.remove(posicion);
                session.setAttribute("listaItems", listaItems);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void save(Item item) {
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        if (listaItems == null) {
            listaItems = new ArrayList<>();
        }
        boolean existe = false;
        for (Item i : listaItems) {
            if (Objects.equals(i.getProductoID(), item.getProductoID())) {
                existe = true;
                // Como ya no hay 'existencias', solo incrementamos cantidad
                i.setCantidad(i.getCantidad() + 1);
                break;
            }
        }
        if (!existe) {
            if (item.getCantidad() <= 0) item.setCantidad(1);
            listaItems.add(item);
        }
        session.setAttribute("listaItems", listaItems);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Item item) {
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        if (listaItems != null && item != null && item.getProductoID() != null) {
            for (Item i : listaItems) {
                if (Objects.equals(i.getProductoID(), item.getProductoID())) {
                    int cant = item.getCantidad();
                    i.setCantidad(cant <= 0 ? 1 : cant);
                    session.setAttribute("listaItems", listaItems);
                    break;
                }
            }
        }
    }

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;

    @Override
    @SuppressWarnings("unchecked")
    public void facturar() {
        // 1) Usuario autenticado
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else if (principal != null) {
            username = principal.toString();
        }
        if (username.isBlank()) {
            System.out.println("username en blanco...");
            return;
        }

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            System.out.println("Usuario no existe en usuarios...");
            return;
        }

        // 2) Crear factura con el usuario
        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaDao.save(factura);

        // 3) Registrar ventas (sin controlar existencias, ya no existe en Producto)
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        if (listaItems != null) {
            double total = 0.0;
            for (Item i : listaItems) {
                // Aun podemos obtener referencia al producto (por si necesitas validar algo)
                var producto = productoDao.getReferenceById(i.getProductoID());

                Venta venta = new Venta(
                        factura.getIdFactura(),
                        i.getProductoID(),
                        i.getPrecio(),     // Double en Producto
                        i.getCantidad()
                );
                ventaDao.save(venta);

                total += (i.getPrecio() == null ? 0.0 : i.getPrecio()) * i.getCantidad();
            }

            // 4) Actualizar total de la factura
            factura.setTotal(total);
            facturaDao.save(factura);

            // 5) Limpiar carrito
            listaItems.clear();
            session.setAttribute("listaItems", listaItems);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public double getTotal() {
        List<Item> listaItems = (List<Item>) session.getAttribute("listaItems");
        double total = 0.0;
        if (listaItems != null) {
            for (Item i : listaItems) {
                total += (i.getPrecio() == null ? 0.0 : i.getPrecio()) * i.getCantidad();
            }
        }
        return total;
    }
}
