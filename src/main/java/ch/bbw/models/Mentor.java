package ch.bbw.models;

import java.util.Date;
import lombok.Data;

@Data
public class Mentor {
    public String name;
    public Date dateOfBirth;
    public Genre genre;
    public Guitar guitar;
}
