package com.bitsapplied.getvectored.application.agents;

import com.bitsapplied.getvectored.application.agents.exceptions.SettingPromptTemplateException;
import com.bitsapplied.getvectored.application.services.FileService;
import com.bitsapplied.getvectored.domain.Tag;
import com.bitsapplied.getvectored.util.JsonUtils;
import com.bitsapplied.getvectored.util.StringUtils;
import com.bitsapplied.morpheus.core.agent.InteractionMode;
import com.bitsapplied.morpheus.core.agent.impl.LLMAgent;
import com.bitsapplied.morpheus.core.env.Context;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Taginator extends LLMAgent {
//    todo: figure out where to store list of tags
    private final String TAG_FILE = ".getvectored/tags.json";
    private final FileService fileService;
    private String promptTemplate;
    private String systemPromptTemplate;

    public Taginator(FileService fileService, Context context, String identifier) {
        super(context, identifier);
        this.fileService = fileService;
        setInteractionMode(InteractionMode.SPEAK_WHEN_SPOKEN_TO);
    }

    public void setPrompts(String systemPromptTemplate, String promptTemplate) {
        try {
            Map<String, String> systemPromptTemplateArguments = new HashMap<>();
            systemPromptTemplateArguments.put("tags", getTags());
            this.systemPromptTemplate = StringUtils.replaceArguments(systemPromptTemplate, systemPromptTemplateArguments);
            this.promptTemplate = promptTemplate;
        } catch (IOException e) {
            throw new SettingPromptTemplateException(e.getMessage());
        }
    }

    public String getTags() throws IOException {
        String stringifiedTags = fileService.readFile(TAG_FILE);
        List<Tag> tags = JsonUtils.fromJSON(stringifiedTags, new TypeReference<>() {});
        return tags.toString();
    }

    @Override
    public String getPromptTemplate() {
        return this.promptTemplate;
    }

    @Override
    public String getSystemPromptTemplate() {
        return this.systemPromptTemplate;
    }

    @Override
    public String getPlanPromptTemplate() {
        return null;
    }
}
