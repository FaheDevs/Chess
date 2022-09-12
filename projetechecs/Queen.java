import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    public Queen(int x, int y, Player owner){
        super(x, y, owner);
    }

    public boolean isMoveAuthorized(Board board, Coordinates destination){
        if(destination.getX() < 0 || destination.getY() < 0 ||
                destination.getX() > 7 || destination.getY() >7) return false;
//        if(!(this.getX() == destination.getX() ^ this.getY() == destination.getY())) return false;
//        if( !(Math.abs(destination.getX() - this.getX()) == Math.abs(destination.getY() - this.getY()) )) {
//            return false;
//        }

        int flag = 0;
        if((this.getX() == destination.getX() ^ this.getY() == destination.getY())) flag++;
        if((Math.abs(destination.getX() - this.getX()) == Math.abs(destination.getY() - this.getY())))
            flag++;
        if(flag == 0) return false;



        List<Piece> boardPieces = board.getPieces();
        List<Coordinates> path = getPath(destination);
        for(Piece p :boardPieces){
            if(p == null) continue;
            if(path == null) continue;

            if(path.contains(p.position)) return false;
        }

        Piece destOccupant = board.getPiece(destination);
        if(destOccupant != null && destOccupant.getColor() != this.getColor()){
            return true;
        }else if(destOccupant == null ) {
            return  true;
        }else return   false;


//
//        List<Coordinates> playerCurrentPiecePlaces = new ArrayList<>();
//        List<Piece> pieces = board.getPieces(this.owner);
//
//
//        for(Piece p : pieces){
//            playerCurrentPiecePlaces.add(new Coordinates(p.getX(),p.getY()));
//        }
//
//        if(getPath(destination).contains(destination) &&
//                !playerCurrentPiecePlaces.contains(destination)){
//            return true;
//        }else{
//            return false;
//        }

    }
    public List<Coordinates> getPath(Coordinates dest){

        List<Coordinates> path = new ArrayList<>();
        //northEast
        if(dest.getY() < this.getY() && dest.getX() > this.getX()){
            int east = this.getX() +1;
            int north = this.getY()-1;
            while(east < dest.getX()){
                path.add(new Coordinates(east, north));
                east++;
                north--;

            }

            return path;
        }

        //northWest
        if(dest.getY() < this.getY() && dest.getX() < this.getX()){
            int west = this.getX() -1;
            int north = this.getY()-1;
            while(west > dest.getX()){
                path.add(new Coordinates(west, north));
                west--;
                north--;
            }
            return path;
        }

        //soutEast
        if(dest.getY() > this.getY() && dest.getX() > this.getX()){
            int east = this.getX() +1;
            int south = this.getY()+1;
            while(east < dest.getX()){
                path.add(new Coordinates(east, south));
                east++;
                south++;
            }
            return path;
        }


        //southWest
        if(dest.getY() > this.getY() && dest.getX() < this.getX()) {
            int south = this.getY()+1;
            int west = this.getX()-1;
            while(south < dest.getY() ){
                path.add(new Coordinates(west, south));
                west--;
                south++;
            }
            return path;
        }

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
        return Piece.Type.QUEEN;
    }

    @Override
    public int getValue() {
        return 9;
    }
    @Override
    public String toString(){
        String printColor = "\033[1;97m";
        if(owner.getColor() == ChessColor.BLACK){
            printColor = "\033[1;91m";
        }

        return printColor + "\u265B" + "\u001B[0m";
    }

}