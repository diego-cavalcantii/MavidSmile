package br.com.mavidsmile.mavidsmile.usecases;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExibiListaPremios {

    List<PremioDTO> exibir(Cliente cliente);
}
