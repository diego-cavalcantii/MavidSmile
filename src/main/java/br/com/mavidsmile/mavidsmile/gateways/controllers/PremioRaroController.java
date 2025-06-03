package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.PremioRaro;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioRaroResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarPremiosRaros;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/premio-raro")
@RequiredArgsConstructor
public class PremioRaroController {

    private final BuscarPremiosRaros buscarPremiosRaros;

    @GetMapping
    public ResponseEntity<List<PremioRaroResponseDTO>> exibiTodosOsPremiosRaros() {
        List<PremioRaro> premios = buscarPremiosRaros.buscarTodos();
        List<PremioRaroResponseDTO> dtos = premios.stream()
                .map(premio -> PremioRaroResponseDTO.builder()
                        .nomePremioRaro(premio.getNomePremioRaro())
                        .descricaoPremioRaro(premio.getDescricaoPremioRaro())
                        .build())
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
