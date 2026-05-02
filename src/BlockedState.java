/**
 * A BlockedState a sáv teljes lezárását reprezentálja.
 *
 * Felelőssége, hogy a sávot a normál forgalom elől lezárja addig,
 * amíg egy megfelelő takarítási művelet meg nem szünteti az akadályt.
 */
public class BlockedState extends LaneState {

    /**
     * Létrehoz egy új akadály állapotot.
     *
     * @param lane az érintett sáv
     */
    public BlockedState(Lane lane) {
        super(lane);
        Skeleton.instance.createObject(this, "lane", lane);
    }

    /**
     * Kezeli a jármű érkezését akadályozott állapotban.
     *
     * A részletes terv szerint a normál járművek számára a sáv nem járható.
     * A jelenlegi repóban azonban nincs külön "stuck", "reject entry" vagy
     * visszaléptető mechanizmus a Vehicle és Lane osztályokban, ezért
     * ez a metódus most a naplózást és a kötelező felületet biztosítja.
     *
     * @param vehicle az érkező jármű
     */
    @Override
    public void onVehicleEnter(Vehicle vehicle) {
        Skeleton.instance.methodCall(this, "onVehicleEnter", "vehicle", vehicle);
        Skeleton.instance.methodReturn(this, "onVehicleEnter");
    }

    /**
     * Kezeli a takarítás hatását akadály állapotban.
     *
     * Takarítás hatására az akadály megszűnik, ezért a BlockedState
     * eltávolítja magát a sáv állapotlistájából.
     */
    @Override
    public void onCleaned() {
        Skeleton.instance.methodCall(this, "onCleaned");
        lane.removeLaneState(this);
        Skeleton.instance.methodReturn(this, "onCleaned");
    }

    /**
     * A havazás önmagában nem változtatja meg a blokkolt állapotot.
     *
     * @param amount a lehullott hó mennyisége
     */
    @Override
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this, "onSnowfall", "amount", amount);
        Skeleton.instance.methodReturn(this, "onSnowfall");
    }
}