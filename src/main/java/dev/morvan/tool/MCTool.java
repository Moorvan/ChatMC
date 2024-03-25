package dev.morvan.tool;

import dev.langchain4j.agent.tool.Tool;
import dev.morvan.client.MCClient;
import dev.morvan.client.MCToolsClient;
import dev.morvan.model.client.CreateFileRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Slf4j
public class MCTool {

    @RestClient
    MCClient mcClient;

    @RestClient
    MCToolsClient mcToolsClient;

    @Tool("create a directory with dirName in the project's /src/main/scala/ directory.")
    public String createDirectory(String dirName) {
        return mcToolsClient.createDirectory(dirName);
    }

    @Tool("use write file into path with content")
    public String writeFile(String dirName, String content) {
        return mcToolsClient.writeFile(CreateFileRequest.builder()
                .dirName(dirName)
                .content(content)
                .build());
    }

    @Tool("""
            execute sbt command at path in mac os system. 
            For example, when call executeCommand("runMain example.Main")
            it will execute `sbt "runMain example.Main"` command at /Users/yuechen/Developer/chisel-projects/ChiselFV.
            """
    )
    public String executeSbtCommand(String sbtCmd) {
        return mcToolsClient.executeSbtCommand(sbtCmd);
    }

    @Tool("call this function for to run model checking with vmt content")
    public String modelChecking(String vmtContent) {
        log.info("Model checking with vmt content: {}", vmtContent);
        return mcClient.check(vmtContent);
    }

//    @Tool("call this function to parser Chisel module to vmt format")
//    public String parseFromChiselToVmt(String chiselContent) {
//        return mcToolsClient.parseFromChiselToVmt(chiselContent);
//    }


}
