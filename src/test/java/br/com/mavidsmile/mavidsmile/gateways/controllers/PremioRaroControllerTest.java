package br.com.mavidsmile.mavidsmile.gateways.controllers;

import br.com.mavidsmile.mavidsmile.domains.PremioRaro;
import br.com.mavidsmile.mavidsmile.gateways.response.PremioRaroResponseDTO;
import br.com.mavidsmile.mavidsmile.usecases.interfaces.BuscarPremiosRaros;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PremioRaroControllerTest {

    @InjectMocks
    private PremioRaroController premioRaroController;

    @Mock
    private BuscarPremiosRaros buscarPremiosRaros;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarTodosOsPremiosRaros() {
        PremioRaro premio = PremioRaro.builder()
                .idPremioRaro("1")
                .nomePremioRaro("Premio Incrivel")
                .descricaoPremioRaro("descricao")
                .build();
        when(buscarPremiosRaros.buscarTodos()).thenReturn(List.of(premio));

        ResponseEntity<List<PremioRaroResponseDTO>> response = premioRaroController.exibiTodosOsPremiosRaros();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(buscarPremiosRaros, times(1)).buscarTodos();
    }
}
