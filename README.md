# Board API

Aplicação Java console para gerenciamento de **boards (quadros)**, **colunas** e **cards**, simulando um fluxo de trabalho no estilo **Kanban**, com suporte a **bloqueio**, **desbloqueio** e **cancelamento** de cards, além de migrações de banco com **Liquibase**.

---

## 🎯 Objetivo

Permitir que o usuário crie e gerencie boards diretamente pelo terminal, controlando:

- Criação e exclusão de boards
- Definição de colunas (iniciais, finais, de cancelamento e adicionais)
- Criação, movimentação, bloqueio, desbloqueio e cancelamento de cards
- Visualização do estado atual do board e de cards específicos

---

## 🧱 Estrutura do Projeto

Pacote base: `dio.board.api`

### Principais pacotes:

- **dto**  
  Objetos de transferência de dados usados para exibição.

- **exception**
    - `CardBlockedException`
    - `CardFinishedException`
    - `EntityNotFoundException`

- **persistence**
    - **config**
        - `ConnectionConfig` — configuração JDBC.
    - **converter**
        - `OffsetDateTimeConverter`
    - **dao**
        - `BoardDao`
        - `BoardColumnDao`
        - `CardDao`
        - `BlockDao`
    - **entity**
        - `Board`
        - `BoardColumn`
        - `BoardColumnKindEnum`
        - `Card`
        - `Block`
    - **migration**
        - `MigrationStrategy` (Liquibase)

- **service**
    - `BoardService`
    - `BoardQueryService`
    - `BoardColumnQueryService`
    - `CardService`
    - `CardQueryService`

- **ui**
    - `MainMenu`
    - `BoardMenu`

### Classe Principal

```java
public class BoardApiApplication {
    public static void main(String[] args) throws SQLException {
        try (var connection = getConnection()) {
            new MigrationStrategy(connection).executeMigration();
        }
        new MainMenu().execute();
    }
}
```

---

## 🛠 Tecnologias Utilizadas

- Java 11+ / 17
- JDBC
- MySQL
- Liquibase
- Lombok

---

## 🗄 Configuração de Banco de Dados

A conexão está em `persistence/config/ConnectionConfig.java`:

```java
var url = "jdbc:mysql://localhost/dio-sbj-board";
var userBoard = "root";
var passwordBoard = "diosbj";
```

### Criar banco:

```sql
CREATE DATABASE dio-sbj-board;
```

Se necessário, ajustar permissões:

```sql
GRANT ALL PRIVILEGES ON dio-sbj-board.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

---

## 📦 Dependências

Certifique-se de ter no classpath:

- mysql-connector-j
- Liquibase
- Lombok

---

## ▶️ Como Executar

### 1. Criar banco de dados
### 2. Compilar o projeto
### 3. Executar:

```bash
java -cp "out:lib/*" dio.board.api.BoardApiApplication
```

---

## 🧮 Funcionalidades

### **Menu Principal**
- Criar board
- Selecionar board
- Excluir board
- Sair

### **Menu de Board**
- Criar card
- Mover card
- Bloquear card
- Desbloquear card
- Cancelar card
- Ver board
- Ver coluna
- Ver card
- Voltar

---

## 🧩 Regras de Negócio

- Boards possuem colunas:
    - INITIAL
    - PENDING
    - FINAL
    - CANCEL

- Cards podem ser:
    - Movidos
    - Bloqueados
    - Desbloqueados
    - Cancelados

- Exceções tratam regras como:
    - Não mover card bloqueado
    - Não mover card finalizado

---

## 🧪 Melhorias Futuras

- API REST
- Autenticação
- Relatórios
- Testes
