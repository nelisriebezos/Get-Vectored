package com.bitsapplied.getvectored;

import com.bitsapplied.getvectored.application.ChunkService;
import com.bitsapplied.getvectored.application.Embedder;
import com.bitsapplied.getvectored.application.FileService;
import com.bitsapplied.getvectored.domain.Chunk;
import com.bitsapplied.getvectored.store.ChunkStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetVectored {


    public static void main(String[] args) throws IOException {
        final String EMBEDDING_MODEL = "text-embedding-3-small";

        FileService fileService = new FileService();
        Embedder embedder = new Embedder(EMBEDDING_MODEL);
        ChunkService chunkService = new ChunkService();
        ChunkStore chunkStore = new ChunkStore(fileService, chunkService);

        List<String> allChunkTexts = new ArrayList<>();

//        TODO: Getting test data into allChunkTexts
//        try {
//            String dragonbornContent = fileService.readFile("data/Dragonborn_chunks.json");
//            Map<String, String> dragonbornMap = JsonUtils.fromJSON(dragonbornContent, Map.class);
//            for (Map.Entry<String, String> entry : dragonbornMap.entrySet()) {
//                allChunkTexts.add(entry.getValue());
//            }
//            String humanContent = fileService.readFile("data/Human_chunks.json");
//            Map<String, String> humanMap = JsonUtils.fromJSON(humanContent, Map.class);
//            for (Map.Entry<String, String> entry : humanMap.entrySet()) {
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

        String question = "What racial traits does a Human have?";
        List<Chunk> allChunks = chunkStore.read();
        List<Double> embeddedQustion = embedder.callEmbeddedFloat(List.of(question)).get(0);

        List<Chunk> relatedChunks = chunkService.getSimilarChunks(allChunks, embeddedQustion);
        String content = "";
        for (Chunk chunk : relatedChunks) {
            content = content.concat(chunk.getText());
        }
        fileService.writeFile(".getvectored/relatedChunks.txt", content);
    }
}
