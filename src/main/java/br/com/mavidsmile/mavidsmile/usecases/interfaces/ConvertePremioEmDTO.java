package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Premio;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioClienteResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ConvertePremioEmDTO {

    PremioClienteResponseDTO PremioClienteResponseDTO(Premio premio);
}
