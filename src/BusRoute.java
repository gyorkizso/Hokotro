import java.util.ArrayList;
import java.util.List;

/**
 * A BusRoute egy adott buszjárat teljes életciklusának és gazdasági
 * értékének menedzseléséért felel.
 *
 * Felelőssége annak nyilvántartása, hogy a busz érintette-e a szükséges
 * megállókat és végállomásokat, valamint a buszvezetőnek járó végső
 * jutalom összegének követése.
 *
 * Asszociáció:
 * - routePoints: a járathoz tartozó érintendő pontok listája
 */
public class BusRoute {
    /** A járathoz tartozó összes érintendő pont. */
    private List<RoutePoint> routePoints;

    /** A járat teljesítéséért járó alapjutalom. */
    private int baseReward;

    /** Egy megálló érintéséért járó bónusz összege. */
    private int bonusPerStop;

    /** Az eddig összegyűjtött bónuszok összege. */
    private int collectedBonus;

    /** A járat jutalmát fogadó pénztárca. */
    private Wallet wallet;

    /**
     * Létrehoz egy új buszjáratot.
     *
     * @param routePoints a járathoz tartozó pontok
     * @param baseReward az alapjutalom
     * @param bonusPerStop az egy megállóért járó bónusz
     */
    public BusRoute(List<RoutePoint> routePoints, int baseReward, int bonusPerStop, Wallet wallet) {
        this.routePoints = routePoints;
        this.baseReward = baseReward;
        this.bonusPerStop = bonusPerStop;
        this.wallet = wallet;
        this.collectedBonus = 0;

        if (this.routePoints == null) {
            this.routePoints = new ArrayList<RoutePoint>();
        }
    }

    /**
     * A busz minden lépésénél meghívható metódus.
     *
     * Végignézi a járat pontjait, és ha a jelenlegi hely valamelyik
     * pont helyével egyezik, akkor meghívja annak applyEffect(this)
     * metódusát.
     *
     * @param currentPos a busz aktuális helye
     */
    public void checkArrival(Intersection currentPos) {
        for (RoutePoint point : routePoints) {
            if (point.location == currentPos) {
                point.applyEffect(this);
            }
        }
    }

    /**
     * Növeli az összegyűjtött bónusz értékét.
     *
     * Ezt a metódust a Stop osztály hívja meg érintéskor.
     */
    public void addBonus() {
        collectedBonus += bonusPerStop;
    }

    /**
     * Beállítja a járat jutalmát fogadó pénztárcát.
     *
     * @param wallet a pénztárca
     */
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * Ellenőrzi, hogy a járat összes végállomását elérte-e már a busz.
     *
     * A jelenlegi skeleton szinten ez a metódus csak az ellenőrzést végzi el.
     * Mivel az osztályleírás nem ad meg külön pénzkezelési asszociációt,
     * itt tényleges pénzjóváírás nem történik.
     */
    public void completeIfTerminalsReached() {
        int visitedTerminals = 0;

        for (RoutePoint point : routePoints) {
            if (point instanceof Terminal && point.isVisited) {
                visitedTerminals++;
            }
        }

        if(visitedTerminals == 2) {
            int totalReward = baseReward + collectedBonus;
            wallet.addFunds(totalReward);
        }
    }
}