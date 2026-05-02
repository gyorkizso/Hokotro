/**
 * A Result egy játékos mérkőzés végi eredményét reprezentálja.
 *
 * Felelőssége, hogy tárolja a játékoshoz tartozó pontszámot,
 * és egységes formában visszaadható legyen.
 */
public class Result {

    /** Az eredményhez tartozó játékos. */
    private Player player;

    /** A játékos pontszáma. */
    private int score;

    /**
     * Létrehoz egy új eredmény objektumot.
     *
     * @param player az érintett játékos
     * @param score a játékos pontszáma
     */
    public Result(Player player, int score) {
        Skeleton.instance.createObject(this,
                "player", player,
                "score", score);

        this.player = player;
        this.score = score;
    }

    /**
     * Visszaadja az eredményhez tartozó játékost.
     *
     * @return a játékos
     */
    public Player getPlayer() {
        Skeleton.instance.methodCall(this, "getPlayer");
        Skeleton.instance.methodReturn(this, "getPlayer", player);

        return player;
    }

    /**
     * Visszaadja a pontszámot.
     *
     * @return a pontszám
     */
    public int getScore() {
        Skeleton.instance.methodCall(this, "getScore");
        Skeleton.instance.methodReturn(this, "getScore", score);

        return score;
    }

    /**
     * Szöveges reprezentációt ad vissza az eredményről.
     *
     * @return "<név> eredménye: <score>"
     */
    @Override
    public String toString() {
        Skeleton.instance.methodCall(this, "toString");

        String result;

        if (player != null) {
            result = player.getName() + " eredménye: " + score;
        } else {
            result = "Ismeretlen játékos eredménye: " + score;
        }

        Skeleton.instance.methodReturn(this, "toString", result);
        return result;
    }
}