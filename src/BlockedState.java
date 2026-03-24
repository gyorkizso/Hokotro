/**
 * A BlockedState a sáv teljes lezárását reprezentálja.
 *
 * Felelőssége a sáv lezárása a normál forgalom elől addig, amíg
 * egy megfelelő takarítási művelet meg nem szünteti az akadályt.
 */
public class BlockedState extends LaneState {
    /**
     * Létrehoz egy új akadály állapotot.
     *
     * @param lane az érintett sáv
     */
    public BlockedState(Lane lane) {
        super(lane);
    }

    /**
     * Kezeli a letakarítás hatását akadály állapotban.
     *
     * A leírás szerint takarítás hatására az akadály megszűnik. Mivel a
     * LaneState bázisosztályban a metódus visszatérési típusa void, itt is
     * ahhoz igazodunk.
     */
    public void onCleaned() {
        // Skeleton implementáció: az akadály megszüntetésének helye.
    }
}