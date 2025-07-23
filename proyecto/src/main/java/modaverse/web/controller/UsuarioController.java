package modaverse.web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import modaverse.web.dao.UsuarioDao;
import modaverse.web.service.UsuarioService;
import modaverse.web.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;
    
     @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/inicioSesion")
    public String mostrarInicioSesion() {
        return "usuario/inicioSesion";
    }

    @PostMapping("/iniciarSesion")
    public String iniciarSesion(
            @RequestParam String correo,
            @RequestParam String contrasena,
            HttpSession session,
            Model model
    ) {
        Usuario usuario = usuarioDao.findByCorreoAndContrasena(correo, contrasena);

        if (usuario != null) {
            session.setAttribute("usuarioActual", usuario);
            if ("admin".equalsIgnoreCase(usuario.getRol())) {
                return "redirect:/admin/menu";
            } else {
                return "redirect:/producto/listado";
            }
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "usuario/inicioSesion";
        }
    }

    @GetMapping("/registroUsuario")
    public String mostrarRegistroUsuario() {
        return "usuario/registroUsuario";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(
            @RequestParam String nombre,
            @RequestParam String apellidos,
            @RequestParam String correo,
            @RequestParam String contrasena,
            @RequestParam String rol,
            HttpSession session
    ) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellidos(apellidos);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContrasena(contrasena);
        nuevoUsuario.setRol(rol);

        usuarioDao.save(nuevoUsuario);
        session.setAttribute("usuarioActual", nuevoUsuario);

        if ("admin".equalsIgnoreCase(rol)) {
            return "redirect:/admin/menu";
        } else {
            return "redirect:/producto/listado";
        }
    }
    @GetMapping("/gestion")
    public String mostrarGestionUsuario(Model model) {
    // Add the list of users for the table
    model.addAttribute("usuarios", usuarioService.getUsuarios());
    // Add an empty user object for the form
    model.addAttribute("usuario", new Usuario());
    return "usuario/gestion";
}
}
   

