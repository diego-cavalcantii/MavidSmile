package br.com.mavidsmile.mavidsmile.gateways.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"nomeCompleto", "email","nomeNivel","pontos", "premiosRecebidos", })
public class ClienteAmizadeResponseDTO {     // ID do cliente amigo
    private String nomeCompleto;
    private String email;
    private String nomeNivel;
    private int pontos;
    private List<PremioClienteResponseDTO> premiosRecebidos;
}

