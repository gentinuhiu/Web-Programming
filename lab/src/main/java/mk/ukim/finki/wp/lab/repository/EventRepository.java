package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exception.EventNotFoundException;
import org.springframework.stereotype.Repository;

import java.security.InvalidParameterException;
import java.util.*;

@Repository
public class EventRepository {
    public static List<Event> events = null;
    public final LocationRepository locationRepository;

    public EventRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
//        Location location1 = this.locationRepository.findAll().get(0);
//        Location location2 = this.locationRepository.findAll().get(1);
//        Location location3 = this.locationRepository.findAll().get(2);
//        Location location4 = this.locationRepository.findAll().get(3);
//        Location location5 = this.locationRepository.findAll().get(4);
//
//        events = new ArrayList<>();
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE), "New Year", "The beginning of a new year", 10, location1, false));
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE), "Halloween", "Scary Trick or Treat", 7.5, location2, false));
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE), "Christmas", "Day of Christmas", 8.75, location3, false));
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE), "Mothers Day", "Day of Mothers", 7, location4,false));
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE), "Labours Day", "Day of Labour", 4, location5,false));
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE), "Anniversary", "Creation date of XYZ", 6.5, location1,false));
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE), "Sunny Hill Concert", "Concert", 5.75, location2,false));
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE), "Fools Day", "Fool Pranks", 7, location3,false));
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE), "Saint Patricks", "Day of Luck", 3, location4,false));
//        events.add(new Event((long)(Math.random() * Long.MAX_VALUE),"Thanksgiving", "Very important day", 7.75, location5,false));
    }
    public static List<Event> findAll(){
        return events;
    }
    public static List<Event> searchEvents(String text){
        String tmp = text.toLowerCase();
        return events.stream().filter(e -> e.getName().toLowerCase().contains(tmp) || e.getDescription().toLowerCase().contains(tmp)).toList();
    }
    public static List<Event> searchEventsByRating(double rating){
        return events.stream().filter(e -> e.getPopularityScore() >= rating).toList();
    }
    public static Event findById(Long id) throws EventNotFoundException{
        Event event = events.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        if(event == null)
            throw new EventNotFoundException(String.valueOf(id));
        return event;
    }
    public static void addEvent(Event event){
        events.add(event);
    }
    public static void updateEvent(Long id, String name, String description, double popularityScore, Location location) {
        Event event = events.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        assert event != null;
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);
    }
    public static void deleteEvent(Long id){
        events.removeIf(e -> e.getId().equals(id));
    }
    public static void decrementRating(Long id){
        Event event = events.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        assert event != null;
        event.setPopularityScore(event.getPopularityScore() - 1);
        event.setRatingChanged(true);
    }
    public static void incrementRating(Long id){
        Event event = events.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        assert event != null;
        event.setPopularityScore(event.getPopularityScore() + 1);
        event.setRatingChanged(true);
    }
}
