package br.com.mavidsmile.mavidsmile.gateways.controllers;


import br.com.mavidsmile.mavidsmile.domains.Premio;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarPremios;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ConvertePremioEmDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/premios")
@RequiredArgsConstructor
public class PremioController {

    private final BuscarPremios buscarPremios;
    private final ConvertePremioEmDTO convertePremioEmDTO;


    @GetMapping
    public ResponseEntity<List<PremioClienteResponseDTO>> exibiTodosOsPremios() {
        List<Premio> premios = buscarPremios.buscarTodosOsPremios();

        List<PremioClienteResponseDTO> premiosDto = premios.stream()
                .map(convertePremioEmDTO::PremioClienteResponseDTO)
                .toList();

        return ResponseEntity.ok(premiosDto);
    }
}
