import java.util.ArrayList;
import java.util.List;

/**
 * A Lane az út egyetlen sávját reprezentálja.
 *
 * Felelőssége a hó és jég mennyiségének nyilvántartása,
 * a sáv állapotainak kezelése, valamint a járművek és időjárási
 * hatások fogadása.
 */
public class Lane {

    /** A sávon felgyülemlett hó mennyisége. */
    private int snowAmount;

    /** A sáv jegesedésének mértéke. */
    private int iceAmount;

    /** A sáv aktuális állapotai. */
    private List<LaneState> laneStates;

    /** A sávon tartózkodó járművek. */
    private List<Vehicle> vehicles;

    /** Az út, amelyhez a sáv tartozik. */
    private Road road;

    /** Jelzi, hogy a sáv aktív-e. */
    private boolean active;

    /** Jelzi, hogy a sáv alagútban van-e. */
    private boolean isTunnel;

    /**
     * Létrehoz egy új sávot út és alagútjelzés nélkül.
     */
    public Lane() {
        this(null, false);
    }

    /**
     * Létrehoz egy új sávot a megadott úthoz.
     *
     * @param road az út, amelyhez a sáv tartozik
     */
    public Lane(Road road) {
        this(road, false);
    }

    /**
     * Létrehoz egy új sávot.
     *
     * @param road az út, amelyhez a sáv tartozik
     * @param isTunnel igaz, ha a sáv alagútban van
     */
    public Lane(Road road, boolean isTunnel) {
        Skeleton.instance.createObject(this, "road", road, "isTunnel", isTunnel);

        this.snowAmount = 0;
        this.iceAmount = 0;
        this.laneStates = new ArrayList<LaneState>();
        this.vehicles = new ArrayList<Vehicle>();
        this.road = road;
        this.active = true;
        this.isTunnel = isTunnel;
    }

    /**
     * Fogadja a járművet és értesíti az állapotokat.
     *
     * @param v az érkező jármű
     */
    public void acceptVehicle(Vehicle v) {
        Skeleton.instance.methodCall(this, "acceptVehicle", "vehicle", v);

        if (v != null) {
            if (!vehicles.contains(v)) {
                vehicles.add(v);
            }

            List<LaneState> statesCopy = new ArrayList<LaneState>(laneStates);

            for (LaneState state : statesCopy) {
                state.onVehicleEnter(v);
            }
        }

        Skeleton.instance.methodReturn(this, "acceptVehicle");
    }

   /**
     * Növeli a havat és erről értesíti az összes sávállapotot.
     *
     * Ha a sáv alagútban van, nem történik változás.
     *
     * @param amount a lehullott hó mennyisége
     */
    public void receiveSnow(int amount) {
        Skeleton.instance.methodCall(this, "receiveSnow", "amount", amount);

        if (!isTunnel && amount > 0) {
            snowAmount += amount;

            List<LaneState> statesCopy = new ArrayList<LaneState>(laneStates);

            for (LaneState state : statesCopy) {
                state.onSnowfall(amount);
            }
        }

        Skeleton.instance.methodReturn(this, "receiveSnow");
    }

    /**
     * Jelzi, hogy a sávot takarítás érte.
     *
     * @param head a takarítást végző fej
     */
    public void clean(PlowHead head) {
        Skeleton.instance.methodCall(this, "clean", "head", head);

        List<LaneState> statesCopy = new ArrayList<LaneState>(laneStates);

        for (LaneState state : statesCopy) {
            state.onCleaned();
        }

        Skeleton.instance.methodReturn(this, "clean");
    }

    /**
     * Eltávolítja a járművet a sávból.
     *
     * @param v az eltávolítandó jármű
     */
    public void removeVehicle(Vehicle v) {
        Skeleton.instance.methodCall(this, "removeVehicle", "vehicle", v);

        vehicles.remove(v);

        Skeleton.instance.methodReturn(this, "removeVehicle");
    }

    /**
     * Lecseréli a megadott állapotot egy újra.
     *
     * @param oldState a lecserélendő állapot
     * @param newState az új állapot
     */
    public void replaceLaneState(LaneState oldState, LaneState newState) {
        Skeleton.instance.methodCall(this, "replaceLaneState", "oldState", oldState, "newState", newState);

        int index = laneStates.indexOf(oldState);

        if (index >= 0 && newState != null) {
            laneStates.set(index, newState);
        }

        Skeleton.instance.methodReturn(this, "replaceLaneState");
    }

    /**
     * Hozzáad egy új állapotot a sávhoz.
     *
     * @param newState az új állapot
     */
    public void addLaneState(LaneState newState) {
        Skeleton.instance.methodCall(this, "addLaneState", "newState", newState);

        if (newState != null && !laneStates.contains(newState)) {
            laneStates.add(newState);
        }

        Skeleton.instance.methodReturn(this, "addLaneState");
    }

    /**
     * Eltávolít egy állapotot a sávból.
     *
     * @param oldState az eltávolítandó állapot
     */
    public void removeLaneState(LaneState oldState) {
        Skeleton.instance.methodCall(this, "removeLaneState", "oldState", oldState);

        laneStates.remove(oldState);

        Skeleton.instance.methodReturn(this, "removeLaneState");
    }

    /**
     * A sáv inaktívvá válik.
     */
    public void deactivateLane() {
        Skeleton.instance.methodCall(this, "deactivateLane");

        active = false;

        Skeleton.instance.methodReturn(this, "deactivateLane");
    }

