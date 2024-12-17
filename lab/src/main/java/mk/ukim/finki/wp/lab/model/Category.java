package mk.ukim.finki.wp.lab.model;

import ch.qos.logback.classic.spi.EventArgUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    @OneToOne
    Event event;

    public Category() {

    }

    public Category(Long id, String name, String description, Event event) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.event = event;
    }
}
