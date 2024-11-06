package br.com.mavidsmile.mavidsmile.gateways.controllers;


import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Premio;
import br.com.mavidsmile.mavidsmile.gateways.response.NivelResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarNiveis;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ConverteNivelEmDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nivel")
@RequiredArgsConstructor
public class NivelController {

    private final BuscarNiveis buscarNiveis;
    private final ConverteNivelEmDTO converteNivelEmDTO;


    @GetMapping
    public ResponseEntity<List<NivelResponseDTO>> exibiTodosOsPremios() {
        List<Nivel> niveis = buscarNiveis.buscarTodosOsNiveis();

        List<NivelResponseDTO> niveisDto = niveis.stream()
                .map(converteNivelEmDTO::nivelResponseDTO)
                .toList();

        return ResponseEntity.ok(niveisDto);
    }
}
