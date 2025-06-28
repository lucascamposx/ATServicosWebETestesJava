package org.example.Controller;

import io.javalin.Javalin;
import org.example.Model.Tarefa;

import java.time.LocalDateTime;
import java.util.*;

import java.time.Instant;
import java.util.Map;

public class TarefaController {
    // Lista para armazenar as tarefas
    public static List<Tarefa> tarefas = new ArrayList<>();
    private static int ultimoId;

    private static int gerarNovoId() {
        return ++ultimoId;
    }

    public static void registrarRotas(Javalin app) {

        // 1. GET simples - resposta direta
        app.get("/hello", ctx -> ctx.result("Hello, Javalin!"));

        // 2. GET status
        app.get("/status", ctx -> {
            ctx.json(Map.of(
                    "status", ctx.status(),
                    "timestamp", Instant.now().toString()
            ));
        });

//         3. POST echo
        app.post("/echo", ctx -> {
            Map<String, String> body = ctx.bodyAsClass(Map.class);
            ctx.json(body);
        });

        // 4. GET saudacao com nome
        app.get("/saudacao/{nome}", ctx -> {
            String nome = ctx.pathParam("nome");
            ctx.json(Map.of("mensagem", "Olá, " + nome + "!"));
        });

        // 5. POST tarefas
        app.post("/tarefas", ctx -> {
            Tarefa tarefa = ctx.bodyAsClass(Tarefa.class);
            tarefa.setId(gerarNovoId()); // gera id automaticamente
            tarefa.setConcluida(false); // valor padrão
            tarefa.setDataCriacao(LocalDateTime.now().toString()); // data atual
            tarefas.add(tarefa); // armazena na lista
            ctx.status(201).json(tarefa); // retorna status
        });

        // 6. GET tarefas
        // Retorna todas as tarefas salvas
        app.get("/tarefas", ctx -> ctx.json(tarefas));

        // Busca tarefa por ID
        app.get("/tarefas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Optional<Tarefa> tarefa = tarefas.stream()
                    .filter(t -> t.getId() == id)
                    .findFirst();
            if (tarefa.isPresent()) {
                ctx.json(tarefa.get());
            } else {
                ctx.status(404).result("Tarefa com ID: " + id + " não encontrada");
            };
        });

    }
}
