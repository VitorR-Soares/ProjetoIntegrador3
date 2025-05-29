/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.controller;

import com.senac.controlp3dPI.entities.Usuario;
import com.senac.controlp3dPI.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioService service;
   
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        
        List<Usuario> usuarios = service.listarUsuarios();
        
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
        
    }
    
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        
        Usuario usuarioSalvo = service.cadastrarUsuario(usuario);
        
        return new ResponseEntity<>(usuarioSalvo, HttpStatus.OK);
        
    }
    
}
