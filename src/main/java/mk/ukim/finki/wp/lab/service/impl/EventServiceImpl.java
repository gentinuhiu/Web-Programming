package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exception.EventNotFoundException;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public List<Event> listAll() {
        return EventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return EventRepository.searchEvents(text);
    }

    @Override
    public List<Event> searchEventsByRating(double rating) {
        return EventRepository.searchEventsByRating(rating);
    }

    @Override
    public Event findById(Long id)throws EventNotFoundException  {
        return EventRepository.findById(id);
    }

    @Override
    public void addEvent(Event event) {
        EventRepository.addEvent(event);
    }

    @Override
    public void updateEvent(Long id, String name, String description, double popularityScore, Location location){
        EventRepository.updateEvent(id, name, description, popularityScore, location);
    }

    @Override
    public void deleteEvent(Long id) {
        EventRepository.deleteEvent(id);
    }

    @Override
    public void decrementRating(Long id) {
        EventRepository.decrementRating(id);
    }

    @Override
    public void incrementRating(Long id) {
        EventRepository.incrementRating(id);
    }
}
