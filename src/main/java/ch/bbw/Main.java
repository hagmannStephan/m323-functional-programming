package ch.bbw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ch.bbw.models.Attendee;
import ch.bbw.util.DataLoader;

public class Main {
    public static void main(String[] args) throws IOException {
        String guitarJson = Files.readString(Paths.get("src/main/resources/mockData/guitar.json"));
        String mentorJson = Files.readString(Paths.get("src/main/resources/mockData/mentor.json"));
        String attendeeJson = Files.readString(Paths.get("src/main/resources/mockData/attendee.json"));

        DataLoader.LoadedData data = DataLoader.loadDataFromJson(guitarJson, mentorJson, attendeeJson);

        List<Attendee> attendeeList = Arrays.asList(data.attendees);

        // Task 4.1: Comparable
        // comparable_4_1(attendeeList);

        // Task 4.2: Comparator
        // comparator_4_2(attendeeList);
    }

    public static void comparable_4_1(List<Attendee> attendees) {
        Collections.sort(attendees);
        System.out.println("------------------------------------------------------");
        System.out.println("4.1 Sorted Attendee List, alphabetically by name:");
        System.out.println("------------------------------------------------------");
        for (Attendee attendee : attendees) {
            System.out.println(attendee.getName());
        }
        System.out.print("\n \n");
    }

    public static void comparator_4_2(List<Attendee> attendees) {
        // Task 4.2: Comparator
        System.out.println("------------------------------------------------------");
        System.out.println("4.2 Sorted Attendee List, by price of guitar (desc):");
        System.out.println("Works with Lambda and inbetween Classes");
        System.out.println("------------------------------------------------------");
        Comparator<Attendee> byPrice = Comparator.comparing((Attendee attendee) -> attendee.getGuitar().getPrice())
            .reversed();    // Make it desc (reverse order)
        Collections.sort(attendees, byPrice);
        for (Attendee attendee : attendees) {
            System.out.println(attendee.getName() + " - Price: " + attendee.getGuitar().getPrice() + " CHF");
        }
        System.out.print("\n \n");
    }
}