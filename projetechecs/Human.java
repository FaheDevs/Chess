public class Human extends Player{

    ChessUI ui;
    public Human(ChessUI ui, ChessColor color){
        super(color);
        this.ui = ui;
    }

    @Override
    public FromTo getFromTo() {
//        FromTo t = ui.waitForPlayerMove();
//        System.out.println("GOT IT!");
//        System.out.println(t.getTo());
//        return t;
        return ui.waitForPlayerMove();
    }


    // all  the  get  move method  does in this class is that it :
    //waits for the player  to move the mouse to indicate a movement
    // it works with waitForPlayerMove() in  class ChessUI
    @Override
    public Move getMove() {


return new Move(null,getFromTo());
    }

}
