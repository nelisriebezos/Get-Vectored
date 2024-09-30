package nelisriebezos.getvectored.application;

import io.github.sashirestela.openai.BaseSimpleOpenAI;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.embedding.EmbeddingBase64;
import io.github.sashirestela.openai.domain.embedding.EmbeddingFloat;
import io.github.sashirestela.openai.domain.embedding.EmbeddingRequest;

import java.util.List;

public class Embedder {
    private BaseSimpleOpenAI openAI;
    private  String embeddingModel;

    public Embedder(String embeddingModel) {
        this.embeddingModel = embeddingModel;
//        TODO: Provide apiKey and organizationId as arguments perhaps?
        String apiKey = System.getenv("OPENAI_GETVECTORED_KEY");
        String organizationId = System.getenv("OPENAI_ORGANIZATION_ID");
        this.openAI = SimpleOpenAI.builder()
                .apiKey(apiKey)
                .organizationId(organizationId)
                .build();
    }

    public List<String> callEmbeddingBase64(List<String> input) {
        var embeddingRequest = EmbeddingRequest.builder()
                .model(embeddingModel)
                .input(input)
                .build();
        var futureEmbedding = openAI.embeddings().createBase64(embeddingRequest);
        var embeddingResponse = futureEmbedding.join();
        return embeddingResponse.getData()
                .stream()
                .map(EmbeddingBase64::getEmbedding)
                .toList();
    }

    public List<List<Double>> callEmbeddedFloat(List<String> input) {
        var embeddingRequest = EmbeddingRequest.builder()
                .model(embeddingModel)
                .input(input)
                .build();
        var futureEmbedding = openAI.embeddings().create(embeddingRequest);
        var embeddingResponse = futureEmbedding.join();
        return embeddingResponse.getData()
                .stream()
                .map(EmbeddingFloat::getEmbedding).toList();
    }
}
