package View;

import Model.*;
import View.RoadDisplay;
import View.SnowplowDisplay;
import View.VehicleDisplay;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map extends JPanel implements VehicleVisitor {
    public Map() {
        this.setLayout(null);
        setSize(600,600);
        try {
            Scanner scanner = new Scanner(new FileInputStream("level.txt"));
            RoadNetwork roadNetwork = new RoadNetwork();
            List<IntersectionDisplay> intersectionDisplayList = new ArrayList<>();

            while (scanner.hasNext()){
            String line = scanner.nextLine();
            if (line.startsWith("i ")){
                IntersectionDisplay intersectionDisplay = new IntersectionDisplay(line, roadNetwork);
                intersectionDisplayList.add(intersectionDisplay);
                add(intersectionDisplay);
            }
            else if (line.startsWith("r ")){
                RoadDisplay roadDisplay = new RoadDisplay(line, roadNetwork, intersectionDisplayList);
                add(roadDisplay);
            }
        }} catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void visit(Car vehicle){
        VehicleDisplay display = new CarDisplay();
        add(display);
    }

    public void visit(Bus vehicle){
        VehicleDisplay display = new BusDisplay();
        add(display);
    }

    public void visit(Snowplow vehicle){
        VehicleDisplay display = new SnowplowDisplay();
        add(display);
    }
}