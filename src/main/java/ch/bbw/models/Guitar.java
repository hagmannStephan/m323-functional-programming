package ch.bbw.models;

import lombok.Data;

@Data
public class Guitar {
    private GuitarBrand brand;
    private String model;
    private Integer price;
    private String color;
}
