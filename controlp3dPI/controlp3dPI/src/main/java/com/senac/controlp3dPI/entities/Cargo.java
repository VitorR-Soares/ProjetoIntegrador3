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
@Table(name="tb_cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_cargo")
    private Integer id;
    @Column(name= "s_nome_cargo")
    private String nome_cargo;
    @Column(name= "dbl_salario")
    private double salario;
    @Column(name= "int_nivel_acesso")
    private int nivel_acesso;
    @OneToMany(mappedBy = "cargo")
    private List<Usuario> usuarios;

    public Cargo() {
    }

    public Cargo(Integer id, String nome_cargo, double salario, int nivel_acesso, List<Usuario> usuarios) {
        this.id = id;
        this.nome_cargo = nome_cargo;
        this.salario = salario;
        this.nivel_acesso = nivel_acesso;
        this.usuarios = usuarios;
    }

    public Cargo(String nome_cargo, double salario, int nivel_acesso, List<Usuario> usuarios) {
        this.nome_cargo = nome_cargo;
        this.salario = salario;
        this.nivel_acesso = nivel_acesso;
        this.usuarios = usuarios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_cargo() {
        return nome_cargo;
    }

    public void setNome_cargo(String nome_cargo) {
        this.nome_cargo = nome_cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(int nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
