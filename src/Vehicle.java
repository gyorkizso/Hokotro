/**
 * A Vehicle a közlekedő járművek közös absztrakt alaposztálya.
 *
 * Felelőssége a játékban való elhelyezkedés és haladás reprezentálása,
 * valamint annak biztosítása, hogy a jármű a sávok állapotától függően
 * tudjon mozogni. A konkrét viselkedések a leszármazottakban jelennek meg.
 *
 * Asszociációk:
 * - currentLane: az a sáv, ahol a jármű jelenleg tartózkodik
 * - owner: a járművet irányító játékos
 * - destination: a jármű aktuális célpontja
 */
public abstract class Vehicle {
    /** Az a sáv, ahol a jármű jelenleg tartózkodik. */
    protected Lane currentLane;

    /** A jármű tulajdonosához vagy irányítójához tartozó játékos. */
    protected Player owner;

    /** A jármű célpontja. */
    protected Object destination;

    /** A jármű sebessége. */
    protected int speed;

    /** Az adott körben még felhasználható mozgásmennyiség. */
    protected int movementRemaining;

    /** Azt jelzi, hogy a jármű aktív-e még a játékban. */
    protected boolean active;

    /**
     * Létrehoz egy új járművet.
     *
     * @param currentLane az aktuális sáv
     * @param owner a jármű tulajdonosa vagy irányítója
     * @param destination a jármű célpontja
     * @param speed a jármű sebessége
     */
    public Vehicle(Lane currentLane, Player owner, Object destination, int speed) {
        this.currentLane = currentLane;
        this.owner = owner;
        this.destination = destination;
        this.speed = speed;
        this.movementRemaining = speed;
        this.active = true;
    }

    /**
     * Megkísérli a jármű áthelyezését egy cél sávra.
     *
     * A szkeleton szintjén ez minimális működést tartalmaz: ha a jármű aktív,
     * és a cél sáv nem null, akkor az aktuális sáv lecserélődik, és meghívódik
     * az új sávra érkezést kezelő metódus.
     *
     * @param targetLane a cél sáv
     * @return igaz, ha az áthelyezés megtörtént; különben hamis
     */
    public boolean tryMoveTo(Lane targetLane) {
        if (!active || targetLane == null) {
            return false;
        }

        currentLane = targetLane;
        onEnterLane(targetLane);
        return true;
    }

    /**
     * Csökkenti a rendelkezésre álló mozgásmennyiséget.
     *
     * A metódus nem engedi 0 alá csökkenni a fennmaradó mozgást.
     *
     * @param amount a levonandó mozgásmennyiség
     */
    public void consumeMovement(int amount) {
        movementRemaining -= amount;
        if (movementRemaining < 0) {
            movementRemaining = 0;
        }
    }
    
    /**
     * Meghívódik, amikor a jármű egy sávra ér.
     *
     * A sáv állapota reagálhat a jármű belépésére.
     *
     * @param lane az a sáv, amelyre a jármű megérkezett
     */
    public void onEnterLane(Lane lane) {
        if (lane != null) {
            lane.acceptVehicle(this);
        }
    }

    /**
     * Megvizsgálja, hogy szükséges-e sávváltás.
     *
     * A szkeleton szintjén ez a metódus csak a hívható felület része,
     * valódi sávváltási logika nélkül.
     */
    public void checkAndChangeLane() {
        // Skeleton implementáció: nincs valódi sávváltási algoritmus.
    }

    /**
     * A jármű kikerül az aktív játékból.
     */
    public void deactivateVehicle() {
        active = false;
    }
}