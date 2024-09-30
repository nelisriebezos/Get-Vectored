package nelisriebezos.getvectored;

import java.util.Arrays;

import io.github.sashirestela.openai.BaseSimpleOpenAI;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.embedding.EmbeddingBase64;
import io.github.sashirestela.openai.domain.embedding.EmbeddingFloat;
import io.github.sashirestela.openai.domain.embedding.EmbeddingRequest;

public class EmbeddingDemo {
	private BaseSimpleOpenAI openAI;
	
	
	public EmbeddingDemo() {
		String apiKey = System.getenv("OPENAI_GETVECTORED_KEY");
		String organizationId = System.getenv("OPENAI_ORGANIZATION_ID");
		this.openAI = SimpleOpenAI.builder()
				.apiKey(apiKey)
				.organizationId(organizationId)
				.build();
	}

    public void demoCallEmbeddingBase64() {
        var embeddingRequest = EmbeddingRequest.builder()
                .model("text-embedding-3-small")
                .input(Arrays.asList(
                        "shiny sun",
                        "blue sky"))
                .build();
        var futureEmbedding = openAI.embeddings().createBase64(embeddingRequest);
        var embeddingResponse = futureEmbedding.join();
        embeddingResponse.getData()
                .stream()
                .map(EmbeddingBase64::getEmbedding)
                .forEach(System.out::println);
    }

	public void demoCallEmbeddedFloat() {
		var embeddingRequest = EmbeddingRequest.builder()
				.model("text-embedding-3-small")
				.input(Arrays.asList(
						"shiny sun",
						"blue sky"
						))
				.build();
        var futureEmbedding = openAI.embeddings().create(embeddingRequest);
        var embeddingResponse = futureEmbedding.join();
        embeddingResponse.getData()
                .stream()
                .map(EmbeddingFloat::getEmbedding)
                .forEach(System.out::println);
		
	}
}
