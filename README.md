# Etapa 1 — Desenvolvimento REST com Javalin

Este projeto Java utiliza o framework Javalin para criação de uma API RESTful simples. A principal classe de controle é TarefaController, localizada no pacote org.example.Controller, que registra as rotas da aplicação e mantém uma lista em memória de tarefas (List<Tarefa>).

### A classe TarefaController é responsável por registrar as rotas da aplicação. Entre os endpoints disponíveis, estão:

GET /hello – Retorna uma saudação simples.

GET /status – Retorna status da requisição e timestamp.

POST /echo – Recebe um JSON e retorna o mesmo conteúdo.

GET /saudacao/{nome} – Responde com uma saudação personalizada.

POST /tarefas – Cria uma nova tarefa e armazena em uma lista estática.

# Etapa 2 — Testes Unitários com JUnit

Para garantir a confiabilidade da API, foram desenvolvidos testes unitários utilizando JUnit 5 com o suporte da ferramenta JavalinTest, 
que permite simular requisições e verificar as respostas de forma isolada e controlada.

### 🔍 Testes Implementados
### ✅ deveRetornarHelloJavalinComStatus200()
Verifica se o endpoint GET /hello retorna status 200 e a mensagem "Hello, Javalin!".

### ✅ deveCriarTarefaERetornarStatus201()
Simula o envio de uma nova tarefa via POST /tarefas e valida se:

O status HTTP retornado é 201 Created;

O JSON de resposta contém os mesmos dados enviados.

### ✅ deveCadastrarEValidarTarefaPorIdComGET()
Cria uma nova tarefa e em seguida busca por seu ID com GET /tarefas/{id}, comparando todos os dados retornados.

### ✅ deveRetornarArrayDeTarefasNaoVazio()
Garante que, após criar pelo menos uma tarefa, a requisição GET /tarefas retorna uma lista JSON com pelo menos um item.

