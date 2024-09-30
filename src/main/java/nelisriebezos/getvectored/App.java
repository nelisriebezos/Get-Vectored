 package nelisriebezos.getvectored;

 import com.fasterxml.jackson.core.JsonProcessingException;
 import nelisriebezos.getvectored.application.ChunkService;
 import nelisriebezos.getvectored.application.Embedder;
 import nelisriebezos.getvectored.application.Workspace;
 import nelisriebezos.getvectored.domain.Chunk;
 import nelisriebezos.getvectored.util.JsonUtils;

 import java.io.IOException;
 import java.lang.reflect.Array;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.logging.Logger;

 public class App {



    public static void main(String[] args) {
//        EmbeddingDemo embeddingDemo = new EmbeddingDemo();
//        embeddingDemo.demoCallEmbeddingBase64();

        final String EMBEDDIGNS_PATH = ".getvectored/embeddings.json";
        final String EMBEDDING_MODEL = "text-embedding-3-small";

        Workspace workspace = new Workspace();
        Embedder embedder = new Embedder(EMBEDDING_MODEL);
        ChunkService chunkService = new ChunkService();

        List<String> allChunkTexts = new ArrayList<>();


        workspace.createDirectory(".getvectored");

        try {
            String fileContent = workspace.readFile("data/test_chunks.json");
            Map<String, String> testChunksMap = JsonUtils.fromJSON(fileContent, Map.class);
//            Map<String, String> dragonbornChunksMap = JsonUtils.fromJSON("data/Dragonborn_chunks.json", Map.class);
//            Map<String, String> humanChunksMap = JsonUtils.fromJSON("data/Human_chunks.json", Map.class);

//            for (Map.Entry<String, String> entry : dragonbornChunksMap.entrySet()) {
//                allChunkTexts.add(entry.getValue());
//            }
//            for (Map.Entry<String, String> entry : humanChunksMap.entrySet()) {
//                allChunkTexts.add(entry.getValue());
//            }

            for (Map.Entry<String, String> entry : testChunksMap.entrySet()) {
                allChunkTexts.add(entry.getValue());
            }
        } catch (JsonProcessingException e) {
            System.out.println("Error while reading processing json");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error while reading test data file");
            throw new RuntimeException(e);
        }

        List<String> embeddings = embedder.callEmbeddingBase64(allChunkTexts);
        List<Chunk> chunks = chunkService.createChunks( embeddings, allChunkTexts);

        try {
            String jsonChunks = JsonUtils.toJSON(chunks);
            workspace.writeFile(EMBEDDIGNS_PATH, jsonChunks);
        } catch (JsonProcessingException e) {
            System.out.println("Error while writing the json chunks");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error while writing the embeddings file");
            throw new RuntimeException(e);
        }
    }
}
