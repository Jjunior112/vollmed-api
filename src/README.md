# 🩺 API Clínica Médica

API REST para gerenciamento de **pacientes**, **médicos** e **consultas**. Usa autenticação via **JWT (Bearer Token)** e segue a especificação **OpenAPI 3.1.0**.

---

## 🔐 Autenticação

Para acessar os endpoints protegidos, obtenha um token JWT no login:

### `POST /login`

#### Corpo:
```json
{
  "login": "usuario",
  "password": "senha"
}
```

#### Resposta:
```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6..."
```

Use o token nas requisições:
```
Authorization: Bearer <seu_token>
```

---

## 👤 Pacientes

### `GET /pacientes`

Lista todos os pacientes com paginação.

**Parâmetros:**
- `page`
- `size`
- `sort`

---

### `GET /pacientes/{id}`

Retorna os dados de um paciente pelo ID.

---

### `POST /pacientes`

Cadastra um novo paciente.

**Exemplo:**
```json
{
  "nome": "João da Silva",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "cpf": "12345678901",
  "endereco": {
    "logradouro": "Rua A",
    "bairro": "Centro",
    "cep": "12345678",
    "cidade": "Cidade",
    "uf": "SP"
  }
}
```

---

### `PUT /pacientes/{id}`

Atualiza os dados de um paciente existente.

---

### `DELETE /pacientes/{id}`

Desativa ou remove um paciente do sistema.

---

## 🩺 Médicos

### `GET /medicos`

Lista os médicos cadastrados.

**Parâmetros opcionais:**
- `page`, `size`, `sort`

---

### `GET /medicos/{id}`

Busca dados de um médico por ID.

---

### `POST /medicos`

Cadastra um novo médico.

**Exemplo:**
```json
{
  "nome": "Dra. Ana Paula",
  "email": "ana@clinic.com",
  "telefone": "11988887777",
  "crm": "12345",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Av. Brasil",
    "bairro": "Centro",
    "cep": "87654321",
    "cidade": "São Paulo",
    "uf": "SP"
  }
}
```

---

### `PUT /medicos/{id}`

Atualiza dados do médico.

---

### `DELETE /medicos/{id}`

Remove ou inativa o cadastro do médico.

---

## 📅 Consultas

### `GET /consultas`

Lista todas as consultas agendadas.

**Parâmetros:**
- `page`, `size`, `sort`
- `paciente`: (opcional) ID do paciente

---

### `GET /consultas/{id}`

Retorna detalhes de uma consulta específica.

---

### `POST /consultas`

Agenda uma nova consulta.

**Exemplo:**
```json
{
  "idPaciente": 1,
  "idMedico": 2,
  "data": "2025-08-01T14:30:00",
  "especialidade": "DERMATOLOGIA"
}
```

---

### `DELETE /consultas/{id}`

Cancela uma consulta.

---

## 📄 Schemas usados

- `DadosCadastroPaciente`, `DadosCadastroMedico`: usados nos cadastros
- `UpdatePaciente`, `UpdateMedico`: usados nas edições
- `DadosDetalhamentoConsulta`: resposta ao buscar consulta
- `Pageable`: paginação com `page`, `size`, `sort`

---

## 🛡️ Segurança

Todos os endpoints (exceto `/login`) requerem JWT.

**Header:**
```
Authorization: Bearer <seu_token>
```

---

## 🧪 Testando a API

Você pode usar:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Postman / Insomnia
- `curl`

---

## ✅ Funcionalidades Implementadas

- [x] CRUD de Pacientes
- [x] CRUD de Médicos
- [x] Agendamento e cancelamento de Consultas
- [x] Filtro de Consultas por paciente
- [x] Paginação
- [x] Autenticação JWT
- [x] Validações com Bean Validation

---

## 📄 Licença

MIT License.  
Contribuições são bem-vindas!

---

