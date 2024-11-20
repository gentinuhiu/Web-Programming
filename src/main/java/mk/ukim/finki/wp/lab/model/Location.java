package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private Long id;
    private String name;
    private String address;
    private String capacity;
    private String description;
}