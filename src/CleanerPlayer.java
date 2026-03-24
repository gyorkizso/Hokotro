/**
 * A CleanerPlayer egy hókotrót irányító játékost reprezentál.
 *
 * Felelőssége a csapat pénzének növelése az utak takarításával, valamint
 * a hókotrók, fejek és üzemanyagok menedzselése a garázsokban.
 *
 * Asszociáció:
 * - snowplow: az általa irányított hókotró
 */
public class CleanerPlayer extends Player {
    /** A játékos birtokában lévő hókotró. */
    private Snowplow snowplow;

    /**
     * Létrehoz egy új takarító játékost.
     *
     * @param name a játékos neve
     * @param vehicle a játékoshoz tartozó jármű
     * @param wallet a játékos pénzkezeléséhez tartozó objektum
     * @param snowplow az irányított hókotró
     */
    public CleanerPlayer(String name, Vehicle vehicle, Wallet wallet, Snowplow snowplow) {
        super(name, vehicle, wallet);
        this.snowplow = snowplow;
    }

    /**
     * Elvégzi a takarítást az adott hókotróval.
     *
     * A skeleton szintjén a metódus meghívja a hókotró munkavégző
     * metódusát, valódi jutalmazási logika nélkül.
     *
     * @param plow a takarítást végző hókotró
     */
    public void performCleaning(Snowplow plow) {
        if (plow != null) {
            plow.work();
        }
    }

    /**
     * Lecseréli a megadott hókotró fejét.
     *
     * A fejcsere a skeleton szintjén a hókotró megfelelő metódusának
     * meghívásával történik.
     *
     * @param plow az érintett hókotró
     * @param newHead az új fej
     */
    public void changePlowHead(Snowplow plow, PlowHead newHead) {
        if (plow != null && newHead != null) {
            plow.equipHead(newHead);
        }
    }

    /**
     * Visszaadja a takarító játékos pontszámát.
     *
     * A skeleton szintjén ez egy egyszerű helykitöltő visszatérési érték.
     *
     * @return a játékos pontszáma
     */
    public int getScore() {
        return 0;
    }
}