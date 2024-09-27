package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrdenarListaPorPontos {
    List<ClienteRankingResponseDTO> execute(List<ClienteRankingResponseDTO> rankingDeClientes);
}
