/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.controller;

import com.senac.controlp3dPI.controller.dto.EncomendaDTO;
import com.senac.controlp3dPI.entities.Encomenda;
import com.senac.controlp3dPI.service.EncomendaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encomenda")
public class EncomendaController {
    
    @Autowired
    EncomendaService service;
   
    @GetMapping
    public ResponseEntity<List<Encomenda>> listarEncomendas(){
        
        List<Encomenda> encomendas = service.listarEncomendas();
        
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
        
    }
    
    @GetMapping("/data/{data}")
    public ResponseEntity<List<Encomenda>> pesquisaEncomendaPorData(@PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        
        List<Encomenda> encomendasPorData = service.pesquisaPorDataDeEntrega(data);
        
        return new ResponseEntity<>(encomendasPorData, HttpStatus.OK);        
        
    }
    
    @PostMapping
    public ResponseEntity<?> cadastrarEncomenda(@RequestBody EncomendaDTO dto) {
        
        Encomenda encomenda = service.cadastrarEncomenda(dto);
        
        return new ResponseEntity<>(encomenda, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/entregar")
    public ResponseEntity<?> entregarEncomenda(@PathVariable Integer id) {
        try {
            Encomenda atualizada = service.entregarEncomenda(id);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
