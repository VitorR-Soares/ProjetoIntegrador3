/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.service;

import com.senac.controlp3dPI.entities.Categoria;
import com.senac.controlp3dPI.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository repository;
    
    @Transactional
    public List<Categoria> listarCategorias(){
        
        List<Categoria> categorias = repository.findAll();
        
        return categorias;
        
    }
    
    @Transactional
    public Categoria pesquisaCategoria(Integer id){
        
        Categoria categoriaEncontrada = repository.findById(id).get();
        
        return categoriaEncontrada;
        
        
    }
}
