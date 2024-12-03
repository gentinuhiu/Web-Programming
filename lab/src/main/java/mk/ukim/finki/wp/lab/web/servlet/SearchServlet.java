package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    EventService eventService;
    EventBookingService eventBookingService;
    SpringTemplateEngine templateEngine;

    public SearchServlet(EventService eventService, EventBookingService eventBookingService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String text = req.getParameter("text");
        String rating = req.getParameter("rating");

        List<Event> allEvents = new ArrayList<Event>();
        //List<Event> eventsByText = new ArrayList<>();
        //List<Event> eventsByRating = new ArrayList<>();
        List<EventBooking> allBookings = new ArrayList<>();

        if(text != null && !text.isEmpty()){
            context.setVariable("text", eventService.searchEvents(text));
            context.setVariable("textBooking", eventBookingService.searchBookings(text));

            allEvents.addAll(eventService.searchEvents(text));
            //eventsByText.addAll(eventService.searchEvents(text));

            allBookings.addAll(eventBookingService.searchBookings(text));
        }
        if(rating != null && !rating.isEmpty()) {
            context.setVariable("rating", eventService.searchEventsByRating(Double.valueOf(rating)));
            //eventsByRating.addAll(eventService.searchEventsByRating(Double.valueOf(rating)));

            allEvents.addAll(eventService.searchEventsByRating(Double.valueOf(rating)));
        }

        if(allEvents.isEmpty())
            allEvents = eventService.listAll();
        if(allBookings.isEmpty())
            allBookings = eventBookingService.listAll();

        context.setVariable("events", allEvents);
        context.setVariable("bookings", allBookings);

        templateEngine.process("listEvents.html", context, resp.getWriter());
    }
}
