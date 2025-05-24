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

        public LoadedData(Guitar[] guitars, Mentor[] mentors) {
            this.guitars = guitars;
            this.mentors = mentors;
        }
    }

    public static LoadedData loadDataFromJson(String guitarJson, String mentorJson) throws IOException {
        Guitar[] guitars = loadGuitarsFromJson(guitarJson);
        Mentor[] mentors = loadMentorsFromJson(mentorJson, guitars);
        return new LoadedData(guitars, mentors);
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
                mentor.setDateOfBirth(null); // or handle differently
            }
            mentor.setGenre(Genre.valueOf(m.genre.toUpperCase()));
            mentor.setGuitar(guitars[rand.nextInt(guitars.length)]);
            mentors[i] = mentor;
        }

        return mentors;
    }

    // DTO classes for JSON mapping
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
}
