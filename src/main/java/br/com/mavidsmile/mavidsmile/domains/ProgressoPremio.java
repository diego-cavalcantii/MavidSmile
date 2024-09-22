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
public class ProgressoPremio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idProgressoPremio;

    // Relacionamento ManyToOne com Progresso
    @ManyToOne
    @JoinColumn(name = "id_progresso")  // Chave estrangeira para Progresso
    private Progresso progresso;

    // Relacionamento ManyToOne com Premio
    @ManyToOne
    @JoinColumn(name = "id_premio")  // Chave estrangeira para Premio
    private Premio premio;
}
