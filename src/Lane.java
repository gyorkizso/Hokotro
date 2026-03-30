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
        Skeleton.instance.createObject(this);
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
        Skeleton.instance.methodCall(this,"acceptVehicle", "vehicle", v);
        int i;

        if (v != null) {
            vehicles.add(v);
            for (i = 0; i < laneStates.size(); i++) {
                laneStates.get(i).onVehicleEnter(v);
            }
        }
        Skeleton.instance.methodReturn(this, "acceptVehicle");
    }

    /**
     * Növeli a havat és erről értesíti az összes sávállapotot.
     *
     * @param amount a hozzáadandó hómennyiség
     */
    public void receiveSnow(int amount) {
        Skeleton.instance.methodCall(this,"receiveSnow", "amount", amount);
        int i;

        if (amount > 0) {
            snowAmount += amount;
            for (i = 0; i < laneStates.size(); i++) {
                laneStates.get(i).onSnowfall(amount);
            }
        }
        Skeleton.instance.methodReturn(this, "receiveSnow");
    }

    /**
     * Meghívódik, ha a sáv le lett tisztítva, és erről értesíti az összes
     * sávállapotot.
     *
     * @param head a tisztítást végző fej
     */
    public void clean(PlowHead head) {
        Skeleton.instance.methodCall(this,"clean","head",head);
        int i;

        if (head != null) {
            for (i = 0; i < laneStates.size(); i++) {
                laneStates.get(i).onCleaned();
            }
        }
        Skeleton.instance.methodReturn(this, "clean");
    }

    /**
     * Lecseréli a megadott sávállapotot egy újra.
     *
     * @param oldState a lecserélendő régi állapot
     * @param newState az új állapot
     */
    public void replaceLaneState(LaneState oldState, LaneState newState) {
        Skeleton.instance.methodCall(this,"replaceLaneState", "oldState",oldState,"newState", newState);
        int index;

        index = laneStates.indexOf(oldState);
        if (index >= 0 && newState != null) {
            laneStates.set(index, newState);
        }
        Skeleton.instance.methodReturn(this, "replaceLaneState");
    }

    /**
     * Hozzáad egy új sávállapotot a sávhoz.
     *
     * @param newState a hozzáadandó állapot
     */
    public void addLaneState(LaneState newState) {
        Skeleton.instance.methodCall(this,"addLaneState", "newState",newState);
        Skeleton.instance.methodReturn(this, "addLaneState");
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
        Skeleton.instance.methodCall(this,"removeLaneState","oldState", oldState);
        Skeleton.instance.methodReturn(this, "removeLaneState");
        laneStates.remove(oldState);
    }

    /**
     * A sáv inaktívvá válik.
     */
    public void deactivateLane() {
        Skeleton.instance.methodCall(this,"deactivateLane");
        active = false;
        Skeleton.instance.methodReturn(this, "deactivateLane");
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
        Skeleton.instance.methodCall(this,"getSnowAmount");
        Skeleton.instance.methodReturn(this, "getSnowAmount",snowAmount);
        return snowAmount;
    }

     /**
     * Visszaadja a sáv aktuális jégmennyiségét.
     *
     * @return az aktuális jégmennyiség
     */
    public int getIceAmount() {
        return iceAmount;
    }

    /**
     * Lenullázza a sáv aktuális hómennyiségét.
     *
     * Megjegyzés: ez a metódus technikai segédfüggvény a skeletonhoz.
     */
    public void clearSnow() {
        Skeleton.instance.methodCall(this,"clearSnow");
        snowAmount = 0;
        Skeleton.instance.methodReturn(this, "clearSnow");
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
        Skeleton.instance.methodCall(this,"removeAllIce");
        int previousIceAmount;

        previousIceAmount = iceAmount;
        iceAmount = 0;

        Skeleton.instance.methodReturn(this, "removeAllIce", previousIceAmount);
        return previousIceAmount;
    }

    /**
     * Beállítja a hómennyiséget.
     * @param amount a beállítandó hómennyiség.
     */
    public void setSnowAmount(int amount){
        snowAmount = amount;
    }

    /**
     * Beállítja a hómennyiséget.
     * @param amount a beállítandó jégmennyiség.
     */
    public void setIceAmount(int amount){
        iceAmount = amount;
    }
}