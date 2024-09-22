package br.com.mavidsmile.mavidsmile.gateways.requests;

import lombok.Data;

@Data
public class AdicionarAmigoRequestDTO {
    private String clienteIdTemAmigo;
    private String clienteIdEhAmigo;
}
