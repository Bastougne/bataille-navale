package ensta.model.ship;

import ensta.util.Orientation;

public class Carrier extends AbstractShip {
    Carrier( Orientation orientation ) {
        super( "Carrier", 'C', 5, orientation );
    }

    Carrier() {
        this( Orientation.EAST );
    }
    
}
