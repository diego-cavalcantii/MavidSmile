package br.com.mavidsmile.mavidsmile.usecases;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;

import java.util.List;

public interface BuscarClientes {
    List<Cliente> buscarTodos();

    Cliente buscarPorId(String clienteId);

    List<Cliente> buscarClientesPorRankingDeRegistros();
}
