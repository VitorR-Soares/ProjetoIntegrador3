/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.controller;

import com.senac.controlp3dPI.entities.Cliente;
import com.senac.controlp3dPI.service.ClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService service;
   
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        
        List<Cliente> clientes = service.listarClientes();
        
        return new ResponseEntity<>(clientes, HttpStatus.OK);
        
    }
    
    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
        
        Cliente clienteSalvo = service.cadastrarCliente(cliente);
        
        return new ResponseEntity<>(clienteSalvo, HttpStatus.OK);
        
    }
    
    
    
    
}
