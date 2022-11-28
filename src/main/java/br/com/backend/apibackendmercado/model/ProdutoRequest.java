package br.com.backend.apibackendmercado.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoRequest {
    private String nome;
    private Long quantidade;
    private Double valor;
    private String observacao;
}
