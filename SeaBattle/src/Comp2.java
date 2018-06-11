
import java.util.Random;

/**
 * Computer intelligence
 */
public class Comp2 {

	private int[][] fieldArray;
	
	private int x, y;
	private int cleverLvl;
	Random rand;

	
	public Comp2(int[][] fieldArray, int lvl) {
		this.fieldArray = fieldArray;
		cleverLvl = lvl;
		this.rand = new Random();
	}

	
	private boolean checkAround(int x, int y) {
		if (checkUp(x, y))
			return true;
		if (checkDown(x, y))
			return true;
		if (checkRight(x, y))
			return true;
		if (checkLeft(x, y))
			return true;
		return false;
	}

	private boolean checkDown(int x, int y) {
		if (y < 9) {
			if (fieldArray[x][y + 1] != -1) {
				if (fieldArray[x][y + 1] == -2) {
					// this.x = x;
					//
					// this.y = y + 2;
					
					return checkDown(x,y+1);
				} else {
					this.x = x;
					this.y = y + 1;
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkUp(int x, int y) {
		if (y > 0) {
			if (fieldArray[x][y - 1] != -1) {
				if (fieldArray[x][y - 1] == -2) {
					// this.x = x;
					//
					// this.y = y - 2;
					return checkUp(x,y-1);
				} else {
					this.x = x;
					this.y = y - 1;
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkRight(int x, int y) {
		if (x < 9) {
			if (fieldArray[x + 1][y] != -1) {
				if (fieldArray[x + 1][y] == -2) {
					// if (x < 8 && fieldArray[x + 2][y] != -1 && fieldArray[x + 2][y] != -2) {
					// this.x = x + 2;
					// } else {
					// return false;
					// }
					// this.y = y;
					return checkRight(x+1,y);
				} else {
					this.x = x + 1;
					this.y = y;
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkLeft(int x, int y) {
		if (x > 0) {
			if (fieldArray[x - 1][y] != -1) {
				if (fieldArray[x - 1][y] == -2) {
					// if (x > 2 && fieldArray[x - 2][y] != -1 && fieldArray[x - 2][y] != -2) {
					// this.x = x - 2;
					// } else {
					// this.x = x + 1;
					// }
					// this.y = y;
					return checkLeft(x-1,y);
				} else {
					this.x = x - 1;
					this.y = y;
					return true;
				}
			}
		}

		return false;

	}
// try to make a shot
	public void tryShot() {
		x = rand.nextInt(10);
		y = rand.nextInt(10);
		if (cleverLvl == 1) {
			while (fieldArray[x][y] == -1 || fieldArray[x][y] == -2) {
				x = rand.nextInt(10);
				y = rand.nextInt(10);
			}
		}
		if (cleverLvl == 2) {
			boolean stop = true;
			
				for (int j = 0; j < 10; j++) {
					for (int i = 0; i < 10; i++) {
						if (fieldArray[j][i] == -2) {
							if (checkAround(j, i)) {
								stop=false;
								break;
							}
						}
					}
				
				if(stop) {
					x = rand.nextInt(10);
					y = rand.nextInt(10);
					while (fieldArray[x][y] == -1 || fieldArray[x][y] == -2) {
						x = rand.nextInt(10);
						y = rand.nextInt(10);
					}
					
				}
			}
		}
	}

	/**
	 * get X cordinat
	 * 
	 * @return
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * get Y cordinat
	 * 
	 * @return
	 */
	public int getY() {
		return this.y;
	}

}