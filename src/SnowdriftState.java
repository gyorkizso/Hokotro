/**
 * A SnowdriftState a hótorlasz állapotot reprezentálja.
 *
 * Felelőssége, hogy a sávot a normál járművek számára járhatatlanná
 * reprezentálja, valamint hogy takarítás után eltávolítsa magát,
 * ha a sávon már nem maradt hó.
 */
public class SnowdriftState extends LaneState {

    /**
     * Létrehoz egy új hótorlasz állapotot.
     *
     * @param lane az érintett sáv
     */
    public SnowdriftState(Lane lane) {
        super(lane);
        Skeleton.instance.createObject(this, "lane", lane);
    }

    /**
     * Kezeli a jármű érkezését hótorlasz állapotban.
     *
     * A részletes terv szerint a hótorlasz a normál járművek számára
     * nem járható. A jelenlegi repóban azonban a "stuck" állapotot
     * nem ez a metódus állítja be közvetlenül, mert a Vehicle és Lane
     * jelenlegi felülete ehhez még nem ad elég támogatást.
     *
     * Emiatt a metódus most csak a hívási felületet és a naplózást
     * biztosítja. A tényleges "elakadt-e" ellenőrzés külön logikában
     * kezelhető a sáv állapotlistája alapján.
     *
     * @param vehicle az érkező jármű
     */
    @Override
    public void onVehicleEnter(Vehicle vehicle) {
        Skeleton.instance.methodCall(this, "onVehicleEnter", "vehicle", vehicle);
        Skeleton.instance.methodReturn(this, "onVehicleEnter");
    }

    /**
     * Kezeli a havazás hatását hótorlasz állapotban.
     *
     * A hótorlasz állapotában a sáv már elérte a maximális
     * hómennyiséget, ezért további havazás nem növeli azt.
     *
     * @param amount a lehullott hó mennyisége
     */
    @Override
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this, "onSnowfall", "amount", amount);
        Skeleton.instance.methodReturn(this, "onSnowfall");
    }

    /**
     * Kezeli a letakarítás hatását hótorlasz állapotban.
     *
     * Ha a takarítás után a sáv hómennyisége 0,
     * a SnowdriftState eltávolítja magát a sávról.
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