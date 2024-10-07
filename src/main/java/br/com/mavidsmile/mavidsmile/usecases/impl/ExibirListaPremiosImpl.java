package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;

import br.com.mavidsmile.mavidsmile.gateways.response.PremioResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ExibiListaPremios;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExibirListaPremiosImpl implements ExibiListaPremios {

    @Override
    public List<PremioResponseDTO> executa(Cliente cliente) {
        return cliente.getProgresso().getPremiosRecebidos().stream()
                .map(premio -> PremioResponseDTO.builder()
                        .nomePremio(premio.getPremio().getNomePremio())
                        .descricaoPremio(premio.getPremio().getDescricaoPremio())
                        .build())
                .toList();
    }
}
