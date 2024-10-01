package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.*;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ExibiClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {


    private final ExibiClienteDTO exibiClienteDTO;
    private final BuscarClientes buscarClientes;
    private final ClienteRepository clienteRepository;


    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> exibiTodosOsClientes() {
        List<Cliente> clientes = buscarClientes.buscarTodos();

        List<ClienteResponseDTO> clientesDTO = clientes.stream()
                .map(exibiClienteDTO::transformarClienteGetDTO)
                .toList();

        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteResponseDTO> exibiUmCliente(@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        ClienteResponseDTO clienteDTO = exibiClienteDTO.transformarClienteGetDTO(cliente);

        return ResponseEntity.ok(clienteDTO);
    }



    @DeleteMapping("/{clienteId}")
    public ResponseEntity<String> deletarUmCliente(@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        clienteRepository.delete(cliente);

        return ResponseEntity.ok("Cliente deletado com sucesso");
    }

}
