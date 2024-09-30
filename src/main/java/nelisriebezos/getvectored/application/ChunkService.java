package nelisriebezos.getvectored.application;

import nelisriebezos.getvectored.domain.Chunk;

import java.util.ArrayList;
import java.util.List;

public class ChunkService {

    public List<Chunk> createChunks(List<String> embeddings, List<String> chunkContent) {
        ArrayList<Chunk> chunks = new ArrayList<>();
        for (int i = 0; i < embeddings.size(); i++) {
            String text = chunkContent.get(i);
            chunks.add(createChunk(embeddings.get(i), text));
        }
        return chunks;
    }

    public Chunk createChunk(String embedding, String text) {
        return new Chunk(text, embedding);
    }


}
