package Foods;

public enum Dish {
    SOUP("суп"),
    BORSCHT("борщ"),
    PORRIDGE("кашу"),
    PASTA("макароны"),
    POTATO("картофель");


    private final String name;

    Dish(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

