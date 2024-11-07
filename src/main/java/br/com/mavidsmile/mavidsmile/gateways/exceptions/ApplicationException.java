package br.com.mavidsmile.mavidsmile.gateways.exceptions;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }
}
