package ch.bbw.util;

import java.util.Comparator;
import ch.bbw.models.Mentor;

public class MentorByDateOfBirth implements Comparator<Mentor> {

    @Override
    public int compare(Mentor m1, Mentor m2) {
        if (m1 == null || m2 == null) {
            return 0; // Handle null cases as needed
        }
        return m1.getDateOfBirth().compareTo(m2.getDateOfBirth());
    }

}