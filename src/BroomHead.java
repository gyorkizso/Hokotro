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
    }

    /**
     * Kifejti a söprő fej hatását.
     *
     * A skeleton szintjén a metódus a szekvenciadiagram szerinti fő
     * hívásokat hajtja végre, részletes elosztási algoritmus nélkül.
     *
     * @param plow az érintett hókotró
     * @param currentLane az aktuális sáv
     * @param road az aktuális út
     */
    public void applyTo(Snowplow plow, Lane currentLane, Road road) {
        List<Lane> neighbors;
        int i;

        if (currentLane == null || road == null) {
            return;
        }

        currentLane.getSnowAmount();
        neighbors = road.getLaneNeighbors(currentLane, 1);

        for (i = 0; i < neighbors.size(); i++) {
            neighbors.get(i).receiveSnow(1);
        }

        currentLane.clearSnow();
        currentLane.clean(this);
    }

    /**
     * Visszaadja a fej árát.
     *
     * @return a fej ára
     */
    public int getPrice() {
        return PRICE;
    }
}