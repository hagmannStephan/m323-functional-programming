package ch.bbw.util;

import ch.bbw.models.Guitar;
import ch.bbw.models.GuitarBrand;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class DataLoader {

    public static Guitar[] loadGuitarsFromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Step 1: Parse raw JSON into a list of temporary Map-based objects
        List<GuitarJson> tempList = mapper.readValue(json, new TypeReference<List<GuitarJson>>() {});

        // Step 2: Convert to Guitar[] using Enum conversion
        Guitar[] guitars = new Guitar[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            GuitarJson g = tempList.get(i);
            Guitar guitar = new Guitar();
            guitar.setBrand(GuitarBrand.valueOf(g.brand.toUpperCase())); // Ensure case match
            guitar.setModel(g.model);
            guitar.setPrice(g.price);
            guitar.setColor(g.color);
            guitars[i] = guitar;
        }

        return guitars;
    }

    // Internal class to match JSON structure
    private static class GuitarJson {
        public String brand;
        public String model;
        public Integer price;
        public String color;
    }
}
