package br.com.mavidsmile.mavidsmile.gateways;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteAmigoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<ClienteGETResponseDTO>> exibiTodosOsClientes() {
        // Busca todos os clientes do banco de dados
        List<Cliente> clientes = clienteRepository.findAll();

        // Mapeia os clientes para ClienteGETResponseDTO
        List<ClienteGETResponseDTO> clientesDTO = clientes.stream()
                .map(cliente -> {
                    // Mapeia os prêmios recebidos para PremioDTO
                    List<PremioDTO> premiosDTO = cliente.getProgresso().getPremiosRecebidos().stream()
                            .map(progressoPremio -> PremioDTO.builder()
                                    .nomePremio(progressoPremio.getPremio().getNomePremio())
                                    .descricaoPremio(progressoPremio.getPremio().getDescricaoPremio())
                                    .fotosNecessarias(progressoPremio.getPremio().getFotosNecessarias())
                                    .build())
                            .collect(Collectors.toList());

                    // Constrói o ClienteGETResponseDTO
                    return ClienteGETResponseDTO.builder()
                            .nomeCompleto(cliente.getNomeCompleto())
                            .email(cliente.getEmail())
                            .nomeNivel(cliente.getNivel().getNomeNivel())
                            .premiosRecebidos(premiosDTO) // Passa a lista de prêmios
                            .build();
                })
                .collect(Collectors.toList());

        // Retorna a lista de clientes no formato DTO
        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("/progresso/{clienteId}")
    public ResponseEntity<ClienteProgressoResponseDTO> exibiProgressoDeUmCliente (@PathVariable String clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        List<PremioDTO> premioDTO = cliente.getProgresso().getPremiosRecebidos().stream()
                .map(premio -> PremioDTO.builder()
                        .nomePremio(premio.getPremio().getNomePremio())
                        .descricaoPremio(premio.getPremio().getDescricaoPremio())
                        .fotosNecessarias(premio.getPremio().getFotosNecessarias())
                        .build())
                .collect(Collectors.toList());

        ClienteProgressoResponseDTO clienteProgressoDTO = ClienteProgressoResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .registros(String.valueOf(cliente.getProgresso().getRegistros()))
                .premiosRecebidos(premioDTO)
                .porcentagemRegistros(String.valueOf(cliente.getProgresso().getPorcentagemRegistros()))
                .build();

        return ResponseEntity.ok(clienteProgressoDTO);

    }


}
