package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.*;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.ProgressoNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.AdicionarRegistroProgresso;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ExibiClienteDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final AdicionarRegistroProgresso adicionarRegistroProgresso;
    private final ExibiClienteDTO exibiClienteDTO;
    private final BuscarClientes buscarClientes;
    private final ClienteRepository clienteRepository;


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

        ClienteGETResponseDTO clienteDTO = exibiClienteDTO.transformarClienteGetDTO(cliente);

        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<ClienteRankingResponseDTO>> exibiRankingGeralDosClientes() {

        List<Cliente> amigosCliente = buscarClientes.buscarClientesPorRankingDePontos();


        List<ClienteRankingResponseDTO> clientesDTO = amigosCliente.stream()
                .map(exibiClienteDTO::transformarClienteRankingDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientesDTO);
    }

    @ResponseStatus
    @GetMapping("/progresso/{clienteId}")
    public ResponseEntity<ClienteProgressoResponseDTO> exibiProgressoDeUmCliente (@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        if(cliente.getProgresso() == null){
            throw new ProgressoNotFoundException("Progresso do cliente com ID " + clienteId + " não encontrado");
        }

        ClienteProgressoResponseDTO clienteProgressoDTO = exibiClienteDTO.transformarClienteProgressoDTO(cliente);
        return ResponseEntity.ok(clienteProgressoDTO);
    }


    @PostMapping("/adicionar-registro/{clienteId}")
    public ResponseEntity<String> adicionarRegistroDeUmCliente(@PathVariable @Valid String clienteId) {

        adicionarRegistroProgresso.adicionarRegistro(clienteId);

        return ResponseEntity.ok("Registro adicionado com sucesso");

    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<String> deletarUmCliente(@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        clienteRepository.delete(cliente);

        return ResponseEntity.ok("Cliente deletado com sucesso");
    }

}
