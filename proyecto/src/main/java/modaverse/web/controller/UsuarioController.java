package modaverse.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping("/inicioSesion")
    public String mostrarInicioSesion() {
        return "usuario/inicioSesion";
    }

    @GetMapping("/registroUsuario")
    public String mostrarRegistroUsuario() {
        return "usuario/registroUsuario";
    }
}
