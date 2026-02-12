package Persons;

public enum Mood {
    HAPPY("счастье"),
    NORMAL("спокой"),
    ANNOYED("усталость"),
    FURIOUS("раздраженность");

    private final String description;
    Mood(String description) { this.description = description; }
    public String getDescription() { return description; }
}
