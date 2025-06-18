package org.example.Exercicio03;

import java.nio.charset.StandardCharsets;
import java.net.*;

public class GetStatus {
    public static void main(String[] args) throws Exception {
        // Criar a URL
        URL url = new URI("http://localhost:7000/status").toURL();

        // Abrir conexão
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Definir método
        conn.setRequestMethod("GET");

        // Definir header
        conn.setRequestProperty("Accept", "application/json");

        // Obter status
        int status = conn.getResponseCode();
        System.out.println("Status HTTP: " + status);

        // Ler resposta
        String resposta = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        System.out.println("Resposta JSON:");
        System.out.println(resposta);

        // Fechar conexão
        conn.disconnect();
    }
}
