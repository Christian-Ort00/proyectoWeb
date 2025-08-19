/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modaverse.web.dao;

import modaverse.web.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    // username está mapeado a la columna Correo en la entidad
    Usuario findByUsername(String username);

    Usuario findByUsernameAndPassword(String username, String password);

    Usuario findByUsernameOrNombre(String username, String nombre);

    boolean existsByUsernameOrNombre(String username, String nombre);
}
