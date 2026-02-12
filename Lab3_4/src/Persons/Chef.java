package Persons;

public final class Chef extends Shorty<Mood> implements Hireable {

    private Mood mood;
    private boolean hireable = false;

    public Chef(String name) {
        super(name);
    }

    public void cook(Waiter waiter) {
        System.out.println(this.getName() + " готовит " + waiter.getlastOrder());
    }

    @Override
    public void talk(Mood mood) {
        System.out.println("Я Шеф!");
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

    @Override
    public void beHireable() {
        staff.add(this.getName());
        hireable = true;
        System.out.println("Хозяйка наняла Шефа");
    }

}
