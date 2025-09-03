package org.example.model;

import java.util.List;

public class MatchWrapper {
    private Match match;
    private List<Player> players;
    private List<Event> events;

    // Getters and setters
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
}
