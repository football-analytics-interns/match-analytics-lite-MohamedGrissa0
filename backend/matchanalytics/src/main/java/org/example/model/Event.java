package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment PK
    private Long id;

    private int minute;

    private String type;

    @Column(name = "player_id")
    private Long playerId;

    // Store meta as JSON string in DB
    @Column(columnDefinition = "TEXT")
    private String meta;

    // --- Constructors ---
    public Event() {}

    public Event(int minute, String type, Long playerId, String meta) {
        this.minute = minute;
        this.type = type;
        this.playerId = playerId;
        this.meta = meta;
    }

    // --- Getters & Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}
