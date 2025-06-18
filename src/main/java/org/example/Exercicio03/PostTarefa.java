package org.example.Exercicio03;

import java.nio.charset.StandardCharsets;
import java.io.OutputStream;
import java.net.*;

public class PostTarefa {
    public static void main(String[] args) throws Exception {
        // Cria URL
        URL url = new URI("http://localhost:7000/tarefas").toURL();

        // Abrir Conexao
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Definir Metodo
        conn.setRequestMethod("POST");

        // Definir headers
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);


        String jsonInputString = """
                {
                    "titulo": "Correr",
                    "descricao": "Teste"
                }
                """;


        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input);
        }

        // Obter status HTTP
        int status = conn.getResponseCode();
        System.out.println("Status HTTP: " + status);

        // resposta
        String resposta = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("Resposta da API: " + resposta);

        conn.disconnect();
    }
}
