package br.com.mavidsmile.mavidsmile.gateways.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PremioDTO {
    private String nomePremio;
    private String descricaoPremio;
}
