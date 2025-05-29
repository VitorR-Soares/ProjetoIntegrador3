/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.service;

import com.senac.controlp3dPI.entities.Cliente;
import com.senac.controlp3dPI.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository repository;
    
    @Transactional
    public List<Cliente> listarClientes(){
        
        List<Cliente> clientes = repository.findAll();
        
        return clientes;
        
    }
    
    @Transactional
    public Cliente cadastrarCliente(Cliente cliente){
        
        Cliente clienteSalvo = repository.save(cliente);
        
        return clienteSalvo;
        
    }
    
}
