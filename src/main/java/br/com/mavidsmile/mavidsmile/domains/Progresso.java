package br.com.mavidsmile.mavidsmile.domains;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Progresso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_progresso;

    @ManyToOne
    private Nivel nivel;

    @OneToMany(mappedBy = "progresso")
    private List<Premio> premios_recebidos;


    private int registros;
    private double porcentagem_registros;
}
