package Model;

/**
 * A Model.Salt a sószóró fej működéséhez szükséges sókészletet reprezentálja.
 */
public class Salt extends Consumable {

    /**
     * Létrehoz egy új só készletet.
     *
     * @param amount a kezdeti mennyiség
     * @param owner a készlet tulajdonosa
     */
    public Salt(int amount, Object owner) {
        super(amount, "kg", owner);

        Skeleton.instance.createObject(this, "amount", amount, "unitName", "kg", "owner", owner);
    }
}