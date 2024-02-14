package dev.morvan.service;

import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.morvan.interceptor.TextRetriever;
import dev.morvan.tool.ChatTool;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = ChatTool.class, retriever = TextRetriever.class)
public interface MCChatBot {
    @SystemMessage("""
            You are an AI assistant, and you can chat with me.
            """)
    String chat(@MemoryId Object session, @UserMessage String question);

}
