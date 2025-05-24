package ch.bbw.models;

import lombok.Data;
import java.util.Date;

@Data
public class Attendee {
    public String name;
    public Date dateOfBirth;
    public Integer rank;
    public Guitar guitar;
    public Mentor mentor;
}
