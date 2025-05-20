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
import java.time.LocalDate;

@Entity
@Table(name = "tb_encomenda")
public class Encomenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_encomenda")
    private Integer id;
    @Column(name= "s_descricao")
    private String descricao;
    @Column(name= "dbl_valor_total")
    private double valor_total;
    @Column(name= "dbl_valor_final")
    private double valor_final;
    @Column(name= "date_data_realizacao")
    private LocalDate data;
    @Column(name= "date_data_entrega")
    private LocalDate data_entrega;
    @Column(name= "s_status")
    private String status;
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="id_pagamento")
    private Pagamento pagamento;

    public Encomenda() {
    }

    public Encomenda(Integer id, String descricao, double valor_total, double valor_final, LocalDate data, LocalDate data_entrega, String status, Cliente cliente, Usuario usuario, Pagamento pagamento) {
        this.id = id;
        this.descricao = descricao;
        this.valor_total = valor_total;
        this.valor_final = valor_final;
        this.data = data;
        this.data_entrega = data_entrega;
        this.status = status;
        this.cliente = cliente;
        this.usuario = usuario;
        this.pagamento = pagamento;
    }

    public Encomenda(String descricao, double valor_total, double valor_final, LocalDate data, LocalDate data_entrega, String status, Cliente cliente, Usuario usuario, Pagamento pagamento) {
        this.descricao = descricao;
        this.valor_total = valor_total;
        this.valor_final = valor_final;
        this.data = data;
        this.data_entrega = data_entrega;
        this.status = status;
        this.cliente = cliente;
        this.usuario = usuario;
        this.pagamento = pagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    
    
}
