package dev.morvan.tool;

import dev.langchain4j.agent.tool.Tool;
import dev.morvan.client.MCToolsClient;
import dev.morvan.model.client.CreateFileRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@Slf4j
public class MCTool {

    @RestClient
    MCToolsClient mcToolsClient;

    @Tool("Create a directory with [dirName] in the ChiselFV project's /src/main/scala/ directory.")
    public String createDirectory(String dirName) {
        log.info("Create directory: {}", dirName);
        return mcToolsClient.createDirectory(dirName);
    }

    @Tool("Use write file into path of the ChiselFV project: /src/main/scala/[dirName]/[fileName] with [content]")
    public String writeFile(String dirName, String fileName, String content) {
        log.info("Write file: {}/{} with content: {}", dirName, fileName, content);
        return mcToolsClient.writeFile(CreateFileRequest.builder()
                .dirName(dirName)
                .fileName(fileName)
                .content(content)
                .build());
    }

    @Tool("When meet error, you can delete the file of the ChiselFV project: /src/main/scala/[dirName]/[fileName]")
    public String deleteFile(String dirName, String fileName) {
        log.info("Delete file: {}/{}", dirName, fileName);
        return mcToolsClient.deleteFile(dirName, fileName);
    }

    @Tool("Read file content from path of the ChiselFV project: /src/main/scala/[dirName]/[fileName]")
    public String readFile(String dirName, String fileName) {
        log.info("Read file: {}/{}", dirName, fileName);
        return mcToolsClient.readFile(dirName, fileName);
    }

    @Tool("List files in the ChiselFV project's /src/main/scala/[dirName] directory.")
    public String listFiles(String dirName) {
        log.info("List files in directory: {}", dirName);
        return mcToolsClient.listFiles(dirName);
    }

    @Tool("""
            Execute sbt command at path of the ChiselFV project.
            It will execute the command: sbt "[sbtCmd]" at the path of the ChiselFV project.
            For example, when call executeCommand("runMain example.Main")
            it will execute `sbt "runMain example.Main"`
            """
    )
    public String executeSbtCommand(String sbtCmd) {
        log.info("Execute sbt command: {}", sbtCmd);
        return mcToolsClient.executeSbtCommand(sbtCmd);
    }

//    @Tool("call this function for to run model checking with vmt content")
//    public String modelChecking(String vmtContent) {
//        log.info("Model checking with vmt content: {}", vmtContent);
//        return mcClient.check(vmtContent);
//    }

//    @Tool("call this function to parser Chisel module to vmt format")
//    public String parseFromChiselToVmt(String chiselContent) {
//        return mcToolsClient.parseFromChiselToVmt(chiselContent);
//    }


}
