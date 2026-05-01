/**
 * A HeadPurchase új kotrófej vásárlásának modellje.
 *
 * Felelőssége az árazás biztosítása és a megvásárolt fej átadásának
 * reprezentálása a vásárló felé.
 */
public class HeadPurchase implements Purchasable {

    /** A kotrófej ára. */
    private int price;

    /** A megvásárolható konkrét kotrófej. */
    private PlowHead headToGive;

    /**
     * Létrehoz egy új fej-vásárlási tételt.
     *
     * @param price a fej ára
     * @param headToGive az átadandó kotrófej
     */
    public HeadPurchase(int price, PlowHead headToGive) {
        Skeleton.instance.createObject(this, "price", price, "headToGive", headToGive);

        this.price = price;
        this.headToGive = headToGive;
    }

    /**
     * Visszaadja a vásárlási tétel árát.
     *
     * @return a kotrófej ára
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

        if (buyer != null && headToGive != null) {
            buyer.receiveHead(headToGive);
        }

        Skeleton.instance.methodReturn(this, "applyPurchase");
    }
}