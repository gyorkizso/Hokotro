/**
 * A BusPurchase gyorsabb busz vásárlásának modellje.
 *
 * Felelőssége az árazás biztosítása és a megvásárolt busz átadásának
 * reprezentálása a vásárló felé.
 */
public class BusPurchase extends Purchasable {
    /** A megvásárolható busz sablonja. */
    private Bus newBusTemplate;

    /**
     * Létrehoz egy új busz-vásárlási tételt.
     *
     * @param price a busz ára
     * @param displayName a tétel megnevezése
     * @param shop a vásárlást lebonyolító garázs
     * @param buyer a vásárló játékos
     * @param newBusTemplate az átadandó busz sablonja
     */
    public BusPurchase(int price, String displayName, Garage shop, Player buyer,
            Bus newBusTemplate) {
        super(price, displayName, shop, buyer);
        Skeleton.instance.createObject(this,  "price", price,"displayName",displayName,"shop",shop,"buyer",buyer);
        this.newBusTemplate = newBusTemplate;
    }

    /**
     * Végrehajtja a vásárlás hatását.
     *
     * A skeleton szintjén a metódus az új busz átadásának helyét
     * reprezentálja. A konkrét hozzárendeléshez külön, dokumentált
     * járműkezelő metódusra lenne szükség.
     *
     * @param buyer a vásárló játékos
     */
    public void applyPurchase(Player buyer) {
        Skeleton.instance.methodCall(this, "applyPurchase", "buyer", buyer);
        Bus purchasedBus;

        purchasedBus = newBusTemplate;
        purchasedBus = purchasedBus;

        // Skeleton implementáció:
        // itt történne az új busz átadása a vásárlónak.

        Skeleton.instance.methodReturn(this, "applyPurchase");
    }
}