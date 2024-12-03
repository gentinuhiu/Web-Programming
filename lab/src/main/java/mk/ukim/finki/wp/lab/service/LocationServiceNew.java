package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exception.LocationNotFoundException;
import mk.ukim.finki.wp.lab.repository.ILocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class LocationServiceNew {
    private final ILocationRepository locationRepository;

    public LocationServiceNew(ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }
    public Location findById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
    }

}

