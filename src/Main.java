import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Prototype prototype;

    static PrintStream outStream;

    static List<String> state;

    public static void reset(){
        new Skeleton();
        prototype = new Prototype();
        state = new ArrayList<>();
    }

    public static void help(){
        System.out.println("Használható parancsokért lásd a dokumentáció 7.1.2 fejezetét.");
    }

    public static void save(String fileName){
        try {
            PrintStream file = new PrintStream(new FileOutputStream(fileName));
            for (String line : state){
                file.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Kimeneti fájl nem hozható létre");
        }
    }

    public static InputStream load(String fileName){
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Bemeneti fájl nem létezik.");
        }
        return null;
    }

    public static void main(String[] args){
        reset();

        InputStream start = System.in;

        String outFile = "";
        if (args.length > 1) {
            InputStream stream = load(args[0]);
            if (stream != null){
                start = stream;
            }
            outFile = args[1];
        }
        else if (args.length > 0) {
            outFile = args[0];
        }

        List<OutputStream> streams = new ArrayList<>();
        streams.add(System.out);

        try {
            streams.add((new FileOutputStream(outFile)));
        } catch (FileNotFoundException e) {
            System.out.println("Kimeneti fájl nem hozható létre.");
        }
        
        outStream = new PrintStream(new CombinedOutputStream(streams));
        Skeleton.instance.outStream = outStream;

        execute(start);
    }
    static void execute(InputStream stream){
        Scanner scanner = new Scanner(stream);
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            String command = words[0].toLowerCase();

            if (command.equals("exit")){
                break;
            }

            switch (command) {
                case "create_bus":
                    prototype.createBus(words[1]);
                    break;
                case "create_snowplow":
                    prototype.createSnowplow(words[1]);
                    break;
                case "create_player":
                    prototype.createPlayers(Integer.parseInt(words[1]), Integer.parseInt(words[2]), words[3]);
                    break;
                case "create_car":
                    prototype.createCar(words[1], words[2]);
                    break;
                case "create_match":
                    prototype.createMatch();
                    break;
                case "create_intersection":
                    prototype.createIntersection();
                    break;
                case "create_road":
                    prototype.createRoad(words[1], words[2], Integer.parseInt(words[3]));
                    break;
                case "create_garage":
                    prototype.createGarage(words[1]);
                    break;
                case "create_route":
                    prototype.createRoute(words[1], words[2], words[3]);
                    break;
                case "load":
                    execute(load(words[1]));
                    break;
                case "save":
                    save(words[1]);
                    break;
                case "reset":
                    reset();
                    break;

                case "help":
                    help();
                    break;

                case "next_turn":
                    prototype.nextTurn();
                    break;

                case "pass":
                    prototype.pass();
                    break;

                case "set_random":
                    prototype.setRandom(RandomType.valueOf(words[1]), Boolean.parseBoolean(words[2]));
                    break;

                case "start_match":
                    prototype.startMatch();
                    break;

                case "set":
                    prototype.set(SetType.valueOf(words[1]), words[2], words[3]);
                    break;

                case "print":
                    if (words.length > 2){
                        prototype.print(PrintType.valueOf(words[1]), words[2]);
                    }
                    else {
                        prototype.print(PrintType.valueOf(words[1]));
                    }
                    break;

                case "snowfall":
                    prototype.snowfall(Integer.parseInt(words[1]));
                    break;

                case "move_vehicle":
                    prototype.moveVehicle(words[1], words[2]);
                    break;

                case "use_head":
                    prototype.useHead(words[1], HeadType.valueOf(words[2]));
                    break;

                case "handle_collision":
                    prototype.handleCollision(words[1], words[2]);
                    break;

                case "remove_obstacle":
                    prototype.removeObstacle(words[1]);
                    break;

                case "change_plow_head":
                    prototype.changePlowHead(words[1], HeadType.valueOf(words[2]));
                    break;

                case "car_route":
                    prototype.carRoute(words[1]);
                    break;

                case "change_lane":
                    prototype.changeLane(words[1]);
                    break;

                case "complete_bus_route":
                    prototype.completeBusRoute(words[1]);
                    break;

                case "visit_stop":
                    prototype.visitStop(words[1], words[2]);
                    break;

                case "vehicle_stuck":
                    prototype.vehicleStuck(words[1]);
                    break;

                case "purchase":
                    prototype.purchase(words[1], BuyType.valueOf(words[2]));
                    break;

                case "free_vehicle":
                    prototype.freeVehicle(words[1]);
                    break;

                case "score_cleaner":
                    prototype.scoreCleaner(words[1]);
                    break;
                default:
                    outStream.println("Unknown command");
                    break;
            }
            state.add(line);
        }
    }
}