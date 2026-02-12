package Persons;

import java.util.List;

public final class Owner extends Shorty<Mood> implements Hireable {

    private double restaurantBalance;
    private List<Hireable> staff;
    private Mood mood;

    public Owner(String name) {
        super(name);
    }

    public void chekProfit(int orderCount) {
        if (orderCount > 6) {
            this.mood = Mood.HAPPY;
            System.out.println(this.getName() + " очень счастлива! Деньги текут рекой!");
        } else {
            this.mood = Mood.NORMAL;
            System.out.println(this.getName() + " спокойна. День только начался.");
        }
    }

    public double getBalance() {return restaurantBalance;}

    public List<Hireable> getStaff() {
        return staff;
    }


    public void talk(Mood mood) {
        System.out.println("Я Хозяйка!");
    }

    @Override
    public void beHireable() {

    }
}
