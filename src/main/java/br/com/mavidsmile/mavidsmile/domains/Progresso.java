package br.com.mavidsmile.mavidsmile.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private String idProgresso;

    @OneToMany(mappedBy = "progresso")
    private List<ProgressoPremio> premiosRecebidos;


    private int registros;
    private int pontos;
}
