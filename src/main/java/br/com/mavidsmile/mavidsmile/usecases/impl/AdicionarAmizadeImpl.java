package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Amizade;
import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.AmizadeNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.AmigosRepository;
import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmigoRequestDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.AdicionarAmizade;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdicionarAmizadeImpl implements AdicionarAmizade {


    private final AmigosRepository amigosRepository;
    private final BuscarClientes buscarClientes;

    @Override
    public void adicionarAmigo(AdicionarAmigoRequestDTO requestDTO) {
        Cliente clienteTemAmigo = buscarClientes.buscarPorId(requestDTO.clienteIdTemAmigo());

        Cliente clienteEhAmigo = buscarClientes.buscarPorId(requestDTO.clienteIdEhAmigo());

        if(clienteTemAmigo.equals(clienteEhAmigo)) {
            throw new AmizadeNotFoundException("Não é possível adicionar você mesmo como amigo");
        }

        List<Amizade> amigos = clienteTemAmigo.getAmigos();

        for (Amizade amigo : amigos) {
            if (amigo.getClienteIdEhAmigo().equals(clienteEhAmigo)) {
                throw new AmizadeNotFoundException("Amizade já existe");
            }
        }

        Amizade novoAmigo = Amizade.builder()
                .clienteIdTemAmigo(clienteTemAmigo)
                .clienteIdEhAmigo(clienteEhAmigo)
                .build();

        amigosRepository.save(novoAmigo);
    }
}
