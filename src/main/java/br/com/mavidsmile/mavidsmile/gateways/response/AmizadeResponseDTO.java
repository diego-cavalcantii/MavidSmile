package br.com.mavidsmile.mavidsmile.gateways.response;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class AmizadeResponseDTO extends RepresentationModel<AmizadeResponseDTO> {
    private ClienteAmizadeResponseDTO clienteTemAmigo;
    private ClienteAmizadeResponseDTO clienteEhAmigo;
}
