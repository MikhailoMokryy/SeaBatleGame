import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

	public int[][] fieldArray1;
	public int[][] fieldArray2;
	public ArrayList<Ship> shipArray1 = new ArrayList<Ship>();
	public ArrayList<Ship> shipArray2 = new ArrayList<Ship>();
	private int[] sizeShips = {4,3,3,2,2,2,1,1,1,1};
	private int hitcount1;
	private int hitcount2;


	public GameLogic() {
		fieldArray1 = new int[10][10];
		fieldArray2 = new int[10][10];
		
	}

	public void startGame() {
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				fieldArray1[i][k] = 0; 
				fieldArray2[i][k] = 0;
			}
		}
		 hitcount1 = 0;
		 hitcount2 = 0;
	}

	public void addShipsOnDeck(Ship sh, int[][] fieldArray) {
		
		if (sh.isHorizontal()) {
			for (int i = 0; i < sh.getDeckNum(); i++) {
				fieldArray[sh.getXcor()][sh.getYcor() + i] = sh.getDeckNum();
			}
		}
		else if (!sh.isHorizontal()) {
			for (int i = 0; i < sh.getDeckNum(); i++) {
				fieldArray[sh.getXcor() + i][sh.getYcor()] = sh.getDeckNum();
			}
		}
		if (fieldArray.equals(fieldArray1))
			shipArray1.add(sh);
		else if  (fieldArray.equals(fieldArray2))
			shipArray2.add(sh);
	}

	// Checking are other ships directly around of ship
	private boolean isShipAround(int row, int column, int[][] fieldArray) {
		boolean isShip = false;
		if ((row > 0) && (fieldArray[row - 1][column] > 0))
			isShip = true;
		else if ((row > 0) && (column < 9) && (fieldArray[row - 1][column + 1] > 0))
			isShip = true;
		else if ((column < 9) && (fieldArray[row][column + 1] > 0))
			isShip = true;
		else if ((row < 9) && (column < 9) && (fieldArray[row + 1][column + 1] > 0))
			isShip = true;
		else if ((row < 9) && (fieldArray[row + 1][column] > 0))
			isShip = true;
		else if ((row < 9) && (column > 0) && (fieldArray[row + 1][column - 1] > 0))
			isShip = true;
		else if ((column > 0) && (fieldArray[row][column - 1] > 0))
			isShip = true;
		else if ((row > 0) && (column > 0) && (fieldArray[row - 1][column - 1] > 0))
			isShip = true;
		return isShip;
	}

	// Input coordinates of ship manually or random
	public void fillField(int[][] fieldArray, boolean isRandom) {
		boolean isRepeat;
		int row = 0, column = 0;
		boolean orientation = false;
		Random rand = new Random();

		for (int i = 0; i < 10; i++) {

			
			if (!isRandom) {
			}

			isRepeat = true;
			do {
				if (!isRandom) {
					// game with another player
				} else {
					row = rand.nextInt(10);
					column = rand.nextInt(10);
					orientation = rand.nextBoolean();
				}
				if (setShip(i,row, column, orientation, fieldArray))
					isRepeat = false;

			} while (isRepeat);
		}
	}

	// Checking of opportunity to set ship by coordinates by row or column
	private boolean setShip(int index, int row, int column, boolean orientation, int[][] fieldArray) {
		boolean isSet = true;
		if (fieldArray[row][column] > 0)
			isSet = false;
		else if (orientation)
			for (int i = 0; i < getSizeShips()[index]; i++)
				if ((column + i >= 10) || (isShipAround(row, column + i, fieldArray)))
					isSet = false;
				else
					;
		else if (!orientation)
			for (int i = 0; i < getSizeShips()[index]; i++)
				if ((row + i >= 10) || (isShipAround(row + i, column, fieldArray)))
					isSet = false;
				else
					;
		if (isSet) {
			addShipsOnDeck(new Ship(row, column, getSizeShips()[index], orientation), fieldArray);
		}
		return isSet;
	}


	
	public void sendShoot(int row, int column, int[][] fieldArray) {
		ArrayList<Ship> shipArray = new ArrayList<Ship>();

		int field = 0;
		if (fieldArray.equals(fieldArray1)) {
			shipArray= shipArray1;
			field = 1;
		} else if (fieldArray.equals(fieldArray2)) {
			shipArray=shipArray2;
			field = 2;
		}
		
		for (Ship s : shipArray) {

			if(!s.isDead()&&fieldArray[row][column]==s.getDeckNum()) {
			if (s.isHorizontal()) {
				for (int i = 0; i < s.getDeckNum(); i++) {
					if (s.getXcor() == row && (s.getYcor() + i) == column&&fieldArray[s.getXcor()][s.getYcor() + i]==s.getDeckNum()) {
						
						if (s.getHealth() > 0) {
							s.setHealth(s.getHealth() - 1);     	
						}else {
							System.out.println(s.getDeckNum() + " - killed horizontal Field: " + field);
							setFieldAroundHorizontalShip(s, fieldArray);
						}
						countHits(field);
					}
				}
			}
			
			else if (!s.isHorizontal()) {
				for (int i = 0; i < s.getDeckNum(); i++) {
					if ((s.getXcor() + i) == row && s.getYcor() == column&&fieldArray[s.getXcor()+i][s.getYcor()]==s.getDeckNum()) {
					
						if (s.getHealth() > 0) {
							s.setHealth(s.getHealth() - 1);		   
						}else {
							System.out.println(s.getDeckNum() + " - killed vertical Field: " + field);
							setFieldAroundVerticalShip(s, fieldArray);
							}
						countHits(field);
						}    
					}
				}
			}
		}
	}


	private void setFieldAroundHorizontalShip(Ship s, int[][] fieldArray) {

		for (int i = 0; i < s.getDeckNum(); i++) {
			if(s.getXcor()>0&&fieldArray[s.getXcor()-1][s.getYcor()+i] == 0)
				fieldArray[s.getXcor()-1][s.getYcor()+i] = -1;
			if(s.getXcor()<9&&fieldArray[s.getXcor()+1][s.getYcor()+i] == 0)
				fieldArray[s.getXcor()+1][s.getYcor()+i] = -1;
		
			if(s.getXcor()>0&&s.getYcor()>0&&fieldArray[s.getXcor()-1][s.getYcor()-1] == 0)
				fieldArray[s.getXcor()-1][s.getYcor()-1] = -1;
			if(s.getYcor()>0&&fieldArray[s.getXcor()][s.getYcor()-1] == 0)
				fieldArray[s.getXcor()][s.getYcor()-1] = -1;
			if(s.getXcor()<9&&s.getYcor()>0&&fieldArray[s.getXcor()+1][s.getYcor()-1] == 0)
				fieldArray[s.getXcor()+1][s.getYcor()-1] = -1;	
			if(s.getXcor()>0&&(s.getYcor()+s.getDeckNum())<10&&fieldArray[s.getXcor()-1][s.getYcor()+s.getDeckNum()] == 0)
				fieldArray[s.getXcor()-1][s.getYcor()+s.getDeckNum()] = -1;
			if((s.getYcor()+s.getDeckNum())<10&&fieldArray[s.getXcor()][s.getYcor()+s.getDeckNum()] == 0)
				fieldArray[s.getXcor()][s.getYcor()+s.getDeckNum()] = -1;
			if(s.getXcor()<9&&(s.getYcor()+s.getDeckNum())<10&&fieldArray[s.getXcor()+1][s.getYcor()+s.getDeckNum()] == 0)
				fieldArray[s.getXcor()+1][s.getYcor()+s.getDeckNum()] = -1;
			
		}
			
		
	}
	
	private void	setFieldAroundVerticalShip(Ship s, int[][] fieldArray) {
	
		for (int i = 0; i < s.getDeckNum(); i++) {
			if(s.getYcor()>0&&fieldArray[s.getXcor()+i][s.getYcor()-1] == 0)
				fieldArray[s.getXcor()+i][s.getYcor()-1] = -1;
			 if(s.getYcor()<9&&fieldArray[s.getXcor()+i][s.getYcor()+1] == 0)
				fieldArray[s.getXcor()+i][s.getYcor()+1] = -1;
       		
		    if(s.getXcor()>0&&s.getYcor()>0&&fieldArray[s.getXcor()-1][s.getYcor()-1] == 0)
				fieldArray[s.getXcor()-1][s.getYcor()-1] = -1;
			if(s.getXcor()>0&&fieldArray[s.getXcor()-1][s.getYcor()] == 0)
				fieldArray[s.getXcor()-1][s.getYcor()] = -1;
			if(s.getXcor()>0&&s.getYcor()<9&&fieldArray[s.getXcor()-1][s.getYcor()+1] == 0)
				fieldArray[s.getXcor()-1][s.getYcor()+1] = -1;	
			if((s.getXcor()+s.getDeckNum())<10&&s.getYcor()>0&&fieldArray[s.getXcor()+s.getDeckNum()][s.getYcor()-1] == 0)
				fieldArray[s.getXcor()+s.getDeckNum()][s.getYcor()-1] = -1;
			if((s.getXcor()+s.getDeckNum())<10&&fieldArray[s.getXcor()+s.getDeckNum()][s.getYcor()] == 0)
				fieldArray[s.getXcor()+s.getDeckNum()][s.getYcor()] = -1;
			if((s.getXcor()+s.getDeckNum())<10&&s.getYcor()<9&&fieldArray[s.getXcor()+s.getDeckNum()][s.getYcor()+1] == 0)
				fieldArray[s.getXcor()+s.getDeckNum()][s.getYcor()+1] = -1;
		}
	
	}
	
	private void countHits(int field) {
		if(hitcount1 == 20) System.out.println("Player 1 won!");
		if(hitcount2 ==  20) System.out.println("Player 2 won!");
		if (field ==1) ++hitcount1; 
		if (field ==2) ++hitcount2; 
	
		
	}
	public int[] getSizeShips() {
		return sizeShips;
	}
}
