package nelisriebezos.getvectored.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import nelisriebezos.getvectored.application.ChunkService;
import nelisriebezos.getvectored.application.FileService;
import nelisriebezos.getvectored.domain.Chunk;
import nelisriebezos.getvectored.util.JsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChunkStore {
    private static final Logger logger = Logger.getLogger(ChunkStore.class.getName());
    private static final String STORAGE_FOLDER = ".getvectored";
    private static final String CHUNK_FILE = "/chunks.json";
    private final FileService fileService;
    private final ChunkService chunkService;

    public ChunkStore(FileService fileService, ChunkService chunkService) {
        this.fileService = fileService;
        this.chunkService = chunkService;
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
