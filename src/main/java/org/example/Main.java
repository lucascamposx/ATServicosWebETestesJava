package org.example;

import io.javalin.Javalin;
import org.example.Controller.TarefaController;

public class Main {
    public static void main(String[] args) {
        // Porta dinâmica
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "7000"));

        Javalin app = Javalin.create().start(port);

        new TarefaController().registrarRotas(app);
    }
}
