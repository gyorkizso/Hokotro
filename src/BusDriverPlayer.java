/**
 * A BusDriverPlayer egy buszt irányító játékost reprezentál.
 *
 * Felelőssége a járatok teljesítése és a buszok eljuttatása a
 * végállomásokra a megállók megfelelő érintésével.
 *
 * Asszociáció:
 * - bus: az általa vezetett busz
 */
public class BusDriverPlayer extends Player {
    /** A játékos birtokában lévő busz. */
    private Bus bus;

    /**
     * Létrehoz egy új buszvezető játékost.
     *
     * @param name a játékos neve
     * @param vehicle a játékoshoz tartozó jármű
     * @param wallet a játékos pénzkezeléséhez tartozó objektum
     * @param bus az irányított busz
     */
    public BusDriverPlayer(String name, Vehicle vehicle, Wallet wallet, Bus bus) {
        super(name, vehicle, wallet);
        this.bus = bus;
    }

    /**
     * Kiválaszt egy járatot, és hozzárendeli a megadott buszhoz.
     *
     * @param bus az érintett busz
     * @param route a kiválasztott járat
     */
    public void selectRoute(Bus bus, BusRoute route) {
        if (bus != null) {
            bus.setActiveRoute(route);
        }
    }

    /**
     * Lezárja a busz befejezett járatát.
     *
     * A skeleton szintjén ez egy hívható felületet biztosító helykitöltő
     * metódus, külön jutalmazási logika nélkül.
     *
     * @param bus az érintett busz
     */
    public void completeRoute(Bus bus) {
        if (bus != null) {
            bus.clearRoute();
        }
    }

    /**
     * Visszaadja a buszvezető pontszámát.
     *
     * A skeleton szintjén ez egy egyszerű helykitöltő visszatérési érték.
     *
     * @return a játékos pontszáma
     */
    public int getScore() {
        return 0;
    }
}