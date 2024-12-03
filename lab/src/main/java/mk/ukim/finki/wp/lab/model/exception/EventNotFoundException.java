package mk.ukim.finki.wp.lab.model.exception;

public class EventNotFoundException extends Exception{
    public EventNotFoundException(Long id) {
        super("Event with ID: " + id + " was not found");
    }
}
