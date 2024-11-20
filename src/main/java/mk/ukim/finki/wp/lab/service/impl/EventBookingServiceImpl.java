package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.EventBookingRepository;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, String numberOfTickets) throws InvalidParameterException {
        try {
            return EventBookingRepository.placeBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        }
        catch (InvalidParameterException e){
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @Override
    public List<EventBooking> listAll() {
        return EventBookingRepository.findAllBookings();
    }

    @Override
    public List<EventBooking> searchBookings(String text) {
        return EventBookingRepository.searchBookings(text);
    }
}
