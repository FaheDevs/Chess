public class Coordinates{
    private int x;
    private int y;
    
    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
	return x;
    }
    public int getY(){
	return y;
    }

    @Override
    public String toString(){
	return "("+x+", "+y+")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        Coordinates other = (Coordinates) o;

        if(this.x != other.x || this.y != other.y){
            return false;
        }
        return true;
    }
}
