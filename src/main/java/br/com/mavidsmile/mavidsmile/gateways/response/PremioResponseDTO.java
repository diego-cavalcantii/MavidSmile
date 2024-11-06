package br.com.mavidsmile.mavidsmile.gateways.response;


import br.com.mavidsmile.mavidsmile.domains.Nivel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PremioResponseDTO {
    private String nomePremio;
    private String descricaoPremio;
}
