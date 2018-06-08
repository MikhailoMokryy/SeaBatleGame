
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
//		fieldArray1[5][8] = 3;
//		fieldArray2[3][9] = 1;
//		fieldArray2[2][7] = 1;
	}
}
