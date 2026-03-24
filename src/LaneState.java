/**
 * A LaneState egy útsáv aktuális állapotát írja le.
 *
 * Felelőssége, hogy az állapothoz tartozó szabályok az adott állapothoz
 * kötötten jelenjenek meg.
 */
public abstract class LaneState {
    /** Az a sáv, amelynek az állapotát ez az objektum reprezentálja. */
    protected Lane lane;

    /**
     * Létrehoz egy új sávállapot-objektumot.
     *
     * @param lane az érintett sáv
     */
    public LaneState(Lane lane) {
        this.lane = lane;
    }

    /**
     * Reagál arra, hogy egy jármű a sávra érkezik.
     *
     * A skeleton szintjén az alapértelmezett implementáció nem végez műveletet.
     *
     * @param vehicle a belépő jármű
     */
    public void onVehicleEnter(Vehicle vehicle) {
        // Skeleton alapértelmezett implementáció.
    }

    /**
     * Kezeli a havazás hatását az adott állapotban.
     *
     * A skeleton szintjén az alapértelmezett implementáció nem végez műveletet.
     *
     * @param amount a lehullott hó mennyisége
     */
    public void onSnowfall(int amount) {
        // Skeleton alapértelmezett implementáció.
    }

    /**
     * Reagál arra, hogy a sávot letakarították.
     *
     * A skeleton szintjén az alapértelmezett implementáció nem végez műveletet.
     */
    public void onCleaned() {
        // Skeleton alapértelmezett implementáció.
    }
}