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

    public void createBus(String locationName, String playerName, String destinationName){
        Lane location = get(locationName);
        BusDriverPlayer player = get(playerName);
        Object destination = get(destinationName);
        Snowplow snowplow = new Snowplow(location, player, destination, 10); }

    public void createSnowplow(String locationName, String playerName, String destinationName){
        Lane location = get(locationName);
        BusDriverPlayer player = get(playerName);
        Object destination = get(destinationName);
        Snowplow snowplow = new Snowplow(location, player, destination, 10);
    }

    public void createMatch(){
        Match match = new Match(getAll(Player.class),getAll(Vehicle.class), new RoadNetwork());
    }

    public  void createIntersection(){
        RoadNetwork network = get(RoadNetwork.class);
        Intersection intersection = new Intersection("");
        if (network != null){
            network.addIntersection(intersection);
        }
    }

    public void createRoad(String s1, String s2){
        Intersection i1 = get(s1);
        Intersection i2 = get(s2);

        RoadNetwork network = get(RoadNetwork.class);
        Road road = new Road(i1, i2);
        if (network != null){
            network.addRoad(road);
        }
    }

    public void createGarage(String name){
        Intersection intersection = get(name);
        Garage garage = new Garage(intersection);
    }

    public void nextTurn(){
        Match match = get(Match.class);
    }

    public void pass(){
        Match match = get(Match.class);
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
                //TODO: DURATION
                ((Lane) obj).addLaneState(new SaltedState((Lane) obj, 1));
            }
        }
    }

    public void print(PrintType type){
        //TODO
        switch (type){
            case Players -> {
            }
            case Vehicles -> {
            }
            case Lanes -> {
            }
            case Road -> {
            }
            case Intersection -> {
            }
            case Wallet -> {
            }
            case Inventory -> {
            }
        }
    }

    public void print(PrintType type, String name){
        Object obj = get(name);
        switch (type){
            case Players -> {
                Player player = ((Player) obj);
                //TODO
            }
            case Vehicles -> {
            }
            case Lanes -> {
            }
            case Road -> {
            }
            case Intersection -> {
            }
            case Wallet -> {
            }
            case Inventory -> {
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
        Road road = get(Road.class);
        changePlowHead(snowplowName, head);
        vehicle.work(road);
    }

    public void handleCollision(String name1, String name2){
        Vehicle vehicle1 = get(name1);
        Vehicle vehicle2 = get(name1);


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
                newHead = new BroomHead(vehicle, vehicle.getLane());
            }
            case Thrower -> {
                newHead = new ThrowerHead(vehicle, vehicle.getLane());
            }
            case IceBreaker -> {
                newHead = new IceBreakerHead(vehicle, vehicle.getLane());
            }
            case SaltSpreader -> {
                // newHead = new SaltSpreaderHead(vehicle, vehicle.getLane());
            }
            case Dragon -> {
                //newHead = new DragonHead(vehicle, vehicle.getLane());
            }
            case GravelSpreader -> {
                //newHead = new GravelSpreaderHead(vehicle, vehicle.getLane());
            }
            default -> throw new IllegalStateException("Unexpected value: " + head);
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

    }

    public void purchase(String playerName, BuyType type){
        Player player = get(playerName);

        switch (type){
            case Thrower -> {
            }
            case IceBreaker -> {
            }
            case SaltSpreader -> {
            }
            case Dragon -> {
            }
            case GravelSpreader -> {
            }
            case Salt -> {
            }
            case Kerosene -> {
            }
            case Gravel -> {
            }
            case Snowplow -> {
            }
            case Bus -> {
            }
        }
    }

    public void freeVehicle(String name){
        Vehicle vehicle = get(name);
        vehicle.getLane().removeLaneState(vehicle.getLane().getLaneStates().stream().filter(BlockedState.class::isInstance).findFirst().get());
    }

    public void scoreCleaner(String name){
        CleanerPlayer cleaner = get(name);
        Match match = get(Match.class);

        match.getResultFor(cleaner);
    }
}
