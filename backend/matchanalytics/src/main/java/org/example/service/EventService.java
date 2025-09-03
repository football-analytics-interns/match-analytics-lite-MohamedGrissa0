package org.example.service;

import org.example.model.Event;
import org.example.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event updateEvent(Long id, Event event) {
        Event existing = eventRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setMinute(event.getMinute());
        existing.setType(event.getType());
        existing.setPlayerId(event.getPlayerId());
        existing.setMeta(event.getMeta());

        return eventRepository.save(existing);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
