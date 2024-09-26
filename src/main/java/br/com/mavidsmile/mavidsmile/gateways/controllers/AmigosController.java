package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.AmigosNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmigoRequestDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.AdicionarAmigo;
import br.com.mavidsmile.mavidsmile.usecases.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.ExibiClienteDTO;
import br.com.mavidsmile.mavidsmile.usecases.ExibiListaPremios;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/amigos")
@RequiredArgsConstructor
public class AmigosController {

    private final AdicionarAmigo adicionarAmigo;
    private final BuscarClientes buscarClientes;
    private final ExibiClienteDTO exibiClienteDTO;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<ClienteGETResponseDTO>> exibiOsAmigosDeUmCliente(@PathVariable String clienteId) {
        // Busca o cliente pelo id
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        if(cliente.getAmigos().isEmpty()) {
            throw new AmigosNotFoundException("Nenhum amigo encontrado");
        }

        // Extrai a lista de amigos do cliente
        List<ClienteGETResponseDTO> amigosDTO = cliente.getAmigos().stream()
                .map(amigo -> {
                    Cliente amigoCliente = amigo.getClienteIdEhAmigo();
                    return exibiClienteDTO.transformarClienteGetDTO(amigoCliente);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(amigosDTO);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarUmAmigo(@RequestBody @Valid AdicionarAmigoRequestDTO requestDTO) {
        adicionarAmigo.adicionarAmigo(requestDTO);
        return ResponseEntity.ok("Amigo adicionado com sucesso");
    }



}
