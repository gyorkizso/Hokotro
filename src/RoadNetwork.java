import java.util.ArrayList;
import java.util.List;

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
        intersections = new ArrayList<Intersection>();
        roads = new ArrayList<Road>();
        garages = new ArrayList<Garage>();
    }

    /**
     * Hozzáad egy kereszteződést az úthálózathoz.
     *
     * @param i a hozzáadandó kereszteződés
     */
    public void addIntersection(Intersection i) {
        if (i != null) {
            intersections.add(i);
        }
    }

    /**
     * Hozzáad egy utat az úthálózathoz.
     *
     * @param r a hozzáadandó út
     */
    public void addRoad(Road r) {
        if (r != null) {
            roads.add(r);
        }
    }

    /**
     * Megkeresi és visszaadja a két csomópont közötti legrövidebb, járható utat.
     *
     * A skeleton szintjén itt nincs valódi útkereső algoritmus; a metódus
     * csak egy hívható helykitöltő.
     *
     * @param from a kiinduló csomópont
     * @param to a cél csomópont
     * @return az útvonalat alkotó utak listája
     */
    public List<Road> findShortestPath(Intersection from, Intersection to) {
        return new ArrayList<Road>();
    }
}