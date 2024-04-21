package org.example.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Venue {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "city_name")
    private String city_name;
    @Column(name = "country_name")
    private String country_name;
    @Column(name = "map_coordinates")
    private String map_coordinates;
    @Column(name = "country_code")
    private String country_code;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<Event> events;
}
