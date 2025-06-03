package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.PremioRaro;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.PremioRaroNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.PremioRaroRepository;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarPremiosRaros;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarPremiosRarosImpl implements BuscarPremiosRaros {

    private final PremioRaroRepository premioRaroRepository;

    @Override
    public List<PremioRaro> buscarTodos() {
        List<PremioRaro> premios = premioRaroRepository.findAll();
        if (premios.isEmpty()) {
            throw new PremioRaroNotFoundException("Nenhum prêmio raro encontrado");
        }
        return premios;
    }
}
