package org.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
}
