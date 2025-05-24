package ch.bbw.models;

public enum Genre {
    ROCK,
    BLUES,
    JAZZ,
    METAL,
    COUNTRY,
    FOLK,
    REGGAE,
    CLASSICAL,
    FUNK,
    POP;

    // You can add methods or fields if needed
    public String getDescription() {
        switch (this) {
            case ROCK:
                return "Characterized by a strong beat and often uses electric guitars.";
            case BLUES:
                return "Known for its expressive guitar solos and emotional depth.";
            case JAZZ:
                return "Features improvisation and complex chords, often with a smooth sound.";
            case METAL:
                return "Characterized by heavy distortion and aggressive guitar riffs.";
            case COUNTRY:
                return "Involves storytelling and often features acoustic guitars.";
            case FOLK:
                return "Emphasizes acoustic instruments and traditional melodies.";
            case REGGAE:
                return "Features offbeat rhythms and often uses clean guitar tones.";
            case CLASSICAL:
                return "Focuses on intricate fingerpicking and traditional compositions.";
            case FUNK:
                return "Known for its rhythmic groove and syncopated guitar riffs.";
            case POP:
                return "Features catchy melodies and often incorporates various guitar styles.";
            default:
                return "Unknown guitar genre.";
        }
    }
}
