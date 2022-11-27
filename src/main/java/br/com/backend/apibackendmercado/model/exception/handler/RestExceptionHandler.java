package br.com.backend.apibackendmercado.model.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.backend.apibackendmercado.model.exception.ErrorMessage;
import br.com.backend.apibackendmercado.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    //Sempre que existir um ResourceNotFoundExeception ele chama o metodo abaixo
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFound(ResourceNotFoundException exception){
        
        ErrorMessage errorMessage = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
