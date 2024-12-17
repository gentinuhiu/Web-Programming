package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exception.EventNotFoundException;
import mk.ukim.finki.wp.lab.model.exception.LocationNotFoundException;
import mk.ukim.finki.wp.lab.repository.IEventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceNew {
    private final IEventRepository eventRepository;

    public EventServiceNew(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<Event> findByLocationId(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
    public Event findById(Long id) throws EventNotFoundException {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }
}
