public class Map extends JComponent implements VehicleVisitor {
    public Map(Match match){
        for (Intersection intersection : match.getNetwork().getIntersections()){
            IntersectionDisplay display = new IntersectionDisplay();
            addComponent(display);
        }

        for (Road road : match.getNetwork().getRoads()){
            RoadDisplay display = new RoadDisplay();
            addComponent(display);

        }

        for (Vehicle vehicle : match.getVehicles()){
            vehicle.visit(this);
        }
    }

    public void visit(Car vehicle){
            VehicleDisplay display = new CarDisplay();
            addComponent(display);
    }

    public void visit(Bus vehicle){
            VehicleDisplay display = new BusDisplay();
            addComponent(display);
    }

    public void visit(Snowplow vehicle){
            VehicleDisplay display = new SnowplowDisplay();
            addComponent(display);
    }
}