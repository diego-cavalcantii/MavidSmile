package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Notificacao;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NivelRepository;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NotificacaoRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.NotificacaoResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.EnviarNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EnviarNotificacaoImpl implements EnviarNotificacao {

    private final BuscarClientes buscarClientes;
    private final NivelRepository nivelRepository;
    private final NotificacaoRepository notificacaoRepository;


    @Override
    public Notificacao nivelAtualizado(Cliente cliente) {

        Nivel nivel = nivelRepository.findByNomeNivel(cliente.getNivel().getNomeNivel());

        if(nivel != null){
            String mensagem = gerarMensagem(cliente, nivel);

            Notificacao nivelAtualizado =  Notificacao.builder()
                    .cliente(cliente)
                    .mensagem(mensagem)
                    .tipo("Nivel Atualizado")
                    .build();

            notificacaoRepository.save(nivelAtualizado);

            return nivelAtualizado;

        }

        return null;
    }

    private String gerarMensagem(Cliente cliente, Nivel nivel) {
        String[] nomeSplitado = cliente.getNomeCompleto().split(" ");
        String primeiroNome = nomeSplitado[0];
        String ultimoNome = nomeSplitado[nomeSplitado.length - 1];
        return "Parabéns " + primeiroNome + " " + ultimoNome + ", você atingiu o nível " + nivel.getNomeNivel() + "!";
    }
}
