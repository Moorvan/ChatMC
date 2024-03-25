package dev.morvan.client;

import dev.morvan.model.client.CreateFileRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("")
@RegisterRestClient
public interface MCToolsClient {
    @POST
    @Path("parse-from-chisel-to-vmt")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    String parseFromChiselToVmt(String body);

    @POST
    @Path("chatmc/execute-sbt-command")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    String executeSbtCommand(String cmd);


    @PUT
    @Path("chatmc/execute-sbt-command")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.WILDCARD)
    String createDirectory(@QueryParam("dirName") String dirName);

    @POST
    @Path("chatmc/execute-sbt-command")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    String writeFile(CreateFileRequest request);

}
