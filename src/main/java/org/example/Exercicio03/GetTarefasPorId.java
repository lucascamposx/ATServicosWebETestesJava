package org.example.Exercicio03;

import java.nio.charset.StandardCharsets;
import java.net.*;

public class GetTarefasPorId {
    public static void main(String[] args) throws Exception {
        // Id de busca
        int id = 1;

        // Criar URL
        URL url = new URI("http://localhost:7000/tarefas/" + id).toURL();

        // Abrir conexão
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Definir método
        conn.setRequestMethod("GET");

        // Definir headers
        conn.setRequestProperty("Accept", "application/json");

        // Obter status
        int status = conn.getResponseCode();
        System.out.println("Status HTTP: " + status);


        if (status == 200) {
            String resposta = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("Resposta da API:");
            System.out.println(resposta);
        } else {
            System.out.println("Não foi possível encontrar a tarefa. Verifique se o ID existe.");
        }

        // Fechar conexão
        conn.disconnect();
    }
}
