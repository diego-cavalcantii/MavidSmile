package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Amizade;
import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.AmizadeNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.AmigosRepository;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.RemoverAmizade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RemoverAmizadeImpl implements RemoverAmizade {

    private final BuscarClientes buscarClientes;
    private final AmigosRepository amigosRepository;

    @Override
    public void executa(String clienteId, String amigoId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);



        Amizade amizade = cliente.getAmigos().stream()
                .filter(amigo -> amigo.getClienteIdEhAmigo().getIdCliente().equals(amigoId))
                .findFirst()
                .orElseThrow(() -> new AmizadeNotFoundException("Amigo não encontrado"));

        cliente.getAmigos().remove(amizade);
        amigosRepository.delete(amizade);
    }
}
