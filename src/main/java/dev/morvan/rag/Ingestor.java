package dev.morvan.rag;

import static dev.langchain4j.data.document.splitter.DocumentSplitters.recursive;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import io.quarkiverse.langchain4j.redis.RedisEmbeddingStore;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.io.File;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Slf4j
@ApplicationScoped
public class Ingestor {

    @Inject
    RedisEmbeddingStore store;

    @Inject
    EmbeddingModel embeddingModel;

    @ConfigProperty(name = "prompts.init.filepath")
    String originalDirPath;

    public void ingest(@Observes StartupEvent event) {
        List<Document> documents = FileSystemDocumentLoader.loadDocuments(
                new File(originalDirPath).toPath(),
                new TextDocumentParser());
        ingest(documents);
    }

    public void ingest(String filePath) {
        Document document = FileSystemDocumentLoader.loadDocument(
                new File(filePath).toPath(),
                new TextDocumentParser());
        ingest(Collections.singletonList(document));
        log.info("Ingested document: {}", filePath);
    }

    private void ingest(List<Document> documents) {
        log.info("Ingesting documents...");
        var ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .embeddingModel(embeddingModel)
                .documentSplitter(recursive(500, 0))
                .build();
        ingestor.ingest(documents);
        log.info("Ingested {} documents", documents.size());
    }

}
