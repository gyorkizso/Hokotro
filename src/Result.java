/**
 * A Result egy játékos eredményét reprezentáló egyszerű értékobjektum.
 */
public class Result {
    /** A tárolt pontszám. */
    private int score;

    /**
     * Létrehoz egy új eredményt.
     *
     * @param score a tárolt pontszám
     */
    public Result(int score) {
        this.score = score;
    }
}