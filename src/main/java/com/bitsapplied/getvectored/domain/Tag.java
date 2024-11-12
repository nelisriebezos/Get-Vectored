package com.bitsapplied.getvectored.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag {
    private String name;
    private String description;

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
