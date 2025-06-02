/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_venda")
    private Integer id;
    @Column(name= "dbl_valor_total")
    private double valor_total;
    @Column(name= "dbl_valor_final")
    private double valor_final;
    @Column(name= "date_data")
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="id_pagamento")
    private Pagamento pagamento;
    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProdutoVenda> produtoVendas;

    public Venda() {
    }

    public Venda(Integer id, double valor_total, double valor_final, LocalDate data, Cliente cliente, Usuario usuario, Pagamento pagamento, List<ProdutoVenda> produtoVendas) {
        this.id = id;
        this.valor_total = valor_total;
        this.valor_final = valor_final;
        this.data = data;
        this.cliente = cliente;
        this.usuario = usuario;
        this.pagamento = pagamento;
        this.produtoVendas = produtoVendas;
    }

    public Venda(double valor_total, double valor_final, LocalDate data, Cliente cliente, Usuario usuario, Pagamento pagamento, List<ProdutoVenda> produtoVendas) {
        this.valor_total = valor_total;
        this.valor_final = valor_final;
        this.data = data;
        this.cliente = cliente;
        this.usuario = usuario;
        this.pagamento = pagamento;
        this.produtoVendas = produtoVendas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public double getValor_final() {
        return valor_final;
    }

    public void setValor_final(double valor_final) {
        this.valor_final = valor_final;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public List<ProdutoVenda> getProdutoVendas() {
        return produtoVendas;
    }

    public void setProdutoVendas(List<ProdutoVenda> produtoVendas) {
        this.produtoVendas = produtoVendas;
    }
    
    
    
}
