package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.*;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ExibiListaPremios exibiListaPremios;
    private final AdicionarRegistroProgresso adicionarRegistroProgresso;
    private final AtualizarNivelCliente atualizarNivelCliente;
    private final ExibiClienteDTO exibiClienteDTO;
    private final BuscarClientes buscarClientes;


    @GetMapping
    public ResponseEntity<List<ClienteGETResponseDTO>> exibiTodosOsClientes() {
        // Busca todos os clientes do banco de dados
        List<Cliente> clientes = buscarClientes.buscarTodos();

        // Mapeia os clientes para ClienteGETResponseDTO
        List<ClienteGETResponseDTO> clientesDTO = clientes.stream()
                .map(exibiClienteDTO::tranformarClienteEmDTO)
                .collect(Collectors.toList());

        // Retorna a lista de clientes no formato DTO
        return ResponseEntity.ok(clientesDTO);
    }

    @ResponseStatus
    @GetMapping("/progresso/{clienteId}")
    public ResponseEntity<ClienteProgressoResponseDTO> exibiProgressoDeUmCliente (@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        if(cliente.getProgresso() == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        ClienteProgressoResponseDTO clienteProgressoDTO = ClienteProgressoResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .registros(String.valueOf(cliente.getProgresso().getRegistros()))
                .premiosRecebidos(exibiListaPremios.exibir(cliente))
                .porcentagemRegistros(String.valueOf(cliente.getProgresso().getPorcentagemRegistros()))
                .build();
        return ResponseEntity.ok(clienteProgressoDTO);
    }

    @PostMapping("/adicionar-registro/{clienteId}")
    public ResponseEntity<String> adicionarRegistroDeUmCliente(@PathVariable String clienteId) {

        adicionarRegistroProgresso.adicionarRegistro(clienteId);

        atualizarNivelCliente.atualizarNivel(clienteId);

        return ResponseEntity.ok("Registro adicionado com sucesso");


    }
}
