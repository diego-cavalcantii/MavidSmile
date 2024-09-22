package br.com.mavidsmile.mavidsmile.gateways;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioDTO;
import br.com.mavidsmile.mavidsmile.usecases.ExibiListaPremios;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/amigos")
@RequiredArgsConstructor
public class AmigosController {

    private final ClienteRepository clienteRepository;
    private final ExibiListaPremios exibiListaPremios;


    @GetMapping("/{clienteId}")
    public ResponseEntity<List<ClienteGETResponseDTO>> exibiOsAmigosDeUmCliente(@PathVariable String clienteId) {
        // Busca o cliente pelo id
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        // Extrai a lista de amigos do cliente
        List<ClienteGETResponseDTO> amigosDTO = cliente.getAmigos().stream()
                .map(amigo -> {
                    Cliente amigoCliente = amigo.getClienteIdEhAmigo();  // O cliente que é o amigo
                    return ClienteGETResponseDTO.builder()
                            .nomeCompleto(amigoCliente.getNomeCompleto())
                            .email(amigoCliente.getEmail())
                            .nomeNivel(amigoCliente.getNivel().getNomeNivel())
                            .email(amigoCliente.getEmail())
                            .premiosRecebidos(exibiListaPremios.exibir(amigoCliente)) // Passa a lista de prêmios
                            .build();
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(amigosDTO);
    }
}
