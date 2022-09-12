public class Main{

	public static GameUI g;
    public static void main(String[] args) {
    	ChessUI ui = new ChessUI();
	 g = new GameUI(ui, "boardConfigurationFiles/FullBoard.txt", new ChessBot(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));

	g.play();
    }

}
