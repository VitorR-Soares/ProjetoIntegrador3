/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name="tb_categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_categoria")
    private Integer id;
    @Column(name= "s_nome")
    private String nome_categoria;
    @Column(name= "int_qtd_produtos")
    private int qtd_produtos;
    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    public Categoria() {
    }
    
    public Categoria(Integer id, String nome_categoria, int qtd_produtos, List<Produto> produtos) {
        this.id = id;
        this.nome_categoria = nome_categoria;
        this.qtd_produtos = qtd_produtos;
        this.produtos = produtos;
    }

    public Categoria(String nome_categoria, int qtd_produtos, List<Produto> produtos) {
        this.nome_categoria = nome_categoria;
        this.qtd_produtos = qtd_produtos;
        this.produtos = produtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public int getQtd_produtos() {
        return qtd_produtos;
    }

    public void setQtd_produtos(int qtd_produtos) {
        this.qtd_produtos = qtd_produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
}
