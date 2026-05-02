import java.util.List;

/**
 * A BroomHead a söprő fejet reprezentálja.
 *
 * Felelőssége a hó eltávolítása az aktuális sávból úgy, hogy azt a
 * közvetlenül szomszédos sávokhoz helyezi át.
 */
public class BroomHead extends PlowHead {
    /** A fej ára. */
    private static final int PRICE = 100;

    /**
     * Létrehoz egy új söprő fejet.
     *
     * @param plow a fejhez tartozó hókotró
     * @param targetLane a fej aktuális cél sávja
     */
    public BroomHead(Snowplow plow, Lane targetLane) {
        super(plow, targetLane);
        Skeleton.instance.createObject(this, "plow", plow, "targetLane", targetLane);
    }

    /**
     * Kifejti a söprő fej hatását.
     *
     * A fej a havat az aktuális sávból a közvetlen szomszédos sávokba tolja.
     * Ha nincs szomszédos sáv, akkor a hó az út mellé kerül.
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
        List<Lane> neighbors = road.getLaneNeighbors(currentLane, 1);

        if (snowAmount > 0 && !neighbors.isEmpty()) {
            int i;
            int distributedPerLane = snowAmount / neighbors.size();
            int remainder = snowAmount % neighbors.size();

            for (i = 0; i < neighbors.size(); i++) {
                int amountToPush = distributedPerLane;
                if (remainder > 0) {
                    amountToPush++;
                    remainder--;
                }
                if (amountToPush > 0) {
                    neighbors.get(i).receiveSnow(amountToPush);
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