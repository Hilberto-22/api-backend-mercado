package br.com.backend.apibackendmercado.model.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    
    private String titleError;
    private Integer status;
    private String message;
}
