/**
 * A Consumable a játékban használt fogyó erőforrásokat reprezentálja.
 *
 * Felelőssége a mennyiség nyilvántartása, valamint annak biztosítása,
 * hogy csak elegendő készlet esetén történjen felhasználás.
 */
public class Consumable {

    /** Az aktuális mennyiség. */
    private int amount;

    /** A mértékegység neve. */
    private String unitName;

    /** A készlet tulajdonosa (opcionális). */
    private Object owner;

    /**
     * Létrehoz egy új fogyóanyagot.
     *
     * @param amount a kezdeti mennyiség
     * @param unitName a mértékegység neve
     * @param owner a készlet tulajdonosa
     */
    public Consumable(int amount, String unitName, Object owner) {
        Skeleton.instance.createObject(this,
                "amount", amount,
                "unitName", unitName,
                "owner", owner);

        this.amount = amount;
        this.unitName = unitName;
        this.owner = owner;
    }

    /**
     * Csökkenti a mennyiséget, ha elegendő készlet áll rendelkezésre.
     *
     * @param used a felhasznált mennyiség
     * @return igaz, ha sikeres volt a felhasználás
     */
    public boolean consume(int used) {
        Skeleton.instance.methodCall(this, "consume", "used", used);

        if (used <= 0 || amount < used) {
            Skeleton.instance.methodReturn(this, "consume", false);
            return false;
        }

        amount -= used;

        Skeleton.instance.methodReturn(this, "consume", true);
        return true;
    }

    /**
     * Növeli a készletet.
     *
     * @param added a hozzáadott mennyiség
     */
    public void refill(int added) {
        Skeleton.instance.methodCall(this, "refill", "added", added);

        if (added > 0) {
            amount += added;
        }

        Skeleton.instance.methodReturn(this, "refill");
    }

    /**
     * Visszaadja az aktuális mennyiséget.
     *
     * @return a készlet mennyisége
     */
    public int getAmount() {
        Skeleton.instance.methodCall(this, "getAmount");
        Skeleton.instance.methodReturn(this, "getAmount", amount);

        return amount;
    }

    /**
     * Visszaadja a mértékegység nevét.
     *
     * @return a mértékegység neve
     */
    public String getUnitName() {
        Skeleton.instance.methodCall(this, "getUnitName");
        Skeleton.instance.methodReturn(this, "getUnitName", unitName);

        return unitName;
    }
}