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
    private String id_cliente;

    @OneToOne
    private Progresso progresso;

    @ManyToOne
    private Nivel nivel;

    @ManyToMany
    private List<Cliente> amigos;

    private String nome_completo;
    private String email;
    private String endereco;
}
