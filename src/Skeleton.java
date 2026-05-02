import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * Singleton osztály a Skeleton teszteseteinek, valamint ki és bemenetének kezelésére
 */
public class Skeleton {
    /** A singleton példány */
    public static Skeleton instance;
    /** A tesztek során létrehozott objektumok neveit tartja számon */
    public Map<Object, String> names = new HashMap<>();

    public PrintStream outStream = System.out;

    Scanner scanner;

    public Skeleton(){
        scanner = new Scanner(System.in);
        instance = this;
    }

    /** Bekér a felhasználótól egy Stringet.
     *
     * Az egész sort beolvassa szóközökkel együtt.
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @param extra extra szöveg amit a normál üzenet után ír.
     * @return a felhasználó által megadott szöveg
     */
    String getStringFromUser(String paramName, String extra){
        outStream.printf("Kérem adja meg a(z) %s értékét %s:", paramName, extra);
        String string = scanner.nextLine();
        return string.strip();
    }

    /** Bekér a felhasználótól egy szöveget
     *
     * Az egész sort beolvassa szóközökkel együtt.
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @return a felhasználó által megadott szöveg
     */
    String getStringFromUser(String paramName){
        return getStringFromUser(paramName, "");
    }

    /** Bekér a felhasználótól egy egész számot
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @return a felhasználó által megadott szám
     */
    int getIntFromUser(String paramName) {
        String raw = getStringFromUser(paramName);
        return Integer.parseInt(raw);
    }

    /** Bekér a felhasználótól egy valós számot
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @return a felhasználó által megadott szám
     */
    double getDoubleFromUser(String paramName) {
        String raw = getStringFromUser(paramName);
        return Double.parseDouble(raw);
    }

    /** Bekér a felhasználótól egy igaz hamis értéke

     * 0 jelenti az igazat és 1 a hamisat. Egyéb érték hibát dob.
     * @param paramName a paraméter neve amihez kérjük az értéket.
     * @return a felhasználó által megadott érték
     */
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

    /** Megkéri a felhasználót, hogy válasszon a megadott opciók közül

     * 0 jelenti az igazat és 1 a hamisat. Egyéb érték hibát dob.
     * @param options az opciók amik fel lesznek sorolva.
     * @return a felhasználó által választott opció sorszáma
     */
    int getListSelectionFromUser(List<String> options){
        for (int i = 0; i < options.size(); i++){
            outStream.printf("%d. %s%n", i, options.get(i));
        }
        outStream.print("Kérem válasszon a fentiek közül:");
        return Integer.parseInt(scanner.nextLine());
    }

    /** Kiírja standard kimenetre egy metódus meghívását
     *
     * @param o az objektum amihez tartozik a metódus
     * @param methodName a metódus neve
     * @param args a metódus argumentumainak nevei és értékei. A neveket és értékeket egymás után kell megadni felváltva, kezdve az első argumentum nevével.
     */
    public void methodCall(Object o, String methodName, Object... args){
        outStream.printf("%s.%s(", names.get(o), methodName);
        for (int j = 0; j < args.length; j+=2) {
            Object paramName = args[j];
            Object value = args[Math.min(j+1, args.length-1)];
            outStream.printf("%s=%s, ", paramName, names.containsKey(value) ? names.get(value) : value);
        }
        outStream.print(") meghívva\n");
    }

    /** Kiírja standard kimenetre egy objektum létrehozását
     *
     * @param o az objektum ami létrejött
     * @param args a konstruktor argumentumainak nevei és értékei. A neveket és értékeket egymás után kell megadni felváltva, kezdve az első argumentum nevével.
     */
    public void createObject(Object o, Object... args) {
        String type = o.getClass().getTypeName();
        int i = 1;
        while (names.containsValue(type+i)){
            i++;
        }
        String name = type+i;
        names.put(o, name);

        outStream.printf("%s típusú objektum létrehozva %s névvel", type, name);
        if (args.length != 0){
            outStream.print(", ezekkel a konstruktor paraméterekkel: ");
            for (int j = 0; j < args.length; j+=2) {
                Object paramName = args[j];
                Object value = args[Math.min(j+1, args.length-1)];
                outStream.printf("%s=%s, ", paramName, names.containsKey(value) ? names.get(value) : value);
            }
        }
        outStream.print("\n");
    }

    /** Kiírja standard kimenetre egy metódus visszatérését
     *
     * @param o az objektum amihez tartozik a metódus
     * @param methodName a metódus neve
     * @param returnValue a metódus visszatérési értéke
     */
    public void methodReturn(Object o, String methodName, Object returnValue) {
        outStream.printf("%s.%s visszatért", names.get(o), methodName);
        if (returnValue != null){
            outStream.printf(" %s értékkel", names.containsKey(returnValue) ? names.get(returnValue) : returnValue);
        }
        outStream.print("\n");
    }

