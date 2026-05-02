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
    /**
     * Létrehoz egy új autót.
     *
     * @param currentLane az aktuális sáv
     * @param owner a jármű tulajdonosa vagy irányítója
     * @param destination a célállomás
     * @param speed a jármű sebessége
     */
    public Car(Lane currentLane, Player owner, Object destination, int speed) {
        super(currentLane, owner, destination, speed);
    }

    /**
     * Végrehajtja az autó körét.
     */
    public void executeTurn() {
        Road currentRoad = currentLane.getRoad();
        
        List<Road> shortestPath = RoadNetwork.findShortestPath(currentRoad, getDestination());

    }

    /**
     * Kezeli az ütközés eseményét.
     */
    public void onCollision() {
        currentLane.addLaneState(new BlockedState(currentLane));
    }
}