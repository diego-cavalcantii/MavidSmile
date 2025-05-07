package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.Cliente;
import br.com.mavidsmile.mavidsmile.domains.ClienteMock;
import br.com.mavidsmile.mavidsmile.gateways.repositories.ClienteRepository;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteResponseDTO;
import br.com.mavidsmile.mavidsmile.gateways.response.ClienteResponseDTOMock;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarClientes;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.ConverteClienteEmDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private BuscarClientes buscarClientes;

    @Mock
    private ConverteClienteEmDTO converteClienteEmDTO;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarTodosOsClientes() {
        // Arrange
        Cliente cliente1 = ClienteMock.criarCliente();
        Cliente cliente2 = ClienteMock.criarCliente();

        when(buscarClientes.buscarTodos()).thenReturn(Arrays.asList(cliente1, cliente2));
        when(converteClienteEmDTO.ClienteResponseDTO(cliente1)).thenReturn(ClienteResponseDTOMock.criarClienteResponseDTO());
        when(converteClienteEmDTO.ClienteResponseDTO(cliente2)).thenReturn(ClienteResponseDTOMock.criarClienteResponseDTO());

        // Act
        ResponseEntity<List<ClienteResponseDTO>> response = clienteController.exibiTodosOsClientes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode()); // <--- Validação concreta do statusCode
        assertEquals(2, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
        verify(buscarClientes, times(1)).buscarTodos();
    }

    @Test
    void deveRetornarUmClientePorId() {
        // Arrange
        String clienteId = "201";
        Cliente cliente = ClienteMock.criarCliente();
        ClienteResponseDTO dto = ClienteResponseDTOMock.criarClienteResponseDTO();

        when(buscarClientes.buscarPorId(clienteId)).thenReturn(cliente);
        when(converteClienteEmDTO.ClienteResponseDTO(cliente)).thenReturn(dto);

        // Act
        ResponseEntity<ClienteResponseDTO> response = clienteController.exibiUmCliente(clienteId);

        // Assert
        assertEquals(dto, response.getBody());
        verify(buscarClientes, times(1)).buscarPorId(clienteId);
    }

    @Test
    void deveDeletarUmCliente() {
        // Arrange
        String clienteId = "201";
        Cliente cliente = ClienteMock.criarCliente();

        when(buscarClientes.buscarPorId(clienteId)).thenReturn(cliente);
        doNothing().when(clienteRepository).delete(cliente);

        // Act
        ResponseEntity<String> response = clienteController.deletarUmCliente(clienteId);

        // Assert
        assertEquals("Cliente deletado com sucesso", response.getBody());
        verify(clienteRepository, times(1)).delete(cliente);
    }


    @Test
    void deveRetornarTodosOsClientesComContratoValido() throws Exception {
        // Arrange
        Cliente cliente1 = ClienteMock.criarCliente();
        Cliente cliente2 = ClienteMock.criarCliente();

        ClienteResponseDTO clienteDTO1 = ClienteResponseDTOMock.criarClienteResponseDTO();
        ClienteResponseDTO clienteDTO2 = ClienteResponseDTOMock.criarClienteResponseDTO();

        when(buscarClientes.buscarTodos()).thenReturn(Arrays.asList(cliente1, cliente2));
        when(converteClienteEmDTO.ClienteResponseDTO(cliente1)).thenReturn(clienteDTO1);
        when(converteClienteEmDTO.ClienteResponseDTO(cliente2)).thenReturn(clienteDTO2);

        // Act
        ResponseEntity<List<ClienteResponseDTO>> response = clienteController.exibiTodosOsClientes();

        // Assert 1: statusCode
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert 2: conteúdo do JSON
        assertEquals(2, response.getBody().size());
        ClienteResponseDTO primeiroCliente = response.getBody().get(0);
        assertEquals("João Silva", primeiroCliente.getNomeCompleto());
        assertEquals("joao.silva@gmail.com", primeiroCliente.getEmail());
        assertEquals("Rua das Flores", primeiroCliente.getEndereco());

        // Assert 3: contrato via JSON Schema
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(primeiroCliente);
        JSONObject jsonObject = new JSONObject(json);

        InputStream schemaStream = getClass().getClassLoader().getResourceAsStream("schemas/cliente-schema.json");
        JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
        Schema schema = SchemaLoader.load(rawSchema);
        schema.validate(jsonObject); // Valida o schema

        verify(buscarClientes, times(1)).buscarTodos();
    }
}
