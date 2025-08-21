/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modaverse.web.service;

import modaverse.web.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    
    // Se obtiene un listado de usuarios en un List
    List<Usuario> getUsuarios();
    
    // Se obtiene un Usuario, a partir del id de un usuario (id seteado en la entidad)
    Usuario getUsuario(Usuario usuario);
    
    // Búsquedas basadas en username (mapeado a la columna 'Correo')
    Usuario getUsuarioPorUsername(String username);

    // Búsqueda por username y password (password mapeado a 'Contrasena')
    Usuario getUsuarioPorUsernameYPassword(String username, String password);
    
    // Búsqueda por username o nombre
    Usuario getUsuarioPorUsernameONombre(String username, String nombre);
    
    // Validación de existencia por username o nombre
    boolean existeUsuarioPorUsernameONombre(String username, String nombre);
    
    // Inserta o actualiza un usuario
    void save(Usuario usuario, boolean crearRolUser);
    
    // Elimina el usuario indicado
    void delete(Usuario usuario);
}
