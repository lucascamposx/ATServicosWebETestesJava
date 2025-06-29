# Etapa 1 — Desenvolvimento REST com Javalin

Este projeto Java utiliza o framework Javalin para criação de uma API RESTful simples. A principal classe de controle é TarefaController, localizada no pacote org.example.Controller, que registra as rotas da aplicação e mantém uma lista em memória de tarefas (List<Tarefa>).

Separei a classe Main com o objetivo de melhorar a legibilidade do projeto. Agora, sua única responsabilidade é iniciar o servidor Javalin utilizando a porta definida (por variável de ambiente ou valor padrão). Toda a lógica de rotas foi delegada à classe TarefaController. 

### A classe TarefaController é responsável por registrar as rotas da aplicação. Entre os endpoints disponíveis, estão:

GET /hello – Retorna uma saudação simples.

GET /status – Retorna status da requisição e timestamp.

POST /echo – Recebe um JSON e retorna o mesmo conteúdo.

GET /saudacao/{nome} – Responde com uma saudação personalizada.

POST /tarefas – Cria uma nova tarefa e armazena em uma lista.

# Etapa 2 — Testes Unitários com JUnit

Para garantir a confiabilidade da API, foram desenvolvidos testes unitários utilizando JUnit com o suporte da ferramenta JavalinTest, 
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

# Etapa 3 — Consumo de API com HttpURLConnection

Foi usada a classe HttpURLConnection do Java para fazer requisições HTTP a API.
Os exemplos estão organizados em classes separadas, cada uma focada em um tipo de requisição, como: consulta de status, listagem de tarefas, busca por ID e criação de nova tarefa:

Consultar o status da API (GET /status);

Listar todas as tarefas cadastradas (GET /tarefas);

Buscar uma tarefa específica pelo ID (GET /tarefas/{id});

Criar uma nova tarefa enviando dados em JSON (POST /tarefas).

Esses exemplos mostram como abrir conexões HTTP, definir métodos e headers, enviar dados no corpo da requisição (no caso do POST) e ler as respostas da API de forma simples e direta, facilitando a integração e os testes da aplicação.
