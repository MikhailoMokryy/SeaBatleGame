import java.util.ArrayList;
import java.util.Random;



public class GameLogic {
	
	
	public int[][] fieldArray1;
	public int[][] fieldArray2;
	public ArrayList <Ship> shipArray1 = new ArrayList <Ship>();
	public ArrayList <Ship> shipArray2 = new ArrayList <Ship>();
	
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
	}

	
public void addShipsOnDeck(Ship sh,int[][] fieldArray) {	
		fieldArray[sh.getXcor()][sh.getYcor()]=1;
	if(sh.isHorizontal()) {
		for(int i = 0;i<sh.getDeckNum();i++) {
		fieldArray[sh.getXcor()][sh.getYcor()+i]=1;
		}
	}
	if(!sh.isHorizontal()) {
		for(int i = 0;i<sh.getDeckNum();i++) {
		fieldArray[sh.getXcor()+i][sh.getYcor()]=1;
		}
	}
	if(fieldArray.equals(fieldArray1))
    shipArray1.add(sh);
	else shipArray2.add(sh);
}



	

    //Checking are other ships directly around of ship
private boolean isShipAround (int row, int column,int[][] fieldArray) {
		boolean isShip = false;
		if ((row > 0) && (fieldArray[row-1][column] > 0))
			isShip = true;
		else if ((row > 0) && (column < 9) && (fieldArray[row-1][column+1] > 0))
			isShip = true;
		else if ((column < 9) && (fieldArray[row][column+1] > 0))
			isShip = true;
		else  if ((row < 9) && (column < 9) && (fieldArray[row+1][column+1] > 0))
			isShip = true;
		else  if ((row < 9) && (fieldArray[row+1][column] > 0))
			isShip = true;
		else  if ((row < 9) && (column > 0) && (fieldArray[row+1][column-1] > 0))
			isShip = true;
		else  if ((column > 0) && (fieldArray[row][column-1] > 0))
			isShip = true;
		else  if ((row > 0) && (column > 0) && (fieldArray[row-1][column-1] > 0))
			isShip = true;
		return isShip;
	}



// Input coordinates of ship manually or random
public void fillField (int[][] fieldArray, boolean isRandom) {
	boolean isRepeat;
	int row = 0, column = 0,health  = 0;
	boolean orientation = false;
	Random rand = new Random();
	
	for (int i = 0; i < 10; i++) {
		
		health = getShipsSize(i);
		if (!isRandom) {}
		
		isRepeat = true;
		do {
			if (!isRandom) { 
				//game with another player
			}
			else {
				row = rand.nextInt(10);
				column = rand.nextInt(10);
				orientation = rand.nextBoolean();
			}
			if (setShip(row,column, health,orientation, fieldArray ))
				isRepeat = false;
			
		}
		while (isRepeat);
	}
}

// Checking of opportunity to set ship by coordinates by row or column
private boolean setShip (int row, int column,int health,  boolean orientation,int[][] fieldArray ) {
	boolean isSet = true;
	if (fieldArray[row][column] > 0)
		isSet = false;
	else if (orientation)
		for (int i = 0; i < health; i++)
			if ((column+i >= 10) || (isShipAround(row,column+i,fieldArray)))
				isSet = false;
			else;
	else if (!orientation)
		for (int i = 0; i < health; i++)
			if ((row+i >= 10) || (isShipAround(row+i,column,fieldArray)))
				isSet = false;
			else;
	if (isSet) {
	    addShipsOnDeck(new Ship(row,column,health, orientation), fieldArray);	
	}
	return isSet;
}

public void sendShoot(int row, int column,int[][] fieldArray) {
	           ArrayList <Ship> shipArray = new ArrayList <Ship>();
	           int field;
				if(fieldArray.equals(fieldArray1)) {
					shipArray = shipArray1;
					field = 1;
				}else {
					shipArray = shipArray2;
					field = 2;
				}
				
					for(Ship s : shipArray) {
						if(s.isHorizontal()) {
							for(int i = 0;i<s.getDeckNum();i++) {		
								if(s.getXcor()==row&&(s.getYcor()+i)==column) {
									if(s.getHealth()>1)
									s.setHealth(s.getHealth()-1);
									else { System.out.println(s.getDeckNum()+" - killed horizontal Field:" +field);	setFieldAroundShip (s, fieldArray); }
								}
							}
						}
						if(!s.isHorizontal()) {
							for(int i = 0;i<s.getDeckNum();i++) {				
								if((s.getXcor()+i)==row&&s.getYcor()==column) {
									if(s.getHealth()>1)
									s.setHealth(s.getHealth()-1);
									else {System.out.println(s.getDeckNum()+" - killed vertical Field:" +field);  setFieldAroundShip (s, fieldArray); }
								}
							}
						}
					}
}


