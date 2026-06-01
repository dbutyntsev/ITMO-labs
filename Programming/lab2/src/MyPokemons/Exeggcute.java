package MyPokemons;

import MyMoves.*;
import ru.ifmo.se.pokemon.*;

public class Exeggcute extends Pokemon {

    public Exeggcute(String name, int level) {
        super(name, level);
        setType(Type.GRASS, Type.PSYCHIC);
        setStats(60, 40, 80, 60, 45, 40);

        addMove(new Confusion());
        addMove(new Facade());
        addMove(new EnergyBall());
    }
}
