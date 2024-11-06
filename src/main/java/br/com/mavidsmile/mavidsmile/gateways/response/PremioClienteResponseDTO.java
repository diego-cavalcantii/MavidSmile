package br.com.mavidsmile.mavidsmile.gateways.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PremioClienteResponseDTO {
    private String nomePremio;
    private String descricaoPremio;
}
