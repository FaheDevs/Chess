import java.util.Stack;

public class GameUI {
    public Board board;
    private Player white;
    private Player black;
    private Player currentPlayer;
    private ChessUI ui;
    private Stack<Move> history;
    int boardValue;

    public GameUI(ChessUI ui, String boardConfigFileName, Player white, Player black){
        this.board = new Board(boardConfigFileName, white, black);
        this.white = white;
        this.black = black;
        this.currentPlayer = white;
        this.ui = ui;
        this.boardValue = board.getBoardValue();
        this.history = new Stack<Move>();
        for(Piece p : board.getPieces()) {
            this.ui.placePiece(p.getType(), p.getColor(), p.getPosition());
        }
    }

    public Board getBoard(){
        return board;
    }
    public boolean undo(){
        return true;
    }

    // returns true if move can  be played i.e if it's legal and if other conditions are verified
    // conditions ( is the right player playing , playing with his pieces , the move is not being done with the king ....)
    public boolean isMovePlayable(Move gameMove){
        Piece p = board.getPiece(gameMove.origin);
        if(p.getOwner() != currentPlayer) return false;
        return p.isMoveAuthorized(board,gameMove.destination);
    }

    // plays the move undos the last move
    public void applyMove(Move move){
        Piece p = board.getPiece(move.origin);
        ui.placePiece(p.getType(), p.getColor(), move.destination);
        ui.removePiece(move.origin);
        board.emptyCell(move.origin);
        p.setPosition(move.destination);
        board.addPiece(p);
        System.out.println(board);
    }
    // switches the players if black becomes white and vice versa
    public void switchPlayers(){
        if(currentPlayer.color == ChessColor.WHITE){
            currentPlayer = black;
        }else currentPlayer = white;
    }

    public Player getOpponent(Player player){

        if(currentPlayer.color == ChessColor.WHITE){
            return  black;
        }else return white;
    }

    public int getScore(){
        int currentBoardValue = board.getBoardValue();
        int currentMoveScore = boardValue - currentBoardValue;
        boardValue = currentBoardValue;
        return currentMoveScore;

    }

    @Override
    public String toString(){
        return "GameUI";
    }
    // returns true if the prey can be took
    public boolean isPrey(Piece prey){
        return false;
    }
    // returns true if the player is check
    public boolean isCheck(Player player){
        return false;
    }
    // returns true if the player is in check mate
    public boolean isCheckMate(Player player){
        return false;
    }
    // returns the player how won or null if ex aequo ( equally)
    public void determineWinner(){
    }

    //  plays a game ,  a game consist of giving  the play succesively to the 2 players and check if a player is in check mate
    //  if arriving at 50 moves and none of the 2 players is in check mate the game stops
    public void play(){
        boolean flag = true;

        while(flag){
            Move move = currentPlayer.getMove();
           if(! isMovePlayable(move)) continue;

            applyMove(move);
            history.add(move);
            currentPlayer.addToScore(getScore());
            System.out.print(currentPlayer+":"+currentPlayer.getScore()+"\t");
            System.out.println(getOpponent(currentPlayer)+":"+getOpponent(currentPlayer).getScore());
            switchPlayers();
            if(history.size() == 50) flag = false;



        }

        System.out.println("###########GAME END##############");
        System.out.print(currentPlayer+":"+currentPlayer.getScore()+"\t");
        System.out.println(getOpponent(currentPlayer)+":"+getOpponent(currentPlayer).getScore());
        System.out.println(white.getScore()>black.getScore()? "{{{WHITE WON}}}":"{{{BLACK WON}}}");

        System.exit(0);

    }
}
