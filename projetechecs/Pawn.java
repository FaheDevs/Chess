import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(int x, int y, Player owner){
        super(x, y, owner);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination){
        if(destination.getX() < 0 || destination.getY() < 0 ||
                destination.getX() > 7 || destination.getY() >7) return false;
        // normal Black Pawn's move forward
        List<Coordinates> blackPawnMoves = new ArrayList<>();
        blackPawnMoves.add(new Coordinates(getX(),getY()-1));
        if(getY() == 6 || getY() == 1){
            blackPawnMoves.add(new Coordinates(getX(),getY()-2));
        }
        if(getOwner().getColor() == ChessColor.BLACK &&
                board.isEmptyCell(destination) &&
                blackPawnMoves.contains(destination)){
            return true;
        }
        // normal White Pawn's move forward

        List<Coordinates> whitePawnMoves = new ArrayList<>();
        whitePawnMoves.add(new Coordinates(getX(),getY()+1));
        if(getY() == 6 || getY() == 1){
            whitePawnMoves.add(new Coordinates(getX(),getY()+2));
        }
        if(getOwner().getColor() == ChessColor.WHITE &&
                board.isEmptyCell(destination) &&
                whitePawnMoves.contains(destination)){
            return true;
        }

        // white Pawn capturing opponent move
        List<Coordinates> whitePawnCapturing = new ArrayList<>();
        whitePawnCapturing.add(new Coordinates(getX()+1,getY()+1));
        whitePawnCapturing.add(new Coordinates(getX()-1,getY()+1));
        List<Coordinates> opponentCurrentPiecePlaces = new ArrayList<>();
        List<Piece> boardPieces = board.getPieces();
        for(Piece p : boardPieces){
            if(!(p.getOwner() == this.getOwner())){
                opponentCurrentPiecePlaces.add(new Coordinates(p.getX(),p.getY()));
            }
        }

        if(whitePawnCapturing.contains(destination) && opponentCurrentPiecePlaces.contains(destination)){

            return true;
        }

        // white Pawn capturing opponent move
        List<Coordinates> blackPawnCapturing = new ArrayList<>();
        blackPawnCapturing.add(new Coordinates(getX()+1,getY()-1));
        blackPawnCapturing.add(new Coordinates(getX()-1,getY()-1));

        if(blackPawnCapturing.contains(destination) && opponentCurrentPiecePlaces.contains(destination)){

            return true;
        }

        return false;

    }



    @Override
    public Piece.Type getType() {
        return Type.PAWN;
    }

    @Override
    public int getValue() {
        return 1;
    }
    @Override
    public String toString(){
        String printColor = "\033[1;97m";
        if(owner.getColor() == ChessColor.BLACK){
            printColor = "\033[1;91m";
        }

        return printColor + "\u265F" + "\u001B[0m";
    }

    public List<Coordinates> getPossibleMoves(){





        return null;
    }

}
