package br.com.mavidsmile.mavidsmile.domains;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idCliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProgresso")
    private Progresso progresso;

    @ManyToOne
    @JoinColumn(name = "idNivel")
    private Nivel nivel;

    @OneToMany(mappedBy = "clienteIdTemAmigo",cascade = CascadeType.ALL)
    List<Amizade> amigos;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nomeCompleto;

    @NotBlank
    @Size(min = 3, max = 100)
    private String email;

    @NotBlank
    @Size(min = 3, max = 100)
    private String endereco;
}
