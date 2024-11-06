package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;

import br.com.mavidsmile.mavidsmile.gateways.response.PremioClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ExibiListaPremios;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExibirListaPremiosImpl implements ExibiListaPremios {

    @Override
    public List<PremioClienteResponseDTO> executa(Cliente cliente) {
        return cliente.getProgresso().getPremiosRecebidos().stream()
                .map(premio -> PremioClienteResponseDTO.builder()
                        .nomePremio(premio.getPremio().getNomePremio())
                        .descricaoPremio(premio.getPremio().getDescricaoPremio())
                        .build())
                .toList();
    }
}
