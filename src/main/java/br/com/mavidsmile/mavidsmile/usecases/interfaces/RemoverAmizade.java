package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Cliente;

public interface RemoverAmizade {
    void executa(Cliente cliente, Cliente amigo);
}
