package org.example.Exercicio01;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    public static Javalin criarApp() {
        return Javalin.create()
                .get("/hello", ctx -> ctx.result("Hello, Javalin!"));
    }

    @Test
    void deveRetornarHelloJavalinComStatus200() {
        JavalinTest.test(criarApp(), (server, client) -> {
            var response = client.get("/hello");

            assertEquals(200, response.code());
            assertEquals("Hello, Javalin!", response.body().string());
        });
    }

}