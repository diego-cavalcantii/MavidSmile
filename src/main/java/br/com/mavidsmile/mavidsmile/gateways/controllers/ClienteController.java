package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.*;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ConverteClienteEmDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {


    private final ConverteClienteEmDTO converteClienteEmDTO;
    private final BuscarClientes buscarClientes;
    private final ClienteRepository clienteRepository;


    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> exibiTodosOsClientes() {
        List<Cliente> clientes = buscarClientes.buscarTodos();

        List<ClienteResponseDTO> clientesDTO = clientes.stream()
                .map(converteClienteEmDTO::ClienteResponseDTO)
                .toList();

        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteResponseDTO> exibiUmCliente(@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        ClienteResponseDTO clienteDTO = converteClienteEmDTO.ClienteResponseDTO(cliente);

        return ResponseEntity.ok(clienteDTO);
    }



    @DeleteMapping("/{clienteId}")
    public ResponseEntity<String> deletarUmCliente(@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        clienteRepository.delete(cliente);

        return ResponseEntity.ok("Cliente deletado com sucesso");
    }

}
