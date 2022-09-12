import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(int x, int y, Player owner){
        super(x, y, owner);
        owner.setKing(this);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination){
        if(destination.getX() < 0 || destination.getY() < 0 ||
                destination.getX() > 7 || destination.getY() >7) return false;

        List<Coordinates> kingMoves = new ArrayList<>();
        kingMoves.add(new Coordinates(getX()+1,getY()));//e
        kingMoves.add(new Coordinates(getX()-1,getY()));//w
        kingMoves.add(new Coordinates(getX(),getY()+1));//n
        kingMoves.add(new Coordinates(getX(),getY()-1));//s
        kingMoves.add(new Coordinates(getX()+1,getY()+1));//n.e
        kingMoves.add(new Coordinates(getX()+1,getY()-1));//s.e
        kingMoves.add(new Coordinates(getX()-1,getY()+1));//n.w
        kingMoves.add(new Coordinates(getX()-1,getY()-1));//s.w


        List<Coordinates> playerCurrentPiecePlaces = new ArrayList<>();
        List<Piece> pieces = board.getPieces(this.owner);

        for(Piece p : pieces){
            playerCurrentPiecePlaces.add(new Coordinates(p.getX(),p.getY()));
        }

        if(kingMoves.contains(destination) &&
                !playerCurrentPiecePlaces.contains(destination)){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public Type getType() {
        return Type.KING;
    }

    @Override
    public int getValue() {
        return 0;
    }


    @Override
    public String toString(){
        String printColor = "\033[1;97m";
        if(owner.getColor() == ChessColor.BLACK){
            printColor = "\033[1;91m";
        }

        return printColor + "\u265A" + "\u001B[0m";
    }
}