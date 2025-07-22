package modaverse.web.service;

import java.util.List;
import modaverse.web.domain.Usuario;

public interface UsuarioService {
    List<Usuario> getUsuarios();
    void save(Usuario usuario);
    void delete(Long usuarioID);
    Usuario getUsuario(Long usuarioID);
    Usuario encontrarPorCorreoYContrasena(String correo, String contrasena);
}
