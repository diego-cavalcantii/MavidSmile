package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmigoRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface AdicionarAmizade {
    void adicionarAmigo(AdicionarAmigoRequestDTO requestDTO);
}
