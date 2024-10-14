package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.gateways.exceptions.*;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErroValidacao(MethodArgumentNotValidException ex) {
        // Captura os erros de validação
        List<DadosErroValidacao> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(DadosErroValidacao::new)
                .collect(Collectors.toList());

        // Retorna a lista de erros com status 400 (BAD_REQUEST)
        return ResponseEntity.badRequest().body(erros);
    }

    // Record para representar os erros de validação
    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }



}
