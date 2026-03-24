/**
 * Az ImmobilizedStatus egy jármű, tipikusan egy busz átmeneti
 * mozgásképtelenségét leíró állapot.
 *
 * Felelőssége a büntetési idő múlásának nyilvántartása ütközés után.
 *
 * Asszociáció:
 * - target: a busz, amelyre a hatás érvényesül
 */
public class ImmobilizedStatus {
    /** A busz, amelyre a mozgásképtelen állapot érvényes. */
    private Bus target;

    /** A hátralévő büntetési körök száma. */
    private int remainingTurns;

    /**
     * Létrehoz egy új mozgásképtelen állapotot.
     *
     * @param target az érintett busz
     * @param remainingTurns a hátralévő büntetési körök száma
     */
    public ImmobilizedStatus(Bus target, int remainingTurns) {
        this.target = target;
        this.remainingTurns = remainingTurns;
    }

    /**
     * Minden kör végén csökkenti a hátralévő büntetési időt.
     *
     * A számláló nem csökkenhet 0 alá.
     */
    public void decrementTurn() {
        if (remainingTurns > 0) {
            remainingTurns--;
        }
    }
}