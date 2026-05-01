/**
 * A RoutePoint egy buszjárat egyetlen állomásának közös, absztrakt leírása.
 *
 * Felelőssége az érintés tényének rögzítése és a pozícióhoz kötöttség
 * biztosítása.
 *
 * Asszociáció:
 * - location: az a csomópont, ahol a megálló vagy végállomás található
 */
public abstract class RoutePoint {
    /** Az a csomópont, ahol a pont található. */
    protected Intersection location;

    /** Tárolja, hogy a pontot érintette-e már a busz az aktuális fuvar során. */
    protected boolean isVisited;

    /**
     * Létrehoz egy új járatpontot.
     *
     * @param location a pont helye
     */
    public RoutePoint(Intersection location) {
        this.location = location;
        this.isVisited = false;
    }

    /**
     * Polimorf művelet, amely az érintés hatását érvényesíti a járaton.
     *
     * A leszármazottaknak gondoskodniuk kell arról, hogy csak akkor fusson le,
     * ha a pont még nem volt érintve, és a futás után az állapot igazra váltson.
     *
     * @param route az érintett járat
     */
    public abstract void applyEffect(BusRoute route);

    /**
     * Visszaadja, hogy a pontot már érintették-e.
     * @return igaz, ha a pontot érintették; különben hamis
     */    
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * Beállítja, hogy a pontot érintették-e.
     * @param visited igazra állítja, ha a pontot érintették; különben hamis
     */
    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    /**
     * Visszaadja a pont fizikai helyét reprezentáló csomópontot.
     * @return a pont helye
     */
    Intersection getLocation() {
        return location;
    }
}