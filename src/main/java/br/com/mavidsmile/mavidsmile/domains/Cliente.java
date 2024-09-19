package br.com.mavidsmile.mavidsmile.domains;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cliente {
    private String id_cliente;
    private String primeiro_nome;
    private String ultimo_nome;
    private String email;
    private String endereco;
    private Nivel nivel;
}
