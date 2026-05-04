import java.util.*;
import java.util.stream.Collectors;

public class Prototype {
    Map<RandomType, Boolean> randomState = new HashMap<>();

    public Prototype(){
        for (RandomType type : RandomType.values()){
            randomState.put(type, true);
        }
    }

    <T> T get(String name) {
        Optional<Object> result = Skeleton.instance.names.keySet().stream().filter((obj) -> Skeleton.instance.names.get(obj).equals(name)).findFirst();
        return (T) (result.isPresent() ? result.get() : null);
    }

    <T> T get(Class<T> clas) {
        Optional<Object> result = Skeleton.instance.names.keySet().stream().filter((obj) -> clas.isAssignableFrom(obj.getClass())).findFirst();
        return (T) (result.isPresent() ? result.get() : null);
    }

    <T> List<T> getAll(Class<T> clas){
        return Skeleton.instance.names.keySet().stream().filter((obj) -> clas.isAssignableFrom(obj.getClass())).map(obj -> (T) obj).collect(Collectors.toList());
    }

    public void createBus(String locationName){
        Lane location = get(locationName);
        Bus bus = new Bus(location, 10); }

    public void createSnowplow(String locationName){
        Lane location = get(locationName);
        Snowplow snowplow = new Snowplow(location, 10);
    }

    public void createCar(String startName, String destinationName) {
        Lane start = get(startName);
        Object destination = get(destinationName);
        RoadNetwork roadNetwork = get(RoadNetwork.class);

        new Car(start, roadNetwork, destination, 1);
    }

    public void createPlayers(int cleaner, int driver, String laneName){
        Lane lane = get(laneName);
        for (int i = 0; i < cleaner; i++) {
            new CleanerPlayer("", new Wallet(0), new Snowplow(lane, 10 ));
        }
        for (int i = 0; i < driver; i++) {
            new BusDriverPlayer("", new Wallet(0), new Bus(lane, 10 ));
        }
    }

    public void createMatch() {
        RoadNetwork roadNetwork = new RoadNetwork();
        for (Road road : getAll(Road.class)){
            roadNetwork.addRoad(road);
        }
        for (Intersection intersection : getAll(Intersection.class)){
            roadNetwork.addIntersection(intersection);
        }
        Match match = new Match(getAll(Player.class), getAll(Vehicle.class), roadNetwork);
        WeatherSystem weatherSystem =  new WeatherSystem(roadNetwork, 1);
    }

    public  void createIntersection(){
        Intersection intersection = new Intersection("");
    }

    public void createRoad(String s1, String s2, int lanes){
        Intersection i1 = get(s1);
        Intersection i2 = get(s2);

        Road road = new Road(i1, i2);
        for (int i = 0; i < lanes; i++) {
            road.addLane(new Lane());
        }

        i1.connectRoad(road);
        i2.connectRoad(road);
    }

    public void createGarage(String name){
        Intersection intersection = get(name);
        Garage garage = new Garage(intersection);
    }

    public void createRoute(String terminal1Name, String terminal2Name, String stopName){
        new BusRoute(List.of(new Terminal(get(terminal1Name)), new Terminal(get(terminal2Name)), new Stop(get(stopName))),1,1, get(Wallet.class));
    }

    public void nextTurn(){
        Match match = get(Match.class);
        for(Player player : match.getPlayers()){
            if (player.vehicle.movementRemaining == 0){
                player.beginTurn();
            }
            player.endTurn();
        }
    }

    public void pass(){
        Match match = get(Match.class);

        for(Player player : match.getPlayers()){
            if (player.vehicle.isActive()){
                player.endTurn();
                return;
            }
        }
    }

    public void setRandom(RandomType type, boolean enabled){
        randomState.remove(type);
        randomState.put(type, enabled);
    }

    public void startMatch(){
        Match match = get(Match.class);
        match.start();
    }

    public void set(SetType type, String name, Object value){
        Object obj = get(name);
        switch (type){
            case Snow:
                ((Lane) obj).setSnowAmount((Integer) value);
                break;
            case Ice:
                ((Lane) obj).setIceAmount((Integer) value);
                break;
            case Blocked:
                ((Lane) obj).addLaneState(new BlockedState((Lane) obj));
                break;
            case Salted:
                ((Lane) obj).addLaneState(new SaltedState((Lane) obj, 1));
                break;
        }
    }

    public void print(PrintType type){
        List<?> objects = new ArrayList<>();
        switch (type){
            case Players:
                objects = getAll(Player.class);
                break;
            case Vehicles:
                objects = getAll(Vehicle.class);
                break;
            case Lanes:
                objects = getAll(Lane.class);
                break;
            case Road:
                objects = getAll(Road.class);
                break;
            case Intersection:
                objects = getAll(Intersection.class);
                break;
            case Wallet:
                objects = getAll(Wallet.class);
                break;
            case Result:
                objects = getAll(Result.class);
                break;
        }
        for (Object obj : objects){
            print(type, Skeleton.instance.names.get(obj));
        }
    }

