/**
 * A SnowdriftState a hótorlasz állapotot reprezentálja.
 *
 * Felelőssége annak megakadályozása, hogy a normál járművek áthaladjanak
 * rajta. A skeleton szintjén ez az osztály csak a dokumentált felületet
 * biztosítja.
 */
public class SnowdriftState extends LaneState {
    /**
     * Létrehoz egy új hótorlasz állapotot.
     *
     * @param lane az érintett sáv
     */
    public SnowdriftState(Lane lane) {
        super(lane);
        Skeleton.instance.createObject(this, "lane",lane);
    }

    /**
     * Kezeli a jármű érkezését hótorlasz állapotban.
     *
     * A skeleton szintjén ez a metódus csak a kötelező felület része,
     * külön áthaladásgátló logika nélkül.
     *
     * @param vehicle az érkező jármű
     */
    public void onVehicleEnter(Vehicle vehicle) {
        Skeleton.instance.methodCall(this,"onVehicleEnter", "vehicle",vehicle);
        // Skeleton implementáció: nincs külön teendő.
        Skeleton.instance.methodReturn(this, "onVehicleEnter");
    }

    /**
     * Kezeli a havazás hatását hótorlasz állapotban.
     *
     * A leírás szerint a hómennyiség már a maximumon van, ezért itt
     * további növekedés nem történik.
     *
     * @param amount a lehullott hó mennyisége
     */
    public void onSnowfall(int amount) {
        Skeleton.instance.methodCall(this,"onSnowfall","amount",amount);
        // Skeleton implementáció: a hó mennyisége nem nő tovább.
        Skeleton.instance.methodReturn(this, "onSnowfall");
    }

    /**
     * Kezeli a letakarítás hatását hótorlasz állapotban.
     *
     * A skeleton szintjén ez a metódus csak a kötelező felület része.
     */
    public void onCleaned() {
        Skeleton.instance.methodCall(this,"onCleaned");
        // Skeleton implementáció: nincs külön teendő.
        Skeleton.instance.methodReturn(this, "onCleaned");
    }
}