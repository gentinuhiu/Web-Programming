package mk.ukim.finki.wp.lab.model.exception;

public class EventNotFoundException extends Exception{
    public EventNotFoundException(String message) {
        super("Event with ID: " + message + " was not found");
    }
}
