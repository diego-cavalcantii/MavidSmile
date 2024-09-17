package br.com.mavidsmile.mavidsmile.domains;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Nivel {
    private String id_nivel;
    private String nome_nivel;
    private Progresso progresso;



}
