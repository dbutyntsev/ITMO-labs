package Persons;

import java.util.ArrayList;
import java.util.List;

public abstract sealed class Shorty<Order> permits Chef, Client, Owner, Waiter {


    private final String name;
    private Mood mood;
    static List<String> staff = new ArrayList<>();
    List<Order> eatenDishes = new ArrayList<>();

    Shorty(String name) {this.name = name;}

    public abstract void talk(Order lastOrder);
    public String getName() {return this.name; }
    public Mood getMood() {return this.mood; }
    public List<Order> getEatenDishes() {return eatenDishes;}


}
