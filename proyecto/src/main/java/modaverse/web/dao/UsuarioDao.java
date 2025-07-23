package modaverse.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import modaverse.web.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByCorreoAndContrasena(String correo, String contrasena);
}
