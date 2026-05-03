import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Prototype {
    Map<RandomType, Boolean> randomState = new HashMap<>();

    public Prototype(){
        for (RandomType type : RandomType.values()){
            randomState.put(type, true);
        }
    }

    <T> T get(String name) {
        Object obj = Skeleton.instance.names.get(name);
        return (T) obj;
    }

    <T> T get(Class<T> clas) {
        return (T) Skeleton.instance.names.keySet().stream().filter((obj) -> clas.isAssignableFrom(obj.getClass())).findFirst().get();
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
        Lane destination = get(destinationName);
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
    }

    public void createGarage(String name){
        Intersection intersection = get(name);
        Garage garage = new Garage(intersection);
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
            case Snow -> {
                ((Lane) obj).setSnowAmount((Integer) value);
            }
            case Ice -> {
                ((Lane) obj).setIceAmount((Integer) value);
            }
            case Blocked -> {
                ((Lane) obj).addLaneState(new BlockedState((Lane) obj));
            }
            case Salted -> {
                ((Lane) obj).addLaneState(new SaltedState((Lane) obj, 1));
            }
        }
    }

    public void print(PrintType type){
        List<?> objects = new ArrayList<>();
        switch (type){
            case Players -> {
                objects = getAll(Player.class);
            }
            case Vehicles -> {
                objects = getAll(Vehicle.class);
            }
            case Lanes -> {
                objects = getAll(Lane.class);}
            case Road -> {
                objects = getAll(Road.class);
            }
            case Intersection -> {
                objects = getAll(Intersection.class);
            }
            case Wallet -> {
                objects = getAll(Wallet.class);
            }
            case Result -> {
                objects = getAll(Result.class);
            }
        }
        for (Object obj : objects){
            print(type, Skeleton.instance.names.get(obj));
        }
    }

    public void print(PrintType type, String name){
        Object obj = get(name);
        switch (type){
            case Players -> {
                Player player = ((Player) obj);
            }
            case Vehicles -> {
            }
            case Lanes -> {
                Skeleton.instance.outStream.printf("STATE %s: laneState={ ", name);
                for (LaneState state : ((Lane) obj).getLaneStates()){
                    Skeleton.instance.outStream.printf("%s ",state.getClass().getName());
                }
                Skeleton.instance.outStream.print("}, vehicles={ ");
                for (Vehicle vehicle : ((Lane) obj).getVehicles()){
                    Skeleton.instance.outStream.printf("%s ",Skeleton.instance.names.get(vehicle));
                }
                Skeleton.instance.outStream.printf("}, road=%s\n", ((Lane) obj).getRoad());
            }
            case Road -> {
            }
            case Intersection -> {
            }
            case Wallet -> {
                Skeleton.instance.outStream.printf("STATE %s: balance=%i \n",name, ((Wallet) obj).getFunds());
            }
            case Result -> {
                Skeleton.instance.outStream.printf("STATE %s: eredménye=%i",name, ((Result) obj).getScore());
            }
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
        Vehicle vehicle2 = get(name1);

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
            case Broom -> {
                newHead = new BroomHead(vehicle, vehicle.getCurrentLane());
            }
            case Thrower -> {
                newHead = new ThrowerHead(vehicle, vehicle.getCurrentLane());
            }
            case IceBreaker -> {
                newHead = new IceBreakerHead(vehicle, vehicle.getCurrentLane());
            }
            case SaltSpreader -> {
                newHead = new SaltSpreaderHead(vehicle, vehicle.getCurrentLane(), new Salt(1,vehicle));
            }
            case Dragon -> {
                newHead = new DragonHead(vehicle, vehicle.getCurrentLane(), new BioKerosene(1,vehicle));
            }
            case GravelSpreader -> {
                newHead = new GravelSpreaderHead(vehicle, vehicle.getCurrentLane(), new Gravel(1,vehicle));
            }}

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
            case BROOM_HEAD -> {
               purchasable = new HeadPurchase(100, new BroomHead(null, null));
            }
            case THROWER_HEAD -> {
                purchasable = new HeadPurchase(100, new ThrowerHead(null, null));
            }
            case IceBreaker_HEAD -> {
                purchasable = new HeadPurchase(100, new IceBreakerHead(null, null));
            }
            case SaltSpreader_HEAD -> {
                purchasable = new HeadPurchase(100, new SaltSpreaderHead((Snowplow) player.vehicle, null, null));
            }
            case Dragon_HEAD -> {
                purchasable = new HeadPurchase(100, new DragonHead((Snowplow) player.vehicle, null, null));
            }
            case GravelSpreader_HEAD -> {
                purchasable = new HeadPurchase(100, new GravelSpreaderHead((Snowplow) player.vehicle, null, null));
            }
            case Salt -> {
                purchasable = new ConsumablePurchase(10, 1,new Salt(1, player.vehicle));
            }
            case Kerosene -> {
                purchasable = new ConsumablePurchase(10, 1,new BioKerosene(1, player.vehicle));
            }
            case Gravel -> {
                purchasable = new ConsumablePurchase(10, 1, new Gravel(1, player.vehicle));
            }
            case Snowplow -> {
                purchasable = new PlowPurchase(500, new Snowplow(null, 20));
            }
            case Bus -> {
                purchasable = new BusPurchase(500, new Bus(null, 20));
            }
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
