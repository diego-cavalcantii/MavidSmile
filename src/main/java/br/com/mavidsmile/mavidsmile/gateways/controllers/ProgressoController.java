package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Notificacao;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.ClienteNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.ProgressoNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NotificacaoRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.NotificacaoResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.AdicionarRegistroProgresso;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ExibiClienteDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/progresso")
@RequiredArgsConstructor
public class ProgressoController {

    private final AdicionarRegistroProgresso adicionarRegistroProgresso;
    private final ExibiClienteDTO exibiClienteDTO;
    private final BuscarClientes buscarClientes;
    private final NotificacaoRepository notificacaoRepository;


    @GetMapping()
    public ResponseEntity<List<ClienteRankingResponseDTO>> exibiRankingGeralDosClientes() {

        List<Cliente> amigosCliente = buscarClientes.buscarClientesPorRankingDePontos();

        if(amigosCliente.isEmpty()){
            throw new ClienteNotFoundException("Nenhum cliente encontrado");
        }

        List<ClienteRankingResponseDTO> clientesDTO = amigosCliente.stream()
                .map(exibiClienteDTO::transformarClienteRankingDTO)
                .toList();

        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("{clienteId}")
    public ResponseEntity<ClienteProgressoResponseDTO> exibiProgressoDeUmCliente (@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        if(cliente.getProgresso() == null){
            throw new ProgressoNotFoundException("Progresso do cliente com ID " + clienteId + " não encontrado");
        }

        ClienteProgressoResponseDTO clienteProgressoDTO = exibiClienteDTO.transformarClienteProgressoDTO(cliente);
        return ResponseEntity.ok(clienteProgressoDTO);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/adicionar-registro/{clienteId}")
    public ResponseEntity<String> adicionarRegistroDeUmCliente(@PathVariable @Valid String clienteId) {

        adicionarRegistroProgresso.executa(clienteId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registro de progresso adicionado com sucesso");

    }

    @GetMapping("/notificacao")
    public ResponseEntity<List<NotificacaoResponseDTO>> enviarNotificacao() {

        List<Notificacao> notificacaos = notificacaoRepository.findAll();

        List<NotificacaoResponseDTO> notificacoesResponseDTOS = notificacaos.stream()
                .map(notificacao -> NotificacaoResponseDTO.builder()
                        .mensagem(notificacao.getMensagem())
                        .tipo(notificacao.getTipo())
                        .build())
                .toList();

        return ResponseEntity.ok(notificacoesResponseDTOS);
    }
}
