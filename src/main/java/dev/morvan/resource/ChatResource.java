package dev.morvan.resource;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/mc-chat")
public class ChatResource {

    @ConfigProperty(name = "env")
    String env;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/env")
    public Uni<String> env() {
        return Uni.createFrom().item("hello " + env);
    }
}
