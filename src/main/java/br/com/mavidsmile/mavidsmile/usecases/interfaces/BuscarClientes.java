package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Cliente;

import java.util.List;

public interface BuscarClientes {
    List<Cliente> buscarTodos();

    Cliente buscarPorId(String clienteId);

    List<Cliente> buscarClientesPorRankingDePontos();
}