    /**
     * Visszaadja a sáv aktuális hómennyiségét.
     *
     * @return a hómennyiség
     */
    public int getSnowAmount() {
        Skeleton.instance.methodCall(this, "getSnowAmount");
        Skeleton.instance.methodReturn(this, "getSnowAmount", snowAmount);
        return snowAmount;
    }

    /**
     * Visszaadja a sáv aktuális jégmennyiségét.
     *
     * @return a jégmennyiség
     */
    public int getIceAmount() {
        Skeleton.instance.methodCall(this, "getIceAmount");
        Skeleton.instance.methodReturn(this, "getIceAmount", iceAmount);
        return iceAmount;
    }


    /**
     * Beállítja a sáv aktuális hómennyiségét.
     *
     * @param amount a beállítandó hómennyiség
     */
    public void setSnowAmount(int amount) {
        Skeleton.instance.methodCall(this, "setSnowAmount", "amount", amount);

        snowAmount = Math.max(0, amount);

        Skeleton.instance.methodReturn(this, "setSnowAmount");
    }

    /**
     * Beállítja a sáv aktuális jégmennyiségét.
     *
     * @param amount a beállítandó jégmennyiség
     */
    public void setIceAmount(int amount) {
        Skeleton.instance.methodCall(this, "setIceAmount", "amount", amount);

        iceAmount = Math.max(0, amount);

        Skeleton.instance.methodReturn(this, "setIceAmount");
    }

    /**
     * Növeli a sáv jegesedésének mértékét a megadott értékkel.
     *
     * @param amount a hozzáadandó jégmennyiség
     */
    public void addIce(int amount) {
        Skeleton.instance.methodCall(this, "addIce", "amount", amount);

        if (amount > 0) {
            iceAmount += amount;
        }

        Skeleton.instance.methodReturn(this, "addIce");
    }


    /**
     * Növeli a sáv hómennyiségét a megadott értékkel.
     *
     * @param amount a hozzáadandó hómennyiség
     */
    public void addSnow(int amount) {
        Skeleton.instance.methodCall(this, "addSnow", "amount", amount);

        if (amount > 0) {
            snowAmount += amount;
        }

        Skeleton.instance.methodReturn(this, "addSnow");
    }

    /**
     * A sáv hómennyiségét 0-ra állítja.
     */
    public void clearSnow() {
        Skeleton.instance.methodCall(this, "clearSnow");

        snowAmount = 0;

        Skeleton.instance.methodReturn(this, "clearSnow");
    }

    /**
     * A sáv jégmennyiségét 0-ra állítja.
     */
    public void clearIce() {
        Skeleton.instance.methodCall(this, "clearIce");

        iceAmount = 0;

        Skeleton.instance.methodReturn(this, "clearIce");
    }

    /**
     * Visszaadja, hogy a sáv rendelkezik-e olyan állapottal,
     * amely akadályozza a közlekedést (pl. BlockedState).
     *
     * @return igaz, ha a sáv blokkolt; különben hamis
     */
    public boolean isBlocked() {
        Skeleton.instance.methodCall(this, "isBlocked");

        boolean blocked = !active;

        for (LaneState state : laneStates) {
            if (state instanceof BlockedState || state instanceof SnowdriftState) {
                blocked = true;
                break;
            }
        }

        Skeleton.instance.methodReturn(this, "isBlocked", blocked);
        return blocked;
    }

    /**
     * Visszaadja az adott távolságra lévő szomszédos sávokat
     * a sávhoz tartozó úton belül.
     *
     * @param distance a keresési távolság
     * @return a szomszédos sávok listája
     */
    public List<Lane> getNeighborLanes(int distance) {
        Skeleton.instance.methodCall(this, "getNeighborLanes", "distance", distance);

        List<Lane> result;

        if (road == null) {
            result = new ArrayList<Lane>();
        } else {
            result = road.getNeighborLanes(this, distance);
        }

        Skeleton.instance.methodReturn(this, "getNeighborLanes", result);
        return result;
    }

    /**
     * Visszaadja azt az utat, amelyhez a sáv tartozik.
     *
     * @return a sávhoz tartozó út
     */
    public Road getRoad() {
        Skeleton.instance.methodCall(this, "getRoad");
        Skeleton.instance.methodReturn(this, "getRoad", road);
        return road;
    }

    /**
     * Visszaadja, hogy a sáv alagútszakaszhoz tartozik-e.
     *
     * @return igaz, ha a sáv alagútban van; különben hamis
     */
    public boolean isTunnel() {
        Skeleton.instance.methodCall(this, "isTunnel");
        Skeleton.instance.methodReturn(this, "isTunnel", isTunnel);
        return isTunnel;
    }

    /**
     * Visszaadja, hogy a sáv rendelkezik-e a megadott állapottal.
     *
     * @param state a keresett állapot
     * @return igaz, ha a sáv tartalmazza az adott állapotot; különben hamis
     */
    public boolean hasLaneState(LaneState state) {
        Skeleton.instance.methodCall(this, "hasLaneState", "state", state);

        boolean result = laneStates.contains(state);

        Skeleton.instance.methodReturn(this, "hasLaneState", result);
        return result;
    }

    /**
     * Visszaadja a sávon tartózkodó járművek listáját.
     *
     * @return a járművek listája
     */
    public List<Vehicle> getVehicles() {
        Skeleton.instance.methodCall(this, "getVehicles");
        Skeleton.instance.methodReturn(this, "getVehicles", vehicles);
        return vehicles;
    }
}