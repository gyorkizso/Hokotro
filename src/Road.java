import java.util.ArrayList;
import java.util.List;

/**
 * A Road két kereszteződést összekötő, esetleg többsávos útszakasz.
 */
public class Road {
    /** Az út két végpontja. */
    private Intersection[] endpoints;

    /** Az úthoz tartozó párhuzamos sávok listája. */
    private List<Lane> lanes;

    /**
     * Létrehoz egy új utat két kereszteződés között.
     *
     * @param first az első végpont
     * @param second a második végpont
     */
    public Road(Intersection first, Intersection second) {
        Skeleton.instance.createObject(this, "first",first,"second",second);
        endpoints = new Intersection[2];
        endpoints[0] = first;
        endpoints[1] = second;
        lanes = new ArrayList<Lane>();
    }

    /**
     * Megadja a haladási irány szerinti következő csomópontot.
     *
     * @param from a jelenlegi csomópont
     * @return a következő csomópont, vagy null
     */
    public Intersection getNextIntersection(Intersection from) {
        Skeleton.instance.methodCall(this,"getNextIntersection" ,"from",from);
        if (from == endpoints[0]) {
            Skeleton.instance.methodReturn(this, "getNextIntersection",endpoints[1]);
            return endpoints[1];
        }
        if (from == endpoints[1]) {
            Skeleton.instance.methodReturn(this, "getNextIntersection",endpoints[0]);
            return endpoints[0];
        }
        Skeleton.instance.methodReturn(this, "getNextIntersection",null);
        return null;
    }

    /**
     * Hozzáad egy sávot az úthoz.
     *
     * Ez a metódus technikai segédfüggvény a skeletonhoz.
     *
     * @param lane a hozzáadandó sáv
     */
    public void addLane(Lane lane) {
        Skeleton.instance.methodCall(this,"addLane", "lane",lane);
        if (lane != null) {
            lanes.add(lane);
        }
        Skeleton.instance.methodReturn(this, "addLane");
    }

    public List<Lane> getLanes(){
        return lanes;
    }

    /**
     * Visszaadja az adott sáv szomszédos sávjait a megadott távolságban.
     *
     * Megjegyzés: ez a metódus technikai segédfüggvény a skeletonhoz,
     * amelyet a söprő és a hányó fej szekvenciadiagramja indokol.
     *
     * @param lane az aktuális sáv
     * @param distance a keresett távolság
     * @return a megfelelő távolságban található szomszédos sávok listája
     */
    public List<Lane> getLaneNeighbors(Lane lane, int distance) {
        Skeleton.instance.methodCall(this,"getLaneNeighbors", "lane", lane, "distance",distance);
        List<Lane> result = new ArrayList<Lane>();
        int index;

        index = lanes.indexOf(lane);
        if (index < 0 || distance < 1) {
            return result;
        }

        if (index - distance >= 0) {
            result.add(lanes.get(index - distance));
        }
        if (index + distance < lanes.size()) {
            result.add(lanes.get(index + distance));
        }

        Skeleton.instance.methodReturn(this, "getLaneNeighbors", result);
        return result;
    }
}