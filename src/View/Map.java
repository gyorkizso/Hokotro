package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map extends JPanel implements VehicleVisitor {
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

    public void visit(Car vehicle) {
        VehicleDisplay display = new CarDisplay();
        add(display);
    }

    public void visit(Bus vehicle) {
        VehicleDisplay display = new BusDisplay();
        add(display);
    }

    public void visit(Snowplow vehicle) {
        VehicleDisplay display = new SnowplowDisplay();
        add(display);
    }
}