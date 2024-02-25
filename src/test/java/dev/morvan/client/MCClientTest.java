package dev.morvan.client;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MCClientTest {

    @RestClient
    MCClient mcClient;

    @Test
    void testCheck() {
        String body = "body";
        String response = mcClient.check(body);
        System.out.println(response);
    }
}
