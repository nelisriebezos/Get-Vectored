package com.bitsapplied.getvectored.application.agents;

import com.bitsapplied.morpheus.core.agent.InteractionMode;
import com.bitsapplied.morpheus.core.agent.impl.LLMAgent;
import com.bitsapplied.morpheus.core.env.Context;

public class DnDAgent extends LLMAgent {
    private String promptTemplate;
    private String systemPromptTemplate;

    public DnDAgent(Context context, String identifier) {
        super(context, identifier);
        setInteractionMode(InteractionMode.SPEAK_WHEN_SPOKEN_TO);
    }

    public void setPrompts(String systemPromptTemplate, String promptTemplate) {
        this.promptTemplate = promptTemplate;
        this.systemPromptTemplate = systemPromptTemplate;
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
