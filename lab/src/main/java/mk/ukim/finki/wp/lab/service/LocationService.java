package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;

import java.util.List;

public interface LocationService {
    public List<Location> findAll();
    Location findById(Long id);
    public List<Location> findAllForEvent(Event event);
}
