package br.com.mavidsmile.mavidsmile.usecases.impl;

import br.com.mavidsmile.mavidsmile.domains.Amizade;
import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.exceptions.AmizadeNotFoundException;
import br.com.mavidsmile.mavidsmile.gateways.repositories.AmizadeRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.AmizadeResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteAmizadeResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.AdicionarAmizade;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdicionarAmizadeImpl implements AdicionarAmizade {


    private final AmizadeRepository amizadeRepository;
    private final BuscarClientes buscarClientes;

    @Override
    public AmizadeResponseDTO executa(Cliente cliente, Cliente amigo) {

        if(cliente.equals(amigo)) {
            throw new AmizadeNotFoundException("Não é possível adicionar você mesmo como amigo");
        }

        boolean amizadeExistente = amizadeRepository.existsByClienteIdTemAmigoAndClienteIdEhAmigo(cliente, amigo);

        if (amizadeExistente) {
            throw new AmizadeNotFoundException("Amizade já existe entre os clientes");
        }

        Amizade novoAmigo = Amizade.builder()
                .clienteIdTemAmigo(cliente)
                .clienteIdEhAmigo(amigo)
                .build();

        amizadeRepository.save(novoAmigo);
//        return novoAmigo;

        ClienteAmizadeResponseDTO clienteResponseDTO = ClienteAmizadeResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .email(cliente.getEmail())
                .pontos(cliente.getProgresso() != null ? cliente.getProgresso().getPontos() : 0)
                .nomeNivel(cliente.getNivel() != null ? cliente.getNivel().getNomeNivel() : "Nível não definido")
                .build();

        ClienteAmizadeResponseDTO amigoResponseDTO = ClienteAmizadeResponseDTO.builder()
                .nomeCompleto(amigo.getNomeCompleto())
                .email(amigo.getEmail())
                .pontos(amigo.getProgresso() != null ? amigo.getProgresso().getPontos() : 0)
                .nomeNivel(amigo.getNivel() != null ? amigo.getNivel().getNomeNivel() : "Nível não definido")
                .build();

        return AmizadeResponseDTO.builder()
                .clienteTemAmigo(clienteResponseDTO)
                .clienteEhAmigo(amigoResponseDTO)
                .build();
    }
}