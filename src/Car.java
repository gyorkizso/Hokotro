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
        Skeleton.instance.methodCall(this, "executeTurn", roadNetwork);
        List<Road> shortestPath = roadNetwork.findShortestPath(currentIntersection, getDestination());
        Road nextRoad = shortestPath.get(0);
        for (Lane lane : nextRoad.getLanes()) {
            if (tryMoveTo(lane)) {
                Skeleton.instance.methodReturn(this, "executeTurn");
                return;
            }
        }
        Skeleton.instance.methodReturn(this, "executeTurn");
    }

    /**
     * Kezeli az ütközés eseményét.
     */
    @Override
    public void onCollision() {
        Skeleton.instance.methodCall(this, "onCollision");
        currentLane.addLaneState(new BlockedState(currentLane));
        Skeleton.instance.methodReturn(this, "onCollision");
    }
}