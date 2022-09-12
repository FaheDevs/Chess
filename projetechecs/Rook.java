import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{

    public Rook(int x, int y, Player owner){
        super(x, y, owner);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination){
        if(destination.getX() < 0 || destination.getY() < 0 ||
                destination.getX() > 7 || destination.getY() >7) return false;
        if(!(this.getX() == destination.getX() ^ this.getY() == destination.getY())) return false;

        List<Piece> boardPieces = board.getPieces();
        List<Coordinates> path = getPath(destination);
        for(Piece p :boardPieces){
            if(path.contains(p.position)) return false;
        }

        Piece destOccupant = board.getPiece(destination);
        if(destOccupant != null && destOccupant.getColor() != this.getColor()){
            return true;
        }else if(destOccupant == null ) {
            return  true;
        }else return   false;


    }

    public List<Coordinates> getPath(Coordinates dest){
        List<Coordinates> path = new ArrayList<>();

        //north
        if(this.getY() > dest.getY()){
            int north = this.getY() -1;
            while (north > dest.getY()){
                path.add(new Coordinates(dest.getX(), north));
                north--;
            }
            return path;
        }

        //south
        if(this.getY() < dest.getY()){
            int south = this.getY() +1;
            while (south < dest.getY()){
                path.add(new Coordinates(dest.getX(), south));
                south++;
            }
            return path;
        }

        //east
        if(this.getX() < dest.getX()){
            int east = this.getX() +1;
            while (east < dest.getX()){
                path.add(new Coordinates(east, dest.getY()));
                east++;
            }
            return path;
        }

        //west
        if(this.getX() > dest.getX()){
            int west = this.getX() - 1;
            while (west > dest.getX()){
                path.add(new Coordinates(west, dest.getY()));
                west--;
            }
            return path;
        }




        return null;
    }



    @Override
    public Piece.Type getType() {
        return Type.ROOK;
    }

    @Override
    public int getValue() {
        return 5;
    }
    @Override
    public String toString(){
        String printColor = "\033[1;97m";
        if(owner.getColor() == ChessColor.BLACK){
            printColor = "\033[1;91m";
        }

        return printColor + "\u265C" + "\u001B[0m";
    }

}
