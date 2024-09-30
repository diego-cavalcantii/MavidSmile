package br.com.mavidsmile.mavidsmile.gateways.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Valid
public record AdicionarAmigoRequestDTO(
        @NotNull  String clienteIdTemAmigo,
        @NotNull  String clienteIdEhAmigo) {

}
