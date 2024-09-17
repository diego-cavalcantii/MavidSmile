package br.com.mavidsmile.mavidsmile.domains;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Endereco {
    private String idEndereco;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
}
