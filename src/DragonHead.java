/**
 * A DragonHead a sárkány fejet reprezentálja.
 *
 * Felelőssége a hó és a jég azonnali elolvasztása. Működéséhez
 * biokerozinra van szükség.
 *
 * Asszociáció:
 * - fuelSupply: a rendelkezésre álló biokerozin-készlet
 */
public class DragonHead extends PlowHead {
    /** A fej ára. */
    private static final int PRICE = 300;

    /** A működéshez szükséges üzemanyagkészlet. */
    private Consumable fuelSupply;

    /**
     * Létrehoz egy új sárkány fejet.
     *
     * @param plow a fejhez tartozó hókotró
     * @param targetLane a fej aktuális cél sávja
     * @param fuelSupply a rendelkezésre álló üzemanyagkészlet
     */
    public DragonHead(Snowplow plow, Lane targetLane, Consumable fuelSupply) {
        super(plow, targetLane);
        Skeleton.instance.createObject(this,
                "plow", plow,
                "targetLane", targetLane,
                "fuelSupply", fuelSupply);
        this.fuelSupply = fuelSupply;
    }

    /**
     * Kifejti a sárkány fej hatását.
     *
     * Ha van elegendő üzemanyag, a metódus eltávolítja a sáv havát és jegét,
     * majd hozzáad egy ClearState állapotot a sávhoz.
     *
     * Megjegyzés:
     * A részletes terv szerint ideálisan a meglévő jeges állapotot kellene
     * ClearState-re cserélni, de a jelenlegi Lane publikus felülete nem teszi
     * lehetővé a konkrét lecserélendő állapot visszakeresését.
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

        if (currentLane == null || fuelSupply == null) {
            Skeleton.instance.methodReturn(this, "applyTo");
            return;
        }

        if (fuelSupply.consume(1)) {
            currentLane.setIceAmount(0);
            currentLane.setSnowAmount(0);
            currentLane.addLaneState(new ClearState(currentLane));
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