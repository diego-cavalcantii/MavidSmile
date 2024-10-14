package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Nivel;

public interface AtualizarNivelCliente {
    void executa(Cliente cliente, Nivel nivel);
}
