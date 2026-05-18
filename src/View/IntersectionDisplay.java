package View;

import Model.Intersection;
import Model.RoadNetwork;

import javax.swing.*;
import java.awt.*;

public class IntersectionDisplay extends JComponent {
    public static int INTERSECTION_SIZE = 50;

    Intersection intersection;

    public IntersectionDisplay(String line, RoadNetwork network){
        String[] words = line.split(" ");

        intersection = new Intersection(Integer.toString(network.getIntersections().size()));

        network.addIntersection(intersection);

        setLocation(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
        setSize(INTERSECTION_SIZE,INTERSECTION_SIZE);
    }

    public Intersection getIntersection(){
        return  intersection;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillOval(0,0,INTERSECTION_SIZE,INTERSECTION_SIZE);
    }
}
