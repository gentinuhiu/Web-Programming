package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.awt.print.Book;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class EventBookingController{
    EventBookingService eventBookingService;
    EventService eventService;

    public EventBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }
    @GetMapping
    public String index(Model model) {
        model.addAttribute("searchValue", "");
        model.addAttribute("bookings", eventBookingService.listAll());
        model.addAttribute("bodyContent", "listBookings");
        return "master-template";
    }
    @PostMapping("/book")
    public String book(Model model, @RequestParam(required = false) String event, @RequestParam String numTickets){
        String attendeeName = "Petko Petkov";
        String attendeeAddress = "Address";
        try {
            EventBooking eventBooking = eventBookingService.placeBooking(event, attendeeName, attendeeAddress, numTickets);
            model.addAttribute("eventBooking", eventBooking);
            model.addAttribute("bodyContent", "bookingConfirmation");
            return "master-template";
        }
        catch (InvalidParameterException e){
            return "redirect:/events?error=" + e.getMessage();
        }
    }
    @PostMapping("/search")
    public String search(Model model, @RequestParam String text){
        List<EventBooking> bookings = eventBookingService.searchBookings(text);
        model.addAttribute("searchValue", text);
        model.addAttribute("bookings", bookings);
        model.addAttribute("bodyContent", "listBookings");
        return "master-template";
    }
}
