/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modaverse.web.service.impl;

import modaverse.web.dao.ProductoDao;
import modaverse.web.domain.Producto;
import modaverse.web.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos() {
        return productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Long productoID) {
        return productoDao.findById(productoID).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Long productoID) {
        productoDao.deleteById(productoID);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup) {
//        return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Producto> metodoJPQL(double precioInf, double precioSup) {
//        return productoDao.metodoJPQL(precioInf, precioSup);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Producto> metodoNativo(double precioInf, double precioSup) {
//        return productoDao.metodoNativo(precioInf, precioSup);
//    }
}
