package MyMoves;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove{
    public Facade() {
        super(Type.NORMAL, 70, 100, 20, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.ATTACK, -1);
        p.addEffect(e);
    }

    @Override
    protected String describe() {
        return "применяет Facade";
    }
}
