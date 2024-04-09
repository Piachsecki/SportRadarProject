package org.example.domain;

import lombok.Data;

@Data
public class Venue {
    private String id;
    private String name;
    private Integer capacity;
    private String city_name;
    private String country_name;
    private String map_coordinates;
    private String country_code;
}
