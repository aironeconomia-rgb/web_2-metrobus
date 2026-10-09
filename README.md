# Metrobus - Programação Web II

Projeto adaptado da base original para seguir o modelo das aulas de Programação Web II.

## Arquitetura

Fluxo principal:

`React -> Axios/JSON -> Controller -> Service -> Repository -> JPA/Hibernate -> H2`

### Back-end

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- H2 em arquivo
- Arquitetura em camadas: `controller`, `service`, `repository`, `model`

### Front-end

Local: `src/main/frontend`

- React + TypeScript
- Vite
- Axios
- `components/`, `pages/`, `services/`, `types/`
- CRUD de Estações, Linhas, Motoristas e Veículos
- `useState` e `useEffect`
- estados de carregamento e erro

## Executar o back-end

Na raiz do projeto:

### Windows PowerShell

```powershell
.\mvnw spring-boot:run
```

Back-end: `http://localhost:8080`

Console H2: `http://localhost:8080/h2-console`

No console H2 use:

- JDBC URL: `jdbc:h2:file:./database.db`
- User Name: `sa`
- Password: deixe em branco

## Executar o front-end

Abra outro terminal:

```powershell
cd src\main\frontend
npm install
npm run dev
```

Front-end: `http://localhost:5173`

## Endpoints REST

### Estações
- `GET /api/estacoes`
- `GET /api/estacoes/{id}`
- `POST /api/estacoes`
- `PUT /api/estacoes/{id}`
- `DELETE /api/estacoes/{id}`

### Linhas
- `GET /api/linhas`
- `GET /api/linhas/{id}`
- `POST /api/linhas`
- `PUT /api/linhas/{id}`
- `DELETE /api/linhas/{id}`

### Motoristas
- `GET /api/motoristas`
- `GET /api/motoristas/{id}`
- `POST /api/motoristas`
- `PUT /api/motoristas/{id}`
- `DELETE /api/motoristas/{id}`

### Veículos
- `GET /api/veiculos`
- `GET /api/veiculos/{id}`
- `POST /api/veiculos`
- `PUT /api/veiculos/{id}`
- `DELETE /api/veiculos/{id}`

## Observação sobre relacionamentos

A base original já possuía relacionamentos `@ManyToMany`. Eles foram preservados nos modelos, mas o CRUD desta versão trabalha primeiro os campos simples das entidades, seguindo a ordem didática das aulas. A associação entre entidades pode ser aprofundada na etapa de relacionamentos JPA e DTOs.
