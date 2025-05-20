/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProdutoVendaId {
    
    @Column(name="id_venda")
    private Integer idVenda;
    @Column(name="id_produto")
    private Integer idProduto;

    public ProdutoVendaId() {
    }

    public ProdutoVendaId(Integer idVenda, Integer idProduto) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
    
    
    
}
