package br.com.mavidsmile.mavidsmile.gateways.repositories;

import br.com.mavidsmile.mavidsmile.domains.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NivelRepository extends JpaRepository<Nivel, String> {

    Optional<Nivel> findByPontosNecessarios(int pontosNecessarios);
}
