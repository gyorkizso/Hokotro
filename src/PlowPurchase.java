/**
 * A PlowPurchase új hókotró vásárlásának modellje.
 *
 * Felelőssége az árazás biztosítása és a megvásárolt jármű átadásának
 * reprezentálása a vásárló felé.
 */
public class PlowPurchase implements Purchasable {

    /** A hókotró ára. */
    private int price;

    /** A megvásárolható hókotró példánya. */
    private Snowplow newPlow;

    /**
     * Létrehoz egy új hókotró-vásárlási tételt.
     *
     * @param price a hókotró ára
     * @param newPlow az átadandó hókotró
     */
    public PlowPurchase(int price, Snowplow newPlow) {
        Skeleton.instance.createObject(this, "price", price, "newPlow", newPlow);

        this.price = price;
        this.newPlow = newPlow;
    }

    /**
     * Visszaadja a vásárlási tétel árát.
     *
     * @return a hókotró ára
     */
    @Override
    public int getPrice() {
        Skeleton.instance.methodCall(this, "getPrice");
        Skeleton.instance.methodReturn(this, "getPrice", price);

        return price;
    }

    /**
     * Végrehajtja a vásárlás hatását.
     *
     * @param buyer a vásárló játékos
     */
    @Override
    public void applyPurchase(Player buyer) {
        Skeleton.instance.methodCall(this, "applyPurchase", "buyer", buyer);

        if (buyer != null && newPlow != null) {
            buyer.addVehicle(newPlow);
        }

        Skeleton.instance.methodReturn(this, "applyPurchase");
    }
}