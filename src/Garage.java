import java.util.ArrayList;
import java.util.List;

/**
 * A Garage vásárlási helyszínt reprezentál.
 *
 * Felelőssége a kínálat tárolása és a vásárlási tranzakciók aktív
 * lebonyolítása. A játékos itt tud különböző megvásárolható tételeket
 * beszerezni.
 *
 * Asszociációk:
 * - location: a kereszteződés, ahol a garázs található
 * - offers: az elérhető megvásárolható tételek
 */
public class Garage {
    /** A kereszteződés, ahol a garázs található. */
    private Intersection location;

    /** Az elérhető megvásárolható tételek gyűjteménye. */
    private List<Purchasable> offers;

    /**
     * Létrehoz egy új garázst a megadott helyszínen.
     *
     * @param location a garázs helyszíne
     */
    public Garage(Intersection location) {
        Skeleton.instance.createObject(this, "location", location);
        this.location = location;
        this.offers = new ArrayList<Purchasable>();
    }

    /**
     * Visszaadja a garázs kínálatát.
     *
     * A skeleton szintjén a tényleges listát adja vissza.
     *
     * @return az elérhető megvásárolható tételek listája
     */
    public List<Purchasable> getOffers() {
        Skeleton.instance.methodCall(this,"getOffers");
        Skeleton.instance.methodReturn(this, "getOffers", offers);
        return offers;
    }

    /**
     * Lebonyolít egy vásárlási tranzakciót.
     *
     * A skeleton szintjén a metódus nem végez valódi pénzügyi ellenőrzést,
     * hanem közvetlenül érvényesíti a vásárlás hatását.
     *
     * @param buyer a vásárló játékos
     * @param item a megvásárolandó tétel
     */
    public void processTransaction(Player buyer, Purchasable item) {
        Skeleton.instance.methodCall(this,"processTransaction","buyer",buyer,"item",item);
        if (buyer != null && item != null) {
            item.applyPurchase(buyer);
        }
        Skeleton.instance.methodReturn(this, "processTransaction");
    }
}