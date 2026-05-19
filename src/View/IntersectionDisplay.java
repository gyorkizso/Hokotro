package View;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class IntersectionDisplay extends JComponent {
    public static int INTERSECTION_SIZE = 50;

    Intersection intersection;

    boolean hasStop = false;

    boolean hasTerminal = false;

    public IntersectionDisplay(String line, RoadNetwork network) {
        String[] words = line.split(" ");

        intersection = new Intersection(Integer.toString(network.getIntersections().size()));
        if (words.length > 3) {
            if (words[3].equals("g")) {
                intersection.setGarage(new Garage(intersection));
            }
        }

        network.addIntersection(intersection);

        setLocation(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
        setSize((INTERSECTION_SIZE), (INTERSECTION_SIZE));
    }

    public Intersection getIntersection() {
        return intersection;
    }

    public void setHasStop(boolean val) {
        hasStop = val;
    }

    public void setHasTerminal(boolean val) {
        hasTerminal = val;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int innerSize = INTERSECTION_SIZE;
        if (intersection.hasGarage()) {
            innerSize = drawOuterRing(g, Color.orange, "Garázs");
        }
        if (hasStop) {
            innerSize = drawOuterRing(g, Color.green.brighter(), "Megálló");
        }
        if (hasTerminal) {
            innerSize = drawOuterRing(g, Color.green, "Végállomás");
        }
        g.setColor(Color.black);
        g.fillOval((INTERSECTION_SIZE - innerSize) / 2, (INTERSECTION_SIZE - innerSize) / 2, innerSize, innerSize);
    }

    int drawOuterRing(Graphics g, Color outerColor, String label) {
        g.setColor(outerColor);
        g.fillOval(0, 0, INTERSECTION_SIZE, INTERSECTION_SIZE);
        int innerSize = (INTERSECTION_SIZE / 4) * 3;
        g.setColor(Color.WHITE);
        g.drawString(label, 0, INTERSECTION_SIZE);
        return innerSize;
    }
}
