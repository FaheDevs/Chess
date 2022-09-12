import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(int x, int y, Player owner){
        super(x, y, owner);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination){
        if(destination.getX() < 0 || destination.getY() < 0 ||
                destination.getX() > 7 || destination.getY() >7) return false;

        // if not part of the Bishop path return false;
        if( !(Math.abs(destination.getX() - this.getX()) == Math.abs(destination.getY() - this.getY()) )) {
            return false;
        }

        List<Piece> boardPieces = board.getPieces();
        List<Coordinates> path = getPath(destination);
        for(Piece p :boardPieces){
            if(p == null) continue;
            if(path == null) continue;
            if(path.contains(p.position)) return false;
        }
        Piece destOccupant = board.getPiece(destination);
        if(destOccupant != null && destOccupant.getColor() != this.getColor()){
            return true;
        }else if(destOccupant == null ) {
            return  true;
        }else return   false;



    }

    public List<Coordinates> getPossibleMoves(){
        List<Coordinates> moves = new ArrayList<>();
        //nothEast
        int east = this.getX() +1;
        int north = this.getY()-1;
        while(east < 8 && north > -1 ){
            moves.add(new Coordinates(east, north));
            east++;
            north--;
        }

        //southEast
        east = this.getX() +1;
        int south = this.getY()+1;
        while(east < 8 && south < 8 ){
            moves.add(new Coordinates(east, south));
            east++;
            south++;
        }

        //southWest
        south = this.getY()+1;
        int west = this.getX()-1;
        while(west > -1 && south < 8 ){
            moves.add(new Coordinates(west, south));
            west--;
            south++;
        }

        //nothWest
        west = this.getX() -1;
        north = this.getY()-1;
        while(west > -1 && north > -1 ){
            moves.add(new Coordinates(west, north));
            west--;
            north--;
        }

        return moves;

    }

    public List<Coordinates> getPath(Coordinates dest){

        List<Coordinates> path = new ArrayList<>();

        //northEast
        if(dest.getY() < this.getY() && dest.getX() > this.getX()){
            int east = this.getX() +1;
            int north = this.getY()-1;
            while(east < dest.getX()){
                path.add(new Coordinates(east, north));
                east++;
                north--;

            }

            return path;
        }

        //northWest
        if(dest.getY() < this.getY() && dest.getX() < this.getX()){
            int west = this.getX() -1;
            int north = this.getY()-1;
            while(west > dest.getX()){
                path.add(new Coordinates(west, north));
                west--;
                north--;
            }
            return path;
        }

        //soutEast
        if(dest.getY() > this.getY() && dest.getX() > this.getX()){
            int east = this.getX() +1;
            int south = this.getY()+1;
            while(east < dest.getX()){
                path.add(new Coordinates(east, south));
                east++;
                south++;
            }
            return path;
        }


        //southWest
        if(dest.getY() > this.getY() && dest.getX() < this.getX()) {
            int south = this.getY()+1;
            int west = this.getX()-1;
            while(south < dest.getY() ){
                path.add(new Coordinates(west, south));
                west--;
                south++;
            }
            return path;

        }


        return null;



    }
    @Override
    public Piece.Type getType() {
        return Type.BISHOP;
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public String toString(){
        String printColor = "\033[1;97m";
        if(owner.getColor() == ChessColor.BLACK){
            printColor = "\033[1;91m";
        }

        return printColor + "\u265D" + "\u001B[0m";
    }

}
