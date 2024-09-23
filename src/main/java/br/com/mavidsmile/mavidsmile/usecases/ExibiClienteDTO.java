package br.com.mavidsmile.mavidsmile.usecases;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;

public interface ExibiClienteDTO {
    ClienteGETResponseDTO transformarClienteGetDTO(Cliente cliente);

    ClienteProgressoResponseDTO transformarClienteProgressoDTO(Cliente cliente);

    ClienteRankingResponseDTO transformarClienteRankingDTO(Cliente cliente);
}
