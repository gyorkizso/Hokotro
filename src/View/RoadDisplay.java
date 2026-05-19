package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RoadDisplay extends JComponent {
    Road road;

    IntersectionDisplay i1;
    IntersectionDisplay i2;

    public RoadDisplay(String line, RoadNetwork network, List<IntersectionDisplay> intersections){
        String[] words = line.split(" ");

         i1 =
         intersections.stream().filter((i) -> i.getIntersection().getId().equals(words[1])).findFirst().get();;
         i2 =
         intersections.stream().filter((i) -> i.getIntersection().getId().equals(words[2])).findFirst().get();

         boolean isTunnel = false;
        if (words.length > 4) {
            if (words[4].equals("t")) {
                isTunnel = true;
            }
        }

        road = new Road(i1.getIntersection(), i2.getIntersection());
        for (int i = 0; i < Integer.parseInt(words[3]); i++) {
            Lane lane = new Lane(road, isTunnel);
            lane.addLaneState(new ClearState(lane));
            road.addLane(lane);

        }

        i1.getIntersection().connectRoad(road);
        i2.getIntersection().connectRoad(road);

        network.addRoad(road);

        setLocation(Math.min(i1.getX(), i2.getX()), Math.min(i1.getY(), i2.getY()));
        setSize(Math.abs(i1.getX()-i2.getX()) + i2.getWidth(), Math.abs(i1.getY() - i2.getY()) + i2.getHeight());
    }

    public Road getRoad(){
        return road;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //normálvektor kiszámítása irányvektorból
        double n1 = i1.getY() - i2.getY();
        double n2 = i2.getX() - i1.getX();

        //normalizálás
        double length = Math.sqrt(n1*n1 + n2*n2);
        n1 = n1/length;
        n2 = n2/length;

        //kereszteződések középpontjainak meghatározása az út kordinátájához relatívan
        Point center1 = new Point(i1.getX() - getX() + i1.getWidth()/2,  i1.getY()- getY() + i1.getHeight()/2);
        Point center2 = new Point( i2.getX() - getX() + i2.getWidth()/2,  i2.getY() - getY() + i2.getHeight()/2);

        if (road.getLanes().getFirst().isTunnel()){
            Polygon tunnel = new Polygon();
            tunnel.addPoint((int) (center1.x + i1.getWidth()/2 * n1), (int) (center1.y + i1.getHeight()/2 * n2));
            tunnel.addPoint((int) (center2.x + i1.getWidth()/2 * n1), (int) (center2.y + i1.getHeight()/2 * n2));
            tunnel.addPoint((int) (center2.x - i1.getWidth()/2 * n1), (int) (center2.y - i1.getHeight()/2 * n2));
            tunnel.addPoint((int) (center1.x - i1.getWidth()/2 * n1), (int) (center1.y - i1.getHeight()/2* n2));
            g.setColor(Color.darkGray);
            g.fillPolygon(tunnel);
        }

        int lanes = road.getLanes().size();
        double spacing = (double) i1.getWidth() / Math.max(4, lanes);
        double offset = -spacing/2;
        if (lanes % 2 == 1){
            drawLane(g, road.getLanes().get(lanes-1), n1,n2,center1.x,center1.y,center2.x,center2.y);
            lanes--;
            offset = 0;
        }

        for (int i = 1; i <= lanes/2; i++) {
            double posX = n1 * ((i) * spacing + offset);
            double posY = n2 * ((i) * spacing + offset);
            drawLane(g, road.getLanes().get(2*i-1),n1,n2,center1.x + (int) posX, center1.y + (int) posY, center2.x + (int) posX, center2.y + (int) posY);
            posX *= -1;
            posY *= -1;
            drawLane(g, road.getLanes().get(2*i-2), n1,n2,center1.x + (int) posX, center1.y + (int) posY, center2.x + (int) posX, center2.y + (int) posY);
        }
    }

    void drawLane(Graphics g, Lane l, double n1, double n2, int x1, int y1, int x2, int y2){
        Color color = null;
        switch (l.getLaneStates().getFirst().getClass().getSimpleName()){
            case "SnowyState":
                color = Color.gray;
                break;
            case "SnowdriftState":
                color = Color.WHITE;
                break;
            case "IceSheetState":
                color = Color.BLUE;
                break;
            default:
                color = Color.BLACK;
                break;
        }
        if (l.getLaneStates().stream().anyMatch((state) -> state instanceof BlockedState)){
            color = Color.RED;
        }
        g.setColor(color);
        double thickness = 2.0;

        Polygon lane = new Polygon();
        lane.addPoint(x1 + (int) (n1 * thickness), y1 + (int) (n2 * thickness));
        lane.addPoint(x2 + (int) (n1 * thickness), y2 + (int) (n2 * thickness));
        lane.addPoint(x2 - (int) (n1 * thickness), y2 - (int) (n2 * thickness));
        lane.addPoint(x1 - (int) (n1 * thickness), y1 - (int) (n2 * thickness));
        g.fillPolygon(lane);
    }

    public void addVehicle(VehicleDisplay vehicleDisplay, int lane) {
        add(vehicleDisplay);
        //TODO: vehicleDisplay elhelyezése a megfelelő sávba
    }
}
