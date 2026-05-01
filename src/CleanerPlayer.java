import java.util.ArrayList;
import java.util.List;

/**
 * A CleanerPlayer egy hókotrót irányító játékost reprezentál.
 *
 * Felelőssége a csapat pénzének növelése az utak takarításával, valamint
 * a hókotrók, fejek és fogyóanyagok menedzselése.
 */
public class CleanerPlayer extends Player {

    /** Egy sikeres takarításért járó jutalom. */
    private static final int CLEANING_REWARD = 10;

    /** A játékos birtokában lévő hókotró. */
    private Snowplow snowplow;

    /** A játékos által megvásárolt kotrófejek listája. */
    private List<PlowHead> ownedHeads;

    /** A takarító játékos pontszáma. */
    private int score;

    /**
     * Létrehoz egy új takarító játékost.
     *
     * @param name a játékos neve
     * @param vehicle a játékoshoz tartozó jármű
     * @param wallet a játékos pénztárcája
     * @param snowplow az irányított hókotró
     */
    public CleanerPlayer(String name, Vehicle vehicle, Wallet wallet, Snowplow snowplow) {
        super(name, vehicle, wallet);

        Skeleton.instance.createObject(this, "name", name, "vehicle", vehicle, "wallet", wallet, "snowplow", snowplow);

        this.snowplow = snowplow;
        this.ownedHeads = new ArrayList<PlowHead>();
        this.score = 0;
    }

    /**
     * Elvégzi a takarítást a megadott hókotróval.
     *
     * A hókotró meghívja a felszerelt fej működését, majd sikeres művelet
     * után a játékos pénzt és pontot kap.
     *
     * @param plow a takarítást végző hókotró
     */
    public void performCleaning(Snowplow plow) {
        Skeleton.instance.methodCall(this, "performCleaning", "plow", plow);

        if (plow != null) {
            plow.work();

            if (wallet != null) {
                wallet.addFunds(CLEANING_REWARD);
            }

            score += CLEANING_REWARD;
        }

        Skeleton.instance.methodReturn(this, "performCleaning");
    }

    /**
     * Lecseréli a megadott hókotró fejét.
     *
     * A fejcsere egy mozgásegységbe kerül. Ha a hókotró nem tud már
     * mozogni ebben a körben, a csere nem történik meg.
     *
     * @param plow az érintett hókotró
     * @param newHead az új kotrófej
     */
    public void changePlowHead(Snowplow plow, PlowHead newHead) {
        Skeleton.instance.methodCall(this, "changePlowHead", "plow", plow, "newHead", newHead);

        if (plow != null && newHead != null && plow.getMovementRemaining() > 0) {
            plow.consumeMovement(1);
            plow.equipHead(newHead);
        }

        Skeleton.instance.methodReturn(this, "changePlowHead");
    }

    /**
     * Átveszi a megvásárolt kotrófejet.
     *
     * @param head az átvett kotrófej
     */
    public void receiveHead(PlowHead head) {
        Skeleton.instance.methodCall(this, "receiveHead", "head", head);

        if (head != null) {
            ownedHeads.add(head);
        }

        Skeleton.instance.methodReturn(this, "receiveHead");
    }

    /**
     * Visszaadja a játékos által irányított hókotrót.
     *
     * @return a hókotró
     */
    public Snowplow getSnowplow() {
        Skeleton.instance.methodCall(this, "getSnowplow");
        Skeleton.instance.methodReturn(this, "getSnowplow", snowplow);

        return snowplow;
    }

    /**
     * Visszaadja a játékos megvásárolt kotrófejeit.
     *
     * @return a kotrófejek listája
     */
    public List<PlowHead> getOwnedHeads() {
        Skeleton.instance.methodCall(this, "getOwnedHeads");
        Skeleton.instance.methodReturn(this, "getOwnedHeads", ownedHeads);

        return ownedHeads;
    }

    /**
     * Visszaadja a takarító játékos pontszámát.
     *
     * @return a játékos pontszáma
     */
    @Override
    public int getScore() {
        Skeleton.instance.methodCall(this, "getScore");
        Skeleton.instance.methodReturn(this, "getScore", score);

        return score;
    }
}