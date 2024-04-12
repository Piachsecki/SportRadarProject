package org.example.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table
@Entity
public class Competitor {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "country_code")
    private String country_code;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "qualifier")
    private String qualifier;

    @Column(name = "gender")
    private String gender;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sport_event_id")
//    private Event event;

    @ManyToMany(mappedBy = "competitors")
    private List<Event> events;
}
