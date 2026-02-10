# 📋 Cadastro API

API REST de Cadastro de Clientes desenvolvida em **Spring Boot 3.4.1** com **Java 21**. A aplicação gerencia clientes e cidades através de endpoints REST com validações e persistência em banco de dados.

## 🏗️ Arquitetura e Estrutura

### Stack Tecnológico
- Spring Boot 3.4.1
- Spring Data JPA (ORM)
- Spring Web (REST API)
- Spring Validation
- HSQLDB (banco em memória)
- Lombok (redução de boilerplate)
- Jakarta Persistence API
- **SpringDoc OpenAPI** (Swagger/OpenAPI 3.0)

### Componentes Principais

#### 1. Modelos (`model/`)
- **Cliente**: Representa um cliente com nome, sexo, data de nascimento, idade e relação com Cidade
- **Cidade**: Representa uma cidade com nome e estado

#### 2. APIs REST (`controller/`)
- **ClienteController**: Endpoints CRUD para clientes
- **CidadeController**: Endpoints CRUD para cidades

#### 3. Serviços (`service/`)
- **ClienteService/ClienteServiceImpl**: Lógica de negócio para clientes
- **CidadeService/CidadeServiceImpl**: Lógica de negócio para cidades

#### 4. Repositórios (`repository/`)
- Acesso a dados via Spring Data JPA

#### 5. Configuração (`config/`)
- **AsyncConfig**: Configuração de processamento assíncrono
- **TransactionFilter**: Filtro de transações
- **MdcTaskDecorator**: Contexto MDC para logs

#### 6. Banco de Dados
- HSQLDB em memória
- Script de inicialização (`data.sql`)

---

## ⚙️ Como Configurar e Rodar

### Pré-requisitos
- **Java 21** (JDK)
- **Maven 3.6+** (ou usar o Maven Wrapper incluído)

### Passo 1: Verificar Java
```powershell
java -version
```
Deve retornar Java 21.x

### Passo 2: Compilar o projeto
```powershell
cd c:\Users\marco\workspace\cadastroapi
mvn clean install
```

Ou usando Maven Wrapper:
```powershell
.\mvnw clean install
```

### Passo 3: Rodar a aplicação

**Opção 1 - Via Maven:**
```powershell
mvn spring-boot:run
```

A aplicação será iniciada no terminal e estará disponível em `http://localhost:8080`

**Opção 2 - Via JAR compilado (após o build):**
```powershell
java -jar target\cadastroapi-0.0.1-SNAPSHOT.jar
```

**Exemplos de endpoints:**
- `GET /cliente` - Listar todos os clientes
- `POST /cliente` - Criar novo cliente
- `GET /cidade` - Listar todas as cidades
- `PUT /cliente/{id}` - Atualizar cliente
- `DELETE /cliente/{id}` - Deletar cliente
- `POST /cidade` - Criar cidade

**Exemplo com cURL:**
```powershell
# Listar clientes
curl.exe http://localhost:8080/cliente

# Criar nova cidade
curl.exe -X POST http://localhost:8080/cidade `
  -H "Content-Type: application/json" `
  -d '{"nome":"Curitiba","estado":"PR"}'
```

---

## 📚 Documentação do Swagger/OpenAPI

### Acessar a Documentação Interativa

A API possui documentação interativa automática gerada pelo Swagger. Acesse:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- **OpenAPI YAML**: [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml)

### Recursos do Swagger

- ✅ Interface gráfica interativa
- ✅ Documentação completa de todos endpoints
- ✅ Modelos de dados e validações
- ✅ Possibilidade de testar requisições diretamente
- ✅ Descrições detalhadas de cada operação
- ✅ Códigos de resposta documentados

---

## 📊 Dados Iniciais

O arquivo `data.sql` popula automaticamente o banco com:
- 3 cidades: Campinas, Cosmopolis, São Paulo (todas em SP)
- 1 cliente de exemplo: Marcos Roberto Martini

---

## 🔧 Configuração do Banco

Arquivo `src/main/resources/application.properties`:
- **Banco**: HSQLDB (em memória)
- **URL**: `jdbc:hsqldb:mem:testdb`
- **DDL Auto**: validate (valida o schema em relação aos modelos)
- **Logs SQL**: Habilitados para debug

## 📋 Arquivos de Inicialização do Banco

- **schema.sql**: Define a estrutura das tabelas (executado automaticamente)
- **data.sql**: Popula dados iniciais (executado automaticamente)

---

## 🔐 Configuração do Swagger/OpenAPI

A documentação da API é gerada automaticamente através da dependência `springdoc-openapi-starter-webmvc-ui` a partir das anotações nos controllers:

**Arquivo de Configuração:**
- [src/main/java/br/com/mmartini/cadastro/config/OpenApiConfig.java](src/main/java/br/com/mmartini/cadastro/config/OpenApiConfig.java)

**Anotações Utilizadas:**
- `@Tag`: Agrupa endpoints por funcionalidade
- `@Operation`: Descreve a operação do endpoint
- `@ApiResponse` / `@ApiResponses`: Documenta códigos de resposta
- `@Parameter`: Descreve parâmetros da requisição

---

## 📝 Validações

O projeto utiliza Jakarta Validation com mensagens personalizadas em `ValidationMessages.properties`:
- Nome completo obrigatório
- Sexo obrigatório
- Idade mínima de 0 anos
- Cidade obrigatória

---

## 🧪 Testes

Execute os testes unitários com:
```powershell
mvn test
```

---

## 📦 Build

Gerar JAR executável:
```powershell
mvn clean package
```

O JAR será gerado em `target/cadastroapi-0.0.1-SNAPSHOT.jar`

---

## 👨‍💻 Desenvolvedor

Marcos Roberto Martini

---

## 📄 Licença

Projeto de demonstração Spring Boot
