package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Amizade;
import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.AmizadeResponseDTO;
import org.springframework.stereotype.Service;

@Service

public interface AdicionarAmizade {
    AmizadeResponseDTO executa(Cliente cliente, Cliente amigo);
}
