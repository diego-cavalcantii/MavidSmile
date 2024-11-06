package br.com.mavidsmile.mavidsmile.gateways.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NivelResponseDTO {
    private String nomeNivel;
    private int pontosNecessarios;
    private PremioClienteResponseDTO premio;
}
