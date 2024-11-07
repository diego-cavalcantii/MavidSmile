package br.com.mavidsmile.mavidsmile.gateways.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@Builder
public class ClienteProgressoResponseDTO extends RepresentationModel<ClienteProgressoResponseDTO> {
    private String nomeCompleto;
    private String registros;
    private String pontos;
    private String imgSrc;
    private List<PremioClienteResponseDTO> premiosRecebidos;
}
