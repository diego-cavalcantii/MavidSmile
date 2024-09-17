package br.com.mavidsmile.mavidsmile.domains;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Progresso {
    private String id_progresso;
    private Cliente cliente;
    private Premio premio;
    private Nivel nivel;
    private int registros;
    private LocalDate ultimo_registro;
    private double porcentagem_registros;
}
