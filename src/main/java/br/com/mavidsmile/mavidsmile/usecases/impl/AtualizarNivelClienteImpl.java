package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Progresso;
import br.com.mavidsmile.mavidsmile.domains.ProgressoPremio;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NivelRepository;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ProgressoPremioRepository;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.AtualizarNivelCliente;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.EnviarNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtualizarNivelClienteImpl implements AtualizarNivelCliente {

    private final ClienteRepository clienteRepository;
    private final NivelRepository nivelRepository;
    private final ProgressoPremioRepository progressoPremioRepository;
    private final BuscarClientes buscarClientes;
    private final EnviarNotificacao enviarNotificacao;

    @Override
    public void executa(String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        Progresso progresso = cliente.getProgresso();

        Optional<Nivel> nivelOpt = nivelRepository.findByPontosNecessarios(progresso.getPontos());

        if (nivelOpt.isPresent()) {
            Nivel nivel = nivelOpt.get();

            cliente.setNivel(nivel);
            clienteRepository.save(cliente);


            ProgressoPremio progressoPremio = ProgressoPremio.builder()
                    .progresso(progresso)
                    .premio(nivel.getPremio())
                    .build();
            progressoPremioRepository.save(progressoPremio);

            enviarNotificacao.nivelAtualizado(clienteId);
        }

    }
}
