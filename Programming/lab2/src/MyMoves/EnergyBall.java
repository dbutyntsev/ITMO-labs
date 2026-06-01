package MyMoves;

import ru.ifmo.se.pokemon.*;

public class EnergyBall extends SpecialMove{
    public EnergyBall() {
        super(Type.GRASS, 90, 100, 10, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1) {
            Effect e = new Effect().stat(Stat.SPECIAL_DEFENSE, -1);
            p.addEffect(e);
        }
    }

    @Override
    protected String describe() {
        return "применяет Energy Ball";
    }
}
