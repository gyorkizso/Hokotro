/**
 * A PlowPurchase új hókotró vásárlásának modellje.
 *
 * Felelőssége az árazás biztosítása és a megvásárolt jármű átadásának
 * reprezentálása a vásárló felé.
 */
public class PlowPurchase extends Purchasable {
    /** A megvásárolható hókotró sablonja. */
    private Snowplow newPlowTemplate;

    /**
     * Létrehoz egy új hókotró-vásárlási tételt.
     *
     * @param price a hókotró ára
     * @param displayName a tétel megnevezése
     * @param shop a vásárlást lebonyolító garázs
     * @param buyer a vásárló játékos
     * @param newPlowTemplate az átadandó hókotró sablonja
     */
    public PlowPurchase(int price, String displayName, Garage shop, Player buyer,
            Snowplow newPlowTemplate) {
        super(price, displayName, shop, buyer);
        Skeleton.instance.createObject(this, "price",price,"displayName",displayName,"shop",shop,"buyer",buyer);
        this.newPlowTemplate = newPlowTemplate;
    }

    /**
     * Végrehajtja a vásárlás hatását.
     *
     * A skeleton szintjén a metódus az új hókotró átadásának helyét
     * reprezentálja. A konkrét játékoshoz rendeléshez külön, dokumentált
     * járműkezelő metódusra lenne szükség.
     *
     * @param buyer a vásárló játékos
     */
    public void applyPurchase(Player buyer) {
        Skeleton.instance.methodCall(this,"applyPurchase", "buyer", buyer);
        Snowplow purchasedPlow;

        purchasedPlow = newPlowTemplate;
        purchasedPlow = purchasedPlow;

        // Skeleton implementáció:
        // itt történne az új hókotró átadása a vásárlónak.
        Skeleton.instance.methodReturn(this, "applyPurchase");
    }
}