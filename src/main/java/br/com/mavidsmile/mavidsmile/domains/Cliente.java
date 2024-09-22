package br.com.mavidsmile.mavidsmile.domains;


import jakarta.persistence.*;
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

    @OneToOne
    @JoinColumn(name = "idProgresso")
    private Progresso progresso;

    @ManyToOne
    @JoinColumn(name = "idNivel")
    private Nivel nivel;

    @OneToMany(mappedBy = "clienteIdTemAmigo")
    List<Amigos> amigos;

    private String nomeCompleto;
    private String email;
    private String endereco;
}
