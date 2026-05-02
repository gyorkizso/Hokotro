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
     * @param currentPos a jelenlegi csomópont
     */
    public void checkStop(Intersection currentPos) {
        if (activeRoute == null) {
            return;
        }
        activeRoute.checkArrival(currentPos);
    }

    /**
     * Ütközés esetén mozgásképtelen állapotot rendel a buszhoz.
     */
    @Override
    public void onCollision() {
        setStatus(new ImmobilizedStatus(this, 1));
    }

    /**
     * Visszaadja az aktív járatot.
     * @return az aktív járat
     */
    public BusRoute getActiveRoute() {
        return activeRoute;
    }

    /**
     * Beállítja az aktív járatot.
     * @param route a beállítandó járat
     */
    public void setActiveRoute(BusRoute route) {
        activeRoute = route;
    }

    /**
     * Visszaadja a mozgásképtelen állapotot.
     * @return a mozgásképtelen állapot, vagy null, ha nincs ilyen
     */
    public ImmobilizedStatus getStatus() {
        return status;
    }

    /**
     * Beállítja a mozgásképtelen állapotot.
     * @param status a beállítandó állapot
     */
    public void setStatus(ImmobilizedStatus status) {
        this.status = status;
    }

    /**
     * Törli az aktív járatot.
     *
     * Ez a metódus technikai segédfüggvény a skeletonhoz.
     */
    public void clearRoute() {
        activeRoute = null;
    }

    /**
     * Törli a mozgásképtelen állapotot.
     */
    public void clearStatus() {
        status = null;
    }
}