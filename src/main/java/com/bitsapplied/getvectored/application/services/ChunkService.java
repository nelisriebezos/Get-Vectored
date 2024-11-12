package com.bitsapplied.getvectored.application.services;

import com.bitsapplied.getvectored.domain.Chunk;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import java.util.ArrayList;
import java.util.List;


public class ChunkService {
    private double threshold = 0.7;

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

    public Double CosineSimilarity(List<Double> questionVector, List<Double> dataVector) {
        RealVector v1 = new ArrayRealVector(questionVector.toArray(new Double[0]));
        RealVector v2 = new ArrayRealVector(dataVector.toArray(new Double[0]));

        double dotProduct = v1.dotProduct(v2);
        double magnitude1 = v1.getNorm();
        double magnitude2 = v2.getNorm();

        Double result = dotProduct / (magnitude1 * magnitude2);
        System.out.println(result);
        return result;
    }

    public List<Chunk> getSimilarChunks(List<Chunk> chunks, List<Double> questionVector) {
        List<Chunk> similarChunks = new ArrayList<>();
        for (Chunk chunk : chunks) {
            if (CosineSimilarity(questionVector, chunk.getVectorFloat()) >= threshold) {
                similarChunks.add(chunk);
            }
        }
        return similarChunks;
    }
}
