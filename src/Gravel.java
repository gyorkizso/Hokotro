/**
 * A Gravel a zúzottkő készletet reprezentálja.
 */
public class Gravel extends Consumable {

    /**
     * Létrehoz egy új zúzalék készletet.
     *
     * @param amount a kezdeti mennyiség
     * @param owner a készlet tulajdonosa
     */
    public Gravel(int amount, Object owner) {
        super(amount, "kg", owner);

        Skeleton.instance.createObject(this, "amount", amount, "unitName", "kg", "owner", owner);
    }
}