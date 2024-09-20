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
public class Amigo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String amizade;

    @ManyToOne
    private Cliente ClienteIdEhAmigo;

    @ManyToOne
    private Cliente ClienteIdTemAmigo;

}
