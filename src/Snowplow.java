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
     * @param owner a jármű tulajdonosa vagy irányítója
     * @param destination a célpont
     * @param speed a jármű sebessége
     */
    public Snowplow(Lane currentLane, Player owner, Object destination, int speed) {
        super(currentLane, owner, destination, speed);
    }

    /**
     * Meghívja a felszerelt kotrófej hatását az aktuális sávon.
     *
     * @param road az az út, amelyhez az aktuális sáv tartozik
     */
    public void work(Road road) {
        if (equippedHead != null && currentLane != null && road != null) {
            equippedHead.applyTo(this, currentLane, road);
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
            consumeMovement(1);
        }
    }
}