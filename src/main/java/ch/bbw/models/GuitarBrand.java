package ch.bbw.models;

public enum GuitarBrand {
    FENDER,
    GIBSON,
    YAMAHA,
    IBANEZ,
    PRS, // Paul Reed Smith
    MARTIN,
    EPIPHONE,
    GRETSCH,
    ROLAND,
    ESP;

    public String getDescription() {
        switch (this) {
            case FENDER:
                return "Known for electric guitars like the Stratocaster and Telecaster.";
            case GIBSON:
                return "Famous for iconic models like the Les Paul and SG.";
            case YAMAHA:
                return "Offers a wide range of acoustic and electric guitars.";
            case IBANEZ:
                return "Popular for their innovative designs and playability.";
            case PRS:
                return "Renowned for high-quality craftsmanship and versatile sound.";
            case MARTIN:
                return "Specializes in premium acoustic guitars.";
            case EPIPHONE:
                return "A subsidiary of Gibson, known for affordable versions of classic models.";
            case GRETSCH:
                return "Famous for their unique sound and vintage designs.";
            case ROLAND:
                return "Known for electric guitars and innovative technology.";
            case ESP:
                return "Popular among rock and metal guitarists for their custom designs.";
            default:
                return "Unknown guitar brand.";
        }
    }
}

