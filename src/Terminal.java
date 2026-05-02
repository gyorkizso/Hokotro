/**
 * A Terminal egy járat végállomását reprezentálja.
 *
 * Felelőssége annak jelzése a járat felé, hogy egy fő célpontot
 * sikeresen elért a busz.
 */
public class Terminal extends RoutePoint {
    /**
     * Létrehoz egy új végállomást.
     *
     * @param location a végállomás helye
     */
    public Terminal(Intersection location) {
        super(location);
        Skeleton.instance.createObject(this,
                "location", location);
    }

    /**
     * Jelzi a járatnak, hogy elértek egy végállomást, majd meghívja a
     * completeIfTerminalsReached() ellenőrzést.
     *
     * A hatás csak egyszer fut le.
     *
     * @param route az érintett járat
     */
    public void applyEffect(BusRoute route) {
        Skeleton.instance.methodCall(this, "applyEffect", route);
        if (!isVisited && route != null) {
            isVisited = true;
            route.completeIfTerminalsReached();
        }
        Skeleton.instance.methodReturn(this, "applyEffect");
    }
}