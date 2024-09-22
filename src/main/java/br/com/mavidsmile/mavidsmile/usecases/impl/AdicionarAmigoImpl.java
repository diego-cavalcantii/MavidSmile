package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Amigos;
import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.AmigosRepository;
import br.com.mavidsmile.mavidsmile.gateways.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmigoRequestDTO;
import br.com.mavidsmile.mavidsmile.usecases.AdicionarAmigo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdicionarAmigoImpl implements AdicionarAmigo {

    private final ClienteRepository clienteRepository;
    private final AmigosRepository amigosRepository;

    @Override
    public void adicionarAmigo(AdicionarAmigoRequestDTO requestDTO) {
        Cliente clienteTemAmigo = clienteRepository.findById(requestDTO.getClienteIdTemAmigo())
                .orElseThrow(() -> new RuntimeException("Cliente que adiciona não encontrado"));

        Cliente clienteEhAmigo = clienteRepository.findById(requestDTO.getClienteIdEhAmigo())
                .orElseThrow(() -> new RuntimeException("Cliente que será adicionado não encontrado"));

        Amigos novoAmigo = Amigos.builder()
                .clienteIdTemAmigo(clienteTemAmigo)
                .clienteIdEhAmigo(clienteEhAmigo)
                .build();

        amigosRepository.save(novoAmigo);
    }
}
