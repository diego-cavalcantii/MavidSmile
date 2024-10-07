package br.com.mavidsmile.mavidsmile.gateways.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Valid
public record AdicionarAmizadeRequestDTO(
        @NotNull  String clienteIdTemAmigo,
        @NotNull  String clienteIdEhAmigo) {

}
