package br.com.mavidsmile.mavidsmile.domains;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class Notificacao {
    private String id_notificacao;
    private Cliente cliente;
    private String tipo;
    private String mensagem;
}
