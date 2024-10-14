package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.ClienteNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarClientesImpl implements BuscarClientes {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> buscarTodos() {

        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new ClienteNotFoundException("Nenhum cliente encontrado");
        }

        return clientes;
    }

    @Override
    public Cliente buscarPorId(String clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com ID " + clienteId + " não encontrado"));
    }

    @Override
    public List<Cliente> buscarClientesPorRankingDePontos() {

        return clienteRepository.findAllByOrderByProgressoPontosDesc();
    }


}
