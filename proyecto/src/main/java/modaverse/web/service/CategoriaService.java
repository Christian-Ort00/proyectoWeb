/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modaverse.web.service;

/**
 *
 * @author Usuario
 */


import java.util.List;
import modaverse.web.domain.Categoria;

public interface CategoriaService{
  public List<Categoria> getCategorias();

    public void save(Categoria categoria);

    public void delete(Long categoriaID);

    public Categoria getCategoria(Long categoriaID);
}
