/**
 * A LaneState egy útsáv aktuális állapotát írja le.
 *
 * Felelőssége, hogy az állapothoz tartozó szabályok az adott állapothoz
 * kötötten jelenjenek meg, és a sáv viselkedése polimorf módon legyen kezelhető.
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
     * @param vehicle a belépő jármű
     */
    public abstract void onVehicleEnter(Vehicle vehicle);

    /**
     * Kezeli a havazás hatását az adott állapotban.
     *
     * @param amount a lehullott hó mennyisége
     */
    public abstract void onSnowfall(int amount);

    /**
     * Reagál arra, hogy a sávot letakarították.
     */
    public abstract void onCleaned();
}