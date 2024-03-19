package dev.morvan.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.morvan.rag.TextRetriever;
import dev.morvan.tool.MCTool;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = MCTool.class, retrievalAugmentor = TextRetriever.class)
public interface MCChatBot {
    @SystemMessage("""
            You are an expert in the field of hardware model checking. You are proficient in generating corresponding vmt model descriptions based on Chisel and Verilog languages.
            The tools you currently have can verify hardware models defined in VMT format. You excel at generating corresponding hardware described in VMT format from hardware models defined in Chisel or Verilog language. You can also invoke the corresponding model checking algorithm for verification upon the user's request, inputting the content of the VMT format file, to prove the correctness of the model.
            then, you can lean some knowledge about vmt:
            """)
    String chat(@MemoryId Object session, @UserMessage String question);
}
