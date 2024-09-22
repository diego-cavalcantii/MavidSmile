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
    private String idPremio;

    private String nomePremio;
    private String descricaoPremio;
    private int fotosNecessarias;

    @OneToOne
    @JoinColumn(name = "idNivel")
    private Nivel nivel;

}
