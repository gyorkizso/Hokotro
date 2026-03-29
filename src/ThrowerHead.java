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
        Skeleton.instance.createObject(this, "plow", plow,"targetLane",targetLane);
    }

    /**
     * Kifejti a hányó fej hatását.
     *
     * A skeleton szintjén a metódus a szekvenciadiagram szerinti fő
     * hívásokat hajtja végre, részletes elosztási algoritmus nélkül.
     *
     * @param plow az érintett hókotró
     * @param currentLane az aktuális sáv
     * @param road az aktuális út
     */
    public void applyTo(Snowplow plow, Lane currentLane, Road road) {
        Skeleton.instance.methodCall(this,"applyTo","plow",plow,"currentLane",currentLane,"road",road);
        List<Lane> neighbors;
        int i;

        if (currentLane == null || road == null) {
            Skeleton.instance.methodReturn(this, "applyTo");
            return;
        }

        currentLane.getSnowAmount();
        neighbors = road.getLaneNeighbors(currentLane, 2);

        for (i = 0; i < neighbors.size(); i++) {
            neighbors.get(i).receiveSnow(1);
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
    public int getPrice() {
        Skeleton.instance.methodCall(this,"getPrice");
        Skeleton.instance.methodReturn(this, "getPrice", PRICE);
        return PRICE;
    }
}