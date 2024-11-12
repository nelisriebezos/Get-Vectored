package com.bitsapplied.getvectored.store;

import com.bitsapplied.getvectored.application.services.FileService;
import com.bitsapplied.getvectored.domain.Tag;
import com.bitsapplied.getvectored.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class TagStore {
    private final Logger logger = Logger.getLogger(TagStore.class.getName());
    private final String STORAGE_FOLDER = ".getvectored";
    private final String TAG_FILE = "/tag-index.json";
    private final FileService fileService;

    public TagStore(FileService fileService) {
        this.fileService = fileService;
    }

    public void Persist(List<Tag> tags) {
        String path = STORAGE_FOLDER + TAG_FILE;
        try {
            String json = JsonUtils.toJSON(tags);
            fileService.writeFile(path, json);
        } catch (JsonProcessingException e) {
            logger.warning("Failed to convert to JSON: " + e.getMessage());
        } catch (IOException e) {
            logger.warning("Failed to write the JSON file: " + e.getMessage());
        }
    }

    public List<Tag> Read() {
        String path = STORAGE_FOLDER + TAG_FILE;
        List<Tag> tags = null;
        try {
            String json = fileService.readFile(path);
            tags = JsonUtils.fromJSON(json, List.class);
        } catch (JsonProcessingException e) {
            logger.warning("Failed to convert from JSON: " + e.getMessage());
        } catch (IOException e) {
            logger.warning("Failed to read the JSON file: " + e.getMessage());
        }
        return tags;
    }
}
