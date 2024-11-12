package com.bitsapplied.getvectored.application;

import com.bitsapplied.morpheus.core.Constants;
import com.bitsapplied.morpheus.core.env.Context;
import com.bitsapplied.morpheus.core.env.impl.ContextImpl;
import com.bitsapplied.morpheus.core.util.VariableMap;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ContextManager {
    public ContextManager() {
    }

    public Context createContext() {
        String openAiToken = System.getenv("OPENAI_GETVECTORED_KEY");
        if (StringUtils.isBlank(openAiToken))
            throw new IllegalArgumentException("Please set the environment variable OPEN_AI_TOKEN");

        String rootFolder = System.getProperty("user.dir");
        if (StringUtils.isBlank(rootFolder))
            throw new IllegalArgumentException("Please set the environment variable ROOT_FOLDER");

        VariableMap settings = new VariableMap();
        settings.setProperty(Constants.OPENAITOKEN, openAiToken);
//        todo: ai model beter meegeven (spring .properties achtig iets toevoegen)
        settings.setProperty(Constants.OPENAIMODEL, "gpt-4o-mini");
        Path workspacePath = Paths.get(rootFolder);
        return new ContextImpl(workspacePath, settings);
    }
}
