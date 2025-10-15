# Angel of the Dices 🎲

![Logo do Projeto](https://raw.githubusercontent.com/pHenrymelo/AngelOfTheDices-Web/main/public/logo-violet.png)

## 📖 Sobre o Projeto

**Angel of the Dices** é uma aplicação web full-stack projetada para ser uma plataforma completa de fichas de personagem para RPGs de mesa. A primeira versão foi desenvolvida com foco no sistema **Ordem Paranormal RPG**, implementando suas regras de negócio para criação e gerenciamento de personagens.

Este projeto nasceu como uma iniciativa de estudo para aprofundar conhecimentos na stack **Java com Spring Boot** para o back-end e **React** para o front-end. O objetivo foi construir uma aplicação robusta, escalável e com boas práticas de desenvolvimento, servindo como um protótipo funcional da visão final.

A ambição para o futuro é expandir a plataforma para suportar múltiplos sistemas de RPG, tornando-se uma ferramenta versátil e central para mestres e jogadores.

A aplicação está atualmente deployada e pode ser acessada publicamente.

---

## ✨ Funcionalidades

- **Autenticação de Usuários:** Sistema seguro de criação de conta e login com JWT (Access Tokens e Refresh Tokens).
- **Criação de Personagens:** Formulário completo para criar personagens de Ordem Paranormal, selecionando Origem, Classe, Trilha, Atributos e outras características.
- **Gerenciamento de Ficha:**
    - Visualização detalhada da ficha do personagem com todos os status calculados.
    - Controle de Pontos de Vida, Sanidade e Esforço (ou Pontos de Determinação).
    - Adição e gerenciamento de inventário, com cálculo de carga.
    - Cadastro de ataques, habilidades, rituais e anotações.
    - Upload de imagem para o retrato do personagem.
- **Regras de Jogo:** Endpoints que fornecem as regras de jogo (classes, origens, etc.) para o front-end consumir dinamicamente.

---

## 🛠️ Tecnologias e Arquitetura

O projeto foi construído com uma arquitetura de microsserviços em mente, embora atualmente opere como um monólito coeso. A separação clara de responsabilidades e a organização modular facilitam a manutenção e a futura expansão.

### Back-end (Java & Spring Boot)

- **Framework Principal:** [**Spring Boot**](https://spring.io/projects/spring-boot) para criar uma aplicação autônoma e robusta com configuração mínima.
- **Segurança:** [**Spring Security**](https://spring.io/projects/spring-security) para gerenciar a autenticação e autorização. A autenticação é baseada em **JWT (JSON Web Tokens)**, utilizando um par de Access Token (curta duração) e Refresh Token (longa duração, armazenado em um cookie HttpOnly) para maior segurança.
- **Acesso a Dados:** [**Spring Data JPA**](https://spring.io/projects/spring-data-jpa) com **Hibernate** como implementação para o mapeamento objeto-relacional (ORM), simplificando a interação com o banco de dados.
- **Banco de Dados:** [**PostgreSQL**](https://www.postgresql.org/), um sistema de gerenciamento de banco de dados relacional de código aberto.
- **API:** A aplicação expõe uma **API RESTful** para comunicação com o front-end.
- **Validação:** As entradas da API são validadas usando **Jakarta Bean Validation**.
- **Gerenciamento de Dependências:** [**Maven**](https.maven.apache.org/) para gerenciar as dependências e o build do projeto.
- **Containerização:** O projeto está configurado com `Dockerfile` e `docker-compose.yml` para facilitar a criação de ambientes de desenvolvimento e produção containerizados.

### Front-end

- **Framework:** [**React**](https://reactjs.org/), uma biblioteca JavaScript para construir interfaces de usuário interativas.
- **Comunicação:** Consome a API RESTful do back-end para buscar e enviar dados.

### Estrutura de Pacotes do Back-end

O código-fonte do back-end está organizado de forma modular e segue os princípios da arquitetura em camadas (Controller, Service, Repository).

```
dev/kaiserInc/AngelOfTheDices/
├── auth/             # Lida com autenticação, JWT e cookies.
├── character/        # Pacote principal do domínio, contém tudo relacionado a um personagem.
│   ├── ability/      # Gerenciamento de Habilidades.
│   ├── attack/       # Gerenciamento de Ataques.
│   ├── classPath/    # Classes e Trilhas.
│   ├── dto/          # DTOs (Data Transfer Objects) e Mappers para a entidade Character.
│   ├── expertise/    # Gerenciamento de Perícias.
│   ├── item/         # Gerenciamento de Itens e Inventário.
│   ├── note/         # Gerenciamento de Anotações.
│   └── ...           # Outros subdomínios como Origem, Afinidade, Rituais, etc.
├── config/           # Configurações do Spring (Segurança, MVC, CORS).
├── exception/        # Exceptions customizadas e um GlobalExceptionHandler para respostas de erro padronizadas.
├── rules/            # Endpoint para expor regras de jogo (enums) para o front-end.
├── storage/          # Serviço para armazenamento de arquivos (upload de retratos).
└── user/             # Gerenciamento de usuários (criação, consulta, atualização).
```

Essa estrutura promove a **separação de conceitos (Separation of Concerns)** e o **baixo acoplamento**, onde cada pacote tem uma responsabilidade bem definida. O uso de **DTOs (Data Transfer Objects)** e **Mappers** desacopla a representação da API da representação do banco de dados, uma prática recomendada para APIs robustas.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- [Java (JDK)](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started) (ou uma instância local do PostgreSQL)

### 1. Back-end

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/AngelOfTheDices.git

# Navegue para a pasta do projeto
cd AngelOfTheDices

# Crie um arquivo application.properties ou configure as variáveis de ambiente
# com as credenciais do seu banco de dados e um segredo JWT.
# Ex: spring.datasource.username=seu-user
#     spring.datasource.password=sua-senha
#     jwt.secret=seu-segredo-super-secreto

# Execute a aplicação
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### 2. Front-end


- Visite [AngelOfTheDices-Web](https://github.com/pHenrymelo/AngelOfTheDices-Web) para mais detalhes
---

## 📄 Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
