/**
 * A GravelSpreaderHead a zúzottkő szóró fejet reprezentálja.
 *
 * Felelőssége, hogy az aktuális sávra zúzott követ juttasson,
 * amely megszünteti a jég csúszósságát. Működéséhez zúzalék
 * készlet szükséges; amennyiben ez nem áll rendelkezésre,
 * a fej nem fejti ki hatását.
 *
 * Asszociáció:
 * - gravelSupply: a rendelkezésre álló zúzalék készlet
 */
public class GravelSpreaderHead extends PlowHead {
    /** A fej ára. */
    private static final int PRICE = 280;

    /** A működéshez szükséges zúzalék készlet. */
    private Consumable gravelSupply;

    /**
     * Létrehoz egy új zúzottkő szóró fejet.
     *
     * @param plow a fejhez tartozó hókotró
     * @param targetLane a fej aktuális cél sávja
     * @param gravelSupply a rendelkezésre álló zúzalék készlet
     */
    public GravelSpreaderHead(Snowplow plow, Lane targetLane, Consumable gravelSupply) {
        super(plow, targetLane);
        Skeleton.instance.createObject(this,
                "plow", plow,
                "targetLane", targetLane,
                "gravelSupply", gravelSupply);
        this.gravelSupply = gravelSupply;
    }

    /**
     * Kifejti a zúzottkő szóró fej hatását.
     *
     * Ha van elegendő zúzalék, akkor a sáv takarítási reakciói lefutnak,
     * majd a sáv GravelState állapotot kap.
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

        if (currentLane == null || gravelSupply == null) {
            Skeleton.instance.methodReturn(this, "applyTo");
            return;
        }

        if (gravelSupply.consume(1)) {
            currentLane.clean(this);
            currentLane.addLaneState(new GravelState(currentLane));
        }

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