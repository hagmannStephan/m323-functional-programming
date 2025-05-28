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

        // Task 4.2.1: Comparator with MentorByDateOfBirth
        // comparator_4_2_1(attendeeList);

        // Task 4.2.2: Comparator used with anonymous class
        // comparator_4_2_2(attendeeList);

        // Task 4.2.3: Comparator used with lamda expression
        // comparator_4_2_3(attendeeList);

        // 4.2.4: Comparator Chaining
        // comparator_4_2_4(attendeeList);

        // 4.3: Reversed Order
        comparator_4_3(attendeeList);
    }

    public static void comparable_4_1(List<Attendee> attendees) {
        System.out.println("------------------------------------------------------");
        System.out.println("4.1 Sorted Attendee List, alphabetically by name:");
        System.out.println("------------------------------------------------------");
        Collections.sort(attendees);
        for (Attendee attendee : attendees) {
            System.out.println(attendee.getName());
        }
        System.out.print("\n \n");
    }

    public static void comparator_4_2_1(List<Attendee> attendees) {
        // Task 4.2.1: Comparator with MentorByDateOfBirth
        System.out.println("------------------------------------------------------");
        System.out.println("4.2.1 Sorted Attendee List, by Mentor date of birth:");
        System.out.println("------------------------------------------------------");
        Collections.sort(attendees, new ch.bbw.util.AttendeeByMentorDateOfBirth());
        // Print the results
        for (Attendee attendee : attendees) {
            System.out.println(attendee.getName() + " - Year of Birth Mentor: " + attendee.getMentor().getDateOfBirth().toInstant().atZone(java.time.ZoneId.systemDefault()).getYear());
        }
        System.out.print("\n \n");
    }

    public static void comparator_4_2_2(List<Attendee> attendees) {
        // Task 4.2.2: Comparator used with anonymous class
        System.out.println("------------------------------------------------------");
        System.out.println("4.2.2 Sorted Attendee List, by date of birth:");
        System.out.println("------------------------------------------------------");
        Comparator<Attendee> byDateOfBirth = new Comparator<Attendee>() {
            @Override
            public int compare(Attendee a1, Attendee a2) {
            if (a1 == null || a2 == null) {
                return 0; // Handle null cases as needed
            }
            return a1.getDateOfBirth().compareTo(a2.getDateOfBirth());
            }
        };
        // Print the results
        attendees.sort(byDateOfBirth);
        for (Attendee attendee : attendees) {
            System.out.println(attendee.getName() + " - Date of Birth: " + attendee.getDateOfBirth().toInstant().atZone(java.time.ZoneId.systemDefault()).getYear());
        }
        System.out.print("\n \n");
    }

    public static void comparator_4_2_3(List<Attendee> attendees) {
        // Task 4.2.3: Comparator with lambda expression
        System.out.println("------------------------------------------------------");
        System.out.println("4.2.3 Sorted Attendee List, by rank:");
        System.out.println("------------------------------------------------------");
        attendees.sort((a1, a2) -> {
            return a1.getRank().compareTo(a2.getRank());
        });
        // Print the results
        for(Attendee attendee : attendees) {
            System.out.println(attendee.getName() + "- Rank: " + attendee.getRank());
        }
    }

    public static void comparator_4_2_4(List<Attendee> attendees) {
        // Task 4.2.4: Comparator with comparator chain
        System.out.println("------------------------------------------------------");
        System.out.println("4.2.4 Sorted Attendee List, by guitar price:");
        System.out.println("------------------------------------------------------");
        attendees.sort(
            Comparator.comparing((Attendee a) -> a.getGuitar().getPrice())
                .thenComparing((Attendee a) -> a.getName())
        );
        // Print the results
        for (Attendee attendee : attendees) {
            System.out.println(attendee.getName() + " - Price Guitar: " + attendee.getGuitar().getPrice() + " CHF");
        }
    }

    public static void comparator_4_3(List<Attendee> attendees){
        // Task 4.3: Comparator that reverses a list sorted by guitar price 
        System.out.println("------------------------------------------------------");
        System.out.println("4.2.4 Sorted Attendee List, by guitar price:");
        System.out.println("------------------------------------------------------");
        attendees.sort(
            Comparator.comparing(Attendee::getRank).reversed()
        );
        // Print the resutls
        for(Attendee attendee : attendees) {
            System.out.println(attendee.getName() + " - Rank: " + attendee.getRank());
        }
    }
}