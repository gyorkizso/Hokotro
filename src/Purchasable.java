/**
 * A Purchasable egy olyan megvásárolható tételt ír le, amely a garázsban
 * pénzért beszerezhető.
 *
 * Felelőssége, hogy egységesen kezelhető legyen, mi történik vásárláskor.
 *
 * Az interfész minden megvásárolható elem számára előírja:
 * - az ár lekérdezését
 * - a vásárlás hatásának végrehajtását
 *
 * Asszociációk:
 * - buyer: a vásárló játékos (paraméterként kerül átadásra)
 */
public interface Purchasable {

    /**
     * Visszaadja a tétel árát.
     *
     * @return a tétel ára
     */
    int getPrice();

    /**
     * Végrehajtja a vásárlás hatását.
     *
     * A konkrét viselkedést az implementáló osztály adja meg.
     *
     * @param buyer a vásárló játékos
     */
    void applyPurchase(Player buyer);
}