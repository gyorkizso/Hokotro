/**
 * A BusPurchase gyorsabb busz vásárlásának modellje.
 *
 * Felelőssége az árazás biztosítása és a megvásárolt busz átadásának
 * reprezentálása a vásárló felé.
 */
public class BusPurchase implements Purchasable {

    /** A busz ára. */
    private int price;

    /** A megvásárolható busz példánya. */
    private Bus newBus;

    /**
     * Létrehoz egy új busz-vásárlási tételt.
     *
     * @param price a busz ára
     * @param newBus az átadandó busz
     */
    public BusPurchase(int price, Bus newBus) {
        Skeleton.instance.createObject(this, "price", price, "newBus", newBus);

        this.price = price;
        this.newBus = newBus;
    }

    /**
     * Visszaadja a vásárlási tétel árát.
     *
     * @return a busz ára
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

        if (buyer != null && newBus != null) {
            buyer.addVehicle(newBus);
        }

        Skeleton.instance.methodReturn(this, "applyPurchase");
    }
}