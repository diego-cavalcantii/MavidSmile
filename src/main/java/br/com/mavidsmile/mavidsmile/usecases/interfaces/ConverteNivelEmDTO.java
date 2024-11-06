package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Nivel;
import br.com.mavidsmile.mavidsmile.domains.Premio;
import br.com.mavidsmile.mavidsmile.gateways.response.NivelResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioClienteResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ConverteNivelEmDTO {

    NivelResponseDTO nivelResponseDTO(Nivel nivel);
}