    /** Kiírja standard kimenetre egy metódus visszatérését
     *
     * @param o az objektum amihez tartozik a metódus
     * @param methodName a metódus neve
     */
    public void methodReturn(Object o, String methodName) {
        methodReturn(o, methodName, null);
    }

    /*1. Teszteset:*/
    public void testMatchStart(){
        outStream.println("--- 1. Teszteset: Mérkőzés létrehozása ---");

        /*1. Objektumok létrehozása:*/
        outStream.println("Lane típusú objektum létrehozva l névvel, paraméterek nélkül.");
        Lane l = new Lane();

        outStream.println("Road típusú objektum létrehozva road névvel, ezekkel a konstruktor paraméterekkel: " +
                "[first=null, second=null]"
        );
        Road road = new Road(null, null);

        outStream.println("RoadNetwork típusú objektum létrehozva testNetwork névvel, paraméterek nélkül.");
        RoadNetwork testNetwork = new RoadNetwork();

        outStream.println("Match típusú objektum létrehozva m névvel, ezekkel a konstruktor paraméterekkel:"
                + "[player=null, vehicles=null, network=testNetwork].");
        Match m = new Match(null, null, testNetwork);

        outStream.println("CleanerPlayer típusú objektum létrehozva p1 névvel,"
                + "ezekkel a konstruktor paraméterekkel: [name=CleanerPlayer, vehicle=null, waller=null, snowplow=null].");
        CleanerPlayer p1 = new CleanerPlayer("CleanerPlayer", null, null, null);

        outStream.println("BusDriverPlayer típusú objektum létrehozva p2 névvel, paraméterek nélkül.");
        BusDriverPlayer p2 = new BusDriverPlayer("BusDriverPlayer", null, null, null);

        /*2. Metódushívások:*/
        m.start();
        outStream.println("m.start() meghívva");

        /*3. belső hívások a start()-on belül:*/
        /*a) Lane inicializálása*/
        outStream.println("l.setSnowAmount(amount=0) meghívva");
        l.setSnowAmount(0);
        outStream.println("l.setSnowAmount visszatért void értékkel");

        outStream.println("l.setIceAmount(amount=0) meghívva");
        l.setIceAmount(0);
        outStream.println("l.setIceAmount visszatért void értékkel");

        /*b) Road inicializálása*/
        outStream.println("road.addLane(lane=l) meghívva");
        road.addLane(l);
        outStream.println("road.addLane visszatért void értékkel");

        /*c) Első játékos köre*/
        outStream.println("p1.beginTurn() meghívva");
        p1.beginTurn();
        outStream.println("p1.beginTurn visszatért void értékkel");

        /*d) Második játékos köre*/
        outStream.println("p2.beginTurn() meghívva");
        p2.beginTurn();
        outStream.println("p2.beginTurn visszatért void értékkel");

        outStream.println("m.start visszatért void értékkel");
        outStream.println("--- Teszt vége ---");
    }

