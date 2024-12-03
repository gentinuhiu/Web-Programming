package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventBookingRepository {
    public static List<EventBooking> eventBookings = null;

    public EventBookingRepository() {
        eventBookings = new ArrayList<>();
        eventBookings.add(new EventBooking("New Year", "Petko Petkov", "1.1.1.1", 4L));
        eventBookings.add(new EventBooking("Anniversary", "Petko Petkov-2", "1.1.1.1", 2L));
    }

    public static EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, String numberOfTickets){
        if(eventName == null || eventName.isEmpty())
            throw new InvalidParameterException("Event cannot be empty");
        if(numberOfTickets == null || numberOfTickets.isEmpty())
            throw new InvalidParameterException("Number of tickets cannot be empty");

        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, Long.valueOf(numberOfTickets));
        eventBookings.add(eventBooking);
        return eventBooking;
    }
    public static List<EventBooking> findAllBookings(){
        return eventBookings;
    }
    public static List<EventBooking> searchBookings(String text){
        String tmp = text.toLowerCase();
        return eventBookings.stream().filter(e -> e.getEventName().toLowerCase().contains(tmp) || e.getAttendeeName().toLowerCase().contains(tmp) || e.getAttendeeAddress().toLowerCase().contains(tmp)).toList();
    }
}
