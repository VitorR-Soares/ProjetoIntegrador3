/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.controller;

import com.senac.controlp3dPI.controller.dto.VendaDTO;
import com.senac.controlp3dPI.entities.Venda;
import com.senac.controlp3dPI.service.VendaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class VendaController {
    
    @Autowired
    VendaService service;
   
    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas(){
        
        List<Venda> vendas = service.listarVendas();
        
        return new ResponseEntity<>(vendas, HttpStatus.OK);
        
    }
    
    @GetMapping("/data/{data}")
    public ResponseEntity<List<Venda>> pesquisaVendaPorData(@PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        
        List<Venda> vendasPorData = service.pesquisaPorData(data);
        
        return new ResponseEntity<>(vendasPorData, HttpStatus.OK);        
        
    }
    
    @PostMapping
    public ResponseEntity<?> cadastrarVenda(@RequestBody VendaDTO dto) {
        
        Venda venda = service.cadastrarVenda(dto);
        
        return new ResponseEntity<>(venda, HttpStatus.OK);
    }
    
    
}
