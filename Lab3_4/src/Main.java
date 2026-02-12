import Foods.Dish;
import Foods.Order;
import Persons.*;
import Persons.Shorty.*;

public class Main {

    public static void main(String[] args) {


        Donut donut = Donut.getInstance();
        donut.explainSaltUsage();

        Owner owner = new Owner("Хозяйка");
        owner.chekProfit(owner.getEatenDishes().size());

        Waiter waiter = new Waiter("Официант");
        try {
            waiter.talk(waiter.getEatenDishes().size());
        } catch (staffIsNotHireableException e) {
            System.out.println(e.getMessage());
        }
        waiter.beHireable();
        waiter.talk(waiter.getEatenDishes().size());

        Chef chef = new Chef("Шеф");
        chef.beHireable();
        chef.talk(chef.getEatenDishes().size());

        chef.cook(waiter);

        Client shorty1 = new Client("Коротыш1");
        Order order1 = new Order(Dish.SOUP, true);
        shorty1.order(order1);
        waiter.takeOrder(order1);

        Client shorty2 = new Client("Коротыш2");
        Order order2 = new Order(Dish.BORSCHT, false);
        shorty2.order(order2);

        waiter.takeOrder(order2);
        chef.cook(waiter);

        Client shorty3 = new Client("Коротыш3");
        Order order3 = new Order(Dish.PASTA, true);
        shorty3.order(order3);
        waiter.takeOrder(order3);

        System.out.println();

        Order order4 = new Order(Dish.PORRIDGE, true);
        donut.order(order4);
        donut.instructSaltConsumption(order4);
        waiter.talk(waiter.getEatenDishes().size());

        waiter.takeOrder(order4);
        chef.cook(waiter);
        order4 = new Order(Dish.PASTA, true);
        donut.order(order4);
        waiter.talk(waiter.getEatenDishes().size());
        order1 = new Order(Dish.BORSCHT, true);
        shorty1.order(order1);
        chef.talk(chef.getEatenDishes().size());
        owner.chekProfit(owner.getEatenDishes().size());


    }
}