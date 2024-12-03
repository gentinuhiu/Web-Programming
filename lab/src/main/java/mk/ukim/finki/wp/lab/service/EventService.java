package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exception.EventNotFoundException;

import java.util.*;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    List<Event> searchEventsByRating(double rating);
    Event findById(Long id) throws EventNotFoundException ;
    void addEvent(Event event);
    void updateEvent(Long id, String name, String description, double popularityScore, Location location);
    void deleteEvent(Long id);
    void decrementRating(Long id);
    void incrementRating(Long id);
}
