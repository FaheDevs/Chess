import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Board{
    private Piece[][] array;
    
    public Board(String fileName, Player white, Player black){
	int pieceType;
	int col;
	int row;
	String nextWord;
	Player owner;
	
	this.array = new Piece[8][8];
	try {
	    File file = new File(fileName);
    	    if(file.exists()==false) {
		System.err.println("Error: Cannot find file "+ fileName);
		System.exit(1);		
            } 

	    Scanner in = new Scanner(file);
	    while(in.hasNext()) {
		if ((nextWord = in.nextLine()).length()>2) {
		    pieceType = nextWord.charAt(0);
		    col = nextWord.charAt(1)-'0';
		    row = nextWord.charAt(2)-'0';

		    owner = black;
		    if (pieceType >= 'a' && pieceType <= 'z')
    			owner = white;
		    switch(pieceType) {
		    case 'K' : case 'k' :  
    			{
    				this.addPiece(new King(col, row, owner)); break;
    			}

				case 'N' : case 'n' :
				{
					this.addPiece(new Knight(col, row, owner)); break;
				}
				case 'Q' : case 'q' :
				{
					this.addPiece(new Queen(col, row, owner)); break;
				}
				case 'B' : case 'b' :
				{
					this.addPiece(new Bishop(col, row, owner)); break;
				}
				case 'P' : case 'p' :
				{
					this.addPiece(new Pawn(col, row, owner)); break;
				}
				case 'R' : case 'r' :
				{
					this.addPiece(new Rook(col, row, owner)); break;
				}

		    }
	    	}	    	
	    }
	    in.close();
	}
	catch(FileNotFoundException e) {
	    System.err.println("Error file not found : "+e);
	    System.exit(1);	
	}
    }

    // returns a list of coordinates of all the board
    public List<Coordinates> getAllCoordinates(){
	return null;
    }

    // returns a list of all pieces that the player has
    public List<Piece> getPieces(Player player) {
    	List<Piece> pieces = new ArrayList<>();

    	for(Piece ar[] : array){
    		for(Piece p : ar){
    			if(p == null) continue;
    			if(p.getOwner().equals(player) ) pieces.add(p);
			}
		}


	return pieces;
    }

	// returns a list f all pieces on the board
    public List<Piece> getPieces() {
		List list = new ArrayList();
		for (Piece[] ar : array) {
			for(Piece p : ar){
				if(p == null) continue;
				list.add(p);
			}

		}

		return list;
    }

	// adds a piece to the board
    public void addPiece(Piece piece){
    	array[piece.getY()][piece.getX()] = piece;
    }

	// returns the piece on the position's coordinates
    public Piece getPiece(Coordinates position )
	{
	return array [position.getY()][position.getX()];
    }

	// gets i think the piece directly from the board
    public Piece getPiece(int x, int y){
	return array [y][x];
    }

    public Piece[][] getArray(){
    	return array;
	}

	public int getBoardValue(){
    	int boardValue = 0;
    	for(Piece p : getPieces()){
    		boardValue += p.getValue();
		}
    	return  boardValue;
	}

	//makes the cell empty i.e if there is a piece : it deletes the piece in the position
    public void emptyCell(Coordinates position){
    	if  (!isEmptyCell(position)){
    		array[position.getY()][position.getX()]=null;
		}
    }

    // returns true if the position is empty
    public boolean isEmptyCell(Coordinates pos){

    	return array[pos.getY()][pos.getX()] == null;
    }

    // same as the one before
    public boolean isEmptyCell(int x, int y){
	return  array[y][x] == null;
    }

  // returns true if origin and destination are on the same column and if there are no pieces between the origin and destination
    public boolean sameColumnNothingBetween(Coordinates origin, Coordinates destination){
    	Boolean check = false;
    	// a for loop between origin and destination to check if is  empty
		for (int i =origin.getY();i<destination.getY();i++) {
			if (array[i][origin.getX()] == null) {
				check = true;
			}
		}
		return check;

	}
    //same thing with rows
    public boolean sameRowNothingBetween(Coordinates origin, Coordinates destination){
	return false;
    }
	// same thing with diagonal
    public boolean sameDiagonalNothingBetween(Coordinates origin, Coordinates destination){
	return false;
    }

    @Override
	public String toString(){
    	StringBuffer sb = new StringBuffer();
		for(Piece[] par : array){
			sb.append("|");

			for(Piece p : par ){
				if(p == null){
					sb.append("_|");
				}else{
					sb.append(p+"|");
				}
			}
			sb.append("\n");
		}


    	return  sb.toString();
	}
}
