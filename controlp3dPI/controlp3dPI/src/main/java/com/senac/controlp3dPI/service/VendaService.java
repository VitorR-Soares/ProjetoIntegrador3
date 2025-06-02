/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.service;

import com.senac.controlp3dPI.controller.dto.ProdutoVendaDTO;
import com.senac.controlp3dPI.controller.dto.VendaDTO;
import com.senac.controlp3dPI.entities.Cliente;
import com.senac.controlp3dPI.entities.Pagamento;
import com.senac.controlp3dPI.entities.Produto;
import com.senac.controlp3dPI.entities.ProdutoVenda;
import com.senac.controlp3dPI.entities.ProdutoVendaId;
import com.senac.controlp3dPI.entities.Usuario;
import com.senac.controlp3dPI.entities.Venda;
import com.senac.controlp3dPI.repository.ClienteRepository;
import com.senac.controlp3dPI.repository.PagamentoRepository;
import com.senac.controlp3dPI.repository.ProdutoRepository;
import com.senac.controlp3dPI.repository.UsuarioRepository;
import com.senac.controlp3dPI.repository.VendaRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {
    
    @Autowired
    VendaRepository vendaRepository;
    
    @Autowired
    ProdutoRepository produtoRepository;
    
    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    PagamentoRepository pagamentoRepository;
    
    
    
    @Transactional
    public List<Venda> listarVendas(){
        
        List<Venda> vendas = vendaRepository.findAll();
        
        return vendas;
        
    }
    
    @Transactional
    public List<Venda> pesquisaPorData(LocalDate data){
        
        List<Venda> vendasPorData = vendaRepository.findByData(data);
        
        return vendasPorData;
        
    }
    
    @Transactional
    public Venda cadastrarVenda(VendaDTO dto) {
        Venda venda = new Venda();
        venda.setData(dto.data());
        venda.setValor_total(dto.valorTotal());
        venda.setValor_final(dto.valorFinal());

        // Setando as associações
        Cliente cliente = clienteRepository.findById(dto.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Pagamento pagamento = pagamentoRepository.findById(dto.idPagamento())
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        venda.setCliente(cliente);
        venda.setUsuario(usuario);
        venda.setPagamento(pagamento);

        // Persiste venda primeiro (gerar ID)
        venda = vendaRepository.save(venda);

        List<ProdutoVenda> produtoVendas = new ArrayList<>();

        for (ProdutoVendaDTO prodDTO : dto.produtos()) {
            
            Produto produto = produtoRepository.findById(prodDTO.idProduto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ProdutoVendaId id = new ProdutoVendaId();
            id.setIdProduto(produto.getId());
            id.setIdVenda(venda.getId());
            
            ProdutoVenda produtoVenda = new ProdutoVenda();

            produtoVenda.setProdutoVendaId(id);
            produtoVenda.setVenda(venda);
            produtoVenda.setProduto(produto);
            produtoVenda.setQuantidade(prodDTO.quantidade());

            produtoVendas.add(produtoVenda);
        }

        venda.setProdutoVendas(produtoVendas);

        vendaRepository.save(venda); // salva novamente com os produtos vinculados
        
        return venda;
    }
    
    
}