    public void print(PrintType type, String name){
        Object obj = get(name);
        switch (type){
            case Players:
                Player player = ((Player) obj);
                break;
            case Vehicles:
                break;
            case Lanes:
                Skeleton.instance.outStream.printf("STATE %s: laneState={ ", name);
                for (LaneState state : ((Lane) obj).getLaneStates()){
                    Skeleton.instance.outStream.printf("%s ",state.getClass().getName());
                }
                Skeleton.instance.outStream.print("}, vehicles={ ");
                for (Vehicle vehicle : ((Lane) obj).getVehicles()){
                    Skeleton.instance.outStream.printf("%s ",Skeleton.instance.names.get(vehicle));
                }
                Skeleton.instance.outStream.printf("}, road=%s\n", ((Lane) obj).getRoad());
                break;
            case Road:
                break;
            case Intersection:
                break;
            case Wallet:
                Skeleton.instance.outStream.printf("STATE %s: balance=%d\n",name, ((Wallet) obj).getFunds());
                break;
            case Result:
                Skeleton.instance.outStream.printf("STATE %s: eredménye=%d\n",name, ((Result) obj).getScore());
                break;
        }
    }

    public void snowfall(int amount){
        WeatherSystem weatherSystem = get(WeatherSystem.class);
        weatherSystem.setSnowfallRate(amount);
        weatherSystem.applySnowfall();
    }

    public void moveVehicle(String name, String targetName){
        Vehicle vehicle = get(name);
        Lane target = get(targetName);

        vehicle.tryMoveTo(target);
    }

    public void useHead(String snowplowName, HeadType head){
        Snowplow vehicle = get(snowplowName);
        changePlowHead(snowplowName, head);
        vehicle.work();
    }

    public void handleCollision(String name1, String name2){
        Vehicle vehicle1 = get(name1);
        Vehicle vehicle2 = get(name2);

        vehicle1.onCollision();
        vehicle2.onCollision();
    }

    public void removeObstacle(String name){
        Lane lane = get(name);
        lane.removeLaneState(lane.getLaneStates().stream().filter(BlockedState.class::isInstance).findFirst().get());
    }

    public void changePlowHead(String name, HeadType head){
        Snowplow vehicle = get(name);

        PlowHead newHead = null;
        switch (head){
            case Broom:
                newHead = new BroomHead(vehicle, vehicle.getCurrentLane());
                break;
            case Thrower:
                newHead = new ThrowerHead(vehicle, vehicle.getCurrentLane());
                break;
            case IceBreaker:
                newHead = new IceBreakerHead(vehicle, vehicle.getCurrentLane());
                break;
            case SaltSpreader:
                newHead = new SaltSpreaderHead(vehicle, vehicle.getCurrentLane(), new Salt(1,vehicle));
                break;
            case Dragon:
                newHead = new DragonHead(vehicle, vehicle.getCurrentLane(), new BioKerosene(1,vehicle));
                break;
            case GravelSpreader:
                newHead = new GravelSpreaderHead(vehicle, vehicle.getCurrentLane(), new Gravel(1,vehicle));
                break;
            }

        vehicle.equipHead(newHead);
    }

    public void carRoute(String name){
        Car car = get(name);
        car.executeTurn();
    }

    public void changeLane(String name){
        Car car = get(name);
        car.checkAndChangeLane();
    }

    public void completeBusRoute(String busName){
        Bus bus = get(busName);
        bus.clearRoute();
    }

    public void visitStop(String busName, String stopName){
        Bus bus = get(busName);
        RoutePoint stop = get(stopName);
        bus.checkStop(stop.location);
    }

    public void vehicleStuck(String name){
        Vehicle vehicle = get(name);
        vehicle.getCurrentLane().addLaneState(new BlockedState(vehicle.getCurrentLane()));
    }

    public void purchase(String playerName, BuyType type){
        Player player = get(playerName);

        Purchasable purchasable = null;
        switch (type){
            case BROOM_HEAD:
               purchasable = new HeadPurchase(100, new BroomHead(null, null));
                break;
            case THROWER_HEAD:
                purchasable = new HeadPurchase(100, new ThrowerHead(null, null));
                break;
            case IceBreaker_HEAD:
                purchasable = new HeadPurchase(100, new IceBreakerHead(null, null));
                break;
            case SaltSpreader_HEAD:
                purchasable = new HeadPurchase(100, new SaltSpreaderHead((Snowplow) player.vehicle, null, null));
                break;
            case Dragon_HEAD:
                purchasable = new HeadPurchase(100, new DragonHead((Snowplow) player.vehicle, null, null));
                break;
            case GravelSpreader_HEAD:
                purchasable = new HeadPurchase(100, new GravelSpreaderHead((Snowplow) player.vehicle, null, null));
                break;
            case Salt:
                purchasable = new ConsumablePurchase(10, 1,new Salt(1, player.vehicle));
                break;
            case Kerosene:
                purchasable = new ConsumablePurchase(10, 1,new BioKerosene(1, player.vehicle));
                break;
            case Gravel:
                purchasable = new ConsumablePurchase(10, 1, new Gravel(1, player.vehicle));
                break;
            case Snowplow:
                purchasable = new PlowPurchase(500, new Snowplow(null, 20));
                break;
            case Bus:
                purchasable = new BusPurchase(500, new Bus(null, 20));
                break;
        }
        purchasable.applyPurchase(player);
    }

    public void freeVehicle(String name){
        Vehicle vehicle = get(name);
        vehicle.getCurrentLane().removeLaneState(vehicle.getCurrentLane().getLaneStates().stream().filter(BlockedState.class::isInstance).findFirst().get());
    }

    public void scoreCleaner(String name){
        CleanerPlayer cleaner = get(name);
        Match match = get(Match.class);

        match.getResultFor(cleaner);
    }
}
