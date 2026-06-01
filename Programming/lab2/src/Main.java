import MyPokemons.*;
import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) {

        Battle b = new Battle();
        Exeggcute exeggcute = new Exeggcute("Exeggcute", 1);
        Exeggutor exeggutor = new Exeggutor("Exeggutor", 1);
        Nuzleaf nuzleaf = new Nuzleaf("Nuzleaf", 1);
        Sawk sawk = new Sawk("Sawk", 1);
        Seedot seedot = new Seedot("Seedot", 1);
        Shiftry shiftry = new Shiftry("Shiftry", 1);

        b.addAlly(exeggcute);
        b.addAlly(exeggutor);
        b.addAlly(nuzleaf);
        b.addFoe(sawk);
        b.addFoe(seedot);
        b.addFoe(shiftry);
        b.go();

    }


}


