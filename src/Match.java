import java.util.ArrayList;
import java.util.List;

/**
 * A Match a játék világának összefogó objektuma.
 *
 * Felelőssége a játékosok, az úthálózat és a játék során használt közös
 * erőforrások kapcsolatának fenntartása. A mérkőzés biztosítja azt a
 * játékteret, amelyben a járművek közlekednek és a játékosok tevékenykednek.
 *
 * Asszociációk:
 * - players: a mérkőzésben részt vevő játékosok
 * - vehicles: a mérkőzésben szereplő járművek
 * - network: a pályát leíró úthálózat
 */
public class Match {
    /** A résztvevő játékosok gyűjteménye. */
    private List<Player> players;

    /** A mérkőzésben szereplő járművek gyűjteménye. */
    private List<Vehicle> vehicles;

    /** A játék pályáját leíró úthálózat. */
    private RoadNetwork network;

    /**
     * Létrehoz egy új mérkőzést.
     *
     * @param players a résztvevő játékosok
     * @param vehicles a mérkőzésben szereplő járművek
     * @param network a mérkőzéshez tartozó úthálózat
     */
    public Match(List<Player> players, List<Vehicle> vehicles, RoadNetwork network) {
        Skeleton.instance.createObject(this, "players",players,"vehicles",vehicles,"network",network);
        this.players = players;
        this.vehicles = vehicles;
        this.network = network;

        if (this.players == null) {
            this.players = new ArrayList<Player>();
        }
        if (this.vehicles == null) {
            this.vehicles = new ArrayList<Vehicle>();
        }
    }

    /**
     * Elindítja a mérkőzést.
     *
     * A szkeleton szintjén ez a metódus csak a kezdeti állapot felállításának
     * helyét jelöli ki, valódi játékszervező logika nélkül.
     */
    public void start() {
        Skeleton.instance.methodCall(this,"start");
        // Skeleton implementáció: nincs valódi játékindító logika.
        Skeleton.instance.methodReturn(this, "start");
    }

    /**
     * Lezárja a mérkőzést.
     *
     * A szkeleton szintjén ez a metódus csak a hívható felület része.
     */
    public void finish() {
        Skeleton.instance.methodCall(this,"finish");
        // Skeleton implementáció: nincs valódi lezárási logika.
        Skeleton.instance.methodReturn(this, "finish");
    }

    /**
     * Visszaadja a paraméterként kapott játékos eredményét.
     *
     * A jelenlegi skeleton szinten az eredmény a játékos pontszámából készül.
     *
     * @param p az a játékos, akinek az eredményét kérjük
     * @return a játékos eredménye
     */
    public Result getResultFor(Player p) {
        Skeleton.instance.methodCall(this,"getResultFor", "player", p);
        Result result;
        if (p == null) {
            result = new Result(0);
        }
        else {
            result = new Result(p.getScore());
        }
        Skeleton.instance.methodReturn(this, "getResultFor",result);
        return result;
    }

    /**
     * Visszaadja a mérkőzéshez tartozó pályát.
     *
     * @return a mérkőzés úthálózata
     */
    public RoadNetwork getNetwork() {
        Skeleton.instance.methodCall(this, "getNetwork");
        Skeleton.instance.methodReturn(this, "getNetwork", network);
        return network;
    }
}