import java.util.ArrayList;
import java.util.List;

public abstract class Player{
    protected ChessColor color;
    private int score;
    private King king;
    public boolean isCheck;
    public boolean isCheckMate;

    public Player(ChessColor color){
        this.color = color;
    }

    public ChessColor getColor(){
	return color;
    }

    public int getScore(){
	return score;
    }

    public void addToScore(int value){
        score += value;
    }
    
    public void removeFromScore(int value){
        score -= value;
    }
    
    public abstract FromTo getFromTo();

    public Piece getKing(){
	return null;
    }
    
    public void setKing(King king){
    }
    
    public boolean isCheckMate(Board board){
	return false;
    }

    public void setCheck(){
    }

    public void unSetCheck(){
    }



    //returns a move to play by the player
    public abstract Move getMove();

    // returns a list of all legal movements for the player
    public List<Move> getAllMoves(Board board) {
	return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        Player other = (Player) o;

        if(this.color != other.color){
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
	return color.toString();
    }
}
