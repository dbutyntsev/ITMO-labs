package MyMoves;

import ru.ifmo.se.pokemon.*;

public final class WorkUp extends StatusMove{
    public WorkUp() {
        super(Type.NORMAL, 0, 30);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().stat(Stat.ATTACK, +1);
        Effect e1 = new Effect().stat(Stat.SPECIAL_ATTACK, +1);
        p.addEffect(e);
        p.addEffect(e1);
    }

    @Override
    protected String describe() {
        return "применяет Work Up";
    }
}
