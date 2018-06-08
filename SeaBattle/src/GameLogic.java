
public class GameLogic {
	
	
	public int[][] fieldArray1;
	public int[][] fieldArray2;
	
	public  GameLogic() {
		fieldArray1 = new int[10][10];
		fieldArray2 = new int[10][10];
	}
	
	public void startGame() {
		for(int i = 0; i<10; i++) {
			for(int k = 0; k<10; k++) {	
				fieldArray1[i][k] = 0;
				fieldArray2[i][k] = 0;
			}	
		}
//		fieldArray1[0][0] = 1;
		
		
	}
	
	public void addShips(Ship sh) {
		
		
		fieldArray1[sh.Xcor][sh.Ycor]=2;
	if(sh.horizontal==true) {
		for(int i = 0;i<sh.deckNum;i++) {
			fieldArray1[sh.Xcor][sh.Ycor+i]=2;
		}
	}
	if(sh.horizontal==false) {
		for(int i = 0;i<sh.deckNum;i++) {
			fieldArray1[sh.Xcor+i][sh.Ycor]=2;
		}
	}
}
}
