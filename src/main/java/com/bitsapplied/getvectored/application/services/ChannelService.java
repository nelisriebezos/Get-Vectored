package com.bitsapplied.getvectored.application.services;

import com.bitsapplied.morpheus.core.agent.Agent;
import com.bitsapplied.morpheus.core.agent.Identity;
import com.bitsapplied.morpheus.core.agent.collaboration.Channel;
import com.bitsapplied.morpheus.core.agent.collaboration.ChannelListener;
import com.bitsapplied.morpheus.core.agent.collaboration.ChannelMessage;
import com.bitsapplied.morpheus.core.env.ChannelStore;
import com.bitsapplied.morpheus.core.env.LastReadTracker;
import com.bitsapplied.morpheus.core.streaming.StreamMessage;
import com.bitsapplied.morpheus.core.util.AbortRequestedException;

import java.util.ArrayList;
import java.util.List;

public class ChannelService implements ChannelListener {
    private final ChannelStore channelStore;
    private List<Agent> agents = new ArrayList<>();

    public ChannelService(ChannelStore channelStore) {
        this.channelStore = channelStore;
    }

    public Channel getChannel(String channelId) {
        return channelStore.getChannel(channelId);
    }

    public Channel newChannel(String channelId, String channelDescriptionS1, List<Agent> agents) {
//        todo: Need to handle passing agents to newChannel() better
        if (!channelStore.channelExists(channelId)) {
            channelStore.newChannel(channelId, channelDescriptionS1, agents);
        }
        Channel channel = channelStore.getChannel(channelId);
        channel.addChannelListener(this);
        return channel;
    }

    public void postMessage(String channelId, String sender, String text) {
        Channel channel = channelStore.getChannel(channelId);
        channel.postMessage(sender, text);
    }

    @Override
    public void receive(ChannelMessage channelMessage) {
        System.out.println(String.format("[%s] %s", channelMessage.getSender(), channelMessage.getText()));
    }

    @Override
    public void messageRead(ChannelMessage channelMessage, Identity identity) {

    }

    @Override
    public void trackerUpdated(LastReadTracker lastReadTracker) {

    }

    @Override
    public void receive(StreamMessage streamMessage) throws AbortRequestedException {
    }
}
