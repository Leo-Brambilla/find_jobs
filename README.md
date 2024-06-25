# Find Jobs Application

## Configuração do Banco de Dados

1. Instale o PostgreSQL.
2. Crie um banco de dados com as propriedades contidas no `application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/find_jobs
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Atualize as propriedades no arquivo `application.properties` com suas credenciais do PostgreSQL.

## Executando o Backend

1. Importe o projeto em sua IDE de preferência (IntelliJ, Eclipse, etc.).
2. Execute a aplicação através da classe principal `FindJobsApplication`.

## Executando o Frontend

1. Navegue até o diretório do frontend do projeto.
2. Instale as dependências com `npm install`.
3. Execute o comando `ng serve` para rodar a aplicação Angular.
4. Acesse `http://localhost:4200` em seu navegador.

## Endpoints Disponíveis

### Autenticação
- `POST /auth/login`: Login de usuário.
- `POST /auth/signup`: Cadastro de usuário.

### Vagas
- `GET /jobs`: Listar todas as vagas.
- `GET /jobs/search`: Buscar vagas por critérios.
- `GET /jobs/{id}`: Buscar vaga por ID.
- `POST /jobs`: Criar uma nova vaga (ADMIN).
- `PUT /jobs/{id}`: Atualizar vaga (ADMIN).
- `DELETE /jobs/{id}`: Deletar vaga (ADMIN).

### Candidaturas
- `POST /applications`: Candidatar-se a uma vaga.
- `GET /applications/user/{userId}`: Listar candidaturas de um usuário.
- `GET /applications/job/{jobId}`: Listar candidaturas de uma vaga.

### Usuários
- `GET /users`: Listar todos os usuários.
- `GET /users/{id}`: Buscar usuário por ID.
- `PUT /users/{id}`: Atualizar usuário.
- `DELETE /users/{id}`: Deletar usuário.

## Testando a API

### Usando Postman

1. **Login**
    - Endpoint: `POST /auth/login`
    - Body:
    ```json
    {
        "username": "seu_usuario",
        "password": "sua_senha"
    }
    ```

2. **Cadastro**
    - Endpoint: `POST /auth/signup`
    - Body:
    ```json
    {
        "username": "novo_usuario",
        "password": "nova_senha"
    }
    ```
    - Query Param: `role=USER` ou `role=ADMIN`

3. **Criar Vaga**
    - Endpoint: `POST /jobs`
    - Headers: `Authorization: Bearer {seu_token}`
    - Body:
    ```json
    {
        "jobTitle": "Desenvolvedor Java",
        "jobRequisitions": "Spring Boot, REST API",
        "numberOfJobs": 3,
        "publicationDate": "2024-06-25T12:00:00",
        "jobDescription": "Desenvolvimento de APIs em Spring Boot",
        "jobStatus": "ABERTO",
        "jobType": "SENIOR",
        "jobLocation": "Remoto"
    }
    ```

4. **Listar Vagas**
    - Endpoint: `GET /jobs`
    - Headers: `Authorization: Bearer {seu_token}`

5. **Atualizar Vaga**
    - Endpoint: `PUT /jobs/{id}`
    - Headers: `Authorization: Bearer {seu_token}`
    - Body:
    ```json
    {
        "jobTitle": "Desenvolvedor Java",
        "jobRequisitions": "Spring Boot, REST API",
        "numberOfJobs": 3,
        "publicationDate": "2024-06-25T12:00:00",
        "jobDescription": "Desenvolvimento de APIs em Spring Boot",
        "jobStatus": "ABERTO",
        "jobType": "SENIOR",
        "jobLocation": "Remoto"
    }
    ```

6. **Deletar Vaga**
    - Endpoint: `DELETE /jobs/{id}`
    - Headers: `Authorization: Bearer {seu_token}`

## Considerações Finais

- O frontend possui funcionalidades de login e gerenciamento de vagas dependendo do papel do usuário.
- O usuário com papel "ADMIN" pode criar, editar, listar e deletar vagas.
- O usuário com papel "USER" pode apenas listar as vagas.
