package br.com.backend.apibackendmercado.model.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    
    private String titleError;
    private Integer status;
    private String message;
    
	public ErrorMessage(String titleError, Integer status, String message) {
		super();
		this.titleError = titleError;
		this.status = status;
		this.message = message;
	}
}
