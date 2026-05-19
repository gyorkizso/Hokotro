package View;

import Model.*;

public class VehicleDisplayFactory implements VehicleVisitor {
    RoadDisplay location;
    VehicleDisplay display;

    public VehicleDisplayFactory(RoadDisplay location){
        this.location = location;
    }

    public VehicleDisplay getDisplay(Vehicle vehicle){
        vehicle.accept(this);
        return display;
    }

    @Override
    public void visit(Car vehicle) {
        display = new CarDisplay(location);
    }

    @Override
    public void visit(Bus vehicle) {
        display = new BusDisplay(location);
    }

    @Override
    public void visit(Snowplow vehicle) {
        display = new SnowplowDisplay(location);
    }
}
