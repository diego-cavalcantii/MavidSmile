package br.com.mavidsmile.mavidsmile.usecases.interfaces;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteAmizadeResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteRankingResponseDTO;

public interface ConverteClienteEmDTO {
    ClienteResponseDTO ClienteResponseDTO(Cliente cliente);

    ClienteProgressoResponseDTO ClienteProgressoDTO(Cliente cliente);

    ClienteRankingResponseDTO ClienteRankingDTO(Cliente cliente);

    ClienteAmizadeResponseDTO ClienteAmizadeDTO(Cliente cliente);
}
