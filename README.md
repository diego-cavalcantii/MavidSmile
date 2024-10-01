# API - MavidSmile

## Integrantes do Grupo

- **RM553351 - Diego Cavalcanti** - Responsável pela criação da aplicação
- **RM553352 - Mateus Galeazi** - Responsável pela documentação
- **RM553483 - Vitor de Melo** - Responsável pelos diagramas e o banco

## Instruções de Como Rodar a Aplicação
A aplicação foi desenvolvida utilizando  o MySQL como banco de dados padrão. Para rodar a aplicação, siga os seguintes passos:

**Configurar o Banco de Dados:**

- Certifique-se de que o MySQL está instalado e em execução na sua máquina.
- Crie um banco de dados chamado **mavidsmile** no MySQL.
- Configure as credenciais do banco de dados no arquivo de configuração da aplicação, que está localizado em **src/main/resources/application.yml**.
- Com o banco de dados configurado, basta rodar a aplicação localmente.

**Utilizar Outro Banco de Dados (opcional):**

- Se preferir utilizar outro banco de dados, como PostgreSQL ou H2, você pode alterar as credenciais e a URL de conexão no arquivo de configuração **(application.properties ou application.yml)** de acordo com o banco de dados escolhido.


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
- `GET /clientes/{clienteId}` - Retorna um cliente específico por ID.
- `DELETE /clientes/{clienteId}` - Remove um cliente.

### Progresso
- `POST /progresso/adicionar-registro/{clienteId}` - Cria um progresso para o cliente adicionando um registro, caso ja exista um progresso, adiciona mais um registro.
- `GET /progresso` - Exibe o ranking do progresso de todos os clientes.
- `GET /progresso/{clienteId}` - Retorna um o progresso de um cliente especifico por ID.

- ### Amizades
- `POST /amizade/adicionar` - Adiciona uma amizade entre um cliente que é amigo e que tem amigo.
{
    "clienteIdTemAmigo" : clienteId,
    "clienteIdEhAmigo" : clienteId
 }
- `GET /amizade/{clienteId}` - Retorna os amigos de um cliente.
- `GET /amizade/ranking/{clienteId}` - Retorna o ranking de progresso dos amigos de um cliente.
- `DELETE amizade/remover/{clienteId}/{amigoId}` - Remove uma amizade.



---

## Tecnologias Utilizadas

- **Java 11/17**
- **Spring Boot**
- **MySQL**
- **Hibernate/JPA**
- **Lombok**
- **Postman** (para testar a API)
- **Maven** (para gerenciamento de dependências)
