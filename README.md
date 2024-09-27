# API - MavidSmile

## Integrantes do Grupo

- **RM553351 - Diego Cavalcanti** - Responsável pela criação da aplicação
- **RM553352 - Mateus Galeazi** - Responsável pela documentação
- **RM553483 - Vitor de Melo** - Responsável pelos diagramas e o banco

## Instruções de Como Rodar a Aplicação

**Aqui você irá preencher com as instruções detalhadas de como configurar e rodar a aplicação.**

Exemplo de coisas a considerar para adicionar:
- Requisitos do ambiente (Java, Spring Boot, MySQL, etc.).
- Passos para configurar o banco de dados (comandos de migração ou scripts SQL).
- Comando para rodar a aplicação localmente.
- Comando para rodar os testes.

---

## Diagramas

Aqui você deve inserir os dois diagramas.

1. Diagrama da Arquitetura:
   ![Diagrama da Arquitetura](CAMINHO/DO/DIAGRAMA1.png)

2. Diagrama do Banco de Dados:
   ![Diagrama do Banco de Dados](CAMINHO/DO/DIAGRAMA2.png)

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
