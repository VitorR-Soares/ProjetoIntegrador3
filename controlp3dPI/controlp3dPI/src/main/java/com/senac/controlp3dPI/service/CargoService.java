/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.service;

import com.senac.controlp3dPI.entities.Cargo;
import com.senac.controlp3dPI.repository.CargoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    
    @Autowired
    CargoRepository repository;
    
    @Transactional
    public List<Cargo> listarCargos(){
        
        List<Cargo> cargos = repository.findAll();
        
        return cargos;
        
    }
    
}
