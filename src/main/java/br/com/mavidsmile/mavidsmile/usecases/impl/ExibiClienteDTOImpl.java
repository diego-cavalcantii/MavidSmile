package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ExibiClienteDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ExibiListaPremios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExibiClienteDTOImpl implements ExibiClienteDTO {

    private final ExibiListaPremios exibiListaPremios;


    @Override
    public ClienteGETResponseDTO transformarClienteGetDTO(Cliente cliente) {
        return ClienteGETResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .email(cliente.getEmail())
                .endereco(cliente.getEndereco())
                .nomeNivel(cliente.getNivel() != null ? cliente.getNivel().getNomeNivel() : "Nível não definido")
                .pontos(cliente.getProgresso() != null ? cliente.getProgresso().getPontos() : 0)
                .premiosRecebidos(cliente.getProgresso() != null ? exibiListaPremios.exibir(cliente) : List.of())
                .build();
    }

    @Override
    public ClienteProgressoResponseDTO transformarClienteProgressoDTO(Cliente cliente) {
        return  ClienteProgressoResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .registros(String.valueOf(cliente.getProgresso().getRegistros()))
                .pontos(String.valueOf(cliente.getProgresso().getPontos()))
                .premiosRecebidos(exibiListaPremios.exibir(cliente))
                .build();
    }

    @Override
    public ClienteRankingResponseDTO transformarClienteRankingDTO(Cliente cliente) {
        return ClienteRankingResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .pontos(cliente.getProgresso() != null ? cliente.getProgresso().getPontos() : 0)
                .nomeNivel(cliente.getNivel() != null ? cliente.getNivel().getNomeNivel() : "Nível não definido")
                .build();
    }
}
