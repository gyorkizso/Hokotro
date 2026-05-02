/**
 * A Snowplow takarító által irányított jármű.
 */
public class Snowplow extends Vehicle {
    /** A jelenleg felszerelt kotrófej. */
    private PlowHead equippedHead;

    /**
     * Létrehoz egy új hókotrót.
     *
     * @param currentLane az aktuális sáv
     * @param speed a jármű sebessége
     */
    public Snowplow(Lane currentLane, int speed) {
        super(currentLane, null, speed);
    }

    /**
     * Meghívja a felszerelt kotrófej hatását az aktuális sávon.
     *
     * @param road az az út, amelyhez az aktuális sáv tartozik
     */
    public void work() {
        if (equippedHead != null && currentLane != null && currentLane.getRoad() != null) {
            equippedHead.applyTo(this, currentLane, currentLane.getRoad());
        }
    }

    /**
     * Lecseréli a jelenlegi fejet az újra.
     *
     * @param newHead az új kotrófej
     */
    public void equipHead(PlowHead newHead) {
        if (newHead != null) {
            equippedHead = newHead;
        }
    }
}