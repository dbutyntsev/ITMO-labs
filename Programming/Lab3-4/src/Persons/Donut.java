package Persons;

import Foods.Order;

public final class Donut extends Client {

    private static final String name = null;
    private static Donut instance;

    private Donut() {
        super("Пончик");
    }

    public static Donut getInstance() {
        if (instance == null) {
            instance = new Donut();
        }
        return instance;
    }

    public void hasMoney() {
        System.out.println("Так как " + this.getName() +
                " поимел деньги, он мог заказывать блюда с солями поминутно."
        );
    }

    public void explainSaltUsage() {
        System.out.println(this.getName() +
                " стал объяснять, что соль нужно употреблять в небольших количествах, " +
                "иначе эффект от нее теряется, и ни в коем случае нельзя употреблять соль в чистом виде.\n" +
                "Все это чрезвычайно заинтересовало коротышек, которые даже не представляли себе, что пищу можно было есть с солью.\n"
        );
    }

    public void instructSaltConsumption(Order lastOrder) {
        System.out.println(this.getName() + " наглядно демонстрировал перед " +
                "новыми посетителями преимущества питания с солью, в данный момент конкретно трапезничая " + lastOrder.getDish().getName());
    }

    @Override
    public void eat(Order lastOrder) {
        System.out.println(this.getName() +
                " наглядно демонстрирует перед новыми посетителями преимущества употребления солей,\n" +
                "употребляя " + lastOrder.getDish().getName());
    }

    public void order(Order order) {
        System.out.println(this.getName() + " заказал " + order.getDish().getName() + " с солью.");
        lastOrder = new Order(order.getDish(), true);
        eatenDishes.add(lastOrder);
    }

    @Override
    public void talk(Order lastOrder) {
        System.out.println(this.getName() + " ");
    }


}
