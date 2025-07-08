/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modaverse.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    
    @GetMapping("/listado")
    public String mostrarInicioSesion() {
        return "producto/listado";
    }
    
     @GetMapping("/gestionar")
    public String mostrarGestionProductos() {
        return "producto/gestionar";
    }

}