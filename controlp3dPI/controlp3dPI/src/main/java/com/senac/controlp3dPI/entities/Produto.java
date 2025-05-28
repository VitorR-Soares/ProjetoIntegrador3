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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_produto")
    private Integer id;
    @Column(name= "s_nome_produto")
    private String nome;
    @Column(name= "dbl_valor_unit")
    private double valor_unit;
    @Column(name= "int_qtd")
    private int qtd;    
    @Column(name= "int_horas")
    private int horas_impressao;
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;

    public Produto() {
    }

    public Produto(Integer id, String nome_produto, double valor_unit, int qtd, int horas_impressao, Categoria categoria) {
        this.id = id;
        this.nome = nome_produto;
        this.valor_unit = valor_unit;
        this.qtd = qtd;
        this.horas_impressao = horas_impressao;
        this.categoria = categoria;
    }

    public Produto(Integer id, String nome, double valor_unit, int qtd, int horas_impressao) {
        this.id = id;
        this.nome = nome;
        this.valor_unit = valor_unit;
        this.qtd = qtd;
        this.horas_impressao = horas_impressao;
    }

    public Produto(String nome_produto, double valor_unit, int qtd, int horas_impressao, Categoria categoria) {
        this.nome = nome_produto;
        this.valor_unit = valor_unit;
        this.qtd = qtd;
        this.horas_impressao = horas_impressao;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_produto() {
        return nome;
    }

    public void setNome_produto(String nome_produto) {
        this.nome = nome_produto;
    }

    public double getValor_unit() {
        return valor_unit;
    }

    public void setValor_unit(double valor_unit) {
        this.valor_unit = valor_unit;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getHoras_impressao() {
        return horas_impressao;
    }

    public void setHoras_impressao(int horas_impressao) {
        this.horas_impressao = horas_impressao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }    
    
}
