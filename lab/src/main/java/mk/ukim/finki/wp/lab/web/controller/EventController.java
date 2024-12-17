package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.model.exception.EventNotFoundException;
import mk.ukim.finki.wp.lab.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/*
@Controller
@RequestMapping("/events")
public class EventController {
    private final LocationService locationService;
    private final EventService eventService;
    private final EventBookingService bookingService;

    public EventController(LocationService locationService, EventService eventService, EventBookingService bookingService) {
        this.locationService = locationService;
        this.eventService = eventService;
        this.bookingService = bookingService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model){

        if(error != null){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("searchValue", "");
        model.addAttribute("numTickets", 1);
        model.addAttribute("events", eventService.listAll());
        model.addAttribute("bodyContent", "listEvents");
        return "master-template";
    }
    @GetMapping("/add")
    public String addEvent(Model model){
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("add", true);
        model.addAttribute("bodyContent", "addEvent");
        return "master-template";
    }
    @PostMapping("/add")
    public String saveEvent(String name, String description, String popularityScore, long locationId){
        Location location = locationService.findById(locationId);
        Event event = new Event((long)(Math.random() * Long.MAX_VALUE), name, description, Double.valueOf(popularityScore), location, false);
        eventService.addEvent(event);

        return "redirect:/events";
    }
    @GetMapping("/edit/{id}")
    public String editEvent(Model model, @PathVariable String id){
        Long eventId = Long.valueOf(id);
        try {
            Event event = eventService.findById(eventId);
            model.addAttribute("event", event);
            model.addAttribute("locations", locationService.findAllForEvent(event));
            model.addAttribute("selected", event.getLocation());
        }
        catch (Exception e){
            return "redirect:/events?error=" + e.getMessage();
        }
        model.addAttribute("bodyContent", "addEvent");
        return "master-template";
    }
    @PostMapping("/edit")
    public String editEvent(String id, String name, String description, String popularityScore, long locationId){
        Location location = locationService.findById(locationId);
        eventService.updateEvent(Long.valueOf(id), name, description, Double.valueOf(popularityScore), location);
        return "redirect:/events";
    }
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return "redirect:/events";
    }
    @GetMapping("/increment/{id}")
    public String increment(@PathVariable Long id){
        eventService.incrementRating(id);
        return "redirect:/events";
    }
    @GetMapping("/decrement/{id}")
    public String decrement(@PathVariable Long id){
        eventService.decrementRating(id);
        return "redirect:/events";
    }
    @PostMapping("/search")
    public String search(@RequestParam String text, Model model, @RequestParam(required = false) String error){
        List<Event> events;
        try{
            double rating = Double.parseDouble(text);
            events = eventService.searchEventsByRating(rating);
        }
        catch (Exception e){
            events = eventService.searchEvents(text);
        }
        if(error != null){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("searchValue", text);
        model.addAttribute("numTickets", 1);
        model.addAttribute("events", events);
        model.addAttribute("bodyContent", "listEvents");
        return "master-template";
    }
}
*/
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventServiceNew eventService;
    private final LocationServiceNew locationService;
    private final CategoryServiceNew categoryService;

    public EventController(EventServiceNew eventService, LocationServiceNew locationService, CategoryServiceNew categoryService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllEvents(Model model) {
        model.addAttribute("searchValue", "");
        model.addAttribute("numTickets", 1);
        model.addAttribute("events", eventService.findAll());
        model.addAttribute("bodyContent", "listEvents");
        return "master-template";
    }
    @GetMapping("/add")
    public String addEvent(Model model){
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("add", true);
        model.addAttribute("hasEvent", false);
        model.addAttribute("bodyContent", "addEvent");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveEvent(String name, String description, String popularityScore, long locationId, long categoryId){
        Location location = locationService.findById(locationId);
        Category category = categoryService.findById(categoryId);
        eventService.save(new Event(name, description, Double.valueOf(popularityScore), location, false, category));
        return "redirect:/events";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(Model model, @PathVariable String id){
        Long eventId = Long.valueOf(id);
        try {
            Event event = eventService.findById(eventId);
            model.addAttribute("event", event);
            model.addAttribute("locations", locationService.findAll());
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("selected", event.getLocation());
            model.addAttribute("selected_category", event.getCategory());
        }
        catch (Exception e){
            return "redirect:/events?error=" + e.getMessage();
        }
        model.addAttribute("bodyContent", "addEvent");
        return "master-template";
    }
    @PostMapping("/edit")
    public String editEvent(String id, String name, String description, String popularityScore, long locationId, long categoryId) throws EventNotFoundException {
        Location location = locationService.findById(locationId);
        Category category = categoryService.findById(categoryId);
        Event event = eventService.findById(Long.valueOf(id));
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(Double.parseDouble(popularityScore));
        event.setLocation(location);
        event.setCategory(category);
        eventService.save(event);
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }
    @PostMapping("/search")
    public String search(@RequestParam String text, Model model, @RequestParam(required = false) String error){
        /*List<Event> events;
        try{
            double rating = Double.parseDouble(text);
            events = eventService.searchEventsByRating(rating);
        }
        catch (Exception e){
            events = eventService.searchEvents(text);
        }
        if(error != null){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }*/
        List<Event> events = eventService.findAll();
        events = events.stream().filter(e -> e.getLocation().getName().toLowerCase().contains(text.toLowerCase())).toList();
        if(text.isEmpty())
            events = eventService.findAll();
        model.addAttribute("searchValue", text);
        model.addAttribute("numTickets", 1);
        model.addAttribute("events", events);
        model.addAttribute("bodyContent", "listEvents");
        return "master-template";
    }
}
