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
    public void executeTurn(RoadNetwork roadNetwork) {
        List<Road> shortestPath = roadNetwork.findShortestPath(currentIntersection, getDestination());
        Road nextRoad = shortestPath.get(0);
        for (Lane lane : nextRoad.getLanes()) {
            if (tryMoveTo(lane)) {
                return;
            }
        }
    }

    /**
     * Kezeli az ütközés eseményét.
     */
    @Override
    public void onCollision() {
        currentLane.addLaneState(new BlockedState(currentLane));
    }
}