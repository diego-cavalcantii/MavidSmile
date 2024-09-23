package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.ExibiClienteDTO;
import br.com.mavidsmile.mavidsmile.usecases.ExibiListaPremios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExibiClienteDTOImpl implements ExibiClienteDTO {

    private final ExibiListaPremios exibiListaPremios;


    @Override
    public ClienteGETResponseDTO tranformarClienteEmDTO(Cliente cliente) {
        return ClienteGETResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .email(cliente.getEmail())
                .nomeNivel(cliente.getNivel() != null ? cliente.getNivel().getNomeNivel() : "Nível não definido")
                .premiosRecebidos(cliente.getProgresso() != null ? exibiListaPremios.exibir(cliente) : List.of())
                .build();
    }
}
