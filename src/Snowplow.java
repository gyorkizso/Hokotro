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
        Skeleton.instance.createObject(this,
                "currentLane", currentLane,
                "speed", speed);
    }

    /**
     * Meghívja a felszerelt kotrófej hatását az aktuális sávon.
     */
    public void work() {
        Skeleton.instance.methodCall(this, "work");
        if (equippedHead != null && currentLane != null && currentLane.getRoad() != null) {
            equippedHead.applyTo(this, currentLane, currentLane.getRoad());
        }
        Skeleton.instance.methodReturn(this, "work");
    }

    /**
     * Lecseréli a jelenlegi fejet az újra.
     *
     * @param newHead az új kotrófej
     */
    public void equipHead(PlowHead newHead) {
        Skeleton.instance.methodCall(this, "equipHead", newHead);
        if (newHead != null) {
            equippedHead = newHead;
        }
        Skeleton.instance.methodReturn(this, "equipHead");
    }

    /**
     * Kezeli a jeges sávra érkezést.
     */
    @Override
    public void handleIcyLane(Lane lane) {
        Skeleton.instance.methodCall(this, "handleIcyLane", lane);
        Skeleton.instance.methodReturn(this, "handleIcyLane");
    }
}