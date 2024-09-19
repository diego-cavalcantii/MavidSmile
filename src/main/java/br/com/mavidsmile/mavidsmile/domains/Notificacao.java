package br.com.mavidsmile.mavidsmile.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_notificacao;

    @ManyToOne
    private Cliente cliente;

    private String tipo;
    private String mensagem;
}
