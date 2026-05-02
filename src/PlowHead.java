/**
 * A PlowHead a hókotróra szerelhető fejek közös absztrakt alaposztálya.
 *
 * Felelőssége, hogy egységes keretet adjon a különböző takarítófejek
 * működésének, valamint biztosítsa, hogy a takarítás hatása polimorf
 * módon legyen leírható.
 */
public abstract class PlowHead {
    /** A fejhez tartozó hókotró. */
    protected Snowplow plow;

    /** A fej aktuális cél sávja. */
    protected Lane targetLane;

    /**
     * Létrehoz egy új hókotrófejet.
     *
     * @param plow a fejhez tartozó hókotró
     * @param targetLane a fej aktuális cél sávja
     */
    public PlowHead(Snowplow plow, Lane targetLane) {
        this.plow = plow;
        this.targetLane = targetLane;
    }

    /**
     * A fej kifejti hatását a megadott sávon, a hókotró és az út
     * kontextusának figyelembevételével.
     *
     * @param plow az érintett hókotró
     * @param currentLane az aktuális sáv
     * @param road az aktuális út
     */
    public abstract void applyTo(Snowplow plow, Lane currentLane, Road road);

    /**
     * Visszaadja a fej árát.
     *
     * @return a fej ára
     */
    public abstract int getPrice();
}