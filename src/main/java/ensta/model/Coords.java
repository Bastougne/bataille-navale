package ensta.model;

public class Coords {
    private int x;
    private int y;

    public Coords( Coords coords ) {
        this.x = coords.x;
        this.y = coords.y;
    }

    public Coords() {
        x = 0;
        y = 0;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setX( int x ) { this.x = x; }

    public void setY( int y ) { this.y = y; }

    public boolean isValid( int size ) {
        return ( 0 < x && x < size && 0 < y && y < size ) ? true : false;
    }
}
