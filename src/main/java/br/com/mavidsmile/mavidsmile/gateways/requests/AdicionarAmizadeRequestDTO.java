package br.com.mavidsmile.mavidsmile.gateways.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Valid
public record AdicionarAmizadeRequestDTO(
        @NotBlank String clienteIdTemAmigo,
        @NotBlank  String clienteIdEhAmigo) {

}
