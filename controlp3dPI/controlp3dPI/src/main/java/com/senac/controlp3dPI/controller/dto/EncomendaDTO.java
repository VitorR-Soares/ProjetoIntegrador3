/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.senac.controlp3dPI.controller.dto;

import java.time.LocalDate;

public record EncomendaDTO(String descricao,
    double valorTotal,
    double valorFinal,
    LocalDate data,
    LocalDate dataEntrega,
    Integer idCliente,
    Integer idUsuario,
    Integer idPagamento) {

}
