import java.util.List;

/**
 * A Car számítógép által vezérelt normál jármű.
 *
 * Felelőssége, hogy a legrövidebb járható úton haladjon a célja felé.
 * Jégpáncélon csúszásra hajlamos, ütközés esetén pedig akadályt képezhet.
 *
 * Asszociáció:
 * - destination: az a célállomás, ami felé halad
 */
public class Car extends Vehicle {
    RoadNetwork network;

    /**
     * Létrehoz egy új autót.
     *
     * @param currentLane az aktuális sáv
     * @param destination a célállomás
     * @param speed a jármű sebessége
     */
    public Car(Lane currentLane, RoadNetwork network, Object destination, int speed) {
        super(currentLane, null, speed);
        Skeleton.instance.createObject(this, "currentLane", currentLane, "network", network, "speed", speed);
        this.network = network;
    }

    /**
     * Végrehajtja az autó körét.
     */
    public void executeTurn() {
        Road currentRoad = currentLane.getRoad();
        
        List<Road> shortestPath = network.findShortestPath(currentRoad.getNextIntersection(getDestination()), getDestination());

    }

    /**
     * Kezeli az ütközés eseményét.
     */
    public void onCollision() {
        currentLane.addLaneState(new BlockedState(currentLane));
    }
}