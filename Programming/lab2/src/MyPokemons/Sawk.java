package MyPokemons;

import MyMoves.*;
import ru.ifmo.se.pokemon.*;

public final class Sawk extends Pokemon {

    public Sawk(String name, int level) {
        super(name, level);
        setType(Type.FIGHTING);
        setStats(75, 125, 75, 30, 75,85);

        addMove(new WorkUp());
        addMove(new LowSweep());
        addMove(new FocusBlast());
        addMove(new DoubleTeam());
    }
}
