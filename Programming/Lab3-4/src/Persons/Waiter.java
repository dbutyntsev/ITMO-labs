package Persons;

import Foods.Order;

public final class Waiter extends Shorty<Mood> implements Hireable {

    public Order lastOrder;
    private Mood mood;
    private boolean hireable = false;

    public Waiter(String name) {
        super(name);
    }

    public void takeOrder(Order order) {
        String saltText = order.getSalt() ? " с солью.": " без соли.";
        lastOrder = new Order(order.getDish(), order.getSalt());
        System.out.println("Официант записал заказ: " + order.getDish().getName() + saltText);

    }

    public void talk(Mood mood) {
        System.out.println("Я Официант!");
    }

    public void talk(int orderCount) throws staffIsNotHireableException {
        if (hireable) {
            if (orderCount >= 6) {
                this.mood = Mood.FURIOUS;
                System.out.println(this.getName() + " очень раздражен. Слишком много заказов! Риск суицида.");
            } else if (orderCount >= 4) {
                this.mood = Mood.ANNOYED;
                System.out.println(this.getName() + " немного утомлен. Но в целом чувства в норме.");
            } else {
                this.mood = Mood.NORMAL;
                System.out.println(this.getName() + " спокоен, готов работать.");
            }
        }
        else { throw new staffIsNotHireableException(this.getName() + " еще не нанят."); }

    }

    public String getlastOrder() {
        if (lastOrder != null)
            return lastOrder.getDish().getName();
        return ".... аа пока ничего не заказали(";
    }

    @Override
    public void beHireable() {
        staff.add(this.getName());
        hireable = true;
        System.out.println("Хозяйка наняла Официанта");
    }
}
