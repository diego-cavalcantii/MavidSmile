package br.com.mavidsmile.mavidsmile.domains;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_premio;

    private String nome_premio;
    private String descricao_premio;
    private String fotos_necessarias;

    @ManyToOne
    private Progresso progresso;



}
