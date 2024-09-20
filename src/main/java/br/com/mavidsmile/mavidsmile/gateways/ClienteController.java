package br.com.mavidsmile.mavidsmile.gateways;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<ClienteGETResponseDTO> getAllClientes() {
        // Busca todos os clientes do banco de dados
        List<Cliente> clientes = clienteRepository.findAll();

        // Converte cada cliente em um DTO
        return clientes.stream()
                .map(cliente -> ClienteGETResponseDTO.builder()
                        .nome_nivel(cliente.getNivel().getNome_nivel())  // Extrai apenas o nome do nível
                        .progresso(cliente.getProgresso())
                        .nome_completo(cliente.getNome_completo())
                        .email(cliente.getEmail())
                        .endereco(cliente.getEndereco())
                        .build())
                .collect(Collectors.toList());
    }
}
