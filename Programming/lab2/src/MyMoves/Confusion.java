package MyMoves;

import ru.ifmo.se.pokemon.*;

public class Confusion extends SpecialMove{
    public Confusion() {
        super(Type.PSYCHIC, 50, 100, 25, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.1) {
            Effect e = new Effect().stat(Stat.ATTACK, -1);
            p.addEffect(e);
        }
    }

    @Override
    protected String describe() {
        return "применяет Confusion";
    }
}
