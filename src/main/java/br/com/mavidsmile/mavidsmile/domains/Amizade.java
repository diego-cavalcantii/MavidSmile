package br.com.mavidsmile.mavidsmile.domains;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Amizade extends RepresentationModel<Amizade> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String amizade;

    @ManyToOne
    @JoinColumn(name = "ClienteIdEhAmigo")
    @NotNull
    private Cliente clienteIdEhAmigo;

    @ManyToOne
    @JoinColumn(name = "ClienteIdTemAmigo")
    @NotNull
    private Cliente clienteIdTemAmigo;

}
