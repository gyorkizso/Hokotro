/**
 * A SnowyState a havas útállapotot modellezi.
 *
 * Felelőssége a jegesedés és a további állapotváltozások lehetőségének
 * reprezentálása. A skeleton szintjén ez az osztály csak a dokumentált
 * felületet biztosítja.
 */
public class SnowyState extends LaneState {
    /**
     * Létrehoz egy új havas sávállapotot.
     *
     * @param lane az érintett sáv
     */
    public SnowyState(Lane lane) {
        super(lane);
    }

    /**
     * Kezeli a jármű érkezését havas állapotban.
     *
     * A skeleton szintjén ez a metódus csak a hívható felület része,
     * valódi jegesedési logika nélkül.
     *
     * @param vehicle az érkező jármű
     */
    public void onVehicleEnter(Vehicle vehicle) {
        // Skeleton implementáció: nincs külön teendő.
    }

    /**
     * Kezeli a havazás hatását havas állapotban.
     *
     * A skeleton szintjén ez a metódus csak a hívható felület része,
     * valódi állapotváltási logika nélkül.
     *
     * @param amount a lehullott hó mennyisége
     */
    public void onSnowfall(int amount) {
        // Skeleton implementáció: nincs külön teendő.
    }

}