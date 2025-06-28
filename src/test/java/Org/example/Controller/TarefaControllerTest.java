package org.example.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;
import org.example.Model.Tarefa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TarefaControllerTest {

    public static Javalin criarAppComRotas() {
        Javalin app = Javalin.create();
        TarefaController.registrarRotas(app);
        return app;
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void deveRetornarHelloJavalinComStatus200() {
        JavalinTest.test(criarAppComRotas(), (server, client) -> {
            var response = client.get("/hello");

            assertEquals(200, response.code());
            assertEquals("Hello, Javalin!", response.body().string());
        });
    }

    @Test
    void deveCriarTarefaERetornarStatus201(){
        JavalinTest.test(criarAppComRotas(), ((server, client) -> {
            String novaTarefaJson = """
            {
                "titulo": "Estudar Javalin",
                "descricao": "Aprender como criar API"
            }
            """;

            var response = client.post("/tarefas", novaTarefaJson);

            assertEquals(201, response.code());

            // Converter a resposta para um objeto Tarefa
            Tarefa tarefaCriada = objectMapper.readValue(response.body().string(), Tarefa.class);

            assertEquals("Estudar Javalin", tarefaCriada.getTitulo());
            assertEquals("Aprender como criar API", tarefaCriada.getDescricao());
        }));
    }

    @Test
    void deveCadastrarEValidarTarefaPorIdComGET() {
        JavalinTest.test(criarAppComRotas(), (server, client) -> {
            // Cria tarefa via POST
            String novaTarefaJson = """
            {
                "titulo": "Estudar GET",
                "descricao": "Testar busca por ID"
            }
            """;

            var postResponse = client.post("/tarefas", novaTarefaJson);
            assertEquals(201, postResponse.code());

            // Converte resposta do POST para objeto Tarefa
            Tarefa tarefaCriada = objectMapper.readValue(postResponse.body().string(), Tarefa.class);
            int idCriado = tarefaCriada.getId();

            // Faz GET buscando pelo ID
            var getResponse = client.get("/tarefas/" + idCriado);
            assertEquals(200, getResponse.code());

            // Converte resposta do GET para um objeto Tarefa
            Tarefa tarefaRecebida = objectMapper.readValue(getResponse.body().string(), Tarefa.class);

            assertEquals(tarefaCriada.getId(), tarefaRecebida.getId());
            assertEquals(tarefaCriada.getTitulo(), tarefaRecebida.getTitulo());
            assertEquals(tarefaCriada.getDescricao(), tarefaRecebida.getDescricao());
            assertEquals(tarefaCriada.isConcluida(), tarefaRecebida.isConcluida());
        });
    }

    @Test
    void deveRetornarArrayDeTarefasNaoVazio() {
        JavalinTest.test(criarAppComRotas(), (server, client) -> {
            // Cria tarefa via POST
            String novaTarefaJson = """
            {
                "titulo": "Estudar Java",
                "descricao": "POO"
            }
            """;

            var postResponse = client.post("/tarefas", novaTarefaJson);
            assertEquals(201, postResponse.code());

            // lista de tarefas via GET
            var getResponse = client.get("/tarefas");
            assertEquals(200, getResponse.code());

            // Converte json em lista de Tarefas
            Tarefa[] tarefas = objectMapper.readValue(getResponse.body().string(), Tarefa[].class);

            // Verifica se o array contem pelo menos um item
            assertTrue(tarefas.length > 0, "A lista de tarefas deve conter pelo menos um item");
        });
    }
}