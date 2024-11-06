package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Premio;
import br.com.mavidsmile.mavidsmile.gateways.response.NivelResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ConverteNivelEmDTO;
import org.springframework.stereotype.Service;


@Service
public class ConverteNivelEmDTOImpl implements ConverteNivelEmDTO {
    @Override
    public NivelResponseDTO nivelResponseDTO(Nivel nivel) {
        return NivelResponseDTO.builder()
                .nomeNivel(nivel.getNomeNivel())
                .pontosNecessarios(nivel.getPontosNecessarios())
                .premio(PremioClienteResponseDTO.builder()
                        .nomePremio(nivel.getPremio().getNomePremio())
                        .descricaoPremio(nivel.getPremio().getDescricaoPremio())
                        .build())
                .build();
    }
}