private void setFieldAroundShip (Ship s, int[][] fieldArray) {
	
	  if(s.isHorizontal()) {
		for(int i = 0;i<s.getDeckNum();i++) {
			if ((s.getXcor() > 0) && (fieldArray[s.getXcor()-1][s.getYcor()] != -1))
				fieldArray[s.getXcor()-1][s.getYcor()+i] = -1;
			else if ((s.getXcor() > 0) && (s.getYcor() < 9) && (fieldArray[s.getXcor()-1][s.getYcor()+1] != -1))
				fieldArray[s.getXcor()-1][s.getYcor()+1+i] = -1;
			else if ((s.getYcor() < 9) && (fieldArray[s.getXcor()][s.getYcor()+1] != -1))
				fieldArray[s.getXcor()][s.getYcor()+1+i] = -1;
			else  if ((s.getXcor() < 9) && (s.getYcor() < 9) && (fieldArray[s.getXcor()+1][s.getYcor()+1] != -1))
				fieldArray[s.getXcor()+1][s.getYcor()+1+i] = -1;
			else  if ((s.getXcor() < 9) && (fieldArray[s.getXcor()+1][s.getYcor()] != -1))
				fieldArray[s.getXcor()+1][s.getYcor()+i] = -1;
			else  if ((s.getXcor() < 9) && (s.getYcor() > 0) && (fieldArray[s.getXcor()+1][s.getYcor()-1] != -1))
				fieldArray[s.getXcor()+1][s.getYcor()-1+i] = -1;
			else  if ((s.getYcor() > 0) && (fieldArray[s.getXcor()][s.getYcor()-1] != -1))
				fieldArray[s.getXcor()][s.getYcor()-1+i] = -1;
			else  if ((s.getXcor() > 0) && (s.getYcor() > 0) && (fieldArray[s.getXcor()-1][s.getYcor()-1] != -1))
				fieldArray[s.getXcor()-1][s.getYcor()-1+i] = -1;	
		}
	}
   	if(!s.isHorizontal()) {
		for(int i = 0;i<s.getDeckNum();i++) {
			if ((s.getXcor() > 0) && (fieldArray[s.getXcor()-1][s.getYcor()] != -1))
				fieldArray[s.getXcor()-1+i][s.getYcor()] = -1;
			else if ((s.getXcor() > 0) && (s.getYcor() < 9) && (fieldArray[s.getXcor()-1][s.getYcor()+1] != -1))
				fieldArray[s.getXcor()-1+i][s.getYcor()+1] = -1;
			else if ((s.getYcor() < 9) && (fieldArray[s.getXcor()][s.getYcor()+1] != -1))
				fieldArray[s.getXcor()+i][s.getYcor()+1] = -1;
			else  if ((s.getXcor() < 9) && (s.getYcor() < 9) && (fieldArray[s.getXcor()+1][s.getYcor()+1] != -1))
				fieldArray[s.getXcor()+1+i][s.getYcor()+1] = -1;
			else  if ((s.getXcor() < 9) && (fieldArray[s.getXcor()+1][s.getYcor()] != -1))
				fieldArray[s.getXcor()+1+i][s.getYcor()] = -1;
			else  if ((s.getXcor() < 9) && (s.getYcor() > 0) && (fieldArray[s.getXcor()+1][s.getYcor()-1] != -1))
				fieldArray[s.getXcor()+1+i][s.getYcor()-1] = -1;
			else  if ((s.getYcor() > 0) && (fieldArray[s.getXcor()][s.getYcor()-1] != -1))
				fieldArray[s.getXcor()+i][s.getYcor()-1] = -1;
			else  if ((s.getXcor() > 0) && (s.getYcor() > 0) && (fieldArray[s.getXcor()-1][s.getYcor()-1] != -1))
				fieldArray[s.getXcor()-1+i][s.getYcor()-1] = -1;	
		}
	}
	
		
	}

private int getShipsSize(int i) {
	if (i<=3) return 1;
	else if(i<=6) return 2;
	else if (i<=8) return 3;
	else return 4;
	
}

}
