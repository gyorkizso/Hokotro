/**
 * Az IceBreakerHead a jégtörő fejet reprezentálja.
 *
 * Felelőssége a sávon kialakult jég megszüntetése oly módon, hogy a
 * jeget feltöri, és az így keletkező anyagot hómennyiséggé alakítja át.
 */
public class IceBreakerHead extends PlowHead {
    /** A fej ára. */
    private static final int PRICE = 200;

    /**
     * Létrehoz egy új jégtörő fejet.
     *
     * @param plow a fejhez tartozó hókotró
     * @param targetLane a fej aktuális cél sávja
     */
    public IceBreakerHead(Snowplow plow, Lane targetLane) {
        super(plow, targetLane);
        Skeleton.instance.createObject(this, "plow", plow, "targetLane", targetLane);
    }

    /**
     * Kifejti a jégtörő fej hatását.
     *
     * Eltávolítja a sáv teljes jégmennyiségét, majd a feltört jeget
     * hóként visszahelyezi ugyanarra a sávra.
     *
     * @param plow az érintett hókotró
     * @param currentLane az aktuális sáv
     * @param road az aktuális út
     */
    @Override
    public void applyTo(Snowplow plow, Lane currentLane, Road road) {
        Skeleton.instance.methodCall(this, "applyTo",
                "plow", plow,
                "currentLane", currentLane,
                "road", road);

        if (currentLane == null) {
            Skeleton.instance.methodReturn(this, "applyTo");
            return;
        }

        int removedIce = currentLane.getIceAmount();
        currentLane.clearIce();

        if (removedIce > 0) {
            currentLane.addSnow(removedIce);
        }

        currentLane.clean(this);

        Skeleton.instance.methodReturn(this, "applyTo");
    }

    /**
     * Visszaadja a fej árát.
     *
     * @return a fej ára
     */
    @Override
    public int getPrice() {
        Skeleton.instance.methodCall(this, "getPrice");
        Skeleton.instance.methodReturn(this, "getPrice", PRICE);
        return PRICE;
    }
}