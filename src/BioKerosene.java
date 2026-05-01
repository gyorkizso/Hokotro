/**
 * A BioKerosene a sárkány fej működéséhez szükséges üzemanyag készletet reprezentálja.
 */
public class BioKerosene extends Consumable {

    /**
     * Létrehoz egy új kerozin készletet.
     *
     * @param amount a kezdeti mennyiség
     * @param owner a készlet tulajdonosa
     */
    public BioKerosene(int amount, Object owner) {
        super(amount, "liter", owner);

        Skeleton.instance.createObject(this, "amount", amount, "unitName", "liter", "owner", owner);
    }
}