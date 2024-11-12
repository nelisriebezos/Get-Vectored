package com.bitsapplied.getvectored.application.services;

import com.bitsapplied.morpheus.core.Constants;
import com.bitsapplied.morpheus.core.agent.Agent;
import com.bitsapplied.morpheus.core.agent.Identity;
import com.bitsapplied.morpheus.core.agent.collaboration.Channel;
import com.bitsapplied.morpheus.core.agent.collaboration.ChannelListener;
import com.bitsapplied.morpheus.core.agent.collaboration.ChannelMessage;
import com.bitsapplied.morpheus.core.env.ChannelStore;
import com.bitsapplied.morpheus.core.env.Context;
import com.bitsapplied.morpheus.core.env.LastReadTracker;
import com.bitsapplied.morpheus.core.env.impl.ContextImpl;
import com.bitsapplied.morpheus.core.streaming.StreamMessage;
import com.bitsapplied.morpheus.core.util.AbortRequestedException;
import com.bitsapplied.morpheus.core.util.VariableMap;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ChannelService implements ChannelListener {
    private final ChannelStore channelStore;
    private List<Agent> agents = new ArrayList<>();

    public ChannelService(ChannelStore channelStore) {
        this.channelStore = channelStore;
    }

    public void newChannel(String channelId, String channelDescriptionS1, List<Agent> agents) {
        if (!channelStore.channelExists(channelId)) {
            channelStore.newChannel(channelId, channelDescriptionS1, );
        }
        Channel channel = channelStore.getChannel(channelId);
        channel.addChannelListener(this);
    }

    @Override
    public void receive(ChannelMessage channelMessage) {
    }

    @Override
    public void messageRead(ChannelMessage channelMessage, Identity identity) {

    }

    @Override
    public void trackerUpdated(LastReadTracker lastReadTracker) {

    }

    @Override
    public void receive(StreamMessage streamMessage) throws AbortRequestedException {
        System.out.println(streamMessage.getTextLn());
    }
}
