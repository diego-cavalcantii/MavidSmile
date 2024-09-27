# API - MavidSmile

## Integrantes do Grupo

- **RM553351 - Diego Cavalcanti** - Responsável pela criação da aplicação
- **RM553352 - Mateus Galeazi** - Responsável pela documentação
- **RM553483 - Vitor de Melo** - Responsável pelos diagramas e o banco

## Instruções de Como Rodar a Aplicação

**INSERTS DO BANCO DE DADOS**

**Insira nesta ordem**

-- Prêmios

INSERT INTO `premio`(`descricao_premio`, `id_premio`, `nome_premio`) VALUES ('Kit de escova de dente', '1', 'Kit Escova');
INSERT INTO `premio`(`descricao_premio`, `id_premio`, `nome_premio`) VALUES ('Creme dental 100g', '2', 'Creme Dental');
INSERT INTO `premio`(`descricao_premio`, `id_premio`, `nome_premio`) VALUES ('Fio dental 50m', '3', 'Fio Dental');
INSERT INTO `premio`(`descricao_premio`, `id_premio`, `nome_premio`) VALUES ('Enxaguante bucal 500ml', '4', 'Enxaguante Bucal');
INSERT INTO `premio`(`descricao_premio`, `id_premio`, `nome_premio`) VALUES ('Kit clareador dental', '5', 'Kit Clareador');

-- Niveis

INSERT INTO `nivel`(`pontos_necessarios`, `id_nivel`, `id_premio`, `nome_nivel`) VALUES ('100', '101', '1', 'Bafo de Bosta');  -- Pior nível
INSERT INTO `nivel`(`pontos_necessarios`, `id_nivel`, `id_premio`, `nome_nivel`) VALUES ('2000', '102', '2', 'Gengiva Sangrenta');
INSERT INTO `nivel`(`pontos_necessarios`, `id_nivel`, `id_premio`, `nome_nivel`) VALUES ('3000', '103', '3', 'Cárie Assassina');
INSERT INTO `nivel`(`pontos_necessarios`, `id_nivel`, `id_premio`, `nome_nivel`) VALUES ('4500', '104', '4', 'Raiz Nervosa');
INSERT INTO `nivel`(`pontos_necessarios`, `id_nivel`, `id_premio`, `nome_nivel`) VALUES ('7000', '105', '5', 'Dentes Branquinhos');  -- Melhor nível

-- Clientes

INSERT INTO `cliente`(`email`, `endereco`, `nome_completo`, `id_cliente`) VALUES ('joao.silva@gmail.com', 'Rua das Flores, 123', 'João Silva', '201');
INSERT INTO `cliente`(`email`, `endereco`, `nome_completo`, `id_cliente`) VALUES ('maria.oliveira@gmail.com', 'Avenida Brasil, 456', 'Maria Oliveira', '202');
INSERT INTO `cliente`(`email`, `endereco`, `nome_completo`, `id_cliente`) VALUES ('carlos.santos@gmail.com', 'Rua dos Pinheiros, 789', 'Carlos Santos', '203');
INSERT INTO `cliente`(`email`, `endereco`, `nome_completo`, `id_cliente`) VALUES ('ana.costa@gmail.com', 'Travessa do Sol, 101', 'Ana Costa', '204');
INSERT INTO `cliente`(`email`, `endereco`, `nome_completo`, `id_cliente`) VALUES ('pedro.almeida@gmail.com', 'Alameda das Palmeiras, 202', 'Pedro Almeida', '205');


---

## Diagramas

Aqui você deve inserir os dois diagramas.

1. Diagrama de Classes:
   ![Diagrama da Arquitetura](diagramas/diagrama%20de%20classe.jpeg)

2. Diagrama Entidade Relacionamento:
   ![Diagrama do Banco de Dados](diagramas/diagrama%20entidade%20relacionamento.png)

---

## Link do Vídeo

Aqui você deve adicionar o link para o vídeo explicando a aplicação.

[Link do vídeo](URL_DO_VIDEO_AQUI)

---

## Endpoints

### Clientes
- `GET /clientes` - Retorna todos os clientes.
- `GET /clientes/{id}` - Retorna um cliente específico por ID.


- `DELETE /clientes/{id}` - Remove um cliente.

### Amizades
- `GET /amigos/{id}` - Retorna os amigos de um cliente.
- `POST /amigos/adicionar` - Adiciona uma amizade entre um cliente que é amigo e que tem amigo.
- `GET /amigos/ranking/{id}` - Retorna o ranking de progresso dos amigos de um cliente.
- `DELETE /remover/{clienteId}/{amigoId}` - Remove uma amizade.

### Progresso
- `GET /progresso` - Exibe o ranking do progresso de todos os clientes.
- `GET /progresso/{id}` - Retorna um o progresso de um cliente especifico por ID.
- `POST /clientes/adicionar-registro/{id}` - Cria um progresso para o cliente adicionando um cliente, caso ja tenha adiciona mais um registro.


---

## Tecnologias Utilizadas

- **Java 11/17**
- **Spring Boot**
- **MySQL**
- **Hibernate/JPA**
- **Lombok**
- **Postman** (para testar a API)
- **Maven** (para gerenciamento de dependências)
