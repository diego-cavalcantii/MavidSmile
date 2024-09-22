package br.com.mavidsmile.mavidsmile.gateways.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteAmigoResponseDTO {     // ID do cliente amigo
    private String nome_completo;    // Nome completo do cliente amigo
    private String email;
    private String nome_nivel;// Email do cliente amigo
}

