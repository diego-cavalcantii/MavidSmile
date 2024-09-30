package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExibiListaPremios {

    List<PremioResponseDTO> executa(Cliente cliente);
}
