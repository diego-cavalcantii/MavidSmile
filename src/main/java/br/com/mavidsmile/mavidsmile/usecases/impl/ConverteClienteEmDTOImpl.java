package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteAmizadeResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ConverteClienteEmDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ExibiListaPremios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConverteClienteEmDTOImpl implements ConverteClienteEmDTO {

    private final ExibiListaPremios exibiListaPremios;


    @Override
    public ClienteResponseDTO ClienteResponseDTO(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .email(cliente.getEmail())
                .endereco(cliente.getEndereco())
                .nomeNivel(cliente.getNivel() != null ? cliente.getNivel().getNomeNivel() : "Nível não definido")
                .pontos(cliente.getProgresso() != null ? cliente.getProgresso().getPontos() : 0)
                .premiosRecebidos(cliente.getProgresso() != null ? exibiListaPremios.executa(cliente) : List.of())
                .build();
    }

    @Override
    public ClienteProgressoResponseDTO ClienteProgressoDTO(Cliente cliente) {
        return  ClienteProgressoResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .registros(String.valueOf(cliente.getProgresso().getRegistros()))
                .pontos(String.valueOf(cliente.getProgresso().getPontos()))
                .premiosRecebidos(exibiListaPremios.executa(cliente))
                .build();
    }

    @Override
    public ClienteRankingResponseDTO ClienteRankingDTO(Cliente cliente) {
        return ClienteRankingResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .pontos(cliente.getProgresso() != null ? cliente.getProgresso().getPontos() : 0)
                .nomeNivel(cliente.getNivel() != null ? cliente.getNivel().getNomeNivel() : "Nível não definido")
                .build();
    }

    @Override
    public ClienteAmizadeResponseDTO ClienteAmizadeDTO(Cliente cliente) {
        return ClienteAmizadeResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .email(cliente.getEmail())
                .pontos(cliente.getProgresso() != null ? cliente.getProgresso().getPontos() : 0)
                .nomeNivel(cliente.getNivel() != null ? cliente.getNivel().getNomeNivel() : "Nível não definido")
                .premiosRecebidos(cliente.getProgresso() != null ? exibiListaPremios.executa(cliente) : List.of())
                .build();
    }
}
