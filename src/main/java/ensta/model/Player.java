package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;
// import java.io.Serializable;
import java.util.List;

public class Player {
    /*
     * ** Attributs
     */
    private Board board;
    protected Board opponentBoard;
    private int destroyedCount;
    protected AbstractShip[] ships;
    private boolean lose;

    /*
     * ** Constructeur
     */
    public Player( Board board, Board opponentBoard, List<AbstractShip> ships ) {
        this.setBoard( board );
        this.ships = ships.toArray( new AbstractShip[0] );
        this.opponentBoard = opponentBoard;
    }

    /*
     * ** Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given
     * coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip ship = ships[i];
            String msg = String.format( "placer %d : %s(%d)", i + 1, ship.getName(), ship.getLength() );
            System.out.println( msg );
            InputHelper.ShipInput res = InputHelper.readShipInput();
            ship.setOrientation( Orientation.StringToOrientation( res.orientation ) );
            if ( board.putShip( ship, new Coords( res.x, res.y ) ) )
                ++i;
            done = i == 5;

            board.print();
        } while ( !done );
    }

    public Hit sendHit( Coords coords ) {
        boolean done = false;
        Hit hit = null;

        do {
            System.out.println( "où frapper?" );
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            this.board.sendHit( hitInput.x, hitInput.y );

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
        } while ( !done );

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips( AbstractShip[] ships ) {
        this.ships = ships;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard( Board board ) {
        this.board = board;
    }

    public int getDestroyedCount() {
        return destroyedCount;
    }

    public void setDestroyedCount( int destroyedCount ) {
        this.destroyedCount = destroyedCount;
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose( boolean lose ) {
        this.lose = lose;
    }
}
