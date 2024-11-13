package com.bitsapplied.getvectored.application.agents;

import com.bitsapplied.getvectored.application.agents.exceptions.SettingPromptTemplateException;
import com.bitsapplied.getvectored.application.services.FileService;
import com.bitsapplied.getvectored.util.StringUtils;
import com.bitsapplied.morpheus.core.agent.impl.LLMAgent;
import com.bitsapplied.morpheus.core.env.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Chunkinator extends LLMAgent {
    private final FileService fileService;
    private String promptTemplate;
    private String systemPromptTemplate;

    public Chunkinator(Context context, String identifier, FileService fileService) {
        super(context, identifier);
        this.fileService = fileService;
    }

    public void setPrompts(String systemPromptTemplate, String promptTemplate) {
        try {
            Map<String, String> promptTemplateArguments = new HashMap<>();
            Map<String, String> systemPromptTemplateArguments = new HashMap<>();
            this.systemPromptTemplate = StringUtils.replaceArguments(systemPromptTemplate, systemPromptTemplateArguments);
            this.promptTemplate = StringUtils.replaceArguments(promptTemplate, promptTemplateArguments);
        } catch (IOException e) {
            throw new SettingPromptTemplateException(e.getMessage());
        }
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
        return "";
    }
}
