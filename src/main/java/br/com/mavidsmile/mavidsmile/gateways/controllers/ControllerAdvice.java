package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.gateways.exceptions.AmigosNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.ClienteNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.ProgressoNotFoundException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> trataPropertyvalueException(PropertyValueException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> trataClienteNotFoundException(ClienteNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProgressoNotFoundException.class)
    public ResponseEntity<String> trataProgressoNotFoundException(ProgressoNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AmigosNotFoundException.class)
    public ResponseEntity<String> trataAmigosControllerException(AmigosNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
