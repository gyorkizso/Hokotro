/**
 * A Salt a sószóró fej működéséhez szükséges fogyóanyag.
 *
 * Felelőssége a mennyiség nyilvántartása, biztosítva, hogy a sószóró
 * fej csak addig működjön, amíg van belőle.
 */
public class Salt extends Consumable {
    /**
     * Létrehoz egy új sókészletet.
     *
     * @param owner a készlet tulajdonosa
     * @param amount a kezdeti mennyiség
     */
    public Salt(Object owner, int amount) {
        super(owner, amount, "só");
    }

    /**
     * Csökkenti a rendelkezésre álló só mennyiségét.
     *
     * Megjegyzés: a visszatérési érték a Consumable ősosztály miatt
     * boolean típusú, így jelezhető, hogy volt-e elegendő készlet.
     *
     * @param used a felhasznált mennyiség
     * @return igaz, ha volt elegendő só; különben hamis
     */
    public boolean consume(int used) {
        return super.consume(used);
    }

    /**
     * Növeli a készletet utántöltéskor.
     *
     * @param added a hozzáadandó mennyiség
     */
    public void refill(int added) {
        super.refill(added);
    }
}