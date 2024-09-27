package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.OrdenarListaPorPontos;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdernarListaPorPontos implements OrdenarListaPorPontos {
    @Override
    public List<ClienteRankingResponseDTO> execute(List<ClienteRankingResponseDTO> rankingDeClientes) {
        rankingDeClientes = rankingDeClientes.stream()
                .sorted(Comparator.comparingInt(ClienteRankingResponseDTO::getPontos).reversed())
                .collect(Collectors.toList());

        return rankingDeClientes;
    }
}

