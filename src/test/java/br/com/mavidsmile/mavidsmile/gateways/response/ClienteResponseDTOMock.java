package br.com.mavidsmile.mavidsmile.gateways.response;

import java.util.List;

public class ClienteResponseDTOMock {

    public static ClienteResponseDTO criarClienteResponseDTO() {
        return ClienteResponseDTO.builder()
                .nomeNivel("Nivel 1")
                .nomeCompleto("João Silva")
                .email("joao.silva@gmail.com")
                .endereco("Rua das Flores")
                .pontos(100)
                .imgSrc("img.jpg")
                .premiosRecebidos(List.of(new PremioClienteResponseDTO("1", "Premio 1")))
                .build();
    }
}
