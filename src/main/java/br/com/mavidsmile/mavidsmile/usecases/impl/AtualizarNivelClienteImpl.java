package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Progresso;
import br.com.mavidsmile.mavidsmile.domains.ProgressoPremio;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NivelRepository;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ProgressoPremioRepository;
import br.com.mavidsmile.mavidsmile.usecases.AtualizarNivelCliente;
import br.com.mavidsmile.mavidsmile.usecases.BuscarClientes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtualizarNivelClienteImpl implements AtualizarNivelCliente {

    private final ClienteRepository clienteRepository;
    private final NivelRepository nivelRepository;
    private final ProgressoPremioRepository progressoPremioRepository;
    private final BuscarClientes buscarClientes;

    @Override
    public void atualizarNivel(String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        Progresso progresso = cliente.getProgresso();

        List<Nivel> niveis = nivelRepository.findAll();
        for (Nivel nivel : niveis) {
            if(progresso.getPontos() == nivel.getPontosNecessarios()) {
                cliente.setNivel(nivel);
                clienteRepository.save(cliente);


                ProgressoPremio progressoPremio = ProgressoPremio.builder()
                        .progresso(progresso)
                        .premio(nivel.getPremio())
                        .build();
                progressoPremioRepository.save(progressoPremio);
            }
        }

    }
}
