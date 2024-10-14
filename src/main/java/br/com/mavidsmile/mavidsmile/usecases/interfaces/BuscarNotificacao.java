package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Notificacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuscarNotificacao {
    List<Notificacao> buscarTodas();
    List<Notificacao> buscarPorCliente(Cliente cliente);
}
