package Foods;

public record Order(Dish dish, boolean isSalted) {

    public Dish getDish() {return this.dish;}
    public boolean getSalt() {return this.isSalted;}

}

