/**
 * A Stop a két végállomás között elhelyezkedő köztes megállót reprezentálja.
 *
 * Felelőssége a bónusz összeg hozzáadása a járat teljesítéséhez.
 */
public class Stop extends RoutePoint {
    /**
     * Létrehoz egy új megállót.
     *
     * @param location a megálló helye
     */
    public Stop(Intersection location) {
        super(location);
    }

    /**
     * Érintéskor meghívja a járat addBonus() metódusát.
     *
     * A hatás csak egyszer fut le.
     *
     * @param route az érintett járat
     */
    public void applyEffect(BusRoute route) {
        if (!isVisited && route != null) {
            isVisited = true;
            route.addBonus();
        }
    }
}