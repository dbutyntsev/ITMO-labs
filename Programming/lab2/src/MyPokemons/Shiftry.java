package MyPokemons;

import MyMoves.*;
import ru.ifmo.se.pokemon.*;

public final class Shiftry extends Seedot {

    public Shiftry(String name, int level) {
        super(name, level);
        setType(Type.GRASS, Type.DARK);
        setStats(90, 100, 60, 90,60, 80);

        addMove(new LeafBlade());
        addMove(new LowSweep());
    }
}
