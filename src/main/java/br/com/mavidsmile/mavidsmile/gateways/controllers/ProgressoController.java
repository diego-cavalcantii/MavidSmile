package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.ProgressoNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
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
@RequestMapping("/progresso")
@RequiredArgsConstructor
public class ProgressoController {

    private final AdicionarRegistroProgresso adicionarRegistroProgresso;
    private final ExibiClienteDTO exibiClienteDTO;
    private final BuscarClientes buscarClientes;

    @GetMapping()
    public ResponseEntity<List<ClienteRankingResponseDTO>> exibiRankingGeralDosClientes() {

        List<Cliente> amigosCliente = buscarClientes.buscarClientesPorRankingDePontos();


        List<ClienteRankingResponseDTO> clientesDTO = amigosCliente.stream()
                .map(exibiClienteDTO::transformarClienteRankingDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(clientesDTO);
    }

    @ResponseStatus
    @GetMapping("{clienteId}")
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
}
