package modaverse.web.controller;

import lombok.extern.slf4j.Slf4j;
import modaverse.web.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public String mostrarProductos(Model model) {
        var productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        return "producto/listado";
    }
}
