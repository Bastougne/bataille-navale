package ensta.model.ship;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public void addStrike() { ship.addStrike(); }

    public boolean isStruck() { return struck; }

    public String toString() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_COLOUR = ( isStruck() ) ? "\u001B[31m" : "\u001B[30m";
        return ANSI_COLOUR + ship.getLabel() + ANSI_RESET;
    }

    public boolean isSunk() { return ship.isSunk(); }

    public AbstractShip getShip() { return ship; }

    public void setShip( AbstractShip ship ) { this.ship = ship; }
}
