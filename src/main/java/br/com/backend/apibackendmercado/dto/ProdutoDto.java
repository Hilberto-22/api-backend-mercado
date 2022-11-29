package br.com.backend.apibackendmercado.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ProdutoDto {
    private UUID id;
    private String nome;
    private Long quantidade;
    private Double valor;
    private String observacao;
}
