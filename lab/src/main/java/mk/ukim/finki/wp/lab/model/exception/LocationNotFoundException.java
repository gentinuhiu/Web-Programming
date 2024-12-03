package mk.ukim.finki.wp.lab.model.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long id) {
        super("Location with id " + id + " not found.");
    }
}

