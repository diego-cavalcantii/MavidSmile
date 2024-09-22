package br.com.mavidsmile.mavidsmile.usecases;

import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmigoRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface AdicionarAmigo {
    void adicionarAmigo(AdicionarAmigoRequestDTO requestDTO);
}
