package br.com.backend.apibackendmercado.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Produto {
    
    private UUID id;
    private String nome;
    private Long quantidade;
    private Double valor;
    private String observacao;
}
