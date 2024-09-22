package br.com.mavidsmile.mavidsmile.gateways;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.Premio;
import br.com.mavidsmile.mavidsmile.domains.Progresso;
import br.com.mavidsmile.mavidsmile.domains.ProgressoPremio;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteAmigoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteGETResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteProgressoResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioDTO;
import br.com.mavidsmile.mavidsmile.usecases.ExibiListaPremios;
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
    private final ExibiListaPremios exibiListaPremios;
    private final ProgressoRepository progressoRepository;
    private final PremioRepository premioRepository;
    private final ProgressoPremioRepository progressoPremioRepository;


    @GetMapping
    public ResponseEntity<List<ClienteGETResponseDTO>> exibiTodosOsClientes() {
        // Busca todos os clientes do banco de dados
        List<Cliente> clientes = clienteRepository.findAll();

        // Mapeia os clientes para ClienteGETResponseDTO
        List<ClienteGETResponseDTO> clientesDTO = clientes.stream()
                .map(cliente -> {
                    // Constrói o ClienteGETResponseDTO
                    return ClienteGETResponseDTO.builder()
                            .nomeCompleto(cliente.getNomeCompleto())
                            .email(cliente.getEmail())
                            .nomeNivel(cliente.getNivel() != null ? cliente.getNivel().getNomeNivel() : "Nível não definido")
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
        ClienteProgressoResponseDTO clienteProgressoDTO = ClienteProgressoResponseDTO.builder()
                .nomeCompleto(cliente.getNomeCompleto())
                .registros(String.valueOf(cliente.getProgresso().getRegistros()))
                .premiosRecebidos(exibiListaPremios.exibir(cliente))
                .porcentagemRegistros(String.valueOf(cliente.getProgresso().getPorcentagemRegistros()))
                .build();

        return ResponseEntity.ok(clienteProgressoDTO);
    }

    @PostMapping("/adicionar-registro/{clienteId}")
    public ResponseEntity<String> adicionarRegistroDeUmCliente(@PathVariable String clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        if(cliente.getProgresso() != null){
            cliente.getProgresso().setRegistros(cliente.getProgresso().getRegistros() + 1);
            progressoRepository.save(cliente.getProgresso());

            Progresso progressoCliente = cliente.getProgresso();


            List<Premio> premios = premioRepository.findAll();
            for (Premio premio : premios) {
                if (progressoCliente.getRegistros() == premio.getFotosNecessarias()) {
                    ProgressoPremio progressoPremio = ProgressoPremio.builder().premio(premio).progresso(progressoCliente).build();

                    cliente.setNivel(premio.getNivel());

                    clienteRepository.save(cliente);
                    progressoPremioRepository.save(progressoPremio);

                }
            }

            return ResponseEntity.ok("Registro adicionado com sucesso");

        }

        Progresso novoProgresso = Progresso.builder().registros(1).build();
        novoProgresso = progressoRepository.save(novoProgresso);

        cliente.setProgresso(novoProgresso);
        clienteRepository.save(cliente);



        return ResponseEntity.ok("Registro adicionado com sucesso");
    }
}
