/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.controller;

import com.senac.controlp3dPI.entities.Cargo;
import com.senac.controlp3dPI.service.CargoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cargo")
public class CargoController {
    
    @Autowired
    CargoService service;
   
    @GetMapping
    public ResponseEntity<List<Cargo>> listarCargos(){
        
        List<Cargo> cargos = service.listarCargos();
        
        return new ResponseEntity<>(cargos, HttpStatus.OK);
        
    }
    
}
