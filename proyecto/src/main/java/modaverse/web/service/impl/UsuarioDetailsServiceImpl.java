/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modaverse.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import modaverse.web.dao.UsuarioDao;
import modaverse.web.domain.Usuario;
import modaverse.web.service.UsuarioDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;              // Spring Security user
import org.springframework.security.core.userdetails.UserDetails;  // contrato
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Ahora username == correo (mapeado a campo 'username' en la entidad)
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())); // ADMIN -> ROLE_ADMIN

        return new User(
                usuario.getUsername(),   // <-- usa el getter de la entidad
                usuario.getPassword(),   // <-- idem
                roles
        );
    }
}
