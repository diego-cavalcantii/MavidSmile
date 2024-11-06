package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Premio;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ConvertePremioEmDTO;
import org.springframework.stereotype.Service;


@Service
public class ConvertePremioEmDTOImpl implements ConvertePremioEmDTO {
    @Override
    public PremioClienteResponseDTO PremioClienteResponseDTO(Premio premio) {
        return PremioClienteResponseDTO.builder()
                .nomePremio(premio.getNomePremio())
                .descricaoPremio(premio.getDescricaoPremio())
                .build();
    }
}
