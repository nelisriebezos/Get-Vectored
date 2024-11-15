package com.bitsapplied.getvectored.application.agents;

import com.bitsapplied.getvectored.application.services.FileService;
import com.bitsapplied.getvectored.util.StringUtils;
import com.bitsapplied.morpheus.core.agent.impl.LLMAgent;
import com.bitsapplied.morpheus.core.env.Context;

import java.util.HashMap;
import java.util.Map;

public class Fragmentinator extends LLMAgent {
    private final FileService fileService;
    private String promptTemplate;
    private String systemPromptTemplate;

    public Fragmentinator(Context context, String identifier, FileService fileService) {
        super(context, identifier);
        this.fileService = fileService;
    }

    public void setPrompts(String systemPromptTemplate, String promptTemplate) {
            Map<String, String> promptTemplateArguments = new HashMap<>();
            Map<String, String> systemPromptTemplateArguments = new HashMap<>();
            this.systemPromptTemplate = StringUtils.replaceArguments(systemPromptTemplate, systemPromptTemplateArguments);
            this.promptTemplate = StringUtils.replaceArguments(promptTemplate, promptTemplateArguments);
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
