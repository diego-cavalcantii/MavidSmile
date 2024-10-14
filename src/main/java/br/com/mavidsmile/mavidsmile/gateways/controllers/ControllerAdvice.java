package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.gateways.exceptions.*;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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

    @ExceptionHandler(NotificacaoNotFoundException.class)
    public ResponseEntity<String> trataNotificacaoNotFoundException(NotificacaoNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AmizadeNotFoundException.class)
    public ResponseEntity<String> trataAmigosControllerException(AmizadeNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AmizadeMethodArgumentNotValidException.class)
    public ResponseEntity<String> trataDadosAmizadeException(AmizadeMethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
