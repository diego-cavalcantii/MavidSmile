package br.com.mavidsmile.mavidsmile.gateways.response;

import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Progresso;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteGETResponseDTO {
    private Progresso progresso;
    private String nome_nivel;
    private String nome_completo;
    private String email;
    private String endereco;

}
