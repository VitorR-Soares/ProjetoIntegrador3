/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto_venda")
public class ProdutoVenda {
    
    @EmbeddedId
    private ProdutoVendaId produtoVendaId;    
    
    @ManyToOne
    @MapsId("idVenda")
    @JoinColumn(name = "id_venda")
    private Venda venda;
    
    @ManyToOne
    @MapsId("idProduto")
    @JoinColumn(name = "id_produto")
    private Produto produto;
    
    @Column(name= "quantidade")
    private Integer quantidade;

    public ProdutoVenda() {
    }
    public ProdutoVenda(ProdutoVendaId produtoVendaId, Venda venda, Produto produto, Integer quantidade) {
        this.produtoVendaId = produtoVendaId;
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ProdutoVendaId getProdutoVendaId() {
        return produtoVendaId;
    }

    public void setProdutoVendaId(ProdutoVendaId produtoVendaId) {
        this.produtoVendaId = produtoVendaId;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    
    
    
}
