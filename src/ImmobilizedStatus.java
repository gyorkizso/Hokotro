/**
 * Az ImmobilizedStatus egy jármű, tipikusan egy busz átmeneti
 * mozgásképtelenségét leíró állapot.
 *
 * Felelőssége a büntetési idő múlásának nyilvántartása ütközés után,
 * valamint annak biztosítása, hogy a jármű a büntetés lejárta után
 * újra mozgásképessé váljon.
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
        Skeleton.instance.createObject(this, "target", target, "remainingTurns", remainingTurns);
        this.target = target;
        this.remainingTurns = remainingTurns;
    }

    /**
     * Minden kör végén csökkenti a hátralévő büntetési időt.
     *
     * Ha a büntetés lejár, eltávolítja az állapotot a buszról.
     */
    public void decrementTurn() {
        Skeleton.instance.methodCall(this, "decrementTurn");

        remainingTurns--;

        if (remainingTurns <= 0 && target != null) {
            target.clearStatus();
        }

        Skeleton.instance.methodReturn(this, "decrementTurn");
    }
}