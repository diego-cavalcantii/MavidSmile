package br.com.mavidsmile.mavidsmile.domains;

import java.util.List;

public class ClienteMock {

    public static Cliente criarCliente() {
        Progresso progresso = Progresso.builder().idProgresso("1").build();
        Nivel nivel = Nivel.builder().idNivel("2").nomeNivel("Nivel 1").pontosNecessarios(100).premio(Premio.builder().idPremio("1").descricaoPremio("Escova de Dentes").nomePremio("Escova de Dentes").build()).build();

        return Cliente.builder()
                .idCliente("201")
                .progresso(progresso)
                .nivel(nivel)
                .nomeCompleto("João Silva")
                .email("joao.silva@gmail.com")
                .endereco("Rua das Flores")
                .imgSrc("img.jpg")
                .build();
    }
}
