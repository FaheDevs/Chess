import java.util.ArrayList;
import java.util.List;

public abstract class Piece{
    protected Coordinates position;
    protected Player owner;
    
    public Piece(int x, int y, Player owner){
        this.position = new Coordinates(x,y);
        this.owner = owner;
    }

    public enum Type {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }

    public void setPosition(Coordinates destination){
                position = destination;
    }
    
    public Player getOwner(){

        return owner;
    }

    public ChessColor getColor(){
	return owner.getColor();
    }

    public Coordinates getPosition(){
	return position;
    }

    public int getX(){
	return position.getX();
    }
    
    public int getY(){
	return position.getY();
    }

    public List<Move> getAllMoves(Board board) {
	return null;
    }

    public boolean sameColor(Piece piece){
	return true;
    }



    public List<Coordinates> possibleMoves(Board board){
        List<Coordinates> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8 ; j++) {
                if(isMoveAuthorized(board, new Coordinates(i,j))){
                    moves.add(new Coordinates(i,j));
                }
            }
        }
        return moves;
    }

    public abstract boolean isMoveAuthorized(Board board, Coordinates destination);

    public abstract Type getType();
    public abstract int getValue();



    

}
