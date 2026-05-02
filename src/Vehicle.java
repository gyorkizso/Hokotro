import java.util.List;

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
     * @param destination a jármű célpontja
     * @param speed a jármű sebessége
     */
    public Vehicle(Lane currentLane, Object destination, int speed) {
        this.currentLane = currentLane;
        this.destination = destination;
        this.speed = speed;
        this.movementRemaining = speed;
        this.active = true;
    }

    /**
     * Megkísérli a jármű áthelyezését egy cél sávra.
     * @param targetLane a cél sáv
     * @return igaz, ha az áthelyezés megtörtént; különben hamis
     */
    public boolean tryMoveTo(Lane targetLane) {
        if (!active || targetLane == null || targetLane.isBlocked()) {
            return false;
        }
        if(targetLane != currentLane) {
            consumeMovement(1);
            currentLane.removeVehicle(this);
            currentLane = targetLane;
            onEnterLane(targetLane);
        }
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
     */
    public void checkAndChangeLane() {
        if (currentLane.isBlocked()) {
            List<Lane> adjacentLanes = currentLane.getNeighborLanes(1);
            for (Lane lane : adjacentLanes) {
                if (!lane.isBlocked()) {
                    tryMoveTo(lane);
                    break;
                }
            }
        }
    }

    /**
     * A jármű kikerül az aktív játékból.
     */
    public void deactivateVehicle() {
        active = false;
    }

    /*
     * A jeges sávra érkezés kezelését
     * végzi. A konkrét következmény a jármű típusától függ, ezért a metódus
     * alapértelmezett viselkedését a leszármazott osztályok pontosíthatják.
     */
    public void handleIcyLane(Lane lane){
        
    }

    /*
     * Visszaadja az utat, amelyhez a jármű aktuális sávja tartozik.
     */
    public Road getCurrentRoad() {
        return currentLane == null ? null : currentLane.getRoad();
    }

    /*
     * Visszaadja a jármű célpontját.
     */
    public Intersection getDestination() {
        return (Intersection) destination;
    }

    /*
     * Beállítja a jármű célpontját.
     */
    public void setDestination(Intersection destination) {
        this.destination = destination;
    }

    /*
     * Visszaadja, hogy a jármű aktív-e még a játékban.
     */
    public boolean isActive() {
        return active;
    }

    /*
     * Visszaadja a jármű sebességét.
     */
    public int getSpeed() {
        return speed;
    }

    /*
     * Beállítja a jármű sebességét.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /*
     * Visszaadja a jármű aktuális sávját.
     */
    public Lane getCurrentLane() {
        return currentLane;
    }

    /*
     * Beállítja a jármű aktuális sávját.
     */
    public void setCurrentLane(Lane currentLane) {
        this.currentLane = currentLane;
    }

    /*
     * Beállítja a jármű aktuális mozgásmennyiségét.
     */
    public void setMovementRemaining(int movementRemaining) {
        this.movementRemaining = movementRemaining;
    }

    /*
     * Visszaadja az adott körben még felhasználható mozgásmennyiséget.
     */
    public int getMovementRemaining() {
        return movementRemaining;
    }
}