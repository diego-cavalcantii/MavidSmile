package br.com.mavidsmile.mavidsmile.gateways.repositories;

import br.com.mavidsmile.mavidsmile.domains.Progresso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProgressoRepository extends JpaRepository<Progresso, String> {
}
