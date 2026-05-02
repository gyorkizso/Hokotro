/**
 * A SaltSpreaderHead a sószóró fejet reprezentálja.
 *
 * Felelőssége az útsáv sózása, ami elindítja a hó és a jég fokozatos
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
        Skeleton.instance.createObject(this,
                "plow", plow,
                "targetLane", targetLane,
                "saltSupply", saltSupply);
        this.saltSupply = saltSupply;
    }

    /**
     * Kifejti a sószóró fej hatását.
     *
     * Ha van elegendő sókészlet, a sáv új SaltedState állapotot kap.
     * Ha nincs készlet vagy nincs cél sáv, a metódus hatástalanul tér vissza.
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

        if (currentLane == null || saltSupply == null) {
            Skeleton.instance.methodReturn(this, "applyTo");
            return;
        }

        if (saltSupply.consume(1)) {
            currentLane.addLaneState(new SaltedState(currentLane, 2));
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