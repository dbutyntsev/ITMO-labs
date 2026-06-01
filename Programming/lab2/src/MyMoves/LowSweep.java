package MyMoves;

import ru.ifmo.se.pokemon.*;

public final class LowSweep extends PhysicalMove {
    public LowSweep() {
        super(Type.FIGHTING, 65, 100, 20, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.SPEED, -1);
        p.addEffect(e);
    }

    @Override
    protected String describe() {
        return "применяет Low Sweep";
    }
}
