package com.bitsapplied.getvectored.application.agents.exceptions;

public class SettingPromptTemplateException extends RuntimeException {
    public SettingPromptTemplateException(String errorMessage) {
        super("Failed to set prompt template: " + errorMessage);
    }
}
