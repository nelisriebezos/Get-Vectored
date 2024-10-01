package nelisriebezos.getvectored.application;

import nelisriebezos.getvectored.domain.Chunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkService {

    public List<Chunk> createChunks(List<List<Double>> vectors, List<String> chunkContent) {
        ArrayList<Chunk> chunks = new ArrayList<>();
        for (int i = 0; i < vectors.size(); i++) {
            String text = chunkContent.get(i);
            chunks.add(createChunkFloat(vectors.get(i), text));
        }
        return chunks;
    }

//    public Chunk createChunkBase64(String embedding, String text) {
//        return new Chunk(text, embedding);
//    }

    public Chunk createChunkFloat(List<Double> vector, String text) {
        return new Chunk(text, vector);
    }


}
