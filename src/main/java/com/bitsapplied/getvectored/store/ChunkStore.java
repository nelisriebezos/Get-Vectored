package com.bitsapplied.getvectored.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.bitsapplied.getvectored.application.services.FileService;
import com.bitsapplied.getvectored.domain.Chunk;
import com.bitsapplied.getvectored.util.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChunkStore {
    private final Logger logger = Logger.getLogger(ChunkStore.class.getName());
    private final String STORAGE_FOLDER = ".getvectored";
    private final String CHUNK_FILE = "/chunks.json";
    private final FileService fileService;

    public ChunkStore(FileService fileService) {
        this.fileService = fileService;
    }

    public void persist(List<Chunk> chunks) {
        String path = STORAGE_FOLDER + CHUNK_FILE;
        try {
            String json = JsonUtils.toJSON(chunks);
            fileService.writeFile(path, json);
        } catch (JsonProcessingException e) {
            logger.warning("Failed to convert to JSON: " + e.getMessage());
        } catch (IOException e) {
            logger.warning("Failed to write the JSON file: " + e.getMessage());
        }
    }

    public List<Chunk> read() {
        String path = STORAGE_FOLDER + CHUNK_FILE;
        List<Chunk> chunks = new ArrayList<>();
        try {
            String json = fileService.readFile(path);
            chunks = JsonUtils.fromJSON(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            logger.warning("Failed to convert from JSON: " + e.getMessage());
        } catch (IOException e) {
            logger.warning("Failed to read the JSON file: " + e.getMessage());
        }
        return chunks;
    }
}
