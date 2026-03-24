/**
 * A PlowHead a hókotróra szerelhető fejek közös absztrakt alaposztálya.
 *
 * Felelőssége, hogy egységes módon leírható legyen a takarítás hatása
 * az útsáv állapotára.
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
     * Megjegyzés: a szkeleton szekvenciadiagram a háromparaméteres
     * hívást írja elő, ezért a megvalósítás ehhez igazodik.
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