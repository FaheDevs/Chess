import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChessBot extends Player {
    ChessUI ui;
    GameUI g;
    public ChessBot(ChessUI ui, ChessColor color){
        super(color);
        this.ui = ui;

    }


    @Override
    public FromTo getFromTo() {

        return new FromTo(getMove().origin.getX(),getMove().origin.getY(),
                getMove().destination.getX(),getMove().destination.getY());
    }

    @Override
    public Move getMove() {
        g = Main.g;
        g.getBoard();
        List<Move> possibleMoves = getAllMoves(g.getBoard());

        return possibleMoves.get(new Random().nextInt(possibleMoves.size()));
    }


    public List<Move> getAllMoves(Board board) {
        List<Piece> playerPieces = board.getPieces(this);
        List<Move> possibleMoves = new ArrayList<>();

        for(Piece p : playerPieces){
            List<Coordinates> pieceMoves = p.possibleMoves(board);
            for(Coordinates cord : pieceMoves){
                possibleMoves.add(new Move(p.position, cord,p,null));
            }
        }



        return possibleMoves;
    }
}
