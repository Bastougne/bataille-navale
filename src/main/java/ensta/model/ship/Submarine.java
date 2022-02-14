package ensta.model.ship;

import ensta.util.Orientation;

public class Submarine extends AbstractShip {
    Submarine( Orientation orientation ) {
        super( "Submarine", 'S', 3, orientation );
    }

    Submarine() {
        this( Orientation.EAST );
    }
}
