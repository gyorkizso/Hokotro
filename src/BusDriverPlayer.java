/**
 * A BusDriverPlayer egy buszt irányító játékost reprezentál.
 *
 * Felelőssége a járatok teljesítése és a buszok eljuttatása a
 * végállomásokra a megállók megfelelő érintésével.
 */
public class BusDriverPlayer extends Player {

    /** A játékos birtokában lévő busz. */
    private Bus bus;

    /**
     * Létrehoz egy új buszvezető játékost.
     *
     * @param name a játékos neve
     * @param vehicle a játékoshoz tartozó jármű
     * @param wallet a játékos pénztárcája
     * @param bus az irányított busz
     */
    public BusDriverPlayer(String name, Vehicle vehicle, Wallet wallet, Bus bus) {
        super(name, vehicle, wallet);

        Skeleton.instance.createObject(this, "name", name, "vehicle", vehicle, "wallet", wallet, "bus", bus);

        this.bus = bus;
    }

    /**
     * Kiválaszt egy járatot, és hozzárendeli a megadott buszhoz.
     *
     * @param bus az érintett busz
     * @param route a kiválasztott járat
     */
    public void selectRoute(Bus bus, BusRoute route) {
        Skeleton.instance.methodCall(this,
                "selectRoute",
                "bus", bus,
                "route", route);

        if (bus != null && route != null) {
            route.setWallet(wallet);
            bus.setActiveRoute(route);
        }

        Skeleton.instance.methodReturn(this, "selectRoute");
    }

    /**
     * Lezárja a busz befejezett járatát.
     *
     * A metódus lekéri a busz aktív járatát, majd meghívja rajta
     * a teljesítés ellenőrzését és a jutalom jóváírását.
     *
     * @param bus az érintett busz
     */
    public void completeRoute(Bus bus) {
        Skeleton.instance.methodCall(this, "completeRoute", "bus", bus);

        if (bus != null) {
            BusRoute activeRoute = bus.getActiveRoute();

            if (activeRoute != null) {
                activeRoute.completeIfTerminalsReached();
            }
        }

        Skeleton.instance.methodReturn(this, "completeRoute");
    }

    /**
     * Visszaadja a játékos által vezetett buszt.
     *
     * @return a busz
     */
    public Bus getBus() {
        Skeleton.instance.methodCall(this, "getBus");
        Skeleton.instance.methodReturn(this, "getBus", bus);

        return bus;
    }

    /**
     * Visszaadja a buszvezető pontszámát.
     *
     * @return a játékos pontszáma
     */
    @Override
    public int getScore() {
        Skeleton.instance.methodCall(this, "getScore");

        int score = 0;

        if (wallet != null) {
            score = wallet.getFunds();
        }

        Skeleton.instance.methodReturn(this, "getScore", score);
        return score;
    }
}