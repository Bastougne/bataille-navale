package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip {
    BattleShip( Orientation orientation ) {
        super( "Battle Ship", 'B', 4, orientation );
    }

    BattleShip() {
        this( Orientation.EAST );
    }
}
