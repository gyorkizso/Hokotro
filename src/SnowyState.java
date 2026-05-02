/**
 * A SnowyState a havas útállapotot modellezi.
 *
 * Felelőssége, hogy a járművek letapossák a havat, ezáltal növelve
 * a jegesedést, valamint hogy további havazás esetén a sáv szükség
 * szerint hótorlasz állapotba váltson.
 */
public class SnowyState extends LaneState {
    /**
     * Az a jégmennyiség-küszöb, amelytől a sáv jégpáncélossá válik.
     */
    private static final int ICE_SHEET_THRESHOLD = 1;

    /**
     * Az a hómennyiség-küszöb, amelytől a sáv hótorlasszá válik.
     */
    private static final int SNOWDRIFT_THRESHOLD = 3;

    /**
     * Egy normál jármű áthaladásakor ennyivel nő a jég mennyisége.
     */
    private static final int ICING_RATE = 1;

    /**
     * Létrehoz egy új havas sávállapotot.
     *
     * @param lane az érintett sáv
     */
    public SnowyState(Lane lane) {
        super(lane);
        Skeleton.instance.createObject(this, "lane", lane);
    }

    /**
     * Kezeli a jármű érkezését havas állapotban.
     *
     * A normál járművek letapossák a havat, ami növeli a jég mennyiségét.
     * Ha a jég eléri a küszöböt, a sáv IceSheetState állapotba vált.
     *
     * @param vehicle az érkező jármű
     */
    @Override
    public void onVehicleEnter(Vehicle vehicle) {
        Skeleton.instance.methodCall(this, "onVehicleEnter", "vehicle", vehicle);

        if (vehicle != null && !(vehicle instanceof Snowplow)) {
            int newIceAmount = lane.getIceAmount() + ICING_RATE;
            lane.setIceAmount(newIceAmount);

            if (lane.getIceAmount() >= ICE_SHEET_THRESHOLD) {
                lane.replaceLaneState(this, new IceSheetState(lane));
            }
        }

        Skeleton.instance.methodReturn(this, "onVehicleEnter");
    }

    /**
     * Kezeli a havazás hatását havas állapotban.
     *
     * Ha a sáv hómennyisége eléri a hótorlasz kialakulásának küszöbét,
     * a SnowyState helyére SnowdriftState kerül.
     *
     * @param amount a lehullott hó mennyisége
     */
    @Override
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this, "onSnowfall", "amount", amount);

        if (lane.getSnowAmount() >= SNOWDRIFT_THRESHOLD) {
            lane.replaceLaneState(this, new SnowdriftState(lane));
        }

        Skeleton.instance.methodReturn(this, "onSnowfall");
    }

    /**
     * Kezeli a letakarítás hatását havas állapotban.
     *
     * Ha a takarítás után nem marad hó a sávon,
     * a SnowyState helyére ClearState kerül.
     */
    @Override
    public void onCleaned() {
        Skeleton.instance.methodCall(this, "onCleaned");

        if (lane.getSnowAmount() <= 0) {
            lane.replaceLaneState(this, new ClearState(lane));
        }

        Skeleton.instance.methodReturn(this, "onCleaned");
    }
}