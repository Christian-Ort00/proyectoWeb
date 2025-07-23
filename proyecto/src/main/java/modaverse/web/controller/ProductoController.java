package modaverse.web.controller;

import lombok.extern.slf4j.Slf4j;
import modaverse.web.domain.Producto;
import modaverse.web.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Redirección desde /producto/hombre
    @GetMapping("/hombre")
    public String redirigirAHombre() {
        return "redirect:/producto/listado";
    }

    // Vista para clientes (productos en tarjetas)
    @GetMapping("/listado")
    public String mostrarProductos(Model model) {
        var productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        return "producto/listado";
      
    }

    // Vista para el panel del administrador
    @GetMapping("/gestionar")
    public String gestionarProductos(Model model) {
        var productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("producto", new Producto()); // necesario para el formulario modal
        return "producto/gestionar";
    }

    // Guardar producto (desde modal)
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.save(producto);
        return "redirect:/producto/gestionar";
    }

    // Eliminar producto
    @GetMapping("/eliminar/{productoID}")
    public String eliminarProducto(@PathVariable("productoID") Long productoID) {
        productoService.delete(productoID);
        return "redirect:/producto/gestionar";
    }

    // Modificar producto
    @GetMapping("/modificar/{productoID}")
    public String modificarProducto(@PathVariable("productoID") Long productoID, Model model) {
        var producto = productoService.getProducto(productoID);
        model.addAttribute("producto", producto);
        return "producto/gestionar";
    }
}
