package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Amizade;
import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.AmizadeNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.AmizadeRepository;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.RemoverAmizade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RemoverAmizadeImpl implements RemoverAmizade {

    private final BuscarClientes buscarClientes;
    private final AmizadeRepository amizadeRepository;

    @Override
    public void executa(String clienteId, String amigoId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        Cliente amigo = buscarClientes.buscarPorId(amigoId);

        Amizade amizade = amizadeRepository
                .findByClienteIdTemAmigoAndClienteIdEhAmigo(cliente, amigo)
                .orElseThrow(() -> new AmizadeNotFoundException("Amizade não encontrada"));



        cliente.getAmigos().remove(amizade);
        amizadeRepository.delete(amizade);
    }
}
