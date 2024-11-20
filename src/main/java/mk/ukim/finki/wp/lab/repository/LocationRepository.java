package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LocationRepository {
    private static List<Location> locations = null;
    public LocationRepository() {
        locations = new ArrayList<>();
        locations.add(new Location((long)(Math.random() * Long.MAX_VALUE), "Location-1", "Address-1", "100", "This is the first location"));
        locations.add(new Location((long)(Math.random() * Long.MAX_VALUE), "Location-2", "Address-2", "200", "This is the second location"));
        locations.add(new Location((long)(Math.random() * Long.MAX_VALUE), "Location-3", "Address-3", "300", "This is the third location"));
        locations.add(new Location((long)(Math.random() * Long.MAX_VALUE), "Location-4", "Address-4", "400", "This is the fourth location"));
        locations.add(new Location((long)(Math.random() * Long.MAX_VALUE), "Location-5", "Address-5", "500", "This is the fifth location"));
    }
    public static List<Location> findAll(){
        return locations;
    }
    public static Location findById(Long id){
        return locations.stream().filter(location -> location.getId().equals(id)).findFirst().orElse(null);
    }
    public static List<Location> findAllForEvent(Event event){
        return locations.stream().filter(l -> !l.getId().equals(event.getLocation().getId())).toList();
    }

}
