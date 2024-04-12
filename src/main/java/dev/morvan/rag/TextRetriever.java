package dev.morvan.rag;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import io.quarkiverse.langchain4j.redis.RedisEmbeddingStore;
import jakarta.inject.Singleton;
import java.util.function.Supplier;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class TextRetriever implements Supplier<RetrievalAugmentor> {

    private final RetrievalAugmentor augmentor;

    TextRetriever(RedisEmbeddingStore store, EmbeddingModel model,
            @ConfigProperty(name = "prompts.maxResults", defaultValue = "5") int maxResults,
            @ConfigProperty(name = "prompts.minScore", defaultValue = "0.8") double minScore) {
        EmbeddingStoreContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(model)
                .embeddingStore(store)
                .maxResults(maxResults)
                .minScore(minScore)
                .build();
        augmentor = DefaultRetrievalAugmentor.builder()
                .contentRetriever(contentRetriever)
                .build();
    }

    @Override
    public RetrievalAugmentor get() {
        return augmentor;
    }
}
