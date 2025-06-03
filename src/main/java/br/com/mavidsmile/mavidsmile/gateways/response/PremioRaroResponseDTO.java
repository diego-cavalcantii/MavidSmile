package br.com.mavidsmile.mavidsmile.gateways.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PremioRaroResponseDTO {
    private String nomePremioRaro;
    private String descricaoPremioRaro;
}
