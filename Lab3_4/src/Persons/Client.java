package Persons;
import Foods.Dish;
import Foods.Order;
import java.util.Random;

public sealed class Client extends Shorty<Order> permits Donut{

    public Order lastOrder;
    private Mood mood;
    Dish[] allDishes = Dish.values();
    int randomIndex = new Random().nextInt(allDishes.length);
    Dish randomDish = allDishes[randomIndex];
    Random random = new Random();

    public Client(String name) {
        super(name);
    }

    public void eat(Order lastOrder) {
        System.out.println(this.getName() + " трапезничает " + lastOrder.getDish().getName());
    }

    public void order(Order order) {
        double probability = Math.random();
        String saltText = order.getSalt() ? " с солью. Он с нетерпением хочет попробовать блюдо с солью"
                : " без соли. Лично он немного насторожен.";
        if (probability < 0.4) {
            System.out.println(this.getName() + " заказал " + order.getDish().getName() + saltText);
            System.out.println(this.getName() + " передумал и заказал " + randomDish.getName());
            lastOrder = new Order(randomDish, order.getSalt());
            eatenDishes.add(lastOrder);
        }
        else {
            System.out.println(this.getName() + " заказал " + order.getDish().getName() + saltText);
            lastOrder = new Order(order.getDish(), order.getSalt());
            eatenDishes.add(lastOrder);
        }
    }

    @Override
    public void talk(Order lastOrder) {
        if (lastOrder.getSalt()) {
            if (random.nextBoolean())
                System.out.println(this.getName() + " пробует еду с солью и восхищается ею. Появляется заивисимость..");
            else
                System.out.println(this.getName() + " в очередной раз ест еду с солью.");
        }
    }

}
