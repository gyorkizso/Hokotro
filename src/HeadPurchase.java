/**
 * A HeadPurchase új kotrófej vásárlásának modellje.
 *
 * Felelőssége az árazás biztosítása és a megvásárolt fej átadásának
 * reprezentálása a vásárló felé.
 */
public class HeadPurchase extends Purchasable {
    /** Az átadandó kotrófej. */
    private PlowHead headToGive;

    /**
     * Létrehoz egy új fej-vásárlási tételt.
     *
     * @param price a fej ára
     * @param displayName a tétel megnevezése
     * @param shop a vásárlást lebonyolító garázs
     * @param buyer a vásárló játékos
     * @param headToGive az átadandó fej
     */
    public HeadPurchase(int price, String displayName, Garage shop, Player buyer,
            PlowHead headToGive) {
        super(price, displayName, shop, buyer);
        Skeleton.instance.createObject(this, "price",price,"displayName",displayName,"shop",shop,"buyer",buyer);
        this.headToGive = headToGive;
    }

    /**
     * Végrehajtja a vásárlás hatását.
     *
     * A skeleton szintjén a metódus a fej átadásának helyét reprezentálja.
     * A konkrét felszereléshez külön, dokumentált átadási vagy felszerelési
     * műveletre lenne szükség.
     *
     * @param buyer a vásárló játékos
     */
    public void applyPurchase(Player buyer) {
        Skeleton.instance.methodCall(this,"applyPurchase", "buyer", buyer);
        PlowHead purchasedHead;

        purchasedHead = headToGive;
        purchasedHead = purchasedHead;

        // Skeleton implementáció:
        // itt történne a fej átadása a vásárlónak.
        Skeleton.instance.methodReturn(this, "applyPurchase");
    }
}