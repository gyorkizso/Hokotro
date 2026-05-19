package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map extends JPanel implements PlayerVisitor {
    Player perspectivePlayer;

    List<IntersectionDisplay> intersections = new ArrayList<>();
    List<RoadDisplay> roads = new ArrayList<>();

    public Map() {
        this.setLayout(null);
        setSize(1000, 1000);
        setBackground(Color.LIGHT_GRAY);
        try {
            Scanner scanner = new Scanner(new FileInputStream("level.txt"));
            RoadNetwork roadNetwork = new RoadNetwork();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.startsWith("i ")) {
                    IntersectionDisplay intersectionDisplay = new IntersectionDisplay(line, roadNetwork);
                    intersections.add(intersectionDisplay);
                    add(intersectionDisplay);
                } else if (line.startsWith("r ")) {
                    RoadDisplay roadDisplay = new RoadDisplay(line, roadNetwork, intersections);
                    roads.add(roadDisplay);
                    add(roadDisplay);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPerspective(Player player){
        perspectivePlayer = player;

        player.accept(this);
    }

    public void addVehicle(Vehicle vehicle, RoadDisplay location){
        VehicleDisplayFactory factory = new VehicleDisplayFactory(location);
        add(factory.getDisplay(vehicle));
    }

    @Override
    public void visit(BusDriverPlayer player) {
        BusRoute route = ((Bus)player.getVehicle()).getActiveRoute();
        for (IntersectionDisplay i : intersections){
             i.setHasStop(route.getRoutePoints().stream().anyMatch(point -> point.getLocation().equals(i.getIntersection())));
        }
    }

    @Override
    public void visit(CleanerPlayer player) {

    }
}