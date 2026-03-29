/**
 * A SaltedState a sószóró fej által hátrahagyott átmeneti hatást modellezi.
 *
 * Felelőssége, hogy aktív ideje alatt olvassza a havat és a jeget,
 * valamint meggátolja a friss hó lerakódását.
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
        Skeleton.instance.createObject(this, "lane",lane, "remainingDuration",remainingDuration);
        this.remainingDuration = remainingDuration;
    }

    /**
     * Kezeli a havazás hatását sózott állapotban.
     *
     * A leírás szerint a sózott állapot csökkenti a hó- és jégmennyiséget,
     * valamint fogyasztja a saját időtartamát. Mivel a Lane osztályban ehhez
     * jelenleg nincs dokumentált publikus művelet, a skeleton szintjén itt
     * csak a hátralévő idő csökkentése történik meg.
     *
     * @param amount a lehullott hó mennyisége
     */
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this,"onSnowfall", "amount",amount);
        if (remainingDuration > 0) {
            remainingDuration--;
        }
        Skeleton.instance.methodReturn(this, "onSnowfall");
    }

}