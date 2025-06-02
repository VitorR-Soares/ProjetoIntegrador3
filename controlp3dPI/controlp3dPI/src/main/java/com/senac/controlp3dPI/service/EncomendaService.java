/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.controlp3dPI.service;

import com.senac.controlp3dPI.controller.dto.EncomendaDTO;
import com.senac.controlp3dPI.entities.Cliente;
import com.senac.controlp3dPI.entities.Encomenda;
import com.senac.controlp3dPI.entities.Pagamento;
import com.senac.controlp3dPI.entities.Usuario;
import com.senac.controlp3dPI.repository.ClienteRepository;
import com.senac.controlp3dPI.repository.EncomendaRepository;
import com.senac.controlp3dPI.repository.PagamentoRepository;
import com.senac.controlp3dPI.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncomendaService {
    
    @Autowired
    EncomendaRepository repository;
    
    @Autowired
    ClienteRepository clienteRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    PagamentoRepository pagamentoRepository;
    
    
    
    @Transactional
    public List<Encomenda> listarEncomendas(){
        
        List<Encomenda> encomendas = repository.findAll();
        
        return encomendas;
        
    }
    
    @Transactional
    public List<Encomenda> pesquisaPorDataDeEntrega(LocalDate data){
        
        List<Encomenda> encomendasPorData = repository.findByDataEntrega(data);
        
        return encomendasPorData;
        
    }
    
    @Transactional
    public Encomenda cadastrarEncomenda(EncomendaDTO dto) {
        Encomenda encomenda = new Encomenda();
        encomenda.setDescricao(dto.descricao());
        encomenda.setData(dto.data());
        encomenda.setData_entrega(dto.dataEntrega());
        encomenda.setValor_total(dto.valorTotal());
        encomenda.setValor_final(dto.valorFinal());
        encomenda.setStatus("Pendente");

        // Setando as associações
        Cliente cliente = clienteRepository.findById(dto.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Pagamento pagamento = pagamentoRepository.findById(dto.idPagamento())
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        encomenda.setCliente(cliente);
        encomenda.setUsuario(usuario);
        encomenda.setPagamento(pagamento);

        // Persiste venda primeiro (gerar ID)
        encomenda = repository.save(encomenda);
        
        return encomenda;
    }
    
    public Encomenda entregarEncomenda(Integer id) {
        Encomenda encomenda = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encomenda não encontrada com ID: " + id));

        if ("Entregue".equalsIgnoreCase(encomenda.getStatus())) {
            return null;
        }

        encomenda.setStatus("Entregue");
        return repository.save(encomenda);
    }
    
}
