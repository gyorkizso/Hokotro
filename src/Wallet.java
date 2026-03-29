/**
 * A Wallet a csapat közös pénzének kezeléséért felel.
 *
 * Felelőssége a bevételek jóváírása és a vásárlások fedezetének
 * biztosítása.
 */
public class Wallet {
    /** A csapat aktuális egyenlege. */
    private int balance;

    /**
     * Létrehoz egy új tárcát.
     *
     * @param balance a kezdeti egyenleg
     */
    public Wallet(int balance) {
        this.balance = balance;
    }

    /**
     * Növeli az egyenleget.
     *
     * @param amount a jóváírandó összeg
     */
    public void addFunds(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    /**
     * Levonja az összeget, ha van elegendő fedezet.
     *
     * @param amount a levonandó összeg
     * @return igaz, ha a levonás sikeres volt; különben hamis
     */
    public boolean deductFunds(int amount) {
        if (amount < 0) {
            return false;
        }

        if (balance >= amount) {
            balance -= amount;
            return true;
        }

        return false;
    }

    /**
     * Visszaadja az aktuális egyenleget.
     *
     * @return a jelenlegi egyenleg
     */
    public int getFunds() {
        return balance;
    }
}