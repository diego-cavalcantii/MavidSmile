package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Notificacao;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.ClienteNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.ProgressoNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NivelRepository;
import br.com.mavidsmile.mavidsmile.gateways.repositories.NotificacaoRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.NotificacaoResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/progresso")
@RequiredArgsConstructor
public class ProgressoController {

    private final AdicionarRegistroProgresso adicionarRegistroProgresso;
    private final ConverteClienteEmDTO converteClienteEmDTO;
    private final BuscarClientes buscarClientes;
    private final NivelRepository nivelRepository;
    private final EnviarNotificacao enviarNotificacao;
    private final AtualizarNivelCliente atualizarNivelCliente;


    @GetMapping()
    public ResponseEntity<List<ClienteRankingResponseDTO>> exibiRankingGeralDosClientes() {

        List<Cliente> amigosCliente = buscarClientes.buscarClientesPorRankingDePontos();

        List<ClienteRankingResponseDTO> clientesDTO = amigosCliente.stream()
                .map(converteClienteEmDTO::ClienteRankingDTO)
                .toList();

        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("{clienteId}")
    public ResponseEntity<ClienteProgressoResponseDTO> exibiProgressoDeUmCliente (@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        if(cliente.getProgresso() == null){
            throw new ProgressoNotFoundException("Progresso do cliente com ID " + clienteId + " não encontrado");
        }

        ClienteProgressoResponseDTO clienteProgressoDTO = converteClienteEmDTO.ClienteProgressoDTO(cliente);
        return ResponseEntity.ok(clienteProgressoDTO);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/adicionar-registro/{clienteId}")
    public ResponseEntity<String> adicionarRegistroDeUmCliente(@PathVariable @Valid String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        adicionarRegistroProgresso.executa(cliente);

        Optional<Nivel> nivelOpt = nivelRepository.findByPontosNecessarios(cliente.getProgresso().getPontos());

        if(nivelOpt.isPresent()){
            Nivel nivel = nivelOpt.get();

            atualizarNivelCliente.executa(cliente,nivel);
            Notificacao notificacao = enviarNotificacao.nivelAtualizado(cliente);

            return ResponseEntity.status(HttpStatus.CREATED).body(notificacao.getMensagem());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Registro adicionado com sucesso");

    }


}
