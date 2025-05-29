/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.service;

import com.senac.controlp3dPI.entities.Usuario;
import com.senac.controlp3dPI.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository repository;
    
    @Transactional
    public List<Usuario> listarUsuarios(){
        
        List<Usuario> usuarios = repository.findAll();
        
        return usuarios;
        
    }
    
    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario){
        
        Usuario usuarioSalvo = repository.save(usuario);
        
        return usuarioSalvo;
        
    }
    
}
