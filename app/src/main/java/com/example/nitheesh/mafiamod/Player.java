package com.example.nitheesh.mafiamod;

import java.io.Serializable;

public abstract class Player implements Serializable {
    public final String name;
    public final String description;

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
