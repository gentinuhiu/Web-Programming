package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.LocationServiceNew;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {
    private final LocationServiceNew locationService;

    public LocationController(LocationServiceNew locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public String getAllLocations(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "master-template";
    }

    @PostMapping("/add")
    public String createLocation(@RequestBody Location location) {
        locationService.save(location);
        return "master-template";
    }
}
