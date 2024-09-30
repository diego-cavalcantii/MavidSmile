package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;

public interface ExibiClienteDTO {
    ClienteResponseDTO transformarClienteGetDTO(Cliente cliente);

    ClienteProgressoResponseDTO transformarClienteProgressoDTO(Cliente cliente);

    ClienteRankingResponseDTO transformarClienteRankingDTO(Cliente cliente);
}
