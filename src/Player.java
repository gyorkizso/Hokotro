/**
 * A Player a játékban részt vevő személy által képviselt szereplő.
 *
 * Felelőssége a játékoshoz tartozó jármű és a játékos által elérhető
 * műveletek keretének biztosítása. A konkrét célok és jutalmazás a
 * leszármazottakban jelenik meg.
 */
public abstract class Player {

    /** A játékos azonosítására szolgáló név. */
    protected String name;

    /** A játékoshoz tartozó jármű. */
    protected Vehicle vehicle;

    /** A játékos pénzkezelését végző objektum. */
    protected Wallet wallet;

    /**
     * Létrehoz egy új játékost.
     *
     * @param name a játékos neve
     * @param vehicle a játékoshoz tartozó jármű
     * @param wallet a játékos pénztárcája
     */
    public Player(String name, Vehicle vehicle, Wallet wallet) {
        this.name = name;
        this.vehicle = vehicle;
        this.wallet = wallet;
    }

    /**
     * A játékos körének kezdetét kezeli.
     */
    public void beginTurn() {
        Skeleton.instance.methodCall(this, "beginTurn");

        if (vehicle != null) {
            vehicle.setMovementRemaining(vehicle.getSpeed());
        }

        Skeleton.instance.methodReturn(this, "beginTurn");
    }

    /**
     * A játékos körének végét kezeli.
     */
    public void endTurn() {
        Skeleton.instance.methodCall(this, "endTurn");

        if (vehicle != null) {
            vehicle.setMovementRemaining(0);
        }

        Skeleton.instance.methodReturn(this, "endTurn");
    }

    /**
     * Visszaadja a játékos teljesítményének számszerű értékét.
     *
     * @return a játékos pontszáma
     */
    public abstract int getScore();

    /**
     * Visszaadja a játékos nevét.
     *
     * @return a játékos neve
     */
    public String getName() {
        Skeleton.instance.methodCall(this, "getName");
        Skeleton.instance.methodReturn(this, "getName", name);
        return name;
    }

    /**
     * Visszaadja a játékos járművét.
     *
     * @return a jármű
     */
    public Vehicle getVehicle() {
        Skeleton.instance.methodCall(this, "getVehicle");
        Skeleton.instance.methodReturn(this, "getVehicle", vehicle);
        return vehicle;
    }

    /**
     * Beállítja a játékos járművét.
     *
     * @param vehicle az új jármű
     */
    public void setVehicle(Vehicle vehicle) {
        Skeleton.instance.methodCall(this, "setVehicle", "vehicle", vehicle);

        this.vehicle = vehicle;

        Skeleton.instance.methodReturn(this, "setVehicle");
    }

    /**
     * Hozzárendeli a megadott járművet a játékoshoz.
     *
     * @param vehicle a hozzáadandó jármű
     */
    public void addVehicle(Vehicle vehicle) {
        Skeleton.instance.methodCall(this, "addVehicle", "vehicle", vehicle);

        if (vehicle != null) {
            this.vehicle = vehicle;
        }

        Skeleton.instance.methodReturn(this, "addVehicle");
    }

    /**
     * Visszaadja a játékos pénztárcáját.
     *
     * @return a wallet
     */
    public Wallet getWallet() {
        Skeleton.instance.methodCall(this, "getWallet");
        Skeleton.instance.methodReturn(this, "getWallet", wallet);
        return wallet;
    }

    /**
     * Beállítja a játékos pénztárcáját.
     *
     * @param wallet az új pénztárca
     */
    public void setWallet(Wallet wallet) {
        Skeleton.instance.methodCall(this, "setWallet", "wallet", wallet);

        this.wallet = wallet;

        Skeleton.instance.methodReturn(this, "setWallet");
    }
}