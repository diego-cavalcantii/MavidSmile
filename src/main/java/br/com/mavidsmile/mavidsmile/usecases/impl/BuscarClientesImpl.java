package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.ExibiListaPremios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarClientesImpl implements BuscarClientes {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> buscarTodos() {

        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(String clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    public List<Cliente> buscarClientesPorRankingDeRegistros() {
        return clienteRepository.findAllByOrderByProgressoRegistrosDesc();
    }

}
