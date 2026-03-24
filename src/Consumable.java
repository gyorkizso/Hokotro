/**
 * A Consumable a fogyó készletek közös absztrakt alaposztálya.
 *
 * Felelőssége a mennyiség nyilvántartása és felhasználása, valamint annak
 * biztosítása, hogy az adott erőforrás elfogyása esetén a hozzá kötött
 * tevékenység hatástalanná váljon.
 *
 * Asszociáció:
 * - owner: a készlet tulajdonosa
 */
public abstract class Consumable {
    /** A fogyóanyag tulajdonosa. */
    protected Object owner;

    /** Az aktuálisan rendelkezésre álló mennyiség. */
    protected int amount;

    /** A mértékegység megnevezése. */
    protected String unitName;

    /**
     * Létrehoz egy új fogyóanyag-objektumot.
     *
     * @param owner a fogyóanyag tulajdonosa
     * @param amount a kezdeti mennyiség
     * @param unitName a mértékegység neve
     */
    public Consumable(Object owner, int amount, String unitName) {
        this.owner = owner;
        this.amount = amount;
        this.unitName = unitName;
    }

    /**
     * Megkísérli csökkenteni a mennyiséget felhasználáskor.
     *
     * Ha van elegendő készlet, akkor a mennyiség csökken, és a metódus
     * igaz értékkel tér vissza. Ellenkező esetben nem módosítja az állapotot.
     *
     * @param used a felhasználni kívánt mennyiség
     * @return igaz, ha volt elegendő mennyiség; különben hamis
     */
    public boolean consume(int used) {
        if (used < 0) {
            return false;
        }

        if (amount >= used) {
            amount -= used;
            return true;
        }

        return false;
    }

    /**
     * Növeli a mennyiséget vásárlás vagy utántöltés esetén.
     *
     * @param added a hozzáadandó mennyiség
     */
    public void refill(int added) {
        if (added > 0) {
            amount += added;
        }
    }
}