package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Notificacao;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.NotificacaoNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NotificacaoRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.NotificacaoResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notificacao")
public class NotificacaoController {

    private final NotificacaoRepository notificacaoRepository;
    private final BuscarClientes buscarClientes;
    private final BuscarNotificacao buscarNotificacao;

    @GetMapping
    public ResponseEntity<List<NotificacaoResponseDTO>> todasAsNotificacoes() {

        List<Notificacao> notificacaos = buscarNotificacao.buscarTodas();

        List<NotificacaoResponseDTO> notificacoesResponseDTOS = notificacaos.stream()
                .map(notificacao -> NotificacaoResponseDTO.builder()
                        .mensagem(notificacao.getMensagem())
                        .tipo(notificacao.getTipo())
                        .build())
                .toList();

        return ResponseEntity.ok(notificacoesResponseDTOS);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<NotificacaoResponseDTO>> NotificacoesPorCliente(@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        List<Notificacao> notificacaos = buscarNotificacao.buscarPorCliente(cliente);

        List<NotificacaoResponseDTO> notificacoesResponseDTOS = notificacaos.stream()
                .map(notificacao -> NotificacaoResponseDTO.builder()
                        .mensagem(notificacao.getMensagem())
                        .tipo(notificacao.getTipo())
                        .build())
                .toList();

        return ResponseEntity.ok(notificacoesResponseDTOS);
    }

}
