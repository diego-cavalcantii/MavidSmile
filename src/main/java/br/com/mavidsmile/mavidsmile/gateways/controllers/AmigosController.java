package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.Amigos;
import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.AmigosNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.AmigosRepository;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.requests.AdicionarAmigoRequestDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final OrdenarListaPorPontos ordenarListaPorPontos;
    private final RemoverAmizade removerAmizade;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<ClienteGETResponseDTO>> exibiOsAmigosDeUmCliente(@PathVariable String clienteId) {
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        if(cliente.getAmigos().isEmpty()) {
            throw new AmigosNotFoundException("Nenhum amigo encontrado");
        }

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

        adicionarAmigo.executa(requestDTO);

        return ResponseEntity.ok("Amigo adicionado com sucesso");
    }

    @GetMapping("/ranking/{clienteId}")
    public ResponseEntity<List<ClienteRankingResponseDTO>> exibirRankingDeAmigos(@PathVariable String clienteId){
        Cliente cliente = buscarClientes.buscarPorId(clienteId);

        if(cliente.getAmigos().isEmpty()) {
            throw new AmigosNotFoundException("Nenhum amigo encontrado");
        }

        List<ClienteRankingResponseDTO> listaDeAmigos = cliente.getAmigos().stream()
                .map(amigo -> {
                    Cliente amigoCliente = amigo.getClienteIdEhAmigo();
                    return exibiClienteDTO.transformarClienteRankingDTO(amigoCliente);
                })
                .collect(Collectors.toList());

        listaDeAmigos.add(exibiClienteDTO.transformarClienteRankingDTO(cliente));

        List<ClienteRankingResponseDTO> listaDeaAmigosOrdenadosPorPontos = ordenarListaPorPontos.executa(listaDeAmigos);


        return  ResponseEntity.ok(listaDeaAmigosOrdenadosPorPontos);

    }

    @DeleteMapping("/remover/{clienteId}/{amigoId}")
    public ResponseEntity<String> removerAmizade(@PathVariable String clienteId, @PathVariable String amigoId) {

        removerAmizade.executa(clienteId, amigoId);

        return ResponseEntity.ok("Amizade removida com sucesso");
    }

}
