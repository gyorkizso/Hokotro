/**
 * A SaltedState a sószóró fej által hátrahagyott átmeneti hatást modellezi.
 *
 * Felelőssége, hogy aktív ideje alatt megakadályozza, hogy a frissen
 * lehullott hó tartósan megmaradjon a sávon, valamint hogy a hatás
 * lejártakor eltávolítsa magát a sáv állapotai közül.
 */
public class SaltedState extends LaneState {
    /** A só hatásának hátralévő ideje. */
    private int remainingDuration;

    /**
     * Létrehoz egy új sózott állapotot.
     *
     * @param lane az érintett sáv
     * @param remainingDuration a hátralévő időtartam
     */
    public SaltedState(Lane lane, int remainingDuration) {
        super(lane);
        Skeleton.instance.createObject(this, "lane", lane, "remainingDuration", remainingDuration);
        this.remainingDuration = remainingDuration;
    }

    /**
     * Kezeli a havazás hatását sózott állapotban.
     *
     * A prototípus szintjén a sózott állapot azt biztosítja,
     * hogy a frissen lehulló hó ne maradjon tartósan a sávon.
     * Ennek megfelelően a sáv hómennyiségét 0-ra állítja.
     *
     * Ezután csökkenti a saját hátralévő időtartamát,
     * és lejáratkor eltávolítja magát a sávról.
     *
     * @param amount a lehullott hó mennyisége
     */
    @Override
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this, "onSnowfall", "amount", amount);

        lane.setSnowAmount(0);

        if (remainingDuration > 0) {
            remainingDuration--;
        }

        if (remainingDuration <= 0) {
            lane.removeLaneState(this);
        }

        Skeleton.instance.methodReturn(this, "onSnowfall");
    }

    /**
     * Kezeli a takarítás hatását sózott állapotban.
     *
     * Ha a takarítás után a sávon nincs hó,
     * a sózott állapot eltávolítja magát.
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