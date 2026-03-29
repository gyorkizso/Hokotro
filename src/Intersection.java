import java.util.ArrayList;
import java.util.List;

/**
 * Az Intersection egy kereszteződést ír le, ahol több út is összefuthat.
 *
 * Felelőssége az ide csatlakozó utak kezelése, valamint annak biztosítása,
 * hogy a járművek a kereszteződésen keresztül más utak felé tudjanak
 * továbbhaladni.
 *
 * Asszociációk:
 * - roads: az ide csatlakozó utak
 * - garage: a kereszteződéshez tartozó garázs
 */
public class Intersection {
    /** A kereszteződés azonosítója. */
    private String id;

    /** Az ide csatlakozó utak gyűjteménye. */
    private List<Road> connectedRoads;

    /** A kereszteződéshez tartozó garázs, ha van ilyen. */
    private Garage garage;

    /**
     * Létrehoz egy új kereszteződést.
     *
     * @param id a kereszteződés azonosítója
     */
    public Intersection(String id) {
        Skeleton.instance.createObject(this, "id", id);
        this.id = id;
        this.connectedRoads = new ArrayList<Road>();
    }

    /**
     * Csatlakoztat egy utat a kereszteződéshez.
     *
     * @param r a csatlakoztatandó út
     */
    public void connectRoad(Road r) {
        Skeleton.instance.methodCall(this,"connectRoad", "road", r);
        if (r != null) {
            connectedRoads.add(r);
        }
        Skeleton.instance.methodReturn(this, "connectRoad");
    }
}