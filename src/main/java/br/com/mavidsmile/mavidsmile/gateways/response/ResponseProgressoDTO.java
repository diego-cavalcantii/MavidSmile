package br.com.mavidsmile.mavidsmile.gateways.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class ResponseProgressoDTO extends RepresentationModel<ResponseProgressoDTO> {
    private String mensagem;
    private Link link;
}
