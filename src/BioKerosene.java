import java.util.logging.Logger;

/**
 * A BioKerosene a sárkány fej működéséhez szükséges üzemanyag.
 *
 * Felelőssége a mennyiség kezelésével biztosítani, hogy a sárkány fej
 * csak megfelelő készlet mellett tudjon működni.
 */
public class BioKerosene extends Consumable {
    /**
     * Létrehoz egy új biokerozin-készletet.
     *
     * @param owner a készlet tulajdonosa
     * @param amount a kezdeti mennyiség
     */
    public BioKerosene(Object owner, int amount) {
        super(owner, amount, "biokerozin");
        Skeleton.instance.createObject(this, "owner", owner, "amount", amount);
    }

    /**
     * Elégeti a szükséges üzemanyag mennyiségét.
     *
     * Megjegyzés: a visszatérési érték a Consumable ősosztály miatt
     * boolean típusú, így jelezhető, hogy volt-e elegendő készlet.
     *
     * @param used a felhasznált mennyiség
     * @return igaz, ha volt elegendő üzemanyag; különben hamis
     */
    public boolean consume(int used) {
        Skeleton.instance.methodCall(this, "consume", "used", used);
        boolean returnVal = super.consume(used);
        Skeleton.instance.methodReturn(this, "consume", returnVal);
        return  returnVal;
    }

    /**
     * Növeli az üzemanyagszintet utántöltéskor.
     *
     * @param added a hozzáadandó mennyiség
     */
    public void refill(int added) {
        Skeleton.instance.methodCall(this, "refill", "added", added);
        super.refill(added);
        Skeleton.instance.methodReturn(this, "refill");
    }
}