package br.com.mavidsmile.mavidsmile.gateways.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificacaoResponseDTO {
    private String mensagem;
    private String tipo;
}
