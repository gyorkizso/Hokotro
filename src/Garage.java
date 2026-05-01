import java.util.ArrayList;
import java.util.List;

/**
 * A Garage vásárlási helyszínt reprezentál.
 *
 * Felelőssége a megvásárolható tételek kínálatának tárolása,
 * valamint a vásárlási tranzakciók lebonyolítása.
 *
 * Az osztály biztosítja, hogy a játékos csak megfelelő fedezet
 * esetén tudjon eszközt, járművet, kotrófejet vagy fogyóanyagot
 * vásárolni.
 */
public class Garage {

    /** A kereszteződés, ahol a garázs található. */
    private Intersection location;

    /** Az elérhető megvásárolható tételek gyűjteménye. */
    private List<Purchasable> offers;

    /**
     * Létrehoz egy új garázst a megadott helyszínen.
     *
     * @param location a garázs helyszíne
     */
    public Garage(Intersection location) {
        Skeleton.instance.createObject(this, "location", location);

        this.location = location;
        this.offers = new ArrayList<Purchasable>();
    }

    /**
     * Hozzáad egy új megvásárolható tételt a garázs kínálatához.
     *
     * @param item a hozzáadandó vásárlási tétel
     */
    public void addOffer(Purchasable item) {
        Skeleton.instance.methodCall(this, "addOffer", "item", item);

        if (item != null) {
            offers.add(item);
        }

        Skeleton.instance.methodReturn(this, "addOffer");
    }

    /**
     * Visszaadja a garázs kínálatát.
     *
     * @return az elérhető megvásárolható tételek listája
     */
    public List<Purchasable> getOffers() {
        Skeleton.instance.methodCall(this, "getOffers");
        Skeleton.instance.methodReturn(this, "getOffers", offers);

        return offers;
    }

    /**
     * Visszaadja a garázs helyét.
     *
     * @return a garázshoz tartozó kereszteződés
     */
    public Intersection getLocation() {
        Skeleton.instance.methodCall(this, "getLocation");
        Skeleton.instance.methodReturn(this, "getLocation", location);

        return location;
    }

    /**
     * Lebonyolít egy vásárlási tranzakciót.
     *
     * A metódus ellenőrzi, hogy a tétel szerepel-e a kínálatban,
     * majd megpróbálja levonni az árát a vásárló pénztárcájából.
     * Sikeres fizetés esetén meghívja a vásárlási tétel hatását.
     *
     * @param buyer a vásárló játékos
     * @param item a megvásárolandó tétel
     */
    public void processTransaction(Player buyer, Purchasable item) {
        Skeleton.instance.methodCall(this, "processTransaction", "buyer", buyer, "item", item);

        if (buyer == null || item == null) {
            Skeleton.instance.methodReturn(this, "processTransaction");
            return;
        }

        if (!offers.contains(item)) {
            Skeleton.instance.methodReturn(this, "processTransaction");
            return;
        }

        Wallet wallet = buyer.getWallet();

        if (wallet == null) {
            Skeleton.instance.methodReturn(this, "processTransaction");
            return;
        }

        boolean success = wallet.deductFunds(item.getPrice());

        if (!success) {
            Skeleton.instance.methodReturn(this, "processTransaction");
            return;
        }

        item.applyPurchase(buyer);

        Skeleton.instance.methodReturn(this, "processTransaction");
    }
}