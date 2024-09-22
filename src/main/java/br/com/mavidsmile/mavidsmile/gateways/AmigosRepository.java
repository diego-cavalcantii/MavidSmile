package br.com.mavidsmile.mavidsmile.gateways;

import br.com.mavidsmile.mavidsmile.domains.Amigos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AmigosRepository extends JpaRepository<Amigos, String> {
}
