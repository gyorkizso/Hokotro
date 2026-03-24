/**
 * Az IceSheetState a jégpáncélos útállapotot reprezentálja.
 *
 * Felelőssége a balesetveszély szimulálása: a rajta áthaladó normál
 * járművek számára megcsúszást válthat ki.
 */
public class IceSheetState extends LaneState {
    /**
     * Létrehoz egy új jégpáncél állapotot.
     *
     * @param lane az érintett sáv
     */
    public IceSheetState(Lane lane) {
        super(lane);
    }

    /**
     * Kezeli a jármű érkezését jégpáncélos állapotban.
     *
     * Az osztályleírás szerint itt a jármű egy specifikus metódusát kellene
     * meghívni. Mivel ilyen metódus a Vehicle jelenlegi osztályleírásában
     * nem szerepel, a skeleton szintjén ez a metódus most helykitöltő.
     *
     * @param vehicle az érkező jármű
     */
    public void onVehicleEnter(Vehicle vehicle) {
        // Skeleton implementáció: a Vehicle osztályban nincs dokumentált
        // handleIcyLane(...) metódus, ezért itt nincs további hívás.
    }

}