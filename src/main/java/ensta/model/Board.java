package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;
import ensta.util.Orientation;
import java.util.Objects;

public class Board implements IBoard {

    private static final int DEFAULT_SIZE = 10;

    private String name;
    private int size;
    private ShipState[][] myBoats;
    private boolean[][] myHits;

    public String getName() { return name; }

    public Board( String name, int size ) {
        this.name = name;
        this.size = size;
        myBoats = new ShipState[size][size];
        myHits = new boolean[size][size];
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; i < size; i++ ) {
                myBoats[i][j] = new ShipState();
                myHits[i][j] = false;
            }
        }
    }

    public Board( String name ) {
        this( name, DEFAULT_SIZE );
    }

    public Character boolToChar( boolean bool ) {
        return ( bool ) ? '+' : '-';
    }

    public void print() {
        System.out.println( "Navires : Frappes :" );
        System.out.print( "   " );
        for ( int i = 0; i < size; i++ ) {
            System.out.print( (char)i + ' ' );
        }
        System.out.print( "       " );
        for ( int i = 0; i < size; i++ ) {
            System.out.print( (char)( 65 + i ) + ' ' );
        }
        System.out.println( '\n' );
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                System.out.print( myBoats[i][j].toString() + ' ' );
            }
            System.out.print( "    " );
            for ( int j = 0; j < size; j++ ) {
                System.out.print( boolToChar( myHits[i][j] ) + ' ' );
            }
            System.out.println( '\n' );
        }
    }

    public boolean canPutShip( AbstractShip ship, Coords coords ) {
        Orientation o = ship.getOrientation();
        int dx = 0, dy = 0;
        if ( o == Orientation.EAST ) {
            if ( coords.getX() + ship.getLength() >= this.size ) {
                return false;
            }
            dx = 1;
        } else if ( o == Orientation.SOUTH ) {
            if ( coords.getY() + ship.getLength() >= this.size ) {
                return false;
            }
            dy = 1;
        } else if ( o == Orientation.NORTH ) {
            if ( coords.getY() + 1 - ship.getLength() < 0 ) {
                return false;
            }
            dy = -1;
        } else if ( o == Orientation.WEST ) {
            if ( coords.getX() + 1 - ship.getLength() < 0 ) {
                return false;
            }
            dx = -1;
        }

        Coords iCoords = new Coords( coords );

        for ( int i = 0; i < ship.getLength(); ++i ) {
            if ( this.hasShip( iCoords ) ) {
                return false;
            }
            iCoords.setX( iCoords.getX() + dx );
            iCoords.setY( iCoords.getY() + dy );
        }

        return true;
    }

    public int getSize() { return size; }

    public boolean putShip( AbstractShip ship, Coords coords ) {
        if ( !canPutShip( ship, coords ) )
            return false;

        Orientation o = ship.getOrientation();
        int dx = 0, dy = 0;
        if ( o == Orientation.EAST ) {
            if ( coords.getX() + ship.getLength() >= this.size ) {
                return false;
            }
            dx = 1;
        } else if ( o == Orientation.SOUTH ) {
            if ( coords.getY() + ship.getLength() >= this.size ) {
                return false;
            }
            dy = 1;
        } else if ( o == Orientation.NORTH ) {
            if ( coords.getY() + 1 - ship.getLength() < 0 ) {
                return false;
            }
            dy = -1;
        } else if ( o == Orientation.WEST ) {
            if ( coords.getX() + 1 - ship.getLength() < 0 ) {
                return false;
            }
            dx = -1;
        }

        Coords iCoords = new Coords( coords );

        for ( int i = 0; i < ship.getLength(); ++i ) {
            myBoats[iCoords.getX()][iCoords.getY()].setShip( ship );
            iCoords.setX( iCoords.getX() + dx );
            iCoords.setY( iCoords.getY() + dy );
        }

        return true;
    }

    public boolean hasShip( Coords coords ) {
        return Objects.isNull( myBoats[coords.getX()][coords.getY()].getShip() );
    }

    public void setHit( boolean hit, Coords coords ) {
        myHits[coords.getX()][coords.getY()] = hit;
    }

    public Boolean getHit( Coords coords ) { return myHits[coords.getX()][coords.getY()]; }

    public Hit sendHit( Coords res ) {
        if ( !hasShip( res ) ) {
            return Hit.MISS;
        } else if ( myBoats[res.getX()][res.getY()].getShip().getLabel() == 'D' ) {
            return Hit.DESTROYER;
        } else if ( myBoats[res.getX()][res.getY()].getShip().getLabel() == 'S' ) {
            return Hit.SUBMARINE;
        } else if ( myBoats[res.getX()][res.getY()].getShip().getLabel() == 'B' ) {
            return Hit.BATTLESHIP;
        } else if ( myBoats[res.getX()][res.getY()].getShip().getLabel() == 'C' ) {
            return Hit.CARRIER;
        } else {
            return Hit.STRIKE;
        }
    }
}
