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
    @Transactional
    public void save(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long usuarioID) {
        usuarioDao.deleteById(usuarioID);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Long usuarioID) {
        return usuarioDao.findById(usuarioID).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario encontrarPorCorreoYContrasena(String correo, String contrasena) {
        return usuarioDao.findByCorreoAndContrasena(correo, contrasena);
    }
}
