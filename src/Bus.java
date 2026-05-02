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
     * @param speed a jármű sebessége
     */
    public Bus(Lane currentLane, int speed) {
        super(currentLane, null, speed);
        Skeleton.instance.createObject(this,
                "currentLane", currentLane,
                "speed", speed);
    }

    /**
     * Ellenőrzi, hogy a jelenlegi csomópont egy megálló-e.
     * @param currentPos a jelenlegi csomópont
     */
    public void checkStop(Intersection currentPos) {
        Skeleton.instance.methodCall(this, "checkStop", currentPos);
        if (activeRoute == null) {
            Skeleton.instance.methodReturn(this, "checkStop");
            return;
        }
        activeRoute.checkArrival(currentPos);
        Skeleton.instance.methodReturn(this, "checkStop");
    }

    /**
     * Ütközés esetén mozgásképtelen állapotot rendel a buszhoz.
     */
    @Override
    public void onCollision() {
        Skeleton.instance.methodCall(this, "onCollision");
        setStatus(new ImmobilizedStatus(this, 1));
        Skeleton.instance.methodReturn(this, "onCollision");
    }

    /**
     * Visszaadja az aktív járatot.
     * @return az aktív járat
     */
    public BusRoute getActiveRoute() {
        Skeleton.instance.methodCall(this, "getActiveRoute");
        Skeleton.instance.methodReturn(this, "getActiveRoute", activeRoute);
        return activeRoute;
    }

    /**
     * Beállítja az aktív járatot.
     * @param route a beállítandó járat
     */
    public void setActiveRoute(BusRoute route) {
        Skeleton.instance.methodCall(this, "setActiveRoute", route);
        Skeleton.instance.methodReturn(this, "setActiveRoute");
        activeRoute = route;
    }

    /**
     * Visszaadja a mozgásképtelen állapotot.
     * @return a mozgásképtelen állapot, vagy null, ha nincs ilyen
     */
    public ImmobilizedStatus getStatus() {
        Skeleton.instance.methodCall(this, "getStatus");
        Skeleton.instance.methodReturn(this, "getStatus", status);
        return status;
    }

    /**
     * Beállítja a mozgásképtelen állapotot.
     * @param status a beállítandó állapot
     */
    public void setStatus(ImmobilizedStatus status) {
        Skeleton.instance.methodCall(this, "setStatus", status);
        Skeleton.instance.methodReturn(this, "setStatus");
        this.status = status;
    }

    /**
     * Törli az aktív járatot.
     *
     * Ez a metódus technikai segédfüggvény a skeletonhoz.
     */
    public void clearRoute() {
        Skeleton.instance.methodCall(this, "clearRoute");
        Skeleton.instance.methodReturn(this, "clearRoute");
        activeRoute = null;
    }

    /**
     * Törli a mozgásképtelen állapotot.
     */
    public void clearStatus() {
        Skeleton.instance.methodCall(this, "clearStatus");
        Skeleton.instance.methodReturn(this, "clearStatus");
        status = null;
    }
}