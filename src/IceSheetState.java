/**
 * Az IceSheetState a jégpáncélos útállapotot reprezentálja.
 *
 * Felelőssége a balesetveszély szimulálása: a rajta áthaladó normál
 * járművek számára megcsúszást vagy ütközési következményt idézhet elő.
 * Takarítás után, ha a jég eltűnik, a sáv visszaválthat havas állapotba.
 */
public class IceSheetState extends LaneState {

    /**
     * Létrehoz egy új jégpáncél állapotot.
     *
     * @param lane az érintett sáv
     */
    public IceSheetState(Lane lane) {
        super(lane);
        Skeleton.instance.createObject(this, "lane", lane);
    }

    /**
     * Kezeli a jármű érkezését jégpáncélos állapotban.
     *
     * A részletes terv szerint itt a jármű jeges sáv kezelő műveletét kellene
     * meghívni, de a jelenlegi repóban a Vehicle osztályban nincs
     * handleIcyLane(...) metódus. Emiatt a mostani implementáció
     * konkrét járműtípusok szerint kezeli a helyzetet.
     *
     * @param vehicle az érkező jármű
     */
    @Override
    public void onVehicleEnter(Vehicle vehicle) {
        Skeleton.instance.methodCall(this, "onVehicleEnter", "vehicle", vehicle);

        if (vehicle instanceof Car) {
            ((Car) vehicle).onCollision();
        } else if (vehicle instanceof Bus) {
            ((Bus) vehicle).onCollision();
        }
        // Snowplow esetén nincs külön következmény.

        Skeleton.instance.methodReturn(this, "onVehicleEnter");
    }

    /**
     * Kezeli a havazás hatását jégpáncélos állapotban.
     *
     * A jégpáncél önmagában nem vált át más állapotba pusztán ettől a
     * metódustól, ezért itt csak a hívási felületet biztosítjuk.
     *
     * @param amount a lehullott hó mennyisége
     */
    @Override
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this, "onSnowfall", "amount", amount);
        Skeleton.instance.methodReturn(this, "onSnowfall");
    }

    /**
     * Kezeli a letakarítás hatását jégpáncélos állapotban.
     *
     * Ha a takarítás után a sáv jégmennyisége 0, akkor a jégpáncélos állapot
     * megszűnik, és a sáv havas állapotba kerül vissza.
     */
    @Override
    public void onCleaned() {
        Skeleton.instance.methodCall(this, "onCleaned");

        if (lane.getIceAmount() <= 0) {
            lane.replaceLaneState(this, new SnowyState(lane));
        }

        Skeleton.instance.methodReturn(this, "onCleaned");
    }
}