import java.util.ArrayList;
import java.util.List;

/**
 * A Lane az út egyetlen sávját reprezentálja.
 */
public class Lane {
    /** A felgyülemlett hó mennyisége. */
    private int snowAmount;

    /** A jegesedés mértéke. */
    private int iceAmount;

    /** A sáv aktuális állapotai. */
    private List<LaneState> laneStates;

    /** A sávon lévő járművek. */
    private List<Vehicle> vehicles;

    /** Azt jelzi, hogy a sáv aktív-e. */
    private boolean active;

    /**
     * Létrehoz egy új sávot.
     */
    public Lane() {
        snowAmount = 0;
        iceAmount = 0;
        laneStates = new ArrayList<LaneState>();
        vehicles = new ArrayList<Vehicle>();
        active = true;
    }

    /**
     * Fogadja a járművet és erről értesíti az összes sávállapotot.
     *
     * @param v az érkező jármű
     */
    public void acceptVehicle(Vehicle v) {
        int i;

        if (v != null) {
            vehicles.add(v);
            for (i = 0; i < laneStates.size(); i++) {
                laneStates.get(i).onVehicleEnter(v);
            }
        }
    }

    /**
     * Növeli a havat és erről értesíti az összes sávállapotot.
     *
     * @param amount a hozzáadandó hómennyiség
     */
    public void receiveSnow(int amount) {
        int i;

        if (amount > 0) {
            snowAmount += amount;
            for (i = 0; i < laneStates.size(); i++) {
                laneStates.get(i).onSnowfall(amount);
            }
        }
    }

    /**
     * Meghívódik, ha a sáv le lett tisztítva, és erről értesíti az összes
     * sávállapotot.
     *
     * @param head a tisztítást végző fej
     */
    public void clean(PlowHead head) {
        int i;

        if (head != null) {
            for (i = 0; i < laneStates.size(); i++) {
                laneStates.get(i).onCleaned();
            }
        }
    }

    /**
     * Lecseréli a megadott sávállapotot egy újra.
     *
     * @param oldState a lecserélendő régi állapot
     * @param newState az új állapot
     */
    public void replaceLaneState(LaneState oldState, LaneState newState) {
        int index;

        index = laneStates.indexOf(oldState);
        if (index >= 0 && newState != null) {
            laneStates.set(index, newState);
        }
    }

    /**
     * Hozzáad egy új sávállapotot a sávhoz.
     *
     * @param newState a hozzáadandó állapot
     */
    public void addLaneState(LaneState newState) {
        if (newState != null) {
            laneStates.add(newState);
        }
    }

    /**
     * Eltávolít egy sávállapotot.
     *
     * @param oldState az eltávolítandó állapot
     */
    public void removeLaneState(LaneState oldState) {
        laneStates.remove(oldState);
    }

    /**
     * A sáv inaktívvá válik.
     */
    public void deactivateLane() {
        active = false;
    }

    /**
     * Visszaadja a sáv aktuális hómennyiségét.
     *
     * Megjegyzés: ez a metódus technikai segédfüggvény a skeletonhoz,
     * amelyet a söprő fej használatának szekvenciadiagramja indokol.
     *
     * @return az aktuális hómennyiség
     */
    public int getSnowAmount() {
        return snowAmount;
    }

    /**
     * Lenullázza a sáv aktuális hómennyiségét.
     *
     * Megjegyzés: ez a metódus technikai segédfüggvény a skeletonhoz.
     */
    public void clearSnow() {
        snowAmount = 0;
    }

    /**
     * Eltávolítja a sáv teljes jégmennyiségét, és visszaadja annak értékét.
     *
     * A metódus a jégtörő és sárkány fej  működésének támogatására szolgál.
     * Meghívásakor a sáv jégmennyisége nullára csökken, és a korábbi
     * érték visszatérési értékként kerül átadásra.
     *
     * @return az eltávolított jég mennyisége
     */
    public int removeAllIce() {
        int previousIceAmount;

        previousIceAmount = iceAmount;
        iceAmount = 0;

        return previousIceAmount;
    }
}