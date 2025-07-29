/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modaverse.web.controller;

import lombok.extern.slf4j.Slf4j;
import modaverse.web.domain.Categoria;
import modaverse.web.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/categoria")
public class CategoriaController {

    
    @Autowired
    private CategoriaService categoriaService;

   
    

   
    

    // Vista para el panel del administrador
    @GetMapping("/listado")
    public String gestionarCategorias(Model model) {
        var categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoria", new Categoria()); // necesario para el formulario modal
        return "categoria/listado";
    }

    // Guardar producto (desde modal)
    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
    }

    // Eliminar producto
    @GetMapping("/eliminar/{categoriaID}")
    public String eliminarProducto(@PathVariable("categoriaID") Long categoriaID) {
        categoriaService.delete(categoriaID);
        return "redirect:/categoria/listado";
    }

    // Modificar producto
    @GetMapping("/modificar/{categoriaID}")
public String modificarCategoria(@PathVariable("categoriaID") Long categoriaID, Model model) {
    var categoria = categoriaService.getCategoria(categoriaID);
    model.addAttribute("categoria", categoria);
    return "categoria/modificar"; // <- nombre del HTML
}






}
