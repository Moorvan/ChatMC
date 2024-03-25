package dev.morvan.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.morvan.tool.MCTool;
import io.quarkiverse.langchain4j.RegisterAiService;

//@RegisterAiService(tools = MCTool.class, retrievalAugmentor = TextRetriever.class)
@RegisterAiService(tools = MCTool.class)
public interface MCChatBot {
    @SystemMessage("""
            You are an expert in the field of hardware model checking.
            """)
    String chat(@MemoryId Object session, @UserMessage String question);
}
