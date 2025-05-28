/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.controller;

import com.senac.controlp3dPI.entities.Categoria;
import com.senac.controlp3dPI.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    CategoriaService service;
    
    @GetMapping
    public ResponseEntity<List<Categoria>> listarProdutos(){
        
        List<Categoria> categorias = service.listarCategorias();
        
        return new ResponseEntity<>(categorias, HttpStatus.OK);
        
    }
    
}
