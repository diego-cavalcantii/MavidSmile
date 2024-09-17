package br.com.mavidsmile.mavidsmile.domains;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cliente {
    private String idCliente;
    private String primeiroNome;
    private String ultimoNome;
    private String email;
    private Endereco endereco;
    private Nivel nivel;
}
