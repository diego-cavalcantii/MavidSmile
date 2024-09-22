package br.com.mavidsmile.mavidsmile.gateways.response;

import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Progresso;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClienteGETResponseDTO {
    private String nomeNivel;
    private String nomeCompleto;
    private String email;
    private String endereco;
    private List<PremioDTO> premiosRecebidos;
}
