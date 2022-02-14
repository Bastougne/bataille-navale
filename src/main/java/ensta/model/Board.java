package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class Board implements IBoard {

    private static final int DEFAULT_SIZE = 10;

    private String name;
    private int size;
    private Character[][] myBoats;
    private boolean[][] myHits;

    public Board( String name, int size ) {
        this.name = name;
        this.size = size;
        myBoats = new Character[size][size];
        myHits = new boolean[size][size];
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; i < size; i++ ) {
                myBoats[i][j] = '\u0000';
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
                System.out.print( myBoats[i][j] + ' ' );
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
            myBoats[iCoords.getX()][iCoords.getY()] = ship.getLabel();
            iCoords.setX( iCoords.getX() + dx );
            iCoords.setY( iCoords.getY() + dy );
        }

        return true;
    }

    public boolean hasShip( Coords coords ) {
        return myBoats[coords.getX()][coords.getY()] != '\u0000';
    }

    public void setHit( boolean hit, Coords coords ) {
        myHits[coords.getX()][coords.getY()] = hit;
    }

    public Boolean getHit( Coords coords ) { return myHits[coords.getX()][coords.getY()]; }

    public Hit sendHit( Coords res ) {
        if ( !hasShip( res ) ) {
            return Hit.MISS;
        } else if ( myBoats[res.getX()][res.getY()] == 'D' ) {
            return Hit.DESTROYER;
        } else if ( myBoats[res.getX()][res.getY()] == 'S' ) {
            return Hit.SUBMARINE;
        } else if ( myBoats[res.getX()][res.getY()] == 'B' ) {
            return Hit.BATTLESHIP;
        } else if ( myBoats[res.getX()][res.getY()] == 'C' ) {
            return Hit.CARRIER;
        } else {
            return Hit.STRIKE;
        }
