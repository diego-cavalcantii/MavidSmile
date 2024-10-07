package br.com.mavidsmile.mavidsmile.gateways.repositories;

import br.com.mavidsmile.mavidsmile.domains.Amizade;
import br.com.mavidsmile.mavidsmile.domains.Cliente;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AmizadeRepository extends JpaRepository<Amizade, String> {

    boolean existsByClienteIdTemAmigoAndClienteIdEhAmigo(Cliente clienteIdTemAmigo, Cliente clienteIdEhAmigo);

    Optional<Amizade> findByClienteIdTemAmigoAndClienteIdEhAmigo(Cliente clienteIdTemAmigo, Cliente clienteIdEhAmigo);
}
