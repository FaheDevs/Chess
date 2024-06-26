public class Move{
    final Coordinates origin;
    final Coordinates destination;
    final Piece pieceAtOrigin;
    final Piece pieceAtDestination;
    
    public Move(Board board, Coordinates origin, Coordinates destination){
	this.origin = origin;
	this.destination = destination;
	this.pieceAtOrigin = null;
	this.pieceAtDestination = null;
    }
    
    public Move(Coordinates origin, Coordinates destination, Piece pieceAtOrigin, Piece pieceAtDestination){
	this.origin = origin;
	this.destination = destination;
	this.pieceAtOrigin = pieceAtOrigin;
	this.pieceAtDestination = pieceAtDestination;
    }
    
    public Move(Board board, FromTo ft){
	this.origin = ft.getFrom();
	this.destination = ft.getTo();
	this.pieceAtOrigin = null;
	this.pieceAtDestination = null;
    }
}
    
