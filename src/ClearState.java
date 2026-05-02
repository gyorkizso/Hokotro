/**
 * A ClearState a tiszta, hómentes vagy alacsony havú útállapotot reprezentálja.
 *
 * Felelőssége, hogy havazás hatására ellenőrizze,
 * a sáv elérte-e a havas állapot küszöbét.
 * Ha igen, a sáv SnowyState állapotba vált.
 */
public class ClearState extends LaneState {

    /**
     * Az a hómennyiség, amelytől a sáv havasnak tekintendő.
     *
     * A jelenlegi prototípusban 1 egység hó már havas állapotot jelent.
     * Ha a csapat közös konstansba szervezi a küszöböket,
     * ezt később onnan érdemes használni.
     */
    private static final int SNOWY_THRESHOLD = 1;

    /**
     * Létrehoz egy új tiszta sávállapotot.
     *
     * @param lane az érintett sáv
     */
    public ClearState(Lane lane) {
        super(lane);
        Skeleton.instance.createObject(this, "lane", lane);
    }

    /**
     * Tiszta állapotban a járműbelépés nem okoz külön állapotváltozást.
     *
     * @param vehicle az érkező jármű
     */
    @Override
    public void onVehicleEnter(Vehicle vehicle) {
        Skeleton.instance.methodCall(this, "onVehicleEnter", "vehicle", vehicle);
        Skeleton.instance.methodReturn(this, "onVehicleEnter");
    }

    /**
     * Kezeli a havazás hatását tiszta állapotban.
     *
     * Ha a sáv aktuális hómennyisége eléri a havas állapot küszöbét,
     * a ClearState helyére SnowyState kerül.
     *
     * @param amount a lehullott hó mennyisége
     */
    @Override
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this, "onSnowfall", "amount", amount);

        if (lane.getSnowAmount() >= SNOWY_THRESHOLD) {
            lane.replaceLaneState(this, new SnowyState(lane));
        }

        Skeleton.instance.methodReturn(this, "onSnowfall");
    }

    /**
     * A takarítás tiszta állapot esetén nem okoz további változást.
     */
    @Override
    public void onCleaned() {
        Skeleton.instance.methodCall(this, "onCleaned");
        Skeleton.instance.methodReturn(this, "onCleaned");
    }
}