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

    /** A kereszteződés, ahol a jármű jelenleg tartózkodik. */
    protected Intersection currentIntersection;

    /**
     * Létrehoz egy új járművet.
     *
     * @param currentLane az aktuális sáv
     * @param destination a jármű célpontja
     * @param speed a jármű sebessége
     */
    public Vehicle(Lane currentLane, Object destination, int speed) {
        this.currentLane = currentLane;
        this.currentIntersection = null;
        this.destination = destination;
        this.speed = speed;
        this.movementRemaining = speed;
        this.active = true;
        Skeleton.instance.createObject(this,
                "currentLane", currentLane,
                "destination", destination,
                "speed", speed);
    }

    /**
     * Megkísérli a jármű áthelyezését egy cél sávra.
     * @param targetLane a cél sáv
     * @return igaz, ha az áthelyezés megtörtént; különben hamis
     */
    public boolean tryMoveTo(Lane targetLane) {
        Skeleton.instance.methodCall(this, "tryMoveTo", targetLane);
        if (!active || targetLane == null || targetLane.isBlocked()) {
            Skeleton.instance.methodReturn(this, "tryMoveTo", false);
            return false;
        }
        if(targetLane != currentLane) {
            consumeMovement(1);
            currentLane.removeVehicle(this);
            currentLane = targetLane;
            onEnterLane(targetLane);
        }
        Skeleton.instance.methodReturn(this, "tryMoveTo", true);
        return true;
    }

    /**
     * Megkísérli a jármű áthelyezését egy cél kereszteződésre.
     * @param targetIntersection a cél kereszteződés
     * @return igaz, ha az áthelyezés megtörtént; különben hamis
     */
    public boolean tryMoveTo(Intersection targetIntersection) {
        Skeleton.instance.methodCall(this, "tryMoveTo", targetIntersection);
        Road currentRoad = getCurrentRoad();
        if (!active || targetIntersection == null || currentRoad == null) {
            Skeleton.instance.methodReturn(this, "tryMoveTo", false);
            return false;
        }
        List<Road> connectedRoads = targetIntersection.getConnectedRoads();
        for (Road road : connectedRoads) {
            if(road == currentRoad) {
                consumeMovement(1);
                currentLane.removeVehicle(this);
                currentLane = null;
                currentIntersection = targetIntersection;
                Skeleton.instance.methodReturn(this, "tryMoveTo", true);
                return true;
            }
        }
        Skeleton.instance.methodReturn(this, "tryMoveTo", false);
        return false;
    }

    /**
     * Csökkenti a rendelkezésre álló mozgásmennyiséget.
     *
     * A metódus nem engedi 0 alá csökkenni a fennmaradó mozgást.
     *
     * @param amount a levonandó mozgásmennyiség
     */
    public void consumeMovement(int amount) {
        Skeleton.instance.methodCall(this, "consumeMovement", amount);
        movementRemaining -= amount;
        if (movementRemaining < 0) {
            movementRemaining = 0;
        }
        Skeleton.instance.methodReturn(this, "consumeMovement");
    }
    
    /**
     * Meghívódik, amikor a jármű egy sávra ér.
     *
     * A sáv állapota reagálhat a jármű belépésére.
     *
     * @param lane az a sáv, amelyre a jármű megérkezett
     */
    public void onEnterLane(Lane lane) {
        Skeleton.instance.methodCall(this, "onEnterLane", lane);
        if (lane != null) {
            lane.acceptVehicle(this);
        }
        Skeleton.instance.methodReturn(this, "onEnterLane");
    }

    /**
     * Megvizsgálja, hogy szükséges-e sávváltás.
     */
    public void checkAndChangeLane() {
        Skeleton.instance.methodCall(this, "checkAndChangeLane");
        if (currentLane.isBlocked()) {
            List<Lane> adjacentLanes = currentLane.getNeighborLanes(1);
            for (Lane lane : adjacentLanes) {
                if (!lane.isBlocked()) {
                    tryMoveTo(lane);
                    break;
                }
            }
        }
        Skeleton.instance.methodReturn(this, "checkAndChangeLane");
    }

    /**
     * A jármű kikerül az aktív játékból.
     */
    public void deactivateVehicle() {
        Skeleton.instance.methodCall(this, "deactivateVehicle");
        active = false;
        Skeleton.instance.methodReturn(this, "deactivateVehicle");
    }

    /**
     * A jeges sávra érkezés kezelését
     * végzi. A konkrét következmény a jármű típusától függ, ezért a metódus
     * alapértelmezett viselkedését a leszármazott osztályok pontosíthatják.
     * @param lane a jeges sáv, amelyre a jármű érkezett
     */
    public void handleIcyLane(Lane lane){
        Skeleton.instance.methodCall(this, "handleIcyLane", lane);
        if(lane.getVehicles().size() > 1) {
            this.onCollision();
        }
        Skeleton.instance.methodReturn(this, "handleIcyLane");
    }

    /*
     * Visszaadja az utat, amelyhez a jármű aktuális sávja tartozik.
     */
    public Road getCurrentRoad() {
        Skeleton.instance.methodCall(this, "getCurrentRoad");
        if (currentLane != null) {
            Road road = currentLane.getRoad();
            Skeleton.instance.methodReturn(this, "getCurrentRoad", road);
            return road;
        }
        Skeleton.instance.methodReturn(this, "getCurrentRoad", null);
        return null;
    }

    /*
     * Visszaadja a jármű célpontját.
     */
    public Intersection getDestination() {
        Skeleton.instance.methodCall(this, "getDestination");
        Skeleton.instance.methodReturn(this, "getDestination", (Intersection) destination);
        return (Intersection) destination;
    }

    /*
     * Beállítja a jármű célpontját.
     */
    public void setDestination(Intersection destination) {
        Skeleton.instance.methodCall(this, "setDestination", destination);
        Skeleton.instance.methodReturn(this, "setDestination");
        this.destination = destination;
    }

    /*
     * Visszaadja, hogy a jármű aktív-e még a játékban.
     */
    public boolean isActive() {
        Skeleton.instance.methodCall(this, "isActive");
        Skeleton.instance.methodReturn(this, "isActive", active);
        return active;
    }

    /*
     * Visszaadja a jármű sebességét.
     */
    public int getSpeed() {
        Skeleton.instance.methodCall(this, "getSpeed");
        Skeleton.instance.methodReturn(this, "getSpeed", speed);
        return speed;
    }

    /*
     * Beállítja a jármű sebességét.
     */
    public void setSpeed(int speed) {
        Skeleton.instance.methodCall(this, "setSpeed", speed);
        this.speed = speed;
        Skeleton.instance.methodReturn(this, "setSpeed");
    }

    /*
     * Visszaadja a jármű aktuális sávját.
     */
    public Lane getCurrentLane() {
        Skeleton.instance.methodCall(this, "getCurrentLane");
        Skeleton.instance.methodReturn(this, "getCurrentLane", currentLane);
        return currentLane;
    }

    /*
     * Beállítja a jármű aktuális sávját.
     */
    public void setCurrentLane(Lane currentLane) {
        Skeleton.instance.methodCall(this, "setCurrentLane", currentLane);
        Skeleton.instance.methodReturn(this, "setCurrentLane");
        this.currentLane = currentLane;
    }

    /*
     * Beállítja a jármű aktuális mozgásmennyiségét.
     */
    public void setMovementRemaining(int movementRemaining) {
        Skeleton.instance.methodCall(this, "setMovementRemaining", movementRemaining);
        this.movementRemaining = movementRemaining;
        Skeleton.instance.methodReturn(this, "setMovementRemaining");
    }

    /*
     * Visszaadja az adott körben még felhasználható mozgásmennyiséget.
     */
    public int getMovementRemaining() {
        Skeleton.instance.methodCall(this, "getMovementRemaining");
        Skeleton.instance.methodReturn(this, "getMovementRemaining", movementRemaining);
        return movementRemaining;
    }

    /*
     * Meghívódik, amikor a jármű ütközik egy másik járművel.
     */
    public void onCollision() {
        Skeleton.instance.methodCall(this, "onCollision");
        Skeleton.instance.methodReturn(this, "onCollision");
    }
}