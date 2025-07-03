# LoginSystem

Sistema de autenticação segura com Spring Boot 3, JWT, MySQL, Flyway e Swagger.

## 📋 Descrição

Este projeto implementa um sistema de cadastro e login de usuários com autenticação JWT, hash de senhas, controle de acesso a páginas privadas, versionamento de banco de dados com Flyway e documentação automática via Swagger.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.3
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- MySQL
- Flyway
- Swagger (SpringDoc OpenAPI)
- Lombok

---

## 📁 Estrutura do Projeto

```
src/main/java/com/project/loginsystem/
├── NomedomoduloApplication.java
├── application/
│ ├── dto/
│ └── service/
├── domain/
│ ├── event/
│ ├── model/
│ └── repository/
├── infrastructure/
│ ├── config/
│ └── repository/
├── adapter/
│ ├── in/controller/
│ └── out/kafka/
```
---

## ⚙️ Configuração e Execução

### 1. **Pré-requisitos**

- Java 17+
- MySQL
- Maven 3.8+

### 2. **Banco de Dados**

Crie um banco chamado `login` no MySQL:

```sql
CREATE DATABASE login CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
O Flyway cuidará da criação da tabela users automaticamente.

### 3. Configuração

Edite o arquivo src/main/resources/application.properties:
```
spring.datasource.url=jdbc:mysql://localhost:3306/login?useSSL=false&serverTimezone=UTC
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true

jwt.secret=SUA_CHAVE_SECRETA
jwt.expiration=3600000

server.port=8080
```

## 4. Build e Execução

```
mvn clean install
mvn spring-boot:run
```

## 🚀 Endpoints Principais

### Endpoints Principais

| Método | Rota            | Descrição                   |
|--------|-----------------|-----------------------------|
| POST   | `/auth/register`| &nbsp;&nbsp;Cadastrar novo usuário    |
| POST   | `/auth/login`   | &nbsp;&nbsp;Autenticar e retornar JWT |
| GET    | `/user/me`      | &nbsp;&nbsp;Página privada (JWT)      |


### Exemplo de Requisições
#### Cadastro:
```
POST /auth/register
Content-Type: application/json

{
  "username": "usuario",
  "password": "senha"
}
```
#### Login:
```
POST /auth/login
Content-Type: application/json

{
  "username": "usuario",
  "password": "senha"
}
```
#### Resposta:
```
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```
#### Acesso privado:
```
GET /user/me
Authorization: Bearer {TOKEN}
```

## 🧑‍💻 Documentação
Acesse o Swagger em:
http://localhost:8080/swagger-ui.html

ou
http://localhost:8080/swagger-ui/index.html

## 🗃️ Migração Flyway
O Flyway cria a tabela users automaticamente via migration:

```
CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
```
## 🔒 Segurança

- Senhas são armazenadas com BCrypt.
- JWT é utilizado para autenticação de rotas privadas.
- Apenas endpoints públicos são liberados sem autenticação.

## 📦 Dependências principais (pom.xml)
```
<dependency>org.springframework.boot:spring-boot-starter-web</dependency>
<dependency>org.springframework.boot:spring-boot-starter-security</dependency>
<dependency>org.springframework.boot:spring-boot-starter-data-jpa</dependency>
<dependency>com.mysql:mysql-connector-j</dependency>
<dependency>org.flywaydb:flyway-core</dependency>
<dependency>io.jsonwebtoken:jjwt-api:0.11.5</dependency>
<dependency>io.jsonwebtoken:jjwt-impl:0.11.5</dependency>
<dependency>io.jsonwebtoken:jjwt-jackson:0.11.5</dependency>
<dependency>org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0</dependency>
<dependency>org.projectlombok:lombok</dependency>
```
## 📝 Observações
- Para resetar o banco, basta apagar o banco de dados e rodar o projeto novamente.
- Para produção, altere a jwt.secret para algo forte e seguro.
