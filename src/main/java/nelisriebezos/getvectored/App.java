package nelisriebezos.getvectored;

import nelisriebezos.getvectored.application.ChunkService;
import nelisriebezos.getvectored.application.Embedder;
import nelisriebezos.getvectored.application.FileService;
import nelisriebezos.getvectored.domain.Chunk;
import nelisriebezos.getvectored.store.ChunkStore;

import java.util.ArrayList;
import java.util.List;

public class App {


    public static void main(String[] args) {
        final String EMBEDDING_MODEL = "text-embedding-3-small";

        FileService fileService = new FileService();
        Embedder embedder = new Embedder(EMBEDDING_MODEL);
        ChunkService chunkService = new ChunkService();
        ChunkStore chunkStore = new ChunkStore(fileService, chunkService);

        List<String> allChunkTexts = new ArrayList<>();

//        TODO: Getting test data into allChunkTexts
//        try {
//            String fileContent = fileService.readFile("data/test_chunks.json");
//            Map<String, String> testChunksMap = JsonUtils.fromJSON(fileContent, Map.class);
//            for (Map.Entry<String, String> entry : testChunksMap.entrySet()) {
//                allChunkTexts.add(entry.getValue());
//            }
//        } catch (JsonProcessingException e) {
//            System.out.println("Error while reading processing json");
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            System.out.println("Error while reading test data file");
//            throw new RuntimeException(e);
//        }

//        TODO: Turning the text into vectors
//        List<List<Double>> vectors = embedder.callEmbeddedFloat(allChunkTexts);

//        TODO: Creating chunks
//        List<Chunk> chunks = chunkService.createChunks(vectors, allChunkTexts);

//        TODO: Persisting chunks
//        chunkStore.persist(chunks);

//        TODO: Reading chunks
        List<Chunk> readedChunks = chunkStore.read();
        for (Chunk chunk : readedChunks) {
            System.out.println(chunk.getUuid());
        }
    }
}
