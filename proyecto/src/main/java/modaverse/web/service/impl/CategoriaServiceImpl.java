/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modaverse.web.service.impl;

import java.util.List;
import modaverse.web.domain.Categoria;
import modaverse.web.dao.CategoriaDao;
import modaverse.web.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoriaServiceImpl implements CategoriaService{
    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias() {
        System.out.println("Categorías obtenidas ( en service implement ):");
    
        
        return categoriaDao.findAll();
    }
    
    
    
  

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Long categoriaID) {
        categoriaDao.deleteById(categoriaID);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Long categoriaID) {
        return categoriaDao.findById(categoriaID).orElse(null);
    }
}
