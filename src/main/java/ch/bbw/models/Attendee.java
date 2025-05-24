package ch.bbw.models;

import lombok.Data;
import java.util.Date;

@Data
public class Attendee implements Comparable<Attendee> {
    public String name;
    public Date dateOfBirth;
    public Integer rank;
    public Guitar guitar;
    public Mentor mentor;

    // 4.1 Comparable
    @Override
    public int compareTo(Attendee other) {
        return this.name.compareTo(other.name);
    }
}
