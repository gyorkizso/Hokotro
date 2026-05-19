package View;

import javax.swing.*;

public class VehicleDisplay extends JComponent {
    RoadDisplay location;

    public VehicleDisplay(RoadDisplay location){
        this.location = location;
        location.addVehicle(this,0);
    }
}
