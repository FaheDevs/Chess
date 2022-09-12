import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(int x, int y, Player owner){
        super(x, y, owner);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination){
        if(destination.getX() < 0 || destination.getY() < 0 ||
                destination.getX() > 7 || destination.getY() >7) return false;

        List<Coordinates> knightMoves = new ArrayList<>();
        knightMoves.add(new Coordinates(getX()+1,getY()+2));
        knightMoves.add(new Coordinates(getX()+2,getY()+1));
        knightMoves.add(new Coordinates(getX()+2,getY()-1));
        knightMoves.add(new Coordinates(getX()+1,getY()-2));
        knightMoves.add(new Coordinates(getX()-1,getY()-2));
        knightMoves.add(new Coordinates(getX()-2,getY()-1));
        knightMoves.add(new Coordinates(getX()-2,getY()+1));
        knightMoves.add(new Coordinates(getX()-1,getY()+2));


        List<Coordinates> playerCurrentPiecePlaces = new ArrayList<>();
        List<Piece> pieces = board.getPieces(this.owner);

        for(Piece p : pieces){
            playerCurrentPiecePlaces.add(new Coordinates(p.getX(),p.getY()));
        }

        if(knightMoves.contains(destination) &&
                !playerCurrentPiecePlaces.contains(destination)){
         return true;
        }else{
            return false;
        }

    }


    @Override
    public String toString(){
        String printColor = "\033[1;97m";
        if(owner.getColor() == ChessColor.BLACK){
            printColor = "\033[1;91m";
        }

        return printColor + "\u265E" + "\u001B[0m";
    }



    @Override
    public Piece.Type getType() {
        return Type.KNIGHT;
    }

    @Override
    public int getValue() {
        return 3;
    }
}
