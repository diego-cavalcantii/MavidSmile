package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.NivelNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NivelRepository;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarNiveis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarNiveisImpl implements BuscarNiveis {

    private final NivelRepository nivelRepository;

    @Override
    public List<Nivel> buscarTodosOsNiveis() {
        List<Nivel> niveis = nivelRepository.findAll();
        if (niveis.isEmpty()) {
            throw new NivelNotFoundException("Nenhum nivel encontrado");
        }

        return niveis;
    }
}
