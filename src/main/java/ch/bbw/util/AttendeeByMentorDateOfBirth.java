package ch.bbw.util;

import java.util.Comparator;

import ch.bbw.models.Attendee;

public class AttendeeByMentorDateOfBirth implements Comparator<Attendee> {

    @Override
    public int compare(Attendee a1, Attendee a2) {
        if (a1 == null || a2 == null) {
            return 0; // Handle null cases as needed
        }
        return a1.getMentor().getDateOfBirth().compareTo(a2.getMentor().getDateOfBirth());
    }
}