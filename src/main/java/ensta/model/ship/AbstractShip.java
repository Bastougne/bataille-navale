package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip {
    protected Character label;
    protected String name;
    protected int size;
    protected Orientation orientation;

    public Character getLabel() { return label; }
    public String getName() { return name; }
    public int getLength() { return size; }
    public Orientation getOrientation() { return orientation; }

    public void setOrientation( Orientation orientation ) {
        this.orientation = orientation;
    }

    public AbstractShip( String name, Character label, int size, Orientation orientation ) {
        this.orientation = orientation;
    }
}
