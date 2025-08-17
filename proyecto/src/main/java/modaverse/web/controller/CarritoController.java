/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modaverse.web.controller;

import modaverse.web.domain.Item;
import modaverse.web.domain.Producto;
import modaverse.web.service.ItemService;
import modaverse.web.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductoService productoService;

    // Para ver el carrito
    @GetMapping("/carrito/listado")
    public String inicio(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
        double carritoTotalVenta = 0.0;
        for (Item i : items) {
            Double precio = i.getPrecio() == null ? 0.0 : i.getPrecio();
            carritoTotalVenta += (i.getCantidad() * precio);
        }
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return "/carrito/listado";
    }

    @GetMapping("/carrito/agregar/{productoID}")
    public ModelAndView agregarItem(Model model, @PathVariable Long productoID) {
        // Buscar si ya existe en el carrito
        Item probe = new Item();
        probe.setProductoID(productoID);
        Item item2 = itemService.get(probe);

        // Si no existe, crear Item desde el Producto
        if (item2 == null) {
            Producto producto = productoService.getProducto(productoID);
            item2 = new Item(producto);
        }

        itemService.save(item2);

        var lista = itemService.gets();
        int totalCarritos = 0;
        double carritoTotalVenta = 0.0;
        for (Item i : lista) {
            totalCarritos += i.getCantidad();
            Double precio = i.getPrecio() == null ? 0.0 : i.getPrecio();
            carritoTotalVenta += (i.getCantidad() * precio);
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

    // Para modificar un producto del carrito
    @GetMapping("/carrito/modificar/{productoID}")
    public String modificarItem(@PathVariable Long productoID, Model model) {
        Item probe = new Item();
        probe.setProductoID(productoID);
        Item item = itemService.get(probe);
        model.addAttribute("item", item);
        return "/carrito/modifica";
    }

    // Para eliminar un elemento del carrito
    @GetMapping("/carrito/eliminar/{productoID}")
    public String eliminarItem(@PathVariable Long productoID) {
        Item probe = new Item();
        probe.setProductoID(productoID);
        itemService.delete(probe);
        return "redirect:/carrito/listado";
    }

    @PostMapping("/carrito/guardar")
    public String guardarItem(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";
    }

    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/";
    }
}
