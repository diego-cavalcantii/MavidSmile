package br.com.mavidsmile.mavidsmile.gateways.repositories;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    List<Cliente> findAllByOrderByProgressoRegistrosDesc();
}
