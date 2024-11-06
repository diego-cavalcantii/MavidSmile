package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Premio;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.PremioNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.PremioRepository;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarPremios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarPremiosImpl implements BuscarPremios {

    private final PremioRepository premioRepository;

    @Override
    public List<Premio> buscarTodosOsPremios() {
        List<Premio> premios = premioRepository.findAll();
        if (premios.isEmpty()) {
            throw new PremioNotFoundException("Nenhum premio encontrado");
        }

        return premios;
    }
}
