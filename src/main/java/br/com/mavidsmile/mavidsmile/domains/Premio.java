package br.com.mavidsmile.mavidsmile.domains;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Premio {
    private String id_premio;
    private String nome_premio;
    private String descricao_premio;
    private String qtde_fotos;

}