    /*2. Teszteset:*/
    public void testSnowfall() {
        outStream.println("--- Teszteset: Havazás kezelése ---");

        /*1. Bekérjük a teszteléshez szükséges értékeket.*/
        int snowyThreshold = getIntFromUser("SnowyThreshold/havasSzint");
        int snowdriftThreshold = getIntFromUser("SnowdriftThreshold/torlaszSzint");
        int amount = getIntFromUser("SnowfallRate/Hóesés mértéke)");

        /*2. Objektumok létrehozása:*/
        outStream.println("RoadNetwork típusú objektum létrehozva testNetwork névvel, paraméterek nélkül.");
        RoadNetwork testNetwork = new RoadNetwork();

        outStream.println("WeatherSystem típusú objektum létrehozva ws névvel, ezekkel a konstruktor paraméterekkel: " +
                "[network=testNetwork, snowFallRate=amount]"
        );
        WeatherSystem ws = new WeatherSystem(testNetwork, amount);

        outStream.println("Lane típusú objektum létrehozva l névvel.");
        Lane l = new Lane();

        outStream.println("Road típusú objektum létrehozva road névvel, ezekkel a konstruktor paraméterekkel: " +
                "[first=null, second=null]"
        );
        Road road = new Road(null, null);

        outStream.println("ClearState típusú objektum létrehozva s névvel.");
        ClearState s = new ClearState(l);

        outStream.println("l.addLaneState(newState=s) meghívva");
        l.addLaneState(s);
        outStream.println("l.addLaneState visszatért void értékkel");

        outStream.println("testNetwork.addRoad(r=road) meghívva");
        testNetwork.addRoad(road);
        outStream.println("testNetwork.addRoad(r=road) visszatért void értékkel");

        /*3. Folyamat:*/
        outStream.println("ws.setSnowfallRate(amount=" + amount + ") meghívva");
        ws.setSnowfallRate(amount);
        outStream.println("ws.setSnowfallRate visszatért void értékkel");

        outStream.println("ws.applySnowfall() meghívva");

        outStream.println("l.receiveSnow(amount=" + amount + ") meghívva");

        outStream.println("s.onSnowfall(amount=" + amount + ") meghívva");

        outStream.println("l.getSnowAmount() meghívva");

        /*4. Új érték szimulálása:*/
        int newSnowAmount = amount;
        outStream.println("l.getSnowAmount visszatért " + newSnowAmount + " értékkel");

        outStream.println("l.setSnowAmount(newSnowAmount=" + newSnowAmount + ") meghívva");
        outStream.println("l.setSnowAmount visszatért void értékkel");

        /*5. Az 'alt' blokk: feltételvizsgálat*/
        if (newSnowAmount >= snowdriftThreshold) {
            /*Hótorlasz eset*/
            outStream.println("SnowdriftState típusú objektum létrehozva newState névvel, ezekkel a konstruktor paraméterekkel [lane=l]");
            SnowdriftState newState = new SnowdriftState(l);

            outStream.println("l.replaceLaneState(oldState=s, newState=newState) meghívva");
            /*l.replaceLaneState(this, newState)*/
            outStream.println("l.replaceLaneState visszatért void értékkel");

        } else if (newSnowAmount >= snowyThreshold) {
            /*Havas út eset*/
            outStream.println("SnowyState típusú objektum létrehozva newState névvel, ezekkel a konstruktor paraméterekkel [lane=l]");
            SnowyState newState = new SnowyState(l);

            outStream.println("l.replaceLaneState(oldState=s, newState=newState) meghívva");
            /*l.replaceLaneState(this, newState)*/
            outStream.println("l.replaceLaneState visszatért void értékkel");
        }

        /*6. Többi függvény visszatérése:*/
        outStream.println("s.onSnowfall visszatért void értékkel");
        outStream.println("lane.receiveSnow visszatért void értékkel");
        outStream.println("ws.applySnowfall visszatért void értékkel");

        outStream.println("--- Teszt vége ---");
    }

    /*3. Teszteset:*/
    public void testIcing() {
        outStream.println("--- Teszteset: Jég kialakulása ---");

        // 1. Paraméterek bekérése és objektumok létrehozása:
        int iceSheetThreshold = getIntFromUser("iceSheetThreshold/JégpáncélKüszöb");
        int icingRate = getIntFromUser("icingRate/JegesedésMérték");

        outStream.println("Lane típusú objektum létrehozva l névvel.");
        Lane l = new Lane();

        outStream.println("SnowyState típusú objektum létrehozva s névvel.");
        SnowyState s = new SnowyState(l);

        outStream.println("BusDriverPlayer típusú objektum létrehozva testBusDriverPlayer névvel, ezekkel a konstruktor paraméterekkel: " +
                "[name=BusDriverPlayer, vehicle=null, wallet=null, bus=null]"
        );
        BusDriverPlayer testBusDriverPlayer = new BusDriverPlayer("BusDriverPlayer", null, null, null);

        outStream.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
                "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        int testVehicleSpeed = getIntFromUser("testVehicleSpeed/TesztjárműSebesség");

        outStream.println("Bus típusú objektum létrehozva b névvel, ezekkel a konstruktor paraméterekkel: " +
                "[currentLane=l, owner=testBusDriverPlayer, destination=testDestination," + testVehicleSpeed
        );
        Bus b = new Bus(l, testBusDriverPlayer, testDestination, testVehicleSpeed);

        /*2. Folyamat:*/
        outStream.println("l.acceptVehicle(v=b) meghívva");
        /*l.acceptVehicle(b);*/

        outStream.println("s.onVehicleEnter(v=b) meghívva");
        /*s.onVehicleEnter(b);*/

        outStream.println("l.getIceAmount() meghívva");

        // Szimuláljuk a jelenlegi jégmennyiséget és a növekedést
        int currentIce = 0; // Kiindulási állapot
        int newIceAmount = currentIce + icingRate;
        outStream.println("l.getIceAmount visszatért " + currentIce + " értékkel");

        outStream.println("l.setIceAmount(newIceAmount=" + newIceAmount + ") meghívva");
        outStream.println("l.setIceAmount visszatért void értékkel");

        /*3. Az 'alt' blokk: Jégpáncél kialakulása*/
        if (newIceAmount >= iceSheetThreshold) {
            outStream.println("IceSheetState típusú objektum létrehozva nextState névvel, ezekkel a konstruktor paraméterekkel [lane=l]");
            IceSheetState nextState = new IceSheetState(l);

            outStream.println("l.replaceLaneState(oldState=s, newState=nextState) meghívva");
            l.replaceLaneState(s, nextState);
            outStream.println("l.replaceLaneState visszatért void értékkel");
        }

        /*4. Visszatérések:*/
        outStream.println("s.onVehicleEnter visszatért void értékkel");
        outStream.println("l.acceptVehicle visszatért void értékkel");

        outStream.println("--- Teszt vége ---");
    }

    /*4. Teszteset:*/
    public void testVehicleMoving() {
        outStream.println("--- Teszteset: Jármű mozgatása ---");

        /*1. Objektumok és paraméterek előkészítése:*/
        int movementNeeded = getIntFromUser("movementNeeded/mozgásIgény");
        int movementRemaining = getIntFromUser("movementRemaining/hátralevőMozgás");

        outStream.println("Lane típusú objektum létrehozva currentLane névvel.");
        Lane currentLane = new Lane();

        outStream.println("BusDriverPlayer típusú objektum létrehozva testBusDriverPlayer névvel, ezekkel a konstruktor paraméterekkel: " +
                "[name=BusDriverPlayer, vehicle=null, wallet=null, bus=null]"
        );
        BusDriverPlayer testBusDriverPlayer = new BusDriverPlayer("BusDriverPlayer", null, null, null);

        outStream.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
                "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        int vehicleSpeed = getIntFromUser("vehicleSpeed/BuszSebesség");

        outStream.println("Bus típusú objektum létrehozva v névvel, ezekkel a konstruktor paraméterekkel" +
                "[currentLane=currentLane, owner=testBusDriverPlayer, destination=testDestination, speed=vehicleSpeed]"
        );
        Bus v = new Bus(currentLane, testBusDriverPlayer, testDestination, vehicleSpeed);

        outStream.println("Lane típusú objektum létrehozva target névvel.");
        Lane target = new Lane();

        outStream.println("ClearState típusú objektum létrehozva s névvel.");
        ClearState s = new ClearState(target);

        /*2. Folyamat modellezése:*/
        outStream.println("v.tryMoveTo(target=target) meghívva");

        /*A jármű belső logikája - mozgáspont levonás:*/
        outStream.println("v.consumeMovement(amount=" + movementNeeded + ") meghívva");
        v.consumeMovement(movementNeeded);
        outStream.println("v.consumeMovement visszatért void értékkel");

        /*3. Az 'alt' blokk - Van-e elég mozgáspont?*/
        if (movementRemaining >= movementNeeded) {
            /*Sikeres mozgás ága*/
            outStream.println("target.acceptVehicle(v=v) meghívva");
            /*target.acceptVehicle(v);*/

            /*A sáv továbbhív az állapotára*/
            outStream.println("s.onVehicleEnter(v=v) meghívva");
            s.onVehicleEnter(v);
            outStream.println("s.onVehicleEnter visszatért void értékkel");

            /*A sáv értesíti a járművet az egyéb eseményekről*/
            outStream.println("v.onEnterLane(other=null) meghívva");
            v.onEnterLane(null);
            outStream.println("v.onEnterLane visszatért void értékkel");

            outStream.println("target.acceptVehicle visszatért void értékkel");

            /*Visszatérés a Skeletonnak*/
            outStream.println("v.tryMoveTo visszatért true értékkel");

        } else {
            /*Sikertelen mozgás ága (nincs elég pont)*/
            outStream.println("v.tryMoveTo visszatért false értékkel");
        }

        outStream.println("--- Teszt vége ---");
    }

    /*5. Teszteset:*/
    public void testUsingBroomHead() {

        outStream.println("--- Teszteset: Söprő fej használata ---");

        /*1. Objektumok létrehozása:*/
        int amount = getIntFromUser("aktuális hómennyiség/amount");
        int neighborCount = getIntFromUser("szomszédos sávok száma/neighborCount");
        int spSpeed = getIntFromUser("HókotróSebesség/SnowPlowSpeed");

        outStream.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);

        outStream.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        outStream.println("Lane típusú objektum létrehozva neighborLane névvel.");
        Lane neighborLane = new Lane();

        outStream.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
                "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        outStream.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);

        outStream.println("BroomHead típusú objektum létrehozva bh névvel.");
        BroomHead bh = new BroomHead(sp, cl);

        outStream.println("SnowDriftState típusú objektum létrehozva ss névvel.");
        SnowdriftState ss = new SnowdriftState(cl);

        outStream.println("Road típusú objektum létrehozva r névvel.");
        Road r = new Road(null, null);

        /*2. A folyamat indítása*/
        outStream.println("cp.performCleaning(p=sp) meghívva");

        outStream.println("sp.work() meghívva");

