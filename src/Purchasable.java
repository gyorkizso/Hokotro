/**
 * A Purchasable egy olyan megvásárolható tételt ír le, amely a garázsban
 * pénzért beszerezhető.
 *
 * Felelőssége, hogy egységesen kezelhető legyen, mi történik vásárláskor.
 *
 * Asszociációk:
 * - shop: a vásárlást lebonyolító garázs vagy bolt
 * - buyer: a vásárló játékos
 */
public abstract class Purchasable {
    /** A tétel ára. */
    protected int price;

    /** A megvásárolható tétel megnevezése. */
    protected String displayName;

    /** A vásárlást lebonyolító garázs. */
    protected Garage shop;

    /** A vásárló játékos. */
    protected Player buyer;

    /**
     * Létrehoz egy új megvásárolható tételt.
     *
     * @param price a tétel ára
     * @param displayName a tétel megnevezése
     * @param shop a vásárlást lebonyolító garázs
     * @param buyer a vásárló játékos
     */
    public Purchasable(int price, String displayName, Garage shop, Player buyer) {
        this.price = price;
        this.displayName = displayName;
        this.shop = shop;
        this.buyer = buyer;
    }

    /**
     * Visszaadja a tétel árát.
     *
     * @return a tétel ára
     */
    public int getPrice() {
        return price;
    }

    /**
     * Végrehajtja a vásárlás hatását.
     *
     * A konkrét viselkedést a leszármazott osztály adja meg.
     *
     * @param buyer a vásárló játékos
     */
    public abstract void applyPurchase(Player buyer);
}