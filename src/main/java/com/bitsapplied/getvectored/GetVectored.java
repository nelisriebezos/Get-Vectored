package com.bitsapplied.getvectored;

import com.bitsapplied.getvectored.application.ContextManager;
import com.bitsapplied.getvectored.application.agents.DnDAgent;
import com.bitsapplied.getvectored.application.services.ChannelService;
import com.bitsapplied.morpheus.core.agent.HumanAgent;
import com.bitsapplied.morpheus.core.agent.InteractionMode;
import com.bitsapplied.morpheus.core.agent.collaboration.Channel;
import com.bitsapplied.morpheus.core.agent.collaboration.ChannelMessage;
import com.bitsapplied.morpheus.core.env.Context;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GetVectored {


    public static void main(String[] args) throws IOException {
//        EmbeddingDemo demo = new EmbeddingDemo();
//        demo.demoCallEmbeddingBase64();

//        final String EMBEDDING_MODEL = "text-embedding-3-small";
//
//        FileService fileService = new FileService();
//        EmbeddingService embeddingService = new EmbeddingService(EMBEDDING_MODEL);
//        ChunkService chunkService = new ChunkService();
//        ChunkStore chunkStore = new ChunkStore(fileService);
//
//        List<String> allChunkTexts = new ArrayList<>();
//
////        TODO: Getting test data into allChunkTexts
////        try {
////            String dragonbornContent = fileService.readFile("data/Dragonborn_chunks.json");
////            Map<String, String> dragonbornMap = JsonUtils.fromJSON(dragonbornContent, Map.class);
////            for (Map.Entry<String, String> entry : dragonbornMap.entrySet()) {
////                allChunkTexts.add(entry.getValue());
////            }
////            String humanContent = fileService.readFile("data/Human_chunks.json");
////            Map<String, String> humanMap = JsonUtils.fromJSON(humanContent, Map.class);
////            for (Map.Entry<String, String> entry : humanMap.entrySet()) {
////                allChunkTexts.add(entry.getValue());
////            }
////        } catch (JsonProcessingException e) {
////            System.out.println("Error while reading processing json");
////            throw new RuntimeException(e);
////        } catch (IOException e) {
////            System.out.println("Error while reading test data file");
////            throw new RuntimeException(e);
////        }
//
////        TODO: Turning the text into vectors
////        List<List<Double>> vectors = embedder.callEmbeddedFloat(allChunkTexts);
//
////        TODO: Creating chunks
////        List<Chunk> chunks = chunkService.createChunks(vectors, allChunkTexts);
//
////        TODO: Persisting chunks
////        chunkStore.persist(chunks);
//
//        String question = "What racial traits does a Human have?";
//        List<Chunk> allChunks = chunkStore.read();
//        List<Double> embeddedQustion = embeddingService.callEmbeddedFloat(List.of(question)).getFirst();
//
//        List<Chunk> relatedChunks = chunkService.getSimilarChunks(allChunks, embeddedQustion);
//        String content = "";
//        for (Chunk chunk : relatedChunks) {
//            content = content.concat(chunk.getText());
//        }
//        fileService.writeFile(".getvectored/relatedChunks.txt", content);


        ContextManager contextManager = new ContextManager();
        Context context = contextManager.createContext();
        ChannelService channelService = new ChannelService(context.getChannelStore());
        String CHANNEL_ID = "testChannel";
        String PLAYER_ID = "player";

        HumanAgent humanAgent = new HumanAgent(context, "player");
        DnDAgent agent = new DnDAgent(context, "helpdesk");
        agent.setInteractionMode(InteractionMode.SPEAK_ALWAYS);
        agent.setPrompts("You are an expert when it comes to dnd, and you are designed to help players out with playing the game.",
                "Summary:\n${_summary}\n${_prompt}\n");
        channelService.newChannel(CHANNEL_ID, "channelDescriptionS1", Arrays.asList(humanAgent, agent));

        for (ChannelMessage channelMessage : channelService.getChannel(CHANNEL_ID).getMessages()) {
            System.out.println(String.format("[%s] %s", channelMessage.getSender(), channelMessage.getText()));
        }

        Scanner scanner = new Scanner(System.in);
        String line;

        while (true) {
            System.out.print(String.format("[%s] ", humanAgent.getIdentifier()));
            line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            channelService.postMessage(CHANNEL_ID, PLAYER_ID, line);
        }
        scanner.close();
    }
}