        outStream.println("bh.applyTo(p=sp, l=cl, r=r) meghívva");

        /*Utak lekérése a szomszédokhoz*/
        outStream.println("r.getLanes() meghívva");
        outStream.println("r.getLanes visszatért lanes listával");

        outStream.println("cl.getSnowAmount() meghívva");
        outStream.println("cl.getSnowAmount visszatért " + amount + " értékkel");

        /*3. LOOP - Szomszédos sávok havasítása*/
        for (int i = 1; i <= neighborCount; i++) {
            outStream.println("--- Loop: neighbor " + i + " ---");
            outStream.println("neighbor:Lane.receiveSnow(amount=" + (amount/2) + ") meghívva");
            outStream.println("neighbor:Lane.receiveSnow visszatért void értékkel");
        }

        /*4. Takarítás befejezése és állapotváltás*/
        outStream.println("cl.clean(h=bh) meghívva");

        outStream.println("ss.onCleaned() meghívva");

        outStream.println("ClearState típusú objektum létrehozva cs névvel, ezekkel a konstruktor paraméterekkel [lane=cl]");
        ClearState cs = new ClearState(cl);

        outStream.println("cl.replaceLaneState(oldState=ss, newState=cs) meghívva");
        cl.replaceLaneState(ss, cs);
        outStream.println("cl.replaceLaneState visszatért void értékkel");

        outStream.println("ss.onCleaned visszatért void értékkel");
        outStream.println("cl.clean visszatért void értékkel");

        /*5. Visszatérések*/
        outStream.println("bh.applyTo visszatért void értékkel");
        outStream.println("sp.work visszatért void értékkel");
        outStream.println("cp.performCleaning visszatért void értékkel");

