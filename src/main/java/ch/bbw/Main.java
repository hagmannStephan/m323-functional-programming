package ch.bbw;

import java.io.IOException;
import java.util.Arrays;

import ch.bbw.models.Guitar;
import ch.bbw.util.DataLoader;

public class Main {
    public static void main(String[] args) throws IOException {
        String json = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("src/main/resources/mockData/guitar.json")));
        Guitar[] guitars = DataLoader.loadGuitarsFromJson(json);
        System.out.println(Arrays.toString(guitars));
    }

}