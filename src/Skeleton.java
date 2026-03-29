import java.io.Console;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Skeleton {
    public static Skeleton instance;
    public Map<Object, String> names;
    Scanner scanner;

    Skeleton(){
        scanner = new Scanner(System.in);
        instance = this;
    }

    String getStringFromUser(String paramName, String extra){
        System.out.printf("Kérem adja meg a(z) %s értékét %s:", paramName, extra);
        String string = scanner.nextLine();
        return string.strip();
    }

    String getStringFromUser(String paramName){
        return getStringFromUser(paramName, "");
    }

    int getIntFromUser(String paramName) {
        String raw = getStringFromUser(paramName);
        return Integer.parseInt(raw);
    }

    double getDoubleFromUser(String paramName) {
        String raw = getStringFromUser(paramName);
        return Double.parseDouble(raw);
    }

    boolean getBooleanFromUser(String paramName) {
        String raw = getStringFromUser(paramName, "(0=hamis, 1=igaz):");
        switch (raw){
            case "0":
                return false;
            case "1":
                return true;
            default:
                throw new NumberFormatException();
        }
    }

    int getListSelectionFromUser(List<String> options){
        for (int i = 0; i < options.size(); i++){
            System.out.printf("%i. %s%n", i, options.get(i));
        }
        System.out.print("Kérem válasszon a fentiek közül:");
        return Integer.parseInt(scanner.nextLine());
    }

    public void createObject(Object o, Object... args) {
        String type = o.getClass().getTypeName();
        int i = 1;
        while (names.containsValue(type+i)){
            i++;
        }
        String name = type+i;
        names.put(o, name);

        System.out.printf("%s típusú objektum létrehozva %s névvel", type, name);
        if (args.length != 0){
            System.out.print(", ezekkel a konstruktor paraméterekkel: ");
            for (int j = 0; j < args.length; j++) {
                Object paramName = args[j];
                Object value = args[Math.min(j+1, args.length-1)];
                System.out.printf("%s=%s, ", paramName, names.containsKey(value) ? names.get(value) : value);
            }
        }
        System.out.print("\n");
    }

    public void methodCall(Object o, String methodName, Object... args){
        System.out.printf("%s.%s(", names.get(o), methodName);
        for (int j = 0; j < args.length; j+=2) {
            Object paramName = args[j];
            Object value = args[Math.min(j+1, args.length-1)];
                System.out.printf("%s=%s, ", paramName, names.containsKey(value) ? names.get(value) : value);
        }
        System.out.print(") meghívva\n");
    }

    public void methodReturn(Object o, String methodName, Object returnValue) {
        System.out.printf("%s.%s visszatért", names.get(o), methodName);
        if (returnValue != null){
            System.out.printf(" %s értékkel", names.containsKey(returnValue) ? names.get(returnValue) : returnValue);
        }
        System.out.print("\n");
    }

    public void methodReturn(Object o, String methodName) {
        methodReturn(o, methodName, null);
    }

    public void testMatchStart(){

    }

    public void testSnowfall() {

    }

    public void tesIcing() {

    }

    public void testVehicleMoving() {

    }

    public void testUsingBroomHead() {

    }

    public void testUsingThrowerHead() {

    }

    public void testUsingIceBreakerHead() {

    }

    public void testUsingSaltSpreaderHead() {

    }

    public void testUsingDragonHead() {

    }

    public void testCollisionHandling() {

    }

    /*
    * Akadály eltávolításának tesztelése a hókotróval, amikor a sáv blokkolva van.
    * A megadott üzemanyag mennyiségtől függ a sikeressége. 
    */

    public void testObstacleRemoval(){
        Lane lane = new Lane();
        BlockedState blockedState = new BlockedState(lane);
        lane.addLaneState(blockedState);
        int amount = getIntFromUser("BioKerosene");
        Snowplow snowplow = new Snowplow(lane, null, null, 5);
        DragonHead dragonHead = new DragonHead(snowplow, lane, new BioKerosene(snowplow, amount));
        snowplow.equipHead(dragonHead);
        CleanerPlayer cleanerPlayer = new CleanerPlayer("Takarító", snowplow, new Wallet(0), snowplow);

        cleanerPlayer.performCleaning(snowplow);
    }

    /* 
    * A kotrófej cseréjének tesztelése.
    */

    public void testPlowHeadChange() {
        Snowplow snowplow = new Snowplow(null, null, null, 4);
        BroomHead broomHead = new BroomHead(snowplow, null);
        snowplow.equipHead(broomHead);
        DragonHead dragonHead = new DragonHead(snowplow, null, new BioKerosene(snowplow, 0));
        CleanerPlayer cleanerPlayer = new CleanerPlayer("Takarító", snowplow, new Wallet(0), snowplow);

        cleanerPlayer.changePlowHead(snowplow, dragonHead);
        System.out.println("A kotrófej lecserélve.");
    }

    /*
    * Az autók útvonalválasztásának tesztelése, amikor több útvonal is rendelkezésre áll.
    * Ha nem tud sávot váltani, akkor marad a másik sávon.
    */

    public void testCarRouteSelection() {
        RoadNetwork roadNetwork = new RoadNetwork();
        Intersection start = new Intersection("A");
        Intersection mid = new Intersection("B");
        Intersection goal = new Intersection("C");
        Road rLong1 = new Road(start, mid);
        Road rLong2 = new Road(mid, goal);
        Road rShort = new Road(start, goal);
        Lane lStart = new Lane();
        Lane lShort = new Lane();

        rLong1.addLane(lStart);
        rShort.addLane(lShort);
        roadNetwork.addIntersection(start);
        roadNetwork.addIntersection(goal);
        roadNetwork.addIntersection(mid);
        roadNetwork.addRoad(rLong1);
        roadNetwork.addRoad(rLong2);
        roadNetwork.addRoad(rShort);
        start.connectRoad(rShort);
        start.connectRoad(rLong1);
        mid.connectRoad(rLong1);
        mid.connectRoad(rLong2);
        goal.connectRoad(rShort);
        goal.connectRoad(rLong2);

        Car car = new Car(lStart, null, goal, 0);
        lStart.acceptVehicle(car);

        car.executeTurn();
        roadNetwork.findShortestPath(mid, goal);
        boolean canChange = getBooleanFromUser("másik sáv járható-e");
        if(canChange){
            car.tryMoveTo(new Lane());
            System.out.println("A jármű sikeresen sávot váltott.");
        } else {
            System.out.println("A jármű nem tudott sávot váltani, mert a másik sáv nem járható.");
        }
    }

    /* 
    * A sávváltás tesztelése akadály esetén.
    * Ha a másik sáv is blokkolva van, akkor a jármű megáll.
    */

    public void testLaneChangeOnObstacle() {
        Road road = new Road(null, null);
        Lane current = new Lane();
        Lane adjacent = new Lane();
        current.addLaneState(new BlockedState(current));
        road.addLane(current);
        road.addLane(adjacent);

        Car car = new Car(current, null, null, 0);
        current.acceptVehicle(car);
        boolean canChange = getBooleanFromUser("másik sáv járható-e");

        car.checkAndChangeLane();
        if(canChange){
            car.tryMoveTo(adjacent);
            System.out.println("A jármű sikeresen sávot váltott.");
        } else {
            System.out.println("A jármű nem tudott sávot váltani, mert a másik sáv nem járható.");
        }
    }

    /*
     * A buszjárat teljesítésének tesztelése.
     */

    public void testCompleteBusRoute(){
        Bus bus = new Bus(null, null, null, 0);
        Intersection start = new Intersection("Start");
        Intersection stop_ = new Intersection("Stop");
        Intersection end = new Intersection("End");

        Terminal t1 = new Terminal(start);
        Stop stop = new Stop(stop_);
        Terminal t2 = new Terminal(end);
        List<RoutePoint> points = List.of(t1, stop, t2);
        BusRoute route = new BusRoute(points, 10, 10);
        BusDriverPlayer busDriverPlayer = new BusDriverPlayer("Buszsofőr", bus, new Wallet(0), bus);

        busDriverPlayer.selectRoute(bus, route);

        // Mozgás szimulálása
        for (Intersection i : List.of(start, stop_, end)){ 
            bus.checkStop(i);
            route.checkArrival(i);
        }

        busDriverPlayer.completeRoute(bus);
        int reward = route.getReward();
        System.out.println("A járat teljesítése után a játékos " + reward + " jutalmat kapott.");
    }

    /* 
     * A megálló látogatásának tesztelése.
     */

    public void testVisitBusStop() {
        Bus bus = new Bus(null, null, null, 0);
        Intersection stop_ = new Intersection("Stop");
        Stop stop = new Stop(stop_);
        List<RoutePoint> points = List.of(stop);
        BusRoute route = new BusRoute(points, 0, 10);
        Wallet wallet = new Wallet(0);
        BusDriverPlayer busDriverPlayer = new BusDriverPlayer("Buszsofőr", bus, wallet, bus);

        busDriverPlayer.selectRoute(bus, route);

        // Mozgás szimulálása (az egyszerűség kedvéért csak egy megálló van)
        bus.checkStop(stop_);
        route.checkArrival(stop_);
        busDriverPlayer.completeRoute(bus);

        int reward = route.getReward();
        System.out.println("A megálló érintése után a játékos " + reward + " jutalmat kapott.");
    }

    /* 
     * A hókotróval végzett takarítás tesztelése.
     * A tesztben söprőfejjel van felszerelve, és a sáv havas állapotban van.
     */

    public void testCleanWithSnowPlow() {
        Lane lane = new Lane();
        SnowyState snowyState = new SnowyState(lane);
        lane.addLaneState(snowyState);
        Snowplow snowplow = new Snowplow(lane, null, null, 0);
        BroomHead broomHead = new BroomHead(snowplow, null);
        snowplow.equipHead(broomHead);
        CleanerPlayer cleanerPlayer = new CleanerPlayer("Takarító", snowplow, new Wallet(0), snowplow);

        cleanerPlayer.performCleaning(snowplow);
        lane.replaceLaneState(snowyState, new ClearState(lane));
        System.out.println("A sáv tisztítása megtörtént.");
    }

    /*
    * Jármű beakadásának tesztelése, amikor mindkét sáv blokkolva van.
    * A jármű megpróbál sávot váltani, de mivel a másik sáv is blokkolva van, így megáll.
    * A tesztben egy busz van, de más járművel is működik a logika.
    */

    public void testVehicleStuckInLane() {
        Road road = new Road(null, null);
        Lane lane = new Lane();
        Lane otherLane = new Lane();
        road.addLane(otherLane);
        road.addLane(lane);
        lane.addLaneState(new BlockedState(lane));
        otherLane.addLaneState(new BlockedState(otherLane));
        Bus bus = new Bus(lane, null, null, 10);
        lane.acceptVehicle(bus);

        bus.checkAndChangeLane();
        System.out.println("A busz megpróbált sávot váltani, de a másik sáv is blokkolva van, így megállt.");
    }

    /*
     * A hókotró vásárlásának tesztelése a garázsból.
     * A sikeresség a játékos pénzétől és a hókotró árától függ.
     */

    public void testPurchaseSnowPlowInGarage() {
        Garage garage = new Garage(new Intersection("A"));
        Snowplow plowTemplate = new Snowplow(null, null, null, 0);
        int price = getIntFromUser("Hókotró ára");
        int amount = getIntFromUser("Takarító játékos pénze");
        Wallet wallet = new Wallet(amount);
        CleanerPlayer cleanerPlayer = new CleanerPlayer("Takarító", null, wallet, null);
        PlowPurchase plowPurchase = new PlowPurchase(price, "Hókotró vásárlás", garage, cleanerPlayer, plowTemplate);
        garage.getOffers().add(plowPurchase);

        cleanerPlayer.beginTurn();

        int price_ = plowPurchase.getPrice();
        int amount_ = wallet.getFunds();

        if(amount_ >= price_){
            wallet.deductFunds(price_);
            garage.processTransaction(cleanerPlayer, plowPurchase);
            // cleanerPlayer.addVehicle(plowTemplate);
            System.out.println("Hókotró vásárlása sikeres. Jelenlegi egyenleg: " + wallet.getFunds());
        } else {
            System.out.println("Nincs elég pénz a hókotró megvásárlásához. Jelenlegi egyenleg: " + wallet.getFunds());
        }
    }

    /*
    * A jármű kiszabadításának tesztelése, amikor egy sáv blokkolva van.
    * A tesztben egy autó van, amelyik egy blokkolt sávban ragadt, és egy hókotró szabadítja ki a sáv tisztításával.
    */
        
    public void testFreeVehicle() {
        Road road = new Road(null, null);
        Lane targetLane = new Lane();
        road.addLane(targetLane);
        Road road2 = new Road(null, null);
        Lane otherLane = new Lane();
        road2.addLane(otherLane);
        Car car = new Car(targetLane, null, null, 0);
        targetLane.acceptVehicle(car);
        BlockedState blockedState = new BlockedState(targetLane);
        targetLane.addLaneState(blockedState);

        Snowplow snowplow = new Snowplow(new Lane(), null, null, 10);
        if (snowplow.tryMoveTo(targetLane)){
            targetLane.removeLaneState(blockedState);
        }
        if (car.tryMoveTo(otherLane)){
            System.out.println("A jármű sikeresen kiszabadult a blokkolt sávból.");
        }
    }

    /*
    * A takarítási pontok tesztelése.
    * A tesztben egy hókotró tisztítja a sávot, és a játékos pontokat kap, ha sikerül.
    */

    public void testCleanerPoints() {
        Road road = new Road(null, null);
        RoadNetwork roadNetwork = new RoadNetwork();
        roadNetwork.addRoad(road);
        Lane lane = new Lane();
        road.addLane(lane);
        SnowyState snowyState = new SnowyState(lane);
        lane.addLaneState(snowyState);
        Snowplow snowplow = new Snowplow(lane, null, null, 0);
        BroomHead broomHead = new BroomHead(snowplow, null);
        snowplow.equipHead(broomHead);
        Wallet wallet = new Wallet(0);
        CleanerPlayer cleanerPlayer = new CleanerPlayer("Takarító", snowplow, wallet, snowplow);

        int cleaningReward = getIntFromUser("Takarításért járó pontok");
        boolean shouldCleaningWork = getBooleanFromUser("Takarítás sikeres legyen-e");

        cleanerPlayer.performCleaning(snowplow);
        if(shouldCleaningWork) {
            ClearState normalState = new ClearState(lane);
            lane.replaceLaneState(snowyState, normalState);
            wallet.addFunds(cleaningReward);
        }
        int pointsAfterCleaning = wallet.getFunds();
        // A pontok helyes jóváírása a takarítás sikerességétől függ. 0, ha nem sikerült.
        System.out.println("Takarítás után a játékosnak " + pointsAfterCleaning + " pontja van.");
    }
}
