package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;

import br.com.mavidsmile.mavidsmile.gateways.response.PremioDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ExibiListaPremios;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExibirListaPremiosImpl implements ExibiListaPremios {

    @Override
    public List<PremioDTO> executa(Cliente cliente) {
        return cliente.getProgresso().getPremiosRecebidos().stream()
                .map(premio -> PremioDTO.builder()
                        .nomePremio(premio.getPremio().getNomePremio())
                        .descricaoPremio(premio.getPremio().getDescricaoPremio())
//                        .fotosNecessarias(String.valueOf(premio.getPremio().getFotosNecessarias()))
                        .build())
                .collect(Collectors.toList());
    }
}
