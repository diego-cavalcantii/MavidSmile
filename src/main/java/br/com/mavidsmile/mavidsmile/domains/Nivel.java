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
public class Nivel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idNivel;
    private String nomeNivel;
    private int fotosNecessarias;

    @OneToOne
    @JoinColumn(name = "idPremio")
    private Premio premio;

}
