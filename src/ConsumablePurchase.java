/**
 * A ConsumablePurchase fogyóanyag utántöltésének megvásárlását reprezentálja.
 *
 * Felelőssége, hogy a vásárlás során a megfelelő mennyiségű készletet
 * hozzáadja a célzott fogyóanyaghoz.
 *
 */
public class ConsumablePurchase implements Purchasable {

    /** A fogyóanyagcsomag ára. */
    private int price;

    /** Az utántöltés mértéke. */
    private int refillAmount;

    /** Az a készlet, amelyet a vásárlás során fel kell tölteni. */
    private Consumable targetConsumable;

    /**
     * Létrehoz egy új fogyóanyag-vásárlási tételt.
     *
     * @param price a fogyóanyagcsomag ára
     * @param refillAmount az utántöltés mértéke
     * @param targetConsumable a feltöltendő készlet
     */
    public ConsumablePurchase(int price, int refillAmount, Consumable targetConsumable) {
        this.price = price;
        this.refillAmount = refillAmount;
        this.targetConsumable = targetConsumable;
    }

    /**
     * Visszaadja a vásárlási tétel árát.
     *
     * @return a fogyóanyagcsomag ára
     */
    @Override
    public int getPrice() {
        return price;
    }

    /**
     * Végrehajtja a vásárlás hatását.
     *
     * @param buyer a vásárló játékos
     */
    @Override
    public void applyPurchase(Player buyer) {
        if (targetConsumable == null) {
            return;
        }

        targetConsumable.refill(refillAmount);
    }
}