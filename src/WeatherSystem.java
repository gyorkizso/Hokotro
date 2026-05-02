/**
 * A WeatherSystem a folyamatos havazás szimulációjáért felel.
 *
 * Felelőssége, hogy a játékosok körei között minden sávban megnövelje
 * a hómennyiséget, ezzel biztosítva a havazás akadályozó hatását.
 *
 * Asszociáció:
 * - network: a játéktér úthálózata
 */
public class WeatherSystem {
    /** Az úthálózat, amelyre az időjárás hat. */
    private RoadNetwork network;

    /** A havazás intenzitása. */
    private int snowfallRate;

    /**
     * Létrehoz egy új időjárásrendszert.
     *
     * @param network az érintett úthálózat
     * @param snowfallRate a körönként lehulló hó mennyisége
     */
    public WeatherSystem(RoadNetwork network, int snowfallRate) {
        this.network = network;
        this.snowfallRate = snowfallRate;
        Skeleton.instance.createObject(this, "network", network, "snowfallRate", snowfallRate);
    }

    /**
     * Alkalmazza a havazás hatását az úthálózaton.
     *
     * A 8. fázis terve szerint a metódusnak az összes úton és sávon végig kellene
     * iterálnia, de a main ágon ehhez jelenleg hiányzik a szükséges publikus
     * bejárási felület a RoadNetwork és Road osztályokból.
     */
    public void applySnowfall() {
        Skeleton.instance.methodCall(this, "applySnowfall");
        Skeleton.instance.methodReturn(this, "applySnowfall");
    }

    /**
     * Beállítja a havazás mértékét.
     *
     * @param amount havazás mértéke
     */
    public void setSnowfallRate(int amount) {
        Skeleton.instance.methodCall(this, "setSnowfallRate", "amount", amount);
        snowfallRate = amount;
        Skeleton.instance.methodReturn(this, "setSnowfallRate");
    }

    /**
     * Visszaadja a havazás aktuális intenzitását.
     *
     * @return a havazás mértéke
     */
    public int getSnowfallRate() {
        Skeleton.instance.methodCall(this, "getSnowfallRate");
        Skeleton.instance.methodReturn(this, "getSnowfallRate", snowfallRate);
        return snowfallRate;
    }
}