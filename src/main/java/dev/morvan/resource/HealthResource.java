package dev.morvan.resource;

import dev.morvan.service.MCChatBot;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/health")
public class HealthResource {

    @ConfigProperty(name = "env")
    String env;

    @Inject
    MCChatBot bot;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/env")
    public Uni<String> env() {
        return Uni.createFrom().item("hello " + env);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/chat")
    public Uni<String> chat() {
        return Uni.createFrom().item("hello " + bot.chat(null, "hello"));
    }


}
