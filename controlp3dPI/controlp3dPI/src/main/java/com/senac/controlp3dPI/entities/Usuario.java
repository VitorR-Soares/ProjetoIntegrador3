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
@Table(name="tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_usuario")
    private Integer id;
    @Column(name= "s_nome_usuario")
    private String nomeUsuario;
    @Column(name= "s_senha")
    private String senha;
    @ManyToOne
    @JoinColumn(name="id_cargo")
    private Cargo cargo;

    public Usuario() {
    }

    public Usuario(int id, String nome_usuario, String senha, Cargo cargo) {
        this.id = id;
        this.nomeUsuario = nome_usuario;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Usuario(String nome_usuario, String senha, Cargo cargo) {
        this.nomeUsuario = nome_usuario;
        this.senha = senha;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_usuario() {
        return nomeUsuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nomeUsuario = nome_usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    
    
    
}
