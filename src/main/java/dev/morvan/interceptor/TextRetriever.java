package dev.morvan.interceptor;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.retriever.Retriever;
import io.quarkiverse.langchain4j.redis.RedisEmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TextRetriever implements Retriever<TextSegment> {

    private final EmbeddingStoreRetriever retriever;

    TextRetriever(RedisEmbeddingStore store, EmbeddingModel model,
            @ConfigProperty(name = "prompts.maxResults", defaultValue = "5") int maxResults,
            @ConfigProperty(name = "prompts.minScore", defaultValue = "0.8") double minScore) {
        retriever = EmbeddingStoreRetriever.from(store, model, maxResults, minScore);
    }

    @Override
    public List<TextSegment> findRelevant(String text) {
        return retriever.findRelevant(text);
    }
}
