package org.example.util;

import lombok.experimental.UtilityClass;
import org.example.domain.Event;

import java.util.Comparator;
import java.util.List;

@UtilityClass
public class PrintingUtil {
    public static void printTheResults(Event event){
        System.out.println("Start date: " + event.getStart_date() + ",");
        System.out.println(event.getCompetitors().get(0) + " vs " + event.getCompetitors().get(0)  + ",");
        System.out.println("Venue: " + event.getVenue().getName() +  ",");
//        System.out.println("Highest probable result: " +  + ",");
    }

}
