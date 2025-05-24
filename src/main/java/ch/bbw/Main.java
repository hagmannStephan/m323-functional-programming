package ch.bbw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import ch.bbw.util.DataLoader;

public class Main {
    public static void main(String[] args) throws IOException {
        String guitarJson = Files.readString(Paths.get("src/main/resources/mockData/guitar.json"));
        String mentorJson = Files.readString(Paths.get("src/main/resources/mockData/mentor.json"));
        String attendeeJson = Files.readString(Paths.get("src/main/resources/mockData/attendee.json"));

        DataLoader.LoadedData data = DataLoader.loadDataFromJson(guitarJson, mentorJson, attendeeJson);

        System.out.println(Arrays.toString(data.attendees));
    }
}