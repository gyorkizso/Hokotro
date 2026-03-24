/**
 * A SaltSpreaderHead a sószóró fejet reprezentálja.
 *
 * Felelőssége az útsáv sózása, ami elindítja a hó és a jég folyamatos
 * olvadását. Működéséhez sóra mint fogyóeszközre van szükség.
 *
 * Asszociáció:
 * - saltSupply: a rendelkezésre álló sókészlet
 */
public class SaltSpreaderHead extends PlowHead {
    /** A fej ára. */
    private static final int PRICE = 250;

    /** A sószóráshoz szükséges sókészlet. */
    private Consumable saltSupply;

    /**
     * Létrehoz egy új sószóró fejet.
     *
     * @param plow a fejhez tartozó hókotró
     * @param targetLane a fej aktuális cél sávja
     * @param saltSupply a rendelkezésre álló sókészlet
     */
    public SaltSpreaderHead(Snowplow plow, Lane targetLane, Consumable saltSupply) {
        super(plow, targetLane);
        this.saltSupply = saltSupply;
    }

    /**
     * Kifejti a sószóró fej hatását.
     *
     * A skeleton szintjén, ha van elegendő só, a metódus hozzáad egy
     * sózott állapotot a sávhoz.
     *
     * @param plow az érintett hókotró
     * @param currentLane az aktuális sáv
     * @param road az aktuális út
     */
    public void applyTo(Snowplow plow, Lane currentLane, Road road) {
        if (currentLane == null || saltSupply == null) {
            return;
        }

        if (saltSupply.consume(1)) {
            currentLane.addLaneState(new SaltedState(currentLane, 2));
        }
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