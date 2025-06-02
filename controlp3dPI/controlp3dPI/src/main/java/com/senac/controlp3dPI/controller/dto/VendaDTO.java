/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.senac.controlp3dPI.controller.dto;

import java.time.LocalDate;
import java.util.List;


public record VendaDTO(double valorTotal,
    double valorFinal,
    LocalDate data,
    Integer idCliente,
    Integer idUsuario,
    Integer idPagamento,
    List<ProdutoVendaDTO> produtos) {

}
