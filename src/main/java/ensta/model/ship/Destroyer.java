package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {
    Destroyer( Orientation orientation ) {
        super( "Destroyer", 'D', 2, orientation );
    }

    Destroyer() {
        this( Orientation.EAST );
    }
}
