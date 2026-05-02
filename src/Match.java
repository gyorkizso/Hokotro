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
 * - weatherSystem: a havazást kezelő rendszer
 */
public class Match {
    /** A résztvevő játékosok gyűjteménye. */
    private List<Player> players;

    /** A mérkőzésben szereplő járművek gyűjteménye. */
    private List<Vehicle> vehicles;

    /** A játék pályáját leíró úthálózat. */
    private RoadNetwork network;

    /** A mérkőzéshez tartozó időjárási rendszer. */
    private WeatherSystem weatherSystem;

    /** Azt jelzi, hogy a mérkőzés aktív-e. */
    private boolean active;

    /**
     * Létrehoz egy új mérkőzést.
     *
     * @param players a résztvevő játékosok
     * @param vehicles a mérkőzésben szereplő járművek
     * @param network a mérkőzéshez tartozó úthálózat
     */
    public Match(List<Player> players, List<Vehicle> vehicles, RoadNetwork network) {
        Skeleton.instance.createObject(this,
                "players", players,
                "vehicles", vehicles,
                "network", network);

        this.players = players;
        this.vehicles = vehicles;
        this.network = network;
        this.weatherSystem = null;
        this.active = false;

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
     * Ha van érvényes úthálózat, a mérkőzés aktívvá válik.
     * Ezután végigmegy a játékosokon, és meghívja a körkezdeti logikát.
     */
    public void start() {
        Skeleton.instance.methodCall(this, "start");

        if (network == null) {
            Skeleton.instance.methodReturn(this, "start");
            return;
        }

        active = true;

        int i;
        for (i = 0; i < players.size(); i++) {
            if (players.get(i) != null) {
                players.get(i).beginTurn();
            }
        }

        Skeleton.instance.methodReturn(this, "start");
    }

    /**
     * Lezárja a mérkőzést.
     *
     * A mérkőzés inaktívvá válik, majd minden játékos lezárja a körhöz
     * tartozó műveleteit.
     */
    public void finish() {
        Skeleton.instance.methodCall(this, "finish");

        active = false;

        int i;
        for (i = 0; i < players.size(); i++) {
            if (players.get(i) != null) {
                players.get(i).endTurn();
            }
        }

        Skeleton.instance.methodReturn(this, "finish");
    }

    /**
     * Visszaadja a paraméterként kapott játékos eredményét.
     *
     * @param p az a játékos, akinek az eredményét kérjük
     * @return a játékos eredménye
     */
    public Result getResultFor(Player p) {
        Skeleton.instance.methodCall(this, "getResultFor", "player", p);

        Result result;
        if (p == null) {
            result = new Result(null, 0);
        } else {
            result = new Result(p, p.getScore());
        }

        Skeleton.instance.methodReturn(this, "getResultFor", result);
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

    /**
     * Visszaadja a mérkőzésben résztvevő játékosokat.
     *
     * @return a játékosok listája
     */
    public List<Player> getPlayers() {
        Skeleton.instance.methodCall(this, "getPlayers");
        Skeleton.instance.methodReturn(this, "getPlayers", players);
        return players;
    }

    /**
     * Visszaadja a mérkőzésben szereplő járműveket.
     *
     * @return a járművek listája
     */
    public List<Vehicle> getVehicles() {
        Skeleton.instance.methodCall(this, "getVehicles");
        Skeleton.instance.methodReturn(this, "getVehicles", vehicles);
        return vehicles;
    }

    /**
     * Hozzáad egy játékost a mérkőzéshez.
     *
     * @param player a hozzáadandó játékos
     */
    public void addPlayer(Player player) {
        Skeleton.instance.methodCall(this, "addPlayer", "player", player);

        if (player != null) {
            players.add(player);
        }

        Skeleton.instance.methodReturn(this, "addPlayer");
    }

    /**
     * Hozzáad egy járművet a mérkőzéshez.
     *
     * @param vehicle a hozzáadandó jármű
     */
    public void addVehicle(Vehicle vehicle) {
        Skeleton.instance.methodCall(this, "addVehicle", "vehicle", vehicle);

        if (vehicle != null) {
            vehicles.add(vehicle);
        }

        Skeleton.instance.methodReturn(this, "addVehicle");
    }

    /**
     * Beállítja a mérkőzéshez tartozó időjárási rendszert.
     *
     * @param weatherSystem a beállítandó időjárási rendszer
     */
    public void setWeatherSystem(WeatherSystem weatherSystem) {
        Skeleton.instance.methodCall(this, "setWeatherSystem", "weatherSystem", weatherSystem);
        this.weatherSystem = weatherSystem;
        Skeleton.instance.methodReturn(this, "setWeatherSystem");
    }

    /**
     * Visszaadja a mérkőzéshez tartozó időjárási rendszert.
     *
     * @return az időjárási rendszer
     */
    public WeatherSystem getWeatherSystem() {
        Skeleton.instance.methodCall(this, "getWeatherSystem");
        Skeleton.instance.methodReturn(this, "getWeatherSystem", weatherSystem);
        return weatherSystem;
    }

    /**
     * Visszaadja, hogy a mérkőzés aktív-e.
     *
     * @return igaz, ha a mérkőzés aktív
     */
    public boolean isActive() {
        Skeleton.instance.methodCall(this, "isActive");
        Skeleton.instance.methodReturn(this, "isActive", active);
        return active;
    }
}