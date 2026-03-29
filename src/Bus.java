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
        Skeleton.instance.createObject(this, "currentLane", currentLane, "owner", owner, "destination", destination, "speed", speed);
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
        Skeleton.instance.methodCall(this, "checkStop", "currentPos", currentPos);
        // Skeleton implementáció: nincs valódi megállóellenőrzés.

        Skeleton.instance.methodReturn(this, "checkStop");
    }

    /**
     * Ütközés esetén mozgásképtelen állapotot rendel a buszhoz.
     */
    public void onCollision() {
        Skeleton.instance.methodCall(this, "onCollision");
        status = new ImmobilizedStatus(this, 1);
        Skeleton.instance.methodReturn(this, "onCollision");
    }

    /**
     * Beállítja az aktív járatot.
     *
     * Ez a metódus technikai segédfüggvény a skeletonhoz.
     *
     * @param route a beállítandó járat
     */
    public void setActiveRoute(BusRoute route) {
        Skeleton.instance.methodCall(this, "setActiveRoute","route", route);
        activeRoute = route;
        Skeleton.instance.methodReturn(this, "setActiveRoute");
    }

    /**
     * Törli az aktív járatot.
     *
     * Ez a metódus technikai segédfüggvény a skeletonhoz.
     */
    public void clearRoute() {
        Skeleton.instance.methodCall(this, "clearRoute");
        activeRoute = null;
        Skeleton.instance.methodReturn(this, "clearRoute");
    }
}