/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.service;

import com.senac.controlp3dPI.entities.Categoria;
import com.senac.controlp3dPI.entities.Produto;
import com.senac.controlp3dPI.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    ProdutoRepository repository;
    
    @Autowired
    CategoriaService categoriaService;
    
    @Transactional
    public Produto pesquisaProdutoPorId(Integer id){
        
        Produto produto = repository.findById(id).get();
        
        return produto;
        
    }
    
    @Transactional
    public List<Produto> listarProdutos(){
        
        List<Produto> produtos = repository.findAll();
        
        return produtos;
        
    }
    
    @Transactional 
    public Produto pesquisaPorId(int id){
        
        Produto produtoEncontrado = repository.findById(id).get();
        
        return produtoEncontrado;
        
    }
    
    @Transactional
    public Produto salvarProduto(Produto produto){
        
        Produto produtoSalvo = repository.save(produto);
        
        return produtoSalvo;
        
    }
    
    @Transactional
    public void deletarProduto(Integer id){
        
        repository.deleteById(id);
        
    }
    
    @Transactional
    public Produto atualizarQtdEstoque(Integer id, int qtd){        
        if(repository.existsById(id)){
            Produto produtoEncontrado = repository.findById(id).get();
            int qtdAtual = produtoEncontrado.getQtd();
            produtoEncontrado.setQtd(qtdAtual + qtd);
            repository.save(produtoEncontrado);
            return produtoEncontrado;
        }    
        return null;
        
    }
    
    @Transactional
    public Produto atualizarProduto(Produto produto){  
        System.out.println(produto.getNome_produto());
        System.out.println(produto.getId());
        if(repository.existsById(produto.getId())){
            Produto produtoAntigo = repository.findById(produto.getId()).get();
            System.out.println(produtoAntigo.getCategoria().getNome_categoria());
            Produto produtoAtualizado = new Produto(produto.getId(), produto.getNome_produto(), produto.getValor_unit(), produto.getQtd(), produto.getHoras_impressao());
            produtoAtualizado.setCategoria(produtoAntigo.getCategoria());
            repository.save(produtoAtualizado);
            return produtoAtualizado;
        } else {
            return null;
        }        
    }
    
    @Transactional
    public List<Produto> pesquisaPorNome(String nome){
        
        List<Produto> produtosEncontrados = repository.findByNomeContainingIgnoreCase(nome);
        
        return produtosEncontrados;
        
    }
    
    @Transactional
    public List<Produto> pesquisaPorCategoria(Integer idCategoria){
        
        List<Produto> produtosEncontrados = repository.findByCategoriaId(idCategoria);
        
        return produtosEncontrados;
        
    }
    
    
}
