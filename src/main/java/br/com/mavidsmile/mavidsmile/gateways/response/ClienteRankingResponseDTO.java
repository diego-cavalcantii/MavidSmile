package br.com.mavidsmile.mavidsmile.gateways.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteRankingResponseDTO {
    private String nomeCompleto;
    private String nomeNivel;
    private int pontos;
    private String imgSrc;
}
