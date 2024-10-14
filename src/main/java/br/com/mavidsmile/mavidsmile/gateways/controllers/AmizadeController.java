package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.AmizadeNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmizadeRequestDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.AmizadeResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteAmizadeResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/amizade")
@RequiredArgsConstructor
public class AmizadeController {

    private final AdicionarAmizade adicionarAmizade;
    private final BuscarClientes buscarClientes;
    private final ConverteClienteEmDTO converteClienteEmDTO;
    private final OrdenarListaPorPontos ordenarListaPorPontos;
    private final RemoverAmizade removerAmizade;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<ClienteAmizadeResponseDTO>> exibiOsAmigosDeUmCliente(@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        List<ClienteAmizadeResponseDTO> amigosDTO = buscarClientes.buscarAmigosDeUmCliente(cliente).stream()
                .map(amigo -> {
                    Cliente amigoCliente = amigo.getClienteIdEhAmigo();
                    return converteClienteEmDTO.ClienteAmizadeDTO(amigoCliente);
                })
                .toList();

        return ResponseEntity.ok(amigosDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/adicionar")
    public ResponseEntity<AmizadeResponseDTO> adicionarUmAmigo(@RequestBody @Valid AdicionarAmizadeRequestDTO requestDTO)
    {

        Cliente cliente = buscarClientes.buscarPorId(requestDTO.clienteIdTemAmigo());
        Cliente amigo = buscarClientes.buscarPorId(requestDTO.clienteIdEhAmigo());

        AmizadeResponseDTO amizade = adicionarAmizade.executa(cliente, amigo);

        Link link = linkTo(AmizadeController.class).slash(cliente.getIdCliente()).withSelfRel();

        amizade.add(link);

        return ResponseEntity.status(HttpStatus.CREATED).body(amizade);
    }

    @GetMapping("/ranking/{clienteId}")
    public ResponseEntity<List<ClienteRankingResponseDTO>> exibirRankingDeAmigos(@PathVariable String clienteId){
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        // Captura a lista de amigos do cliente e converte em DTO
        List<ClienteRankingResponseDTO> listaDeAmigos = buscarClientes.buscarAmigosDeUmCliente(cliente).stream()
                .map(amigo -> {
                    Cliente amigoCliente = amigo.getClienteIdEhAmigo();
                    return converteClienteEmDTO.ClienteRankingDTO(amigoCliente);
                })
                .collect(Collectors.toList());

        // Adiciona o cliente na lista de amigos
        listaDeAmigos.add(converteClienteEmDTO.ClienteRankingDTO(cliente));

        // Ordena a lista de amigos por pontos
        List<ClienteRankingResponseDTO> listaDeaAmigosOrdenadosPorPontos = ordenarListaPorPontos.executa(listaDeAmigos);


        return  ResponseEntity.ok(listaDeaAmigosOrdenadosPorPontos);

    }

    @DeleteMapping("/remover/{clienteId}/{amigoId}")
    public ResponseEntity<String> removerAmizade(@PathVariable String clienteId, @PathVariable String amigoId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);
        Cliente amigo = buscarClientes.buscarPorId(amigoId);

        removerAmizade.executa(cliente, amigo);

        return ResponseEntity.ok("Amizade removida com sucesso");
    }



}
