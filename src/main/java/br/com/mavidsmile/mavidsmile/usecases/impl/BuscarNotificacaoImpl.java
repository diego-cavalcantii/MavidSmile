package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Notificacao;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.NotificacaoNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NotificacaoRepository;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BuscarNotificacaoImpl implements BuscarNotificacao {

    private final NotificacaoRepository notificacaoRepository;

    @Override
    public List<Notificacao> buscarTodas() {

        List<Notificacao> notificacaos = notificacaoRepository.findAll();

        if(notificacaos.isEmpty()){
            throw new NotificacaoNotFoundException("Nenhuma notificação encontrada");
        }
        return notificacaos;
    }

    @Override
    public List<Notificacao> buscarPorCliente(Cliente cliente) {

        List<Notificacao> notificacaos = notificacaoRepository.findByCliente(cliente);

        if(notificacaos.isEmpty()){
            throw new NotificacaoNotFoundException("Nenhuma notificação encontrada");
        }

        return notificacaos;
    }
}
