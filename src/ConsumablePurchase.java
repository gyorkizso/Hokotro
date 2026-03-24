/**
 * A ConsumablePurchase fogyóanyag utántöltésének megvásárlását reprezentálja.
 *
 * Felelőssége a cél készlet feltöltése a megadott mértékben.
 */
public class ConsumablePurchase extends Purchasable {
    /** Az a készlet, amelyet a vásárlás feltölt. */
    private Consumable targetConsumable;

    /** Az utántöltés mértéke. */
    private int refillAmount;

    /**
     * Létrehoz egy új fogyóanyag-vásárlási tételt.
     *
     * @param price a csomag ára
     * @param displayName a tétel megnevezése
     * @param shop a vásárlást lebonyolító garázs
     * @param buyer a vásárló játékos
     * @param targetConsumable a feltöltendő készlet
     * @param refillAmount az utántöltés mértéke
     */
    public ConsumablePurchase(int price, String displayName, Garage shop, Player buyer,
            Consumable targetConsumable, int refillAmount) {
        super(price, displayName, shop, buyer);
        this.targetConsumable = targetConsumable;
        this.refillAmount = refillAmount;
    }

    /**
     * Végrehajtja a vásárlás hatását.
     *
     * Meghívja a cél készlet refill(refillAmount) metódusát.
     *
     * @param buyer a vásárló játékos
     */
    public void applyPurchase(Player buyer) {
        if (targetConsumable != null) {
            targetConsumable.refill(refillAmount);
        }
    }
}