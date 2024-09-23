package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmigoRequestDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.AdicionarAmigo;
import br.com.mavidsmile.mavidsmile.usecases.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.ExibiListaPremios;
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

    private final ExibiListaPremios exibiListaPremios;
    private final AdicionarAmigo adicionarAmigo;
    private final BuscarClientes buscarClientes;

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> exibiOsAmigosDeUmCliente(@PathVariable String clienteId) {
        // Busca o cliente pelo id
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        if (cliente == null) {
            return new ResponseEntity<String>("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }

        if(cliente.getAmigos() == null || cliente.getAmigos().isEmpty()) {
            return new ResponseEntity<String>("Cliente não possui amigos", HttpStatus.OK);
        }

        // Extrai a lista de amigos do cliente
        List<ClienteGETResponseDTO> amigosDTO = cliente.getAmigos().stream()
                .map(amigo -> {
                    Cliente amigoCliente = amigo.getClienteIdEhAmigo();  // O cliente que é o amigo
                    return ClienteGETResponseDTO.builder()
                            .nomeCompleto(amigoCliente.getNomeCompleto())
                            .email(amigoCliente.getEmail())
                            .nomeNivel(amigoCliente.getNivel().getNomeNivel())
                            .email(amigoCliente.getEmail())
                            .premiosRecebidos(exibiListaPremios.exibir(amigoCliente)) // Passa a lista de prêmios
                            .build();
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(amigosDTO);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarUmAmigo(@RequestBody AdicionarAmigoRequestDTO requestDTO) {
        adicionarAmigo.adicionarAmigo(requestDTO);
        return ResponseEntity.ok("Amigo adicionado com sucesso");
    }



}
