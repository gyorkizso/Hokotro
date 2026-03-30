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
    }

    /**
     * Alkalmazza a havazás hatását az úthálózaton.
     *
     * Az osztályleírás szerint a metódusnak végig kellene iterálnia az
     * úthálózat összes sávján, és meg kellene hívnia a receiveSnow(...)
     * metódust. Mivel ehhez a jelenlegi osztályleírásokban nincs publikus
     * bejárási felület a RoadNetwork és Road osztályokon, a skeleton
     * szintjén ez most helykitöltő metódus.
     */
    public void applySnowfall() {
        // Skeleton implementáció: a teljes hálózat bejárásához a jelenlegi
        // modellben nincs külön publikus hozzáférés.
    }

    /**
     * Beállítja a havazás mértékét.
     * 
     * @param amount havazás mértéke
     */
    public void setSnowfallRate(int amount){
        snowfallRate = amount;
    }
}