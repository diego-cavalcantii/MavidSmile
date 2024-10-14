package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmizadeRequestDTO;
import org.springframework.stereotype.Service;

@Service

public interface AdicionarAmizade {
    void executa(Cliente cliente, Cliente amigo);
}
