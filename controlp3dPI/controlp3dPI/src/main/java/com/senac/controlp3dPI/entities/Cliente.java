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
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_cliente")
    private Integer id;
    @Column(name= "s_nome_cliente")
    private String nome_cliente;
    @Column(name= "s_telefone")
    private String telefone;
    @Column(name= "s_cpf")
    private String cpf;
    @Column(name= "s_genero")
    private String genero;
    @Column(name= "int_idade")
    private int idade;

    public Cliente() {
    }

    public Cliente(Integer id, String nome_cliente, String telefone, String cpf, String genero, int idade) {
        this.id = id;
        this.nome_cliente = nome_cliente;
        this.telefone = telefone;
        this.cpf = cpf;
        this.genero = genero;
        this.idade = idade;
    }

    public Cliente(String nome_cliente, String telefone, String cpf, String genero, int idade) {
        this.nome_cliente = nome_cliente;
        this.telefone = telefone;
        this.cpf = cpf;
        this.genero = genero;
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    
    
}
