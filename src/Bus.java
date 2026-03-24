/**
 * A Bus buszvezető által irányított normál jármű.
 *
 * Feladata a kijelölt járat bejárása. Ütközés esetén átmenetileg
 * elveszítheti a mozgásképességét.
 *
 * Asszociációk:
 * - activeRoute: az éppen teljesített járat
 * - status: a mozgásképtelenséget kezelő állapot
 */
public class Bus extends Vehicle {
    /** Az éppen teljesített járat. */
    private BusRoute activeRoute;

    /** A mozgásképtelenséget kezelő állapot. */
    private ImmobilizedStatus status;

    /**
     * Létrehoz egy új buszt.
     *
     * @param currentLane az aktuális sáv
     * @param owner a jármű tulajdonosa vagy irányítója
     * @param destination a célpont
     * @param speed a jármű sebessége
     */
    public Bus(Lane currentLane, Player owner, Object destination, int speed) {
        super(currentLane, owner, destination, speed);
    }

    /**
     * Ellenőrzi, hogy a jelenlegi csomópont egy megálló-e.
     *
     * A skeleton szintjén ez a metódus nem végez valódi megállólogikát,
     * csak a hívható felület része.
     *
     * @param currentPos a jelenlegi csomópont
     */
    public void checkStop(Intersection currentPos) {
        // Skeleton implementáció: nincs valódi megállóellenőrzés.
    }

    /**
     * Ütközés esetén mozgásképtelen állapotot rendel a buszhoz.
     */
    public void onCollision() {
        status = new ImmobilizedStatus(this, 1);
    }

    /**
     * Beállítja az aktív járatot.
     *
     * Ez a metódus technikai segédfüggvény a skeletonhoz.
     *
     * @param route a beállítandó járat
     */
    public void setActiveRoute(BusRoute route) {
        activeRoute = route;
    }

    /**
     * Törli az aktív járatot.
     *
     * Ez a metódus technikai segédfüggvény a skeletonhoz.
     */
    public void clearRoute() {
        activeRoute = null;
    }
}