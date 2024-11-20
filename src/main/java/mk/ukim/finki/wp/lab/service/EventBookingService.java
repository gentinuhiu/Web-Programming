package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;

import java.security.InvalidParameterException;
import java.util.List;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, String numberOfTickets) throws InvalidParameterException;
    List<EventBooking> listAll();
    List<EventBooking> searchBookings(String text);
}
