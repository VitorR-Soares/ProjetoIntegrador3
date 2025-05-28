/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.controller;

import com.senac.controlp3dPI.entities.Produto;
import com.senac.controlp3dPI.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    ProdutoService service;
    
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        
        List<Produto> produtos = service.listarProdutos();
        
        return new ResponseEntity<>(produtos, HttpStatus.OK);
        
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<Produto> inserirProduto(@RequestBody Produto produto, 
            @PathVariable("id") int idCategoria){
        
        Produto produtoCadastrado = service.salvarProduto(produto, idCategoria);
        
        return new ResponseEntity<>(produtoCadastrado, HttpStatus.OK);
        
    }
    
    @GetMapping("/pesquisaNome/{nome}")
    public ResponseEntity<List<Produto>> pesquisaPorNome(@PathVariable("nome") String nome){
        
        List<Produto> produtosEncontrados = service.pesquisaPorNome(nome);
        
        return new ResponseEntity<>(produtosEncontrados, HttpStatus.OK);
        
    }
    @GetMapping("/pesquisaCategoria/{idCategoria}")
    public ResponseEntity<List<Produto>> pesquisaPorCategoria(@PathVariable("idCategoria") int idCategoria){
        
        List<Produto> produtosEncontrados = service.pesquisaPorCategoria(idCategoria);
        
        return new ResponseEntity<>(produtosEncontrados, HttpStatus.OK);
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable("id") int id){
        
        service.deletarProduto(id);
        
        return new ResponseEntity<>(HttpStatus.OK);       
        
    }
    @PutMapping
    public ResponseEntity<Produto> atualizaProduto(@RequestBody Produto produto){
        
        Produto produtoSalvo = service.atualizarProduto(produto);        
        
        return new ResponseEntity<>(produtoSalvo, HttpStatus.OK);
    }
    @PutMapping("/{id}/{qtd}")
    public ResponseEntity<Produto> atualizaQtdProduto(@PathVariable("id") int id,
            @PathVariable("qtd") int qtd){
        
        Produto produtoSalvo = service.atualizarQtdEstoque(id, qtd);
        
        return new ResponseEntity<>(produtoSalvo, HttpStatus.OK);
    }
    
    
}
