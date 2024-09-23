package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Amigos;
import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.repositories.AmigosRepository;
import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmigoRequestDTO;
import br.com.mavidsmile.mavidsmile.usecases.AdicionarAmigo;
import br.com.mavidsmile.mavidsmile.usecases.BuscarClientes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdicionarAmigoImpl implements AdicionarAmigo {


    private final AmigosRepository amigosRepository;
    private final BuscarClientes buscarClientes;

    @Override
    public void adicionarAmigo(AdicionarAmigoRequestDTO requestDTO) {
        Cliente clienteTemAmigo = buscarClientes.buscarPorId(requestDTO.getClienteIdTemAmigo());

        Cliente clienteEhAmigo = buscarClientes.buscarPorId(requestDTO.getClienteIdEhAmigo());

        Amigos novoAmigo = Amigos.builder()
                .clienteIdTemAmigo(clienteTemAmigo)
                .clienteIdEhAmigo(clienteEhAmigo)
                .build();

        amigosRepository.save(novoAmigo);
    }
}
