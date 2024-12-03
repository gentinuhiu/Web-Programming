package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    double popularityScore;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    private boolean ratingChanged;

    public Event() {

    }
    public Event(String name, String description, double popularityScore, Location location, boolean ratingChanged) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
        this.ratingChanged = ratingChanged;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPopularityScore(double popularityScore) {
        this.popularityScore = popularityScore;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setRatingChanged(boolean ratingChanged) {
        this.ratingChanged = ratingChanged;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPopularityScore() {
        return popularityScore;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isRatingChanged() {
        return ratingChanged;
    }
}
