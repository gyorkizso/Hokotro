import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Skeleton {
    public static Skeleton instance;
    public Map<Object, String> names = new HashMap<>();
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
            System.out.printf("%d. %s%n", i, options.get(i));
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
            for (int j = 0; j < args.length; j+=2) {
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
            for (int j = 0; j < args.length; j+=2) {
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

    /*1. Teszteset:*/
    public void testMatchStart(){
        System.out.println("--- 1. Teszteset: Mérkőzés létrehozása ---");

        /*1. Objektumok létrehozása:*/
        System.out.println("Lane típusú objektum létrehozva l névvel, paraméterek nélkül.");
        Lane l = new Lane();

        System.out.println("Road típusú objektum létrehozva road névvel, ezekkel a konstruktor paraméterekkel: " +
            "[first=null, second=null]"
        );
        Road road = new Road(null, null);

        System.out.println("RoadNetwork típusú objektum létrehozva testNetwork névvel, paraméterek nélkül.");
        RoadNetwork testNetwork = new RoadNetwork();

        System.out.println("Match típusú objektum létrehozva m névvel, ezekkel a konstruktor paraméterekkel:" 
        + "[player=null, vehicles=null, network=testNetwork].");
        Match m = new Match(null, null, testNetwork);

        System.out.println("CleanerPlayer típusú objektum létrehozva p1 névvel," 
        + "ezekkel a konstruktor paraméterekkel: [name=CleanerPlayer, vehicle=null, waller=null, snowplow=null].");
        CleanerPlayer p1 = new CleanerPlayer("CleanerPlayer", null, null, null);

        System.out.println("BusDriverPlayer típusú objektum létrehozva p2 névvel, paraméterek nélkül.");
        BusDriverPlayer p2 = new BusDriverPlayer("BusDriverPlayer", null, null, null);

        /*2. Metódushívások:*/
        m.start();
        System.out.println("m.start() meghívva");
        
        /*3. belső hívások a start()-on belül:*/
        /*a) Lane inicializálása*/
        System.out.println("l.setSnowAmount(amount=0) meghívva");
        l.setSnowAmount(0);
        System.out.println("l.setSnowAmount visszatért void értékkel");

        System.out.println("l.setIceAmount(amount=0) meghívva");
        l.setIceAmount(0);
        System.out.println("l.setIceAmount visszatért void értékkel");

        /*b) Road inicializálása*/
        System.out.println("road.addLane(lane=l) meghívva");
        road.addLane(l);
        System.out.println("road.addLane visszatért void értékkel");

        /*c) Első játékos köre*/
        System.out.println("p1.beginTurn() meghívva");
        p1.beginTurn();
        System.out.println("p1.beginTurn visszatért void értékkel");

        /*d) Második játékos köre*/
        System.out.println("p2.beginTurn() meghívva");
        p2.beginTurn();
        System.out.println("p2.beginTurn visszatért void értékkel");

        System.out.println("m.start visszatért void értékkel");
        System.out.println("--- Teszt vége ---");
    }

    /*2. Teszteset:*/
    public void testSnowfall() {
        System.out.println("--- Teszteset: Havazás kezelése ---");

        /*1. Bekérjük a teszteléshez szükséges értékeket.*/
        int snowyThreshold = getIntFromUser("SnowyThreshold/havasSzint");
        int snowdriftThreshold = getIntFromUser("SnowdriftThreshold/torlaszSzint");
        int amount = getIntFromUser("SnowfallRate/Hóesés mértéke)");

        /*2. Objektumok létrehozása:*/
        System.out.println("RoadNetwork típusú objektum létrehozva testNetwork névvel, paraméterek nélkül.");
        RoadNetwork testNetwork = new RoadNetwork();

        System.out.println("WeatherSystem típusú objektum létrehozva ws névvel, ezekkel a konstruktor paraméterekkel: " +
            "[network=testNetwork, snowFallRate=amount]"
        );
        WeatherSystem ws = new WeatherSystem(testNetwork, amount);
    
        System.out.println("Lane típusú objektum létrehozva l névvel.");
        Lane l = new Lane();

        System.out.println("Road típusú objektum létrehozva road névvel, ezekkel a konstruktor paraméterekkel: " +
            "[first=null, second=null]"
        );
        Road road = new Road(null, null);
    
        System.out.println("ClearState típusú objektum létrehozva s névvel.");
        ClearState s = new ClearState(l);

        System.out.println("l.addLaneState(newState=s) meghívva");
        l.addLaneState(s);
        System.out.println("l.addLaneState visszatért void értékkel");

        System.out.println("testNetwork.addRoad(r=road) meghívva");
        testNetwork.addRoad(road);
        System.out.println("testNetwork.addRoad(r=road) visszatért void értékkel");

        /*3. Folyamat:*/
        System.out.println("ws.setSnowfallRate(amount=" + amount + ") meghívva");
        ws.setSnowfallRate(amount);
        System.out.println("ws.setSnowfallRate visszatért void értékkel");

        System.out.println("ws.applySnowfall() meghívva");
    
        System.out.println("l.receiveSnow(amount=" + amount + ") meghívva");
    
        System.out.println("s.onSnowfall(amount=" + amount + ") meghívva");
    
        System.out.println("l.getSnowAmount() meghívva");

        /*4. Új érték szimulálása:*/
        int newSnowAmount = amount; 
        System.out.println("l.getSnowAmount visszatért " + newSnowAmount + " értékkel");

        System.out.println("l.setSnowAmount(newSnowAmount=" + newSnowAmount + ") meghívva");
        System.out.println("l.setSnowAmount visszatért void értékkel");

        /*5. Az 'alt' blokk: feltételvizsgálat*/
        if (newSnowAmount >= snowdriftThreshold) {
            /*Hótorlasz eset*/
            System.out.println("SnowdriftState típusú objektum létrehozva newState névvel, ezekkel a konstruktor paraméterekkel [lane=l]");
            SnowdriftState newState = new SnowdriftState(l);
        
            System.out.println("l.replaceLaneState(oldState=s, newState=newState) meghívva");
            /*l.replaceLaneState(this, newState)*/
            System.out.println("l.replaceLaneState visszatért void értékkel");
        
        } else if (newSnowAmount >= snowyThreshold) {
            /*Havas út eset*/
            System.out.println("SnowyState típusú objektum létrehozva newState névvel, ezekkel a konstruktor paraméterekkel [lane=l]");
            SnowyState newState = new SnowyState(l);
        
            System.out.println("l.replaceLaneState(oldState=s, newState=newState) meghívva");
            /*l.replaceLaneState(this, newState)*/
            System.out.println("l.replaceLaneState visszatért void értékkel");
        }

        /*6. Többi függvény visszatérése:*/
        System.out.println("s.onSnowfall visszatért void értékkel");
        System.out.println("lane.receiveSnow visszatért void értékkel");
        System.out.println("ws.applySnowfall visszatért void értékkel");
    
        System.out.println("--- Teszt vége ---");
    }

    /*3. Teszteset:*/
    public void testIcing() {
        System.out.println("--- Teszteset: Jég kialakulása ---");

        // 1. Paraméterek bekérése és objektumok létrehozása:
        int iceSheetThreshold = getIntFromUser("iceSheetThreshold/JégpáncélKüszöb");
        int icingRate = getIntFromUser("icingRate/JegesedésMérték");
    
        System.out.println("Lane típusú objektum létrehozva l névvel.");
        Lane l = new Lane();
    
        System.out.println("SnowyState típusú objektum létrehozva s névvel.");
        SnowyState s = new SnowyState(l);
    
        System.out.println("BusDriverPlayer típusú objektum létrehozva testBusDriverPlayer névvel, ezekkel a konstruktor paraméterekkel: " +
            "[name=BusDriverPlayer, vehicle=null, wallet=null, bus=null]"
        );
        BusDriverPlayer testBusDriverPlayer = new BusDriverPlayer("BusDriverPlayer", null, null, null);

        System.out.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
            "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        int testVehicleSpeed = getIntFromUser("testVehicleSpeed/TesztjárműSebesség");

        System.out.println("Bus típusú objektum létrehozva b névvel, ezekkel a konstruktor paraméterekkel: " +
            "[currentLane=l, owner=testBusDriverPlayer, destination=testDestination," + testVehicleSpeed
        );
        Bus b = new Bus(l, testBusDriverPlayer, testDestination, testVehicleSpeed);

        /*2. Folyamat:*/
        System.out.println("l.acceptVehicle(v=b) meghívva");
        /*l.acceptVehicle(b);*/

        System.out.println("s.onVehicleEnter(v=b) meghívva");
        /*s.onVehicleEnter(b);*/
    
        System.out.println("l.getIceAmount() meghívva");

        // Szimuláljuk a jelenlegi jégmennyiséget és a növekedést
        int currentIce = 0; // Kiindulási állapot
        int newIceAmount = currentIce + icingRate;
        System.out.println("l.getIceAmount visszatért " + currentIce + " értékkel");
    
        System.out.println("l.setIceAmount(newIceAmount=" + newIceAmount + ") meghívva");
        System.out.println("l.setIceAmount visszatért void értékkel");

        /*3. Az 'alt' blokk: Jégpáncél kialakulása*/
        if (newIceAmount >= iceSheetThreshold) {
            System.out.println("IceSheetState típusú objektum létrehozva nextState névvel, ezekkel a konstruktor paraméterekkel [lane=l]");
            IceSheetState nextState = new IceSheetState(l);
        
            System.out.println("l.replaceLaneState(oldState=s, newState=nextState) meghívva");
            l.replaceLaneState(s, nextState);
            System.out.println("l.replaceLaneState visszatért void értékkel");
        }

        /*4. Visszatérések:*/
        System.out.println("s.onVehicleEnter visszatért void értékkel");
        System.out.println("l.acceptVehicle visszatért void értékkel");
    
        System.out.println("--- Teszt vége ---");
    }

    /*4. Teszteset:*/
    public void testVehicleMoving() {
        System.out.println("--- Teszteset: Jármű mozgatása ---");

        /*1. Objektumok és paraméterek előkészítése:*/
        int movementNeeded = getIntFromUser("movementNeeded/mozgásIgény");
        int movementRemaining = getIntFromUser("movementRemaining/hátralevőMozgás");
    
        System.out.println("Lane típusú objektum létrehozva currentLane névvel.");
        Lane currentLane = new Lane();

        System.out.println("BusDriverPlayer típusú objektum létrehozva testBusDriverPlayer névvel, ezekkel a konstruktor paraméterekkel: " +
            "[name=BusDriverPlayer, vehicle=null, wallet=null, bus=null]"
        );
        BusDriverPlayer testBusDriverPlayer = new BusDriverPlayer("BusDriverPlayer", null, null, null);

        System.out.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
            "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        int vehicleSpeed = getIntFromUser("vehicleSpeed/BuszSebesség");

        System.out.println("Bus típusú objektum létrehozva v névvel, ezekkel a konstruktor paraméterekkel" +
            "[currentLane=currentLane, owner=testBusDriverPlayer, destination=testDestination, speed=vehicleSpeed]"
        );
        Bus v = new Bus(currentLane, testBusDriverPlayer, testDestination, vehicleSpeed);
    
        System.out.println("Lane típusú objektum létrehozva target névvel.");
        Lane target = new Lane();

        System.out.println("ClearState típusú objektum létrehozva s névvel.");
        ClearState s = new ClearState(target);

        /*2. Folyamat modellezése:*/
        System.out.println("v.tryMoveTo(target=target) meghívva");
    
        /*A jármű belső logikája - mozgáspont levonás:*/
        System.out.println("v.consumeMovement(amount=" + movementNeeded + ") meghívva");
        v.consumeMovement(movementNeeded);
        System.out.println("v.consumeMovement visszatért void értékkel");

        /*3. Az 'alt' blokk - Van-e elég mozgáspont?*/
        if (movementRemaining >= movementNeeded) {
            /*Sikeres mozgás ága*/
            System.out.println("target.acceptVehicle(v=v) meghívva");
            /*target.acceptVehicle(v);*/
        
            /*A sáv továbbhív az állapotára*/
            System.out.println("s.onVehicleEnter(v=v) meghívva");
            s.onVehicleEnter(v);
            System.out.println("s.onVehicleEnter visszatért void értékkel");
        
            /*A sáv értesíti a járművet az egyéb eseményekről*/
            System.out.println("v.onEnterLane(other=null) meghívva");
            v.onEnterLane(null);
            System.out.println("v.onEnterLane visszatért void értékkel");
        
            System.out.println("target.acceptVehicle visszatért void értékkel");
        
            /*Visszatérés a Skeletonnak*/
            System.out.println("v.tryMoveTo visszatért true értékkel");

        } else {
            /*Sikertelen mozgás ága (nincs elég pont)*/
            System.out.println("v.tryMoveTo visszatért false értékkel");
        }

        System.out.println("--- Teszt vége ---");
    }   

    /*5. Teszteset:*/
    public void testUsingBroomHead() {
        
        System.out.println("--- Teszteset: Söprő fej használata ---");

        /*1. Objektumok létrehozása:*/
        int amount = getIntFromUser("aktuális hómennyiség/amount");
        int neighborCount = getIntFromUser("szomszédos sávok száma/neighborCount");
        int spSpeed = getIntFromUser("HókotróSebesség/SnowPlowSpeed");

        System.out.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);
    
        System.out.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        System.out.println("Lane típusú objektum létrehozva neighborLane névvel.");
        Lane neighborLane = new Lane();

        System.out.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
            "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        System.out.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);
    
        System.out.println("BroomHead típusú objektum létrehozva bh névvel.");
        BroomHead bh = new BroomHead(sp, cl);
     
        System.out.println("SnowDriftState típusú objektum létrehozva ss névvel.");
        SnowdriftState ss = new SnowdriftState(cl);

        System.out.println("Road típusú objektum létrehozva r névvel.");
        Road r = new Road(null, null);

        /*2. A folyamat indítása*/
        System.out.println("cp.performCleaning(p=sp) meghívva");
    
        System.out.println("sp.work() meghívva");
    
        System.out.println("bh.applyTo(p=sp, l=cl, r=r) meghívva");
    
        /*Utak lekérése a szomszédokhoz*/
        System.out.println("r.getLanes() meghívva");
        System.out.println("r.getLanes visszatért lanes listával");
    
        System.out.println("cl.getSnowAmount() meghívva");
        System.out.println("cl.getSnowAmount visszatért " + amount + " értékkel");

        /*3. LOOP - Szomszédos sávok havasítása*/
        for (int i = 1; i <= neighborCount; i++) {
            System.out.println("--- Loop: neighbor " + i + " ---");
            System.out.println("neighbor:Lane.receiveSnow(amount=" + (amount/2) + ") meghívva");
            System.out.println("neighbor:Lane.receiveSnow visszatért void értékkel");
        }

        /*4. Takarítás befejezése és állapotváltás*/
        System.out.println("cl.clean(h=bh) meghívva");
    
        System.out.println("ss.onCleaned() meghívva");
    
        System.out.println("ClearState típusú objektum létrehozva cs névvel, ezekkel a konstruktor paraméterekkel [lane=cl]");
        ClearState cs = new ClearState(cl);
    
        System.out.println("cl.replaceLaneState(oldState=ss, newState=cs) meghívva");
        cl.replaceLaneState(ss, cs);
        System.out.println("cl.replaceLaneState visszatért void értékkel");
    
        System.out.println("ss.onCleaned visszatért void értékkel");
        System.out.println("cl.clean visszatért void értékkel");
    
        /*5. Visszatérések*/
        System.out.println("bh.applyTo visszatért void értékkel");
        System.out.println("sp.work visszatért void értékkel");
        System.out.println("cp.performCleaning visszatért void értékkel");

        System.out.println("--- Teszt vége ---");
}
    
    /*6. Teszteset:*/
    public void testUsingThrowerHead() {
        
        System.out.println("--- Teszteset: Hányó fej használata ---");

        /*1. Paraméterek és objektumok létrehozása:*/
        int amount = getIntFromUser("aktuális hómennyiség/amount");
        int spSpeed = getIntFromUser("HókotróSebesség/SnowplowSpeed");
        int targetLaneDistance = 2; /*Ref: 2 sávval arrébb*/

        System.out.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);
    
        System.out.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        System.out.println("Lane típusú objektum létrehozva otherLane névvel.");
        Lane otherLane = new Lane();

        System.out.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
            "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        System.out.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);
    
        System.out.println("ThrowerHead típusú objektum létrehozva th névvel, ezekkel a paraméterekkel:" + 
            "[plow=sp targetLane=cl]"
        );
        ThrowerHead th = new ThrowerHead(sp, cl);
    
        System.out.println("SnowdriftState típusú objektum létrehozva ss névvel.");
        SnowdriftState ss = new SnowdriftState(cl);

        System.out.println("Road típusú objektum létrehozva r névvel, ezekkel a paraméterekkel:" +
            "[first=null, second=null]"
        );
        Road r = new Road(null, null);

        /*2. Folyamat:*/
        System.out.println("cp.performCleaning(p=sp) meghívva");
        System.out.println("sp.work() meghívva");
    
        /*th: ThrowerHead kapja meg a vezérlést*/
        System.out.println("th.applyTo(p=sp, l=cl, r=r) meghívva");
    
        System.out.println("r.getLanes() meghívva");
        System.out.println("r.getLanes visszatért lanes listával");
    
        System.out.println("cl.getSnowAmount() meghívva");
        System.out.println("cl.getSnowAmount visszatért " + amount + " értékkel");

        /*3. LOOP - Hó áthelyezése távolabbi sávokhoz*/
        System.out.println("--- A célpont sávok " + targetLaneDistance + " egységre vannak ---");
    
        /*Távoli sáv szimulálása:*/
        System.out.println("other:Lane.receiveSnow(amount=" + (amount/2) + ") meghívva");
        System.out.println("other:Lane.receiveSnow visszatért void értékkel");

        /*4. Tisztítás és állapotváltás*/
        System.out.println("cl.clean(h=th) meghívva");
        System.out.println("ss.onCleaned() meghívva");
    
        System.out.println("ClearState típusú objektum létrehozva cs névvel, ezekkel a konstruktor paraméterekkel [lane=cl]");
        ClearState cs = new ClearState(cl);
    
        System.out.println("cl.replaceLaneState(oldState=ss, newState=cs) meghívva");
        cl.replaceLaneState(ss, cs);
        System.out.println("cl.replaceLaneState visszatért void értékkel");
    
        /*5. Visszatérések*/
        System.out.println("ss.onCleaned visszatért void értékkel");
        System.out.println("cl.clean visszatért void értékkel");
        System.out.println("th.applyTo visszatért void értékkel");
        System.out.println("sp.work visszatért void értékkel");
        System.out.println("cp.performCleaning visszatért void értékkel");

        System.out.println("--- Teszt vége ---");
}
    
    /*7. Teszteset:*/
    public void testUsingIceBreakerHead() {
        
        System.out.println("--- Teszteset: Jégtörő fej használata ---");

        /*1. Objektumok és bemenetek*/
        int iceAmount = getIntFromUser("aktuális jégmennyiség/iceAmount");
        int spSpeed = getIntFromUser("HókotróSebesség/SnowplowSpeed");

        System.out.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);
    
        System.out.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        System.out.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
            "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        System.out.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);
    
        System.out.println("IceBreakerHead típusú objektum létrehozva ih névve, ezekkel a konstruktor paraméterekkel:" +
            "[plow=sp, targetLane=cl]"
        );
        IceBreakerHead ih = new IceBreakerHead(sp, cl);
    
        System.out.println("IceSheetState típusú objektum létrehozva is névvel, ezekkel a paraméterekkel:" +
            "[lane=cl]"
        );
        IceSheetState is = new IceSheetState(cl);

        System.out.println("Road típusú objektum létrehozva r névvel, ezekkel a paraméterekkel:" +
            "[first=null, second=null]"
        );
        Road r = new Road(null, null);

        /*2. Folyamat:*/
        System.out.println("cp.performCleaning(p=sp) meghívva");
        System.out.println("sp.work() meghívva");
    
        // ih: IceBreakerHead kapja meg a vezérlést
        System.out.println("ih.applyTo(p=sp, l=cl, r=r) meghívva");
    
        System.out.println("cl.getIceAmount() meghívva");
        int resIceAmount = cl.getIceAmount();
        System.out.println("cl.getIceAmount visszatért " + iceAmount + " értékkel");

        /*A jégtörő fej a jeget hóvá alakítja*/
        System.out.println("cl.receiveSnow(amount=" + iceAmount + ") meghívva");
        cl.receiveSnow(resIceAmount);
        System.out.println("cl.receiveSnow visszatért void értékkel");

        /*3. Tisztítás és állapotváltás: Jégpáncél -> Havas út*/
        System.out.println("cl.clean(h=ih) meghívva");
    
        System.out.println("is.onCleaned() meghívva");
    
        System.out.println("SnowyState típusú objektum létrehozva ss névvel, ezekkel a konstruktor paraméterekkel [lane=cl]");
        SnowyState ss = new SnowyState(cl);
    
        System.out.println("cl.replaceLaneState(oldState=is, newState=ss) meghívva");
        cl.replaceLaneState(is, ss);
        System.out.println("cl.replaceLaneState visszatért void értékkel");
    
        /*4. Visszatérések*/
        System.out.println("is.onCleaned visszatért void értékkel");
        System.out.println("cl.clean visszatért void értékkel");
        System.out.println("ih.applyTo visszatért void értékkel");
        System.out.println("sp.work visszatért void értékkel");
        System.out.println("cp.performCleaning visszatért void értékkel");

        System.out.println("--- Teszt vége ---");
}
    
    /*8. Teszteset:*/
    public void testUsingSaltSpreaderHead() {
        
        System.out.println("--- Teszteset: Sószóró fej használata ---");

        /*1. Paraméterek és objektumok előkészítése*/
        int iceAmount = getIntFromUser("iceAmount/Jégmennyiség");
        int snowAmount = getIntFromUser("snowAmount/Hómennyiség");
        int saltSupply = getIntFromUser("saltSupply/Sómennyiség");
        int spSpeed = getIntFromUser("SnowplowSpeed/HókotróSebesség");

        System.out.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);
    
        System.out.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        System.out.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
            "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        System.out.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);
    
        System.out.println("Salt típusú objektum létrehozva salt néven, ezekkel a konstruktor paraméterekkel:" +
            "[owner=sp, amount= " + saltSupply + "]"
        );
        Salt salt = new Salt(sp, saltSupply);

        System.out.println("SaltSpreaderHead típusú objektum létrehozva ssh névvel, ezekkel a konstruktor paraméterekkel:" +
            "[plow=sp, targetLane=cl, saltSupply=salt]"
        );
        SaltSpreaderHead ssh = new SaltSpreaderHead(sp, cl, salt);
    
        System.out.println("IceSheetState típusú objektum létrehozva is névvel, ezekkel a konstruktor paraméterekkel:" +
            "[lane=cl]"
        );
        IceSheetState is = new IceSheetState(cl);

        /*2. Folyamat:*/
        System.out.println("cp.performCleaning(p=sp) meghívva");
        System.out.println("sp.work() meghívva");
        System.out.println("ssh.applyTo(p=sp, l=cl, r=road) meghívva");

        /*3. OPT blokk - Csak ha van só*/
        if (saltSupply > 0) {
            System.out.println("--- Opt: [saltSupply > 0] ---");
        
            System.out.println("cl.clean(h=ssh) meghívva");
            System.out.println("is.onCleaned() meghívva");
            is.onCleaned();
            System.out.println("is.onCleaned visszatért void értékkel");
            System.out.println("cl.clean visszatért void értékkel");
        
            System.out.println("SaltedState típusú objektum létrehozva ss névvel, ezekkel a konstruktor paraméterekkel" +
            "[lane=cl, remainingDuration=10]");
            SaltedState ss = new SaltedState(cl, 10);
 
            System.out.println("cl.addLaneState(state=ss) meghívva");
            cl.addLaneState(ss);
            System.out.println("cl.addLaneState visszatért void értékkel");
        }

        /*4. Visszatérések:*/
        System.out.println("ssh.applyTo visszatért void értékkel");
        System.out.println("sp.work visszatért void értékkel");
        System.out.println("cp.performCleaning visszatért void értékkel");

        System.out.println("--- Teszt vége ---");
    }
    
    /*9. Teszteset:*/
    public void testUsingDragonHead() {
    
        System.out.println("--- Teszteset: Sárkány fej használata ---");

        /*1. Objektumok és bemenet bekérése*/
        int fuelSupply = getIntFromUser("BioKerosine");
        int spSpeed = getIntFromUser("SnowplowSpeed/HókotróSebesség");

        System.out.println("CleanerPlayer típusú objektum létrehozva cp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[name=CleanerPlayer, vehicle=null, wallet=null, snowplow=null]"
        );
        CleanerPlayer cp = new CleanerPlayer("CleanerPlayer", null, null, null);
    
        System.out.println("Lane típusú objektum létrehozva cl névvel.");
        Lane cl = new Lane();

        System.out.println("Intersection típusú objektum létrehozva testDestination névvel, ezekkel a konstruktor paraméterekkel:" +
            "[id=testID]"
        );
        Intersection testDestination = new Intersection("testID");

        System.out.println("SnowPlow típusú objektum létrehozva sp névvel, ezekkel a konstruktor paraméterekkel:" +
            "[currentLane=cl, owner=cp, destination=testDestination, speed=spSpeed]"
        );
        Snowplow sp = new Snowplow(cl, cp, testDestination, spSpeed);
    
        System.out.println("BioKerosene típusú objektum létrehozva biokerozin névvel, ezekkel a konstruktor paraméterekkel:" +
            "[owner=sp, amount=fuelSupply]"
        );
        BioKerosene biokerozin = new BioKerosene(sp, fuelSupply);

        System.out.println("DragonHead típusú objektum létrehozva dh névvel, ezekkel a konstruktor paraméterekkel:" +
            "[plow=sp, targetLane=cl, fuelSupply=biokerozin]"
        );
        DragonHead dh = new DragonHead(sp, cl, biokerozin);
    
        System.out.println("IceSheetState típusú objektum létrehozva is névvel, ezekkel a konstruktor paraméterekkel:" +
            "[lane=cl]"
        );
        IceSheetState is = new IceSheetState(cl);

        /*2. Folyamat indítása*/
        System.out.println("cp.performCleaning(p=sp) meghívva");
        System.out.println("sp.work() meghívva");
        System.out.println("dh.applyTo(p=sp, l=cl, r=road) meghívva");

        /*3. OPT blokk - Üzemanyag-ellenőrzés*/
        if (fuelSupply > 0) {
            System.out.println("--- Opt: [fuelSupply > 0] ---");
            System.out.println("cl.clean(h=dh) meghívva");
            System.out.println("is.onCleaned() meghívva");
        
            /*Új tiszta állapot létrehozása és beállítása*/
            System.out.println("ClearState típusú objektum létrehozva cs névvel, ezekkel a konstruktor paraméterekkel [lane=cl]");
            ClearState cs = new ClearState(cl);
        
            System.out.println("cl.replaceLaneState(oldState=is, newState=cs) meghívva");
            cl.replaceLaneState(is, cs);
            System.out.println("cl.replaceLaneState visszatért void értékkel");
        
            System.out.println("is.onCleaned visszatért void értékkel");
            System.out.println("cl.clean visszatért void értékkel");

        } else {
            System.out.println("Sárkány fej: Nincs elég üzemanyag a takarításhoz.");
        }

        /*4. Visszatérések*/
        System.out.println("dh.applyTo visszatért void értékkel");
        System.out.println("sp.work visszatért void értékkel");
        System.out.println("cp.performCleaning visszatért void értékkel");

        System.out.println("--- Teszt vége ---");
    }
    
    /*10. Teszteset:*/
    public void testCollisionHandling() {
        
        System.out.println("--- Teszteset: Ütközés kezelése ---");

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

        System.out.println("Intersection típusú objektum létrehozva carDestination névvel, ezekkel a konstruktor paraméterekkel:" +
            "[id=carID]"
        );
        Intersection carDestination = new Intersection("carID");   

        System.out.println("Intersection típusú objektum létrehozva busDestination névvel, ezekkel a konstruktor paraméterekkel:" +
            "[id=busID]"
        );
        Intersection busDestination = new Intersection("busID"); 

        System.out.println("Lane típusú objektum létrehozva l névvel.");
        Lane l = new Lane();

        System.out.println("Car típusú objektum létrehozva c névvel, ezekkel a konstruktor paraméterekkel:" +
            "[currentLane=l owner=null destination=testDestination speed=" + carSpeed + "]"
        );
        Car c = new Car(l, null, carDestination, carSpeed);
      
        System.out.println("IceSheetState típusú objektum létrehozva s névvel, ezekkel a konstruktor paraméterekkel:" 
            + "[lane=l]"
        );
        IceSheetState s = new IceSheetState(l);

        System.out.println("BusDriverPlayer típusú objektum létrehozva testBusDriverPlayer névvel, ezekkel a konstruktor paraméterekkel: " +
            "[name=BusDriverPlayer, vehicle=null, wallet=null, bus=null]"
        );
        BusDriverPlayer testBusDriverPlayer = new BusDriverPlayer("BusDriverPlayer", null, null, null);
    
        System.out.println("Bus típusú objektum létrehozva b névvel, ezekkel a konstruktor paraméterekkel:" +
            "[currentLane=l owner=BusDriverPlayer destination=testDestination3 speed=busSpeed]"
        );
        Bus b = new Bus(l, testBusDriverPlayer, busDestination, busSpeed);

        /*2. Folyamat indítása*/
        System.out.println("c.tryMoveTo(target=l) meghívva");

        /*Első OPT: [canMove]*/
        if (canMove) {
            System.out.println("--- Opt: [canMove] ---");
            System.out.println("l.acceptVehicle(v=c) meghívva");
            System.out.println("s.onVehicleEnter(v=c) meghívva");

            /*Második OPT: [vehicle slips]*/
            if (vehicleSlips) {
                System.out.println("--- Opt: [vehicle slips] ---");
                System.out.println("c.handleIcyLane(lane=l) meghívva");

                /*ALT: Ütközés vs Szabad sáv*/
                if (busIsOnLane) {
                    System.out.println("--- Alt: [bus is on lane] ---");
                    System.out.println("c.onCollision() meghívva");
                
                    System.out.println("BlockedState típusú objektum létrehozva nextState névvel, ezekkel a konstruktor paraméterekkel [lane=l]");
                    System.out.println("l.addLaneState(state=nextState) meghívva");
                    l.addLaneState(s);
                    System.out.println("l.addLaneState visszatért void értékkel");
                
                    System.out.println("b.onCollision() meghívva");
                    System.out.println("ImmobilizedStatus típusú objektum létrehozva status névvel, ezekkel a konstruktor paraméterekkel [vehicle=b]");
                    System.out.println("b.onCollision visszatért void értékkel");
                
                    System.out.println("c.onCollision visszatért void értékkel");

                } else {
                    System.out.println("--- Alt: [lane is free] ---");
                    System.out.println("c.onEnterLane(lane=l) meghívva");
                    System.out.println("c.onEnterLane visszatért void értékkel");
                }
                System.out.println("c.handleIcyLane visszatért void értékkel");
            }
        
            System.out.println("s.onVehicleEnter visszatért void értékkel");
            System.out.println("l.acceptVehicle visszatért void értékkel");
        }

        System.out.println("c.tryMoveTo visszatért void értékkel");
        System.out.println("--- Teszt vége ---");
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

    }

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
