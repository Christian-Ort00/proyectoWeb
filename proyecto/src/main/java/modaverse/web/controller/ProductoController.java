package modaverse.web.controller;

import lombok.extern.slf4j.Slf4j;
import modaverse.web.domain.Producto;
import modaverse.web.service.ProductoService;
import modaverse.web.service.CategoriaService;
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

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/hombre")
    public String redirigirAHombre() {
        return "redirect:/producto/listado";
    }

    @GetMapping("/listado")
    public String mostrarProductos(Model model) {
        var productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        return "producto/listado";
    }

    @GetMapping("/gestionar")
    public String gestionarProductos(Model model) {
        var productos = productoService.getProductos();
        var categorias = categoriaService.getCategorias(); // nuevo
        model.addAttribute("productos", productos);
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categorias); // nuevo
        return "producto/gestionar";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.save(producto);
        return "redirect:/producto/gestionar";
    }

    @GetMapping("/eliminar/{productoID}")
    public String eliminarProducto(@PathVariable("productoID") Long productoID) {
        productoService.delete(productoID);
        return "redirect:/producto/gestionar";
    }

    @GetMapping("/modificar/{productoID}")
    public String modificarProducto(@PathVariable("productoID") Long productoID, Model model) {
        var producto = productoService.getProducto(productoID);
        var categorias = categoriaService.getCategorias(); // nuevo
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias); // nuevo
        return "producto/gestionar";
    }
}
