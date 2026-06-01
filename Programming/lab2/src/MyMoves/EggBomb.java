package MyMoves;

import ru.ifmo.se.pokemon.*;

public class EggBomb extends PhysicalMove{
    public EggBomb() {
        super(Type.NORMAL, 100, 75, 10, 1);
    }

    @Override
    protected String describe() {
        return "применяет Egg Bomb";
    }
}
