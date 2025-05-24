package ch.bbw.util;

import ch.bbw.models.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataLoader {

    public static class LoadedData {
        public final Guitar[] guitars;
        public final Mentor[] mentors;
        public final Attendee[] attendees;

        public LoadedData(Guitar[] guitars, Mentor[] mentors, Attendee[] attendees) {
            this.guitars = guitars;
            this.mentors = mentors;
            this.attendees = attendees;
        }
    }

    public static LoadedData loadDataFromJson(String guitarJson, String mentorJson, String attendeeJson) throws IOException {
        Guitar[] guitars = loadGuitarsFromJson(guitarJson);
        Mentor[] mentors = loadMentorsFromJson(mentorJson, guitars);
        Attendee[] attendees = loadAttendeesFromJson(attendeeJson, guitars, mentors);
        return new LoadedData(guitars, mentors, attendees);
    }

    private static Guitar[] loadGuitarsFromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<GuitarJson> tempList = mapper.readValue(json, new TypeReference<>() {});
        Guitar[] guitars = new Guitar[tempList.size()];

        for (int i = 0; i < tempList.size(); i++) {
            GuitarJson g = tempList.get(i);
            Guitar guitar = new Guitar();
            guitar.setBrand(GuitarBrand.valueOf(g.brand.toUpperCase()));
            guitar.setModel(g.model);
            guitar.setPrice(g.price);
            guitar.setColor(g.color);
            guitars[i] = guitar;
        }

        return guitars;
    }

    private static Mentor[] loadMentorsFromJson(String json, Guitar[] guitars) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<MentorJson> tempList = mapper.readValue(json, new TypeReference<>() {});
        Mentor[] mentors = new Mentor[tempList.size()];

        Random rand = new Random();
        SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);

        for (int i = 0; i < tempList.size(); i++) {
            MentorJson m = tempList.get(i);
            Mentor mentor = new Mentor();
            mentor.setName(m.name);
            try {
                mentor.setDateOfBirth(format.parse(m.dateOfBirth));
            } catch (ParseException e) {
                mentor.setDateOfBirth(null);
            }
            mentor.setGenre(Genre.valueOf(m.genre.toUpperCase()));
            mentor.setGuitar(guitars[rand.nextInt(guitars.length)]);
            mentors[i] = mentor;
        }

        return mentors;
    }

    private static Attendee[] loadAttendeesFromJson(String json, Guitar[] guitars, Mentor[] mentors) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<AttendeeJson> tempList = mapper.readValue(json, new TypeReference<>() {});
        Attendee[] attendees = new Attendee[tempList.size()];

        Random rand = new Random();
        SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);

        for (int i = 0; i < tempList.size(); i++) {
            AttendeeJson a = tempList.get(i);
            Attendee attendee = new Attendee();
            attendee.setName(a.name);
            try {
                attendee.setDateOfBirth(format.parse(a.dateOfBirth));
            } catch (ParseException e) {
                attendee.setDateOfBirth(null);
            }
            attendee.setRank(a.rank);
            attendee.setGuitar(guitars[rand.nextInt(guitars.length)]);
            attendee.setMentor(mentors[rand.nextInt(mentors.length)]);
            attendees[i] = attendee;
        }

        return attendees;
    }

    // Internal DTOs
    private static class GuitarJson {
        public String brand;
        public String model;
        public Integer price;
        public String color;
    }

    private static class MentorJson {
        public String name;
        public String dateOfBirth;
        public String genre;
    }

    private static class AttendeeJson {
        public String name;
        public String dateOfBirth;
        public Integer rank;
    }
}
