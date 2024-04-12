package dev.morvan.client;

import dev.morvan.model.client.CreateFileRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
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
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("chatmc/execute-sbt-command")
    String executeSbtCommand(String cmd);


    @PUT
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("chatmc/create-directory")
    String createDirectory(@QueryParam("dirName") String dirName);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("chatmc/write-file")
    String writeFile(CreateFileRequest request);

    @DELETE
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("chatmc/delete-file")
    String deleteFile(@QueryParam("dirName") String dirName, @QueryParam("fileName") String fileName);


    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("chatmc/read-file")
    String readFile(@QueryParam("dirName") String dirName, @QueryParam("fileName") String fileName);


    @GET
    @Path("chatmc/list-files")
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.TEXT_PLAIN)
    String listFiles(@QueryParam("dirName") String dirName);

}
