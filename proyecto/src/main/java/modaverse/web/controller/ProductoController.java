package modaverse.web.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import modaverse.web.domain.Producto;
import modaverse.web.service.CategoriaService;
import modaverse.web.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    // ====== Navegación por género ======
    @GetMapping("/hombre")
    public String verHombre(Model model) {
        var productos = productoService.getProductosPorCategoriaNombre("Hombre");
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", "Hombre");
        return "producto/listado";
    }

    @GetMapping("/mujer")
    public String verMujer(Model model) {
        var productos = productoService.getProductosPorCategoriaNombre("Mujer");
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", "Mujer");
        return "producto/listado";
    }

    // ====== Listado general (opcionalmente con ?categoria=Hombre/Mujer) ======
    @GetMapping("/listado")
    public String mostrarProductos(
            @RequestParam(value = "categoria", required = false) String categoria,
            Model model) {
        var productos = (categoria == null || categoria.isBlank())
                ? productoService.getProductos()
                : productoService.getProductosPorCategoriaNombre(categoria);
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", (categoria == null || categoria.isBlank()) ? "Todos" : categoria);
        return "producto/listado";
    }

    // ====== Gestión (admin) ======
    @GetMapping("/gestionar")
    public String gestionarProductos(Model model) {
        var productos = productoService.getProductos();
        var categorias = categoriaService.getCategorias();
        model.addAttribute("productos", productos);
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categorias);
        return "producto/gestionar";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.save(producto);
        // Redirige a la sección adecuada según la categoría elegida
        var cat = (producto.getCategoria() != null && producto.getCategoria().getNombre() != null)
                ? producto.getCategoria().getNombre().trim()
                : "";
        if ("Hombre".equalsIgnoreCase(cat)) {
            return "redirect:/producto/hombre";
        } else if ("Mujer".equalsIgnoreCase(cat)) {
            return "redirect:/producto/mujer";
        }
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{productoID}")
    public String eliminarProducto(@PathVariable("productoID") Long productoID) {
        productoService.delete(productoID);
        return "redirect:/producto/gestionar";
    }

    @GetMapping("/modificar/{productoID}")
    public String modificarProducto(@PathVariable("productoID") Long productoID, Model model) {
        var producto = productoService.getProducto(productoID);
        var categorias = categoriaService.getCategorias();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);
        return "producto/gestionar";
    }
}
