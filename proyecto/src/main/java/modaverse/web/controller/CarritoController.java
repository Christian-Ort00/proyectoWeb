package modaverse.web.controller;

import lombok.RequiredArgsConstructor;
import modaverse.web.domain.Producto;
import modaverse.web.domain.Factura;
import modaverse.web.domain.Venta;
import modaverse.web.service.CarritoService;
import modaverse.web.service.ProductoService;
import modaverse.web.dao.FacturaDao;
import modaverse.web.dao.VentaDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;
    private final ProductoService productoService;
    private final FacturaDao facturaDao;
    private final VentaDao ventaDao;

    @GetMapping("/ver")
    public String verCarrito(Model model) {
        model.addAttribute("items", carritoService.getItems());
        model.addAttribute("total", carritoService.getTotal());
        return "carrito/ver";
    }

    @GetMapping("/agregar/{id}")
    public String agregar(@PathVariable("id") Long id) {
        Producto producto = productoService.getProducto(id);
        if (producto != null) {
            carritoService.addProducto(producto);
        }
        return "redirect:/carrito/ver";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        carritoService.removeProducto(id);
        return "redirect:/carrito/ver";
    }

    @GetMapping("/vaciar")
    public String vaciar() {
        carritoService.clear();
        return "redirect:/carrito/ver";
    }

    @GetMapping("/finalizar")
    public String finalizarCompra() {
        if (carritoService.getItems().isEmpty()) {
            return "redirect:/carrito/ver"; // no permite finalizar vacío
        }

        // Crear factura
        Factura factura = new Factura();
        factura.setIdUsuario(1L); // Por ahora fijo en el usuario "Juan" (UsuarioID=1 en tu BD)
        factura.setFecha(java.time.LocalDateTime.now());
        factura.setTotal(carritoService.getTotal());
        factura.setEstado(1);

        factura = facturaDao.save(factura);

        // Crear ventas por cada producto
        for (var item : carritoService.getItems()) {
            Venta venta = new Venta();
            venta.setFactura(factura);
            venta.setProducto(item.getProducto());
            venta.setPrecio(item.getProducto().getPrecio());
            venta.setCantidad(item.getCantidad());
            ventaDao.save(venta);
        }

        carritoService.clear(); // vaciar carrito
        return "redirect:/carrito/confirmacion";
    }

    @GetMapping("/confirmacion")
    public String confirmacion() {
        return "carrito/confirmacion";
    }
}

