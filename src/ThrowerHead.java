import java.util.List;

/**
 * A ThrowerHead a hányó fejet reprezentálja.
 *
 * Felelőssége megegyezik a söprő fejével, azzal a különbséggel, hogy
 * a havat két sávval arrébb lévő sávokhoz helyezi át.
 */
public class ThrowerHead extends PlowHead {
    /** A fej ára. */
    private static final int PRICE = 150;

    /**
     * Létrehoz egy új hányó fejet.
     *
     * @param plow a fejhez tartozó hókotró
     * @param targetLane a fej aktuális cél sávja
     */
    public ThrowerHead(Snowplow plow, Lane targetLane) {
        super(plow, targetLane);
        Skeleton.instance.createObject(this, "plow", plow, "targetLane", targetLane);
    }

    /**
     * Kifejti a hányó fej hatását.
     *
     * A fej a havat az aktuális sávból a két sávval távolabbi
     * szomszédos sávokba dobja. Ha nincs ilyen sáv, akkor a hó
     * az út mellé kerül.
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

        if (currentLane == null || road == null) {
            Skeleton.instance.methodReturn(this, "applyTo");
            return;
        }

        int snowAmount = currentLane.getSnowAmount();
        List<Lane> neighbors = road.getLaneNeighbors(currentLane, 2);

        if (snowAmount > 0 && !neighbors.isEmpty()) {
            int i;
            int distributedPerLane = snowAmount / neighbors.size();
            int remainder = snowAmount % neighbors.size();

            for (i = 0; i < neighbors.size(); i++) {
                int amountToThrow = distributedPerLane;
                if (remainder > 0) {
                    amountToThrow++;
                    remainder--;
                }
                if (amountToThrow > 0) {
                    neighbors.get(i).receiveSnow(amountToThrow);
                }
            }
        }

        currentLane.clearSnow();
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