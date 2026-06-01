package MyMoves;

import ru.ifmo.se.pokemon.*;

public class LeafBlade extends PhysicalMove{
    public LeafBlade() {
        super(Type.GRASS, 90, 100, 15, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.8) {
            Effect e = new Effect().stat(Stat.ATTACK, -1);
            p.addEffect(e);
        }
    }

    @Override
    protected String describe() {
        return "применяет Leaf Blade";
    }
}