        outStream.println("--- Teszt vége ---");
    }

    /*6. Teszteset:*/
    public void testUsingThrowerHead() {

        outStream.println("--- Teszteset: Hányó fej használata ---");

        /*1. Paraméterek és objektumok létrehozása:*/
        int amount = getIntFromUser("aktuális hómennyiség/amount");
        int spSpeed = getIntFromUser("HókotróSebesség/SnowplowSpeed");
        int targetLaneDistance = 2; /*Ref: 2 sávval arrébb*/

        outStream.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);

        outStream.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        outStream.println("Lane típusú objektum létrehozva otherLane névvel.");
        Lane otherLane = new Lane();

        outStream.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
                "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        outStream.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);

        outStream.println("ThrowerHead típusú objektum létrehozva th névvel, ezekkel a paraméterekkel:" +
                "[plow=sp targetLane=cl]"
        );
        ThrowerHead th = new ThrowerHead(sp, cl);

        outStream.println("SnowdriftState típusú objektum létrehozva ss névvel.");
        SnowdriftState ss = new SnowdriftState(cl);

        outStream.println("Road típusú objektum létrehozva r névvel, ezekkel a paraméterekkel:" +
                "[first=null, second=null]"
        );
        Road r = new Road(null, null);

        /*2. Folyamat:*/
        outStream.println("cp.performCleaning(p=sp) meghívva");
        outStream.println("sp.work() meghívva");

        /*th: ThrowerHead kapja meg a vezérlést*/
        outStream.println("th.applyTo(p=sp, l=cl, r=r) meghívva");

        outStream.println("r.getLanes() meghívva");
        outStream.println("r.getLanes visszatért lanes listával");

        outStream.println("cl.getSnowAmount() meghívva");
        outStream.println("cl.getSnowAmount visszatért " + amount + " értékkel");

        /*3. LOOP - Hó áthelyezése távolabbi sávokhoz*/
        outStream.println("--- A célpont sávok " + targetLaneDistance + " egységre vannak ---");

        /*Távoli sáv szimulálása:*/
        outStream.println("other:Lane.receiveSnow(amount=" + (amount/2) + ") meghívva");
        outStream.println("other:Lane.receiveSnow visszatért void értékkel");

        /*4. Tisztítás és állapotváltás*/
        outStream.println("cl.clean(h=th) meghívva");
        outStream.println("ss.onCleaned() meghívva");

        outStream.println("ClearState típusú objektum létrehozva cs névvel, ezekkel a konstruktor paraméterekkel [lane=cl]");
        ClearState cs = new ClearState(cl);

        outStream.println("cl.replaceLaneState(oldState=ss, newState=cs) meghívva");
        cl.replaceLaneState(ss, cs);
        outStream.println("cl.replaceLaneState visszatért void értékkel");

        /*5. Visszatérések*/
        outStream.println("ss.onCleaned visszatért void értékkel");
        outStream.println("cl.clean visszatért void értékkel");
        outStream.println("th.applyTo visszatért void értékkel");
        outStream.println("sp.work visszatért void értékkel");
        outStream.println("cp.performCleaning visszatért void értékkel");

        outStream.println("--- Teszt vége ---");
    }

    /*7. Teszteset:*/
    public void testUsingIceBreakerHead() {

        outStream.println("--- Teszteset: Jégtörő fej használata ---");

        /*1. Objektumok és bemenetek*/
        int iceAmount = getIntFromUser("aktuális jégmennyiség/iceAmount");
        int spSpeed = getIntFromUser("HókotróSebesség/SnowplowSpeed");

        outStream.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);

        outStream.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        outStream.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
                "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        outStream.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);

        outStream.println("IceBreakerHead típusú objektum létrehozva ih névve, ezekkel a konstruktor paraméterekkel:" +
                "[plow=sp, targetLane=cl]"
        );
        IceBreakerHead ih = new IceBreakerHead(sp, cl);

        outStream.println("IceSheetState típusú objektum létrehozva is névvel, ezekkel a paraméterekkel:" +
                "[lane=cl]"
        );
        IceSheetState is = new IceSheetState(cl);

        outStream.println("Road típusú objektum létrehozva r névvel, ezekkel a paraméterekkel:" +
                "[first=null, second=null]"
        );
        Road r = new Road(null, null);

        /*2. Folyamat:*/
        outStream.println("cp.performCleaning(p=sp) meghívva");
        outStream.println("sp.work() meghívva");

        // ih: IceBreakerHead kapja meg a vezérlést
        outStream.println("ih.applyTo(p=sp, l=cl, r=r) meghívva");

        outStream.println("cl.getIceAmount() meghívva");
        int resIceAmount = cl.getIceAmount();
        outStream.println("cl.getIceAmount visszatért " + iceAmount + " értékkel");

        /*A jégtörő fej a jeget hóvá alakítja*/
        outStream.println("cl.receiveSnow(amount=" + iceAmount + ") meghívva");
        cl.receiveSnow(resIceAmount);
        outStream.println("cl.receiveSnow visszatért void értékkel");

        /*3. Tisztítás és állapotváltás: Jégpáncél -> Havas út*/
        outStream.println("cl.clean(h=ih) meghívva");

        outStream.println("is.onCleaned() meghívva");

        outStream.println("SnowyState típusú objektum létrehozva ss névvel, ezekkel a konstruktor paraméterekkel [lane=cl]");
        SnowyState ss = new SnowyState(cl);

        outStream.println("cl.replaceLaneState(oldState=is, newState=ss) meghívva");
        cl.replaceLaneState(is, ss);
        outStream.println("cl.replaceLaneState visszatért void értékkel");

        /*4. Visszatérések*/
        outStream.println("is.onCleaned visszatért void értékkel");
        outStream.println("cl.clean visszatért void értékkel");
        outStream.println("ih.applyTo visszatért void értékkel");
        outStream.println("sp.work visszatért void értékkel");
        outStream.println("cp.performCleaning visszatért void értékkel");

        outStream.println("--- Teszt vége ---");
    }

    /*8. Teszteset:*/
    public void testUsingSaltSpreaderHead() {

        outStream.println("--- Teszteset: Sószóró fej használata ---");

        /*1. Paraméterek és objektumok előkészítése*/
        int iceAmount = getIntFromUser("iceAmount/Jégmennyiség");
        int snowAmount = getIntFromUser("snowAmount/Hómennyiség");
        int saltSupply = getIntFromUser("saltSupply/Sómennyiség");
        int spSpeed = getIntFromUser("SnowplowSpeed/HókotróSebesség");

        outStream.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);

        outStream.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        outStream.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
                "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        outStream.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);

        outStream.println("Salt típusú objektum létrehozva salt néven, ezekkel a konstruktor paraméterekkel:" +
                "[owner=sp, amount= " + saltSupply + "]"
        );
        Salt salt = new Salt(sp, saltSupply);

        outStream.println("SaltSpreaderHead típusú objektum létrehozva ssh névvel, ezekkel a konstruktor paraméterekkel:" +
                "[plow=sp, targetLane=cl, saltSupply=salt]"
        );
        SaltSpreaderHead ssh = new SaltSpreaderHead(sp, cl, salt);

        outStream.println("IceSheetState típusú objektum létrehozva is névvel, ezekkel a konstruktor paraméterekkel:" +
                "[lane=cl]"
        );
        IceSheetState is = new IceSheetState(cl);

        /*2. Folyamat:*/
        outStream.println("cp.performCleaning(p=sp) meghívva");
        outStream.println("sp.work() meghívva");
        outStream.println("ssh.applyTo(p=sp, l=cl, r=road) meghívva");

        /*3. OPT blokk - Csak ha van só*/
        if (saltSupply > 0) {
            outStream.println("--- Opt: [saltSupply > 0] ---");

            outStream.println("cl.clean(h=ssh) meghívva");
            outStream.println("is.onCleaned() meghívva");
            is.onCleaned();
            outStream.println("is.onCleaned visszatért void értékkel");
            outStream.println("cl.clean visszatért void értékkel");

            outStream.println("SaltedState típusú objektum létrehozva ss névvel, ezekkel a konstruktor paraméterekkel" +
                    "[lane=cl, remainingDuration=10]");
            SaltedState ss = new SaltedState(cl, 10);

            outStream.println("cl.addLaneState(state=ss) meghívva");
            cl.addLaneState(ss);
            outStream.println("cl.addLaneState visszatért void értékkel");
        }

        /*4. Visszatérések:*/
        outStream.println("ssh.applyTo visszatért void értékkel");
        outStream.println("sp.work visszatért void értékkel");
        outStream.println("cp.performCleaning visszatért void értékkel");

        outStream.println("--- Teszt vége ---");
    }

    /*9. Teszteset:*/
    public void testUsingDragonHead() {

        outStream.println("--- Teszteset: Sárkány fej használata ---");

        /*1. Objektumok és bemenet bekérése*/
        int fuelSupply = getIntFromUser("BioKerosine");
        int spSpeed = getIntFromUser("SnowplowSpeed/HókotróSebesség");

        outStream.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);

        outStream.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        outStream.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
                "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        outStream.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
                "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);

        outStream.println("BioKerosene típusú objektum létrehozva biokerozin névvel, ezekkel a konstruktor paraméterekkel:" +
                "[owner=sp, amount=fuelSupply]"
        );
        BioKerosene biokerozin = new BioKerosene(sp, fuelSupply);

        outStream.println("DragonHead típusú objektum létrehozva dh névvel, ezekkel a konstruktor paraméterekkel:" +
                "[plow=sp, targetLane=cl, fuelSupply=biokerozin]"
        );
        DragonHead dh = new DragonHead(sp, cl, biokerozin);

        outStream.println("IceSheetState típusú objektum létrehozva is névvel, ezekkel a konstruktor paraméterekkel:" +
                "[lane=cl]"
        );
        IceSheetState is = new IceSheetState(cl);

        /*2. Folyamat indítása*/
        outStream.println("cp.performCleaning(p=sp) meghívva");
        outStream.println("sp.work() meghívva");
        outStream.println("dh.applyTo(p=sp, l=cl, r=road) meghívva");

        /*3. OPT blokk - Üzemanyag-ellenőrzés*/
        if (fuelSupply > 0) {
            outStream.println("--- Opt: [fuelSupply > 0] ---");
            outStream.println("cl.clean(h=dh) meghívva");
            outStream.println("is.onCleaned() meghívva");

            /*Új tiszta állapot létrehozása és beállítása*/
            outStream.println("ClearState típusú objektum létrehozva cs névvel, ezekkel a konstruktor paraméterekkel [lane=cl]");
            ClearState cs = new ClearState(cl);

            outStream.println("cl.replaceLaneState(oldState=is, newState=cs) meghívva");
            cl.replaceLaneState(is, cs);
            outStream.println("cl.replaceLaneState visszatért void értékkel");

            outStream.println("is.onCleaned visszatért void értékkel");
            outStream.println("cl.clean visszatért void értékkel");

        } else {
            outStream.println("Sárkány fej: Nincs elég üzemanyag a takarításhoz.");
        }

        /*4. Visszatérések*/
        outStream.println("dh.applyTo visszatért void értékkel");
        outStream.println("sp.work visszatért void értékkel");
        outStream.println("cp.performCleaning visszatért void értékkel");

        outStream.println("--- Teszt vége ---");
    }

    /*10. Teszteset:*/
    public void testCollisionHandling() {

        outStream.println("--- Teszteset: Ütközés kezelése ---");

        /*1. Objektumok és bemenetek előkészítése*/
        boolean canMove = getBooleanFromUser("canMove: Tud mozogni az autó?)");
        boolean vehicleSlips = false;
        boolean busIsOnLane = false;
        int carSpeed = getIntFromUser("AutóSebesség/CarSpeed");
        int busSpeed = getIntFromUser("BuszSebesség/BusSpeed");

        if (canMove) {
            vehicleSlips = getBooleanFromUser("vehicleSlips: Megcsúszik a jármű a jégen?");
            if (vehicleSlips) {
                busIsOnLane = getBooleanFromUser("busIsOnLane: Van már busz a sávon?");
            }
        }

        outStream.println("Intersection típusú objektum létrehozva carDestination névvel, ezekkel a konstruktor paraméterekkel:" +
                "[id=carID]"
        );
        Intersection carDestination = new Intersection("carID");

        outStream.println("Intersection típusú objektum létrehozva busDestination névvel, ezekkel a konstruktor paraméterekkel:" +
                "[id=busID]"
        );
        Intersection busDestination = new Intersection("busID");

        outStream.println("Lane típusú objektum létrehozva l névvel.");
        Lane l = new Lane();

        outStream.println("Car típusú objektum létrehozva c névvel, ezekkel a konstruktor paraméterekkel:" +
                "[currentLane=l owner=null destination=testDestination speed=" + carSpeed + "]"
        );
        Car c = new Car(l, null, carDestination, carSpeed);

        outStream.println("IceSheetState típusú objektum létrehozva s névvel, ezekkel a konstruktor paraméterekkel:"
                + "[lane=l]"
        );
        IceSheetState s = new IceSheetState(l);

        outStream.println("BusDriverPlayer típusú objektum létrehozva testBusDriverPlayer névvel, ezekkel a konstruktor paraméterekkel: " +
                "[name=BusDriverPlayer, vehicle=null, wallet=null, bus=null]"
        );
        BusDriverPlayer testBusDriverPlayer = new BusDriverPlayer("BusDriverPlayer", null, null, null);

        outStream.println("Bus típusú objektum létrehozva b névvel, ezekkel a konstruktor paraméterekkel:" +
                "[currentLane=l owner=BusDriverPlayer destination=testDestination3 speed=busSpeed]"
        );
        Bus b = new Bus(l, testBusDriverPlayer, busDestination, busSpeed);

        /*2. Folyamat indítása*/
        outStream.println("c.tryMoveTo(target=l) meghívva");

        /*Első OPT: [canMove]*/
        if (canMove) {
            outStream.println("--- Opt: [canMove] ---");
            outStream.println("l.acceptVehicle(v=c) meghívva");
            outStream.println("s.onVehicleEnter(v=c) meghívva");

            /*Második OPT: [vehicle slips]*/
            if (vehicleSlips) {
                outStream.println("--- Opt: [vehicle slips] ---");
                outStream.println("c.handleIcyLane(lane=l) meghívva");

                /*ALT: Ütközés vs Szabad sáv*/
                if (busIsOnLane) {
                    outStream.println("--- Alt: [bus is on lane] ---");
                    outStream.println("c.onCollision() meghívva");

                    outStream.println("BlockedState típusú objektum létrehozva nextState névvel, ezekkel a konstruktor paraméterekkel [lane=l]");
                    outStream.println("l.addLaneState(state=nextState) meghívva");
                    l.addLaneState(s);
                    outStream.println("l.addLaneState visszatért void értékkel");

                    outStream.println("b.onCollision() meghívva");
                    outStream.println("ImmobilizedStatus típusú objektum létrehozva status névvel, ezekkel a konstruktor paraméterekkel [vehicle=b]");
                    outStream.println("b.onCollision visszatért void értékkel");

                    outStream.println("c.onCollision visszatért void értékkel");

                } else {
                    outStream.println("--- Alt: [lane is free] ---");
                    outStream.println("c.onEnterLane(lane=l) meghívva");
                    outStream.println("c.onEnterLane visszatért void értékkel");
                }
                outStream.println("c.handleIcyLane visszatért void értékkel");
            }

            outStream.println("s.onVehicleEnter visszatért void értékkel");
            outStream.println("l.acceptVehicle visszatért void értékkel");
        }

        outStream.println("c.tryMoveTo visszatért void értékkel");
        outStream.println("--- Teszt vége ---");
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
        outStream.println("A kotrófej lecserélve.");
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
            outStream.println("A jármű sikeresen sávot váltott.");
        } else {
            outStream.println("A jármű nem tudott sávot váltani, mert a másik sáv nem járható.");
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
            outStream.println("A jármű sikeresen sávot váltott.");
        } else {
            outStream.println("A jármű nem tudott sávot váltani, mert a másik sáv nem járható.");
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
        outStream.println("A járat teljesítése után a játékos " + reward + " jutalmat kapott.");
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
        outStream.println("A megálló érintése után a játékos " + reward + " jutalmat kapott.");
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
        outStream.println("A sáv tisztítása megtörtént.");
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
        outStream.println("A busz megpróbált sávot váltani, de a másik sáv is blokkolva van, így megállt.");
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
            outStream.println("Hókotró vásárlása sikeres. Jelenlegi egyenleg: " + wallet.getFunds());
        } else {
            outStream.println("Nincs elég pénz a hókotró megvásárlásához. Jelenlegi egyenleg: " + wallet.getFunds());
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
            outStream.println("A jármű sikeresen kiszabadult a blokkolt sávból.");
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
        outStream.println("Takarítás után a játékosnak " + pointsAfterCleaning + " pontja van.");
    }
}
