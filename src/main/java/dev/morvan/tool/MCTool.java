package dev.morvan.tool;

import dev.langchain4j.agent.tool.Tool;
import dev.morvan.client.MCClient;
import dev.morvan.client.ParserClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Request;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Slf4j
public class MCTool {

    @RestClient
    MCClient mcClient;

    @RestClient
    ParserClient parserClient;

    @Tool("call this function for to run model checking with vmt content")
    public String modelChecking(String vmtContent) {
        log.info("Model checking with vmt content: {}", vmtContent);
        return mcClient.check(vmtContent);
    }

    @Tool("call this function to parser Chisel module to vmt format")
    public String parseFromChiselToVmt(String chiselContent) {
        return parserClient.parseFromChiselToVmt(chiselContent);
    }
}
