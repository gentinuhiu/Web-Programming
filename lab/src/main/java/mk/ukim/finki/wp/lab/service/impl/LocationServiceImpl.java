package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Override
    public List<Location> findAll() {
        return LocationRepository.findAll();
    }

    @Override
    public Location findById(Long id) {
        return LocationRepository.findById(id);
    }

    @Override
    public List<Location> findAllForEvent(Event event) {
        return LocationRepository.findAllForEvent(event);
    }
}
