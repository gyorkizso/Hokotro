/**
 * A ClearState a tiszta, hómentes vagy alacsony havú útállapotot reprezentálja.
 *
 * Felelőssége, hogy a havazás hatására a sáv a következő állapot felé
 * mozdulhasson. A skeleton szintjén ez az osztály csak a dokumentált
 * felületet biztosítja.
 */
public class ClearState extends LaneState {
    /**
     * Létrehoz egy új tiszta sávállapotot.
     *
     * @param lane az érintett sáv
     */
    public ClearState(Lane lane) {
        super(lane);
        Skeleton.instance.createObject(this, "lane", lane);
    }

    /**
     * Kezeli a havazás hatását tiszta állapotban.
     *
     * A skeleton szintjén ez a metódus csak a hívható felület része,
     * állapotváltási logika nélkül.
     *
     * @param amount a lehullott hó mennyisége
     */
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this, "onSnowfall", "lane", lane);
        Skeleton.instance.methodReturn(this, "onSnowfall" );
        // Skeleton implementáció: nincs külön teendő.
    }

    
}