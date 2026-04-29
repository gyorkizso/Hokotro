/**
 * A GravelState a zúzottkő szóró fej által létrehozott átmeneti sávállapotot reprezentálja.
 *
 * Felelőssége, hogy a sáv a zúzalék jelenléte alatt ne viselkedjen
 * csúszós felületként. A prototípus szintjén havazás esetén a zúzalékos
 * hatás megmarad, és a sáv emellett havas állapotot is kaphat.
 */
public class GravelState extends LaneState {

    /**
     * Az a hómennyiség-küszöb, amelytől a sáv havasnak tekintendő.
     */
    private static final int SNOWY_THRESHOLD = 1;

    /**
     * Létrehoz egy új zúzalékos állapotot.
     *
     * @param lane az érintett sáv
     */
    public GravelState(Lane lane) {
        super(lane);
        Skeleton.instance.createObject(this, "lane", lane);
    }

    /**
     * Kezeli a jármű érkezését zúzalékos állapotban.
     *
     * A zúzalék miatt a sáv nem viselkedik csúszós felületként,
     * ezért itt nincs külön megcsúszási vagy ütközési logika.
     *
     * @param vehicle az érkező jármű
     */
    @Override
    public void onVehicleEnter(Vehicle vehicle) {
        Skeleton.instance.methodCall(this, "onVehicleEnter", "vehicle", vehicle);
        Skeleton.instance.methodReturn(this, "onVehicleEnter");
    }

    /**
     * Kezeli a havazás hatását zúzalékos állapotban.
     *
     * A 8.2.22 teszt elvárt kimenetéhez igazodva a zúzalékos állapot
     * havazás után is megmarad, és ha a sáv hómennyisége eléri
     * a havas küszöböt, akkor egy SnowyState is hozzáadódik a sávhoz.
     *
     * @param amount a lehullott hó mennyisége
     */
    @Override
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this, "onSnowfall", "amount", amount);

        if (amount > 0 && lane.getSnowAmount() >= SNOWY_THRESHOLD) {
            lane.addLaneState(new SnowyState(lane));
        }

        Skeleton.instance.methodReturn(this, "onSnowfall");
    }

    /**
     * Kezeli a takarítás hatását zúzalékos állapotban.
     *
     * Ha a takarítás után a sávon nincs hó, akkor a zúzalékos állapot
     * is eltávolításra kerül.
     */
    @Override
    public void onCleaned() {
        Skeleton.instance.methodCall(this, "onCleaned");

        if (lane.getSnowAmount() <= 0) {
            lane.removeLaneState(this);
        }

        Skeleton.instance.methodReturn(this, "onCleaned");
    }
}