package com.bitsapplied.getvectored.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class Chunk {
    private UUID uuid;
    private String text;
    private List<Double> vectorFloat;

    public Chunk(String text, List<Double> vectorFloat) {
        this.uuid = UUID.randomUUID();
        this.text = text;
        this.vectorFloat = vectorFloat;
    }
}
