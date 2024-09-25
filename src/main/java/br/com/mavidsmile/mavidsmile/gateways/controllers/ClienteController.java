package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.*;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
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

    private final AdicionarRegistroProgresso adicionarRegistroProgresso;
    private final AtualizarNivelCliente atualizarNivelCliente;
    private final ExibiClienteDTO exibiClienteDTO;
    private final BuscarClientes buscarClientes;


    @GetMapping
    public ResponseEntity<List<ClienteGETResponseDTO>> exibiTodosOsClientes() {
        List<Cliente> clientes = buscarClientes.buscarTodos();

        List<ClienteGETResponseDTO> clientesDTO = clientes.stream()
                .map(exibiClienteDTO::transformarClienteGetDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteGETResponseDTO> exibiUmCliente(@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        if(cliente == null){
            return ResponseEntity.notFound().build();
        }
        ClienteGETResponseDTO clienteDTO = exibiClienteDTO.transformarClienteGetDTO(cliente);

        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<ClienteRankingResponseDTO>> exibiRankingClientes() {
        List<Cliente> clientes = buscarClientes.buscarClientesPorRankingDeRegistros();


        List<ClienteRankingResponseDTO> clientesDTO = clientes.stream()
                .map(exibiClienteDTO::transformarClienteRankingDTO)
                .collect(Collectors.toList());

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

        ClienteProgressoResponseDTO clienteProgressoDTO = exibiClienteDTO.transformarClienteProgressoDTO(cliente);
        return ResponseEntity.ok(clienteProgressoDTO);
    }


    @PostMapping("/adicionar-registro/{clienteId}")
    public ResponseEntity<String> adicionarRegistroDeUmCliente(@PathVariable String clienteId) {

        adicionarRegistroProgresso.adicionarRegistro(clienteId);

        return ResponseEntity.ok("Registro adicionado com sucesso");

    }

}
