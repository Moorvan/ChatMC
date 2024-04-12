package dev.morvan.resource;

import dev.morvan.model.TriagedReview;
import dev.morvan.service.MyAiService;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/ai-test")
public class AiTesting {

    @Inject
    MyAiService myAiService;

    @GET
    @Path("/poem")
    @Produces(MediaType.APPLICATION_JSON)
    public String emailMeAPoem(
            @QueryParam("topic") String topic,
            @QueryParam("length") int length
    ) {
        return myAiService.writePoem(topic, length);
    }

    @GET
    @Path("/review")
    @Produces(MediaType.APPLICATION_JSON)
    public TriagedReview triageReview(
            @QueryParam("review") String review
    ) {
        return myAiService.triage(review);
    }
}
