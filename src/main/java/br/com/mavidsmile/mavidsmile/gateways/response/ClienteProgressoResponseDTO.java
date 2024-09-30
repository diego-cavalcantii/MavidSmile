package br.com.mavidsmile.mavidsmile.gateways.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClienteProgressoResponseDTO {
    private String nomeCompleto;
    private String registros;
    private String pontos;
    private List<PremioResponseDTO> premiosRecebidos;
}
