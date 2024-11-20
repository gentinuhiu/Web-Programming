package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
    private Long id;
    String name;
    String description;
    double popularityScore;
    private Location location;
    private boolean ratingChanged;
}
