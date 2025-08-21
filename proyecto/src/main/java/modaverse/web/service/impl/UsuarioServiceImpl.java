/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modaverse.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import modaverse.web.dao.UsuarioDao;
import modaverse.web.domain.Usuario;
import modaverse.web.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        // La entidad de Modaverse expone id como 'idUsuario'
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameONombre(String username, String nombre) {
        return usuarioDao.findByUsernameOrNombre(username, nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameONombre(String username, String nombre) {
        return usuarioDao.existsByUsernameOrNombre(username, nombre);
    }

    @Override
    @Transactional
    public void save(Usuario usuario, boolean crearRolUser) {
        // Guardar/actualizar (sin manejo de roles, porque tu BD guarda 'rol' en la misma tabla)
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}
