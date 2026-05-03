import java.util.*;

/**
 * A RoadNetwork a város úthálózatának topológiai modelljét valósítja meg.
 *
 * Felelőssége a kereszteződések és az azokat összekötő utak nyilvántartása
 * úgy, hogy a járművek koordináták használata nélkül, kapcsolatok mentén
 * tudjanak közlekedni.
 *
 * Asszociációk:
 * - intersections: a hálózat csomópontjai
 * - roads: a csomópontokat összekötő utak
 * - garages: a hálózatban található garázsok
 */
public class RoadNetwork {
    /** A kereszteződések nyilvántartása. */
    private List<Intersection> intersections;

    /** Az utak nyilvántartása. */
    private List<Road> roads;

    /** A garázsok nyilvántartása. */
    private List<Garage> garages;

    /**
     * Létrehoz egy üres úthálózatot.
     */
    public RoadNetwork() {
        Skeleton.instance.createObject(this);
        intersections = new ArrayList<Intersection>();
        roads = new ArrayList<Road>();
        garages = new ArrayList<Garage>();
    }

    List<Road> getRoads(){
        return roads;
    }

    List<Intersection> getIntersections(){
        return intersections;
    }

    List<Garage> getGarages(){
        return garages;
    }

    /**
     * Hozzáad egy kereszteződést az úthálózathoz.
     *
     * @param i a hozzáadandó kereszteződés
     */
    public void addIntersection(Intersection i) {
        Skeleton.instance.methodCall(this,"addIntersection", "intersection", i);
        if (i != null) {
            intersections.add(i);
        }
        Skeleton.instance.methodReturn(this, "addIntersection");
    }

    /**
     * Hozzáad egy utat az úthálózathoz.
     *
     * @param r a hozzáadandó út
     */
    public void addRoad(Road r) {
        Skeleton.instance.methodCall(this,"addRoad", "road", r);
        if (r != null) {
            roads.add(r);
        }
        Skeleton.instance.methodReturn(this, "addRoad");
    }

    /**
     * Hozzáad egy garázs az úthálózathoz.
     *
     * @param g a hozzáadandó garázs
     */
    public void addGarage(Garage g){
        Skeleton.instance.methodCall(this, "addRoad", "garage", g);
        if (g != null){
            garages.add(g);
        }
        Skeleton.instance.methodReturn(this, "addRoad");
    }

    /**
     * Megkeresi és visszaadja a két csomópont közötti legrövidebb, járható utat.
     *
     * @param start a kiinduló sáv
     * @param to a cél csomópont
     * @return az útvonalat alkotó utak listája
     */
    public List<Road> findShortestPath(Lane start, Intersection to) {
        Intersection from = intersections.stream().filter(i -> i.getConnectedRoads().stream().anyMatch(r -> r.getLanes().contains(start))).findFirst().get();
        Skeleton.instance.methodCall(this,"findShortestPath", "from",from,"to",to);

        List<Road> result = new ArrayList<Road>();
        Queue<Intersection> queue = new ArrayDeque<>();
        List<Intersection> visited = new ArrayList<>();
        Map<Intersection, Intersection> previous = new HashMap<>();

        visited.add(from);
        queue.add(from);

        while(!queue.isEmpty()){
            Intersection current = queue.remove();

            if (current.equals(to)){
                Intersection prev = to;

                //Visszafele felépíti az utat a previous tábla alapján
                while(previous.containsKey(prev)){
                    Intersection currentPrev = prev;
                    result.add(prev.getConnectedRoads().stream().filter(road -> road.getNextIntersection(currentPrev).equals(previous.get(currentPrev))).findFirst().get()) ;
                    prev = previous.get(prev);
                }

                return result;
            }

            for (Road road : current.getConnectedRoads()){
                //Az út járható ha legalább egy sávja járható
                if (road.getLanes().stream().anyMatch(lane -> !lane.isBlocked())){
                    Intersection next = road.getNextIntersection(current);
                    if (!visited.contains(next)){
                        visited.add(next);
                        previous.put(next, current);
                        queue.add(next);
                    }
                }
            }
        }

        Skeleton.instance.methodReturn(this, "findShortestPath", result);
        return result;
    }
}