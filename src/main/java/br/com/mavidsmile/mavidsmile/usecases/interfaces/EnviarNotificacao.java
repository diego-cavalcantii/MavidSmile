package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Notificacao;
import br.com.mavidsmile.mavidsmile.gateways.response.NotificacaoResponseDTO;

public interface EnviarNotificacao {

    Notificacao nivelAtualizado(Cliente cliente);

}
