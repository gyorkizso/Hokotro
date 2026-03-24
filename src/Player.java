/**
 * A Player a játékban részt vevő személy által képviselt szereplő.
 *
 * Felelőssége a játékoshoz tartozó jármű és a játékos által elérhető
 * műveletek keretének biztosítása. A konkrét célok és jutalmazás a
 * leszármazottakban jelenik meg.
 *
 * Asszociációk:
 * - vehicle: a játékoshoz tartozó jármű
 * - wallet: a játékos pénzkezeléséhez tartozó objektum
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
     * @param wallet a játékos pénzkezelését végző objektum
     */
    public Player(String name, Vehicle vehicle, Wallet wallet) {
        this.name = name;
        this.vehicle = vehicle;
        this.wallet = wallet;
    }

    /**
     * A játékos körének kezdetét jelzi.
     *
     * A szkeleton szintjén ez a metódus csak a hívható felület része,
     * valódi játéklogika nélkül.
     */
    public void beginTurn() {
        // Skeleton implementáció: nincs valódi játéklogika.
    }

    /**
     * A játékos körének végét jelzi.
     *
     * A szkeleton szintjén ez a metódus csak a hívható felület része,
     * valódi körlezáró logika nélkül.
     */
    public void endTurn() {
        // Skeleton implementáció: nincs valódi játéklogika.
    }

    /**
     * Visszaadja a játékos teljesítményének számszerű értékét.
     *
     * A pontos számítás a konkrét leszármazott osztály felelőssége.
     *
     * @return a játékos pontszáma
     */
    public abstract int getScore();
}