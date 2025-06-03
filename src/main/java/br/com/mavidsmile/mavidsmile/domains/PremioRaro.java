package br.com.mavidsmile.mavidsmile.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PremioRaro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idPremioRaro;

    @NotBlank
    private String nomePremioRaro;

    @NotBlank
    private String descricaoPremioRaro;
}
