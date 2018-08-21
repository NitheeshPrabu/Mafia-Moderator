package com.example.nitheesh.mafiamod;

public class Role extends Player {
    private int players = 0;
    private boolean isCollapsed = true;
    public String playerName;

    public Role(String name, String description) {
        super(name, description);
    }

    public int getPlayers() {
        return players;
    }

    public void addPlayer() {
        players++;
    }

    public void removePlayer() {
        if (players > 0) {
            players--;
        }
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public void setCollapsed(boolean collapsed) {
        isCollapsed = collapsed;
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public void resetNumPlayers() {
        players = 0;
    }
}
