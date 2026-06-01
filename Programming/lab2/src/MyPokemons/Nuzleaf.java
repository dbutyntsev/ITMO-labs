package MyPokemons;

import MyMoves.*;
import ru.ifmo.se.pokemon.*;

public final class Nuzleaf extends Seedot {

    public Nuzleaf(String name, int level) {
        super(name, level);
        setType(Type.GRASS, Type.DARK);
        setStats(70, 70, 40, 60, 40, 60);

        addMove(new LeafBlade());
    }
}
