package dev.morvan.interceptor;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.retriever.Retriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import io.quarkiverse.langchain4j.redis.RedisEmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TextRetriever implements Retriever<TextSegment> {

    private final EmbeddingStoreRetriever retriever;

    TextRetriever(RedisEmbeddingStore store, EmbeddingModel model) {
        retriever = EmbeddingStoreRetriever.from(store, model, 20, 0.9);
    }

    @Override
    public List<TextSegment> findRelevant(String text) {
        return retriever.findRelevant(text);
    }
}
