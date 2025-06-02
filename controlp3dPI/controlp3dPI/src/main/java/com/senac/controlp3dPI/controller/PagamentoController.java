/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.controller;

import com.senac.controlp3dPI.entities.Pagamento;
import com.senac.controlp3dPI.service.PagamentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    
    @Autowired
    PagamentoService service;
   
    @GetMapping
    public ResponseEntity<List<Pagamento>> listarPagamentos(){
        
        List<Pagamento> pagamentos = service.listarPagamentos();
        
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
        
    }
    
}
