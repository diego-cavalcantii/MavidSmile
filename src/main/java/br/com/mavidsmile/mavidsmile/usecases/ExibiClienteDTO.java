package br.com.mavidsmile.mavidsmile.usecases;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;

public interface ExibiClienteDTO {
    ClienteGETResponseDTO tranformarClienteEmDTO(Cliente cliente);
}
