package br.com.backend.apibackendmercado.dto;

import lombok.Data;

@Data
public class ProdutoDto {
    private String nome;
    private Long quantidade;
    private Double valor;
    private String observacao;
}
