import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

	public int[][] fieldArray1;
	public int[][] fieldArray2;
	public int[][] chooserArray;
	public ArrayList<Ship> shipArray1;
	public ArrayList<Ship> shipArray2;
	private int[] sizeShips = { 4, 3, 3, 2, 2, 2, 1, 1, 1, 1 };

	public GameLogic() {
		fieldArray1 = new int[10][10];
		fieldArray2 = new int[10][10];
		chooserArray = new int[8][7];

	}

	public void startGame() {
		shipArray1 = new ArrayList<Ship>();
		shipArray2 = new ArrayList<Ship>();
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				fieldArray1[i][k] = 0;
				fieldArray2[i][k] = 0;
			}
		}
	}

	public void cleanField(int[][] fieldArray) {
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				fieldArray[i][k] = 0;

			}
		}
	}

	public void cleanEditorField() {
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 7; k++) {
				chooserArray[i][k] = 0;

			}
		}
	}

	public void addShipsOnCooserDeck(Ship sh) {

		if (sh.isHorizontal()) {
			for (int i = 0; i < sh.getDeckNum(); i++) {
				chooserArray[sh.getXcor()][sh.getYcor() + i] = sh.getDeckNum();
			}
		} else if (!sh.isHorizontal()) {
			for (int i = 0; i < sh.getDeckNum(); i++) {
				chooserArray[sh.getXcor() + i][sh.getYcor()] = sh.getDeckNum();
			}
		}
	}

	public void addShipsOnDeck(Ship sh, int[][] fieldArray) {

		if (sh.isHorizontal()) {
			for (int i = 0; i < sh.getDeckNum(); i++) {
				fieldArray[sh.getXcor()][sh.getYcor() + i] = sh.getDeckNum();
			}
		} else if (!sh.isHorizontal()) {
			for (int i = 0; i < sh.getDeckNum(); i++) {
				fieldArray[sh.getXcor() + i][sh.getYcor()] = sh.getDeckNum();
			}
		}
		if (fieldArray.equals(fieldArray1))
			shipArray1.add(sh);
		else if (fieldArray.equals(fieldArray2))
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
		int num1 = 0, num2 = 0, num3 = 0;
		boolean orientation = false;
		Random rand = new Random();
		chooserArray = new int[8][7];
		for (int i = 0; i < 10; i++) {

			if (!isRandom) {
				orientation = false;
				if (getSizeShips()[i] == 1) {
					row = num1++;
					column = 0;
					num1 += getSizeShips()[i];

				} else if (getSizeShips()[i] == 2) {
					row = num2++;
					column = 2;
					num2 += getSizeShips()[i];
				} else if (getSizeShips()[i] == 3) {
					row = num3++;
					column = 4;
					num3 += getSizeShips()[i];
				}

				else if (getSizeShips()[i] == 4) {
					row = 2;
					column = 6;
				}

				addShipsOnCooserDeck(new Ship(row, column, getSizeShips()[i], orientation));
			}

			isRepeat = true;
			do {
				if (!isRandom) {
					isRepeat = false;

				} else {
					row = rand.nextInt(10);
					column = rand.nextInt(10);
					orientation = rand.nextBoolean();
				}
				if (setShip(i, row, column, orientation, fieldArray))
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
			shipArray = shipArray1;
			field = 1;
		} else if (fieldArray.equals(fieldArray2)) {
			shipArray = shipArray2;
			field = 2;
		}

		for (Ship s : shipArray) {

			if (!s.isDead() && fieldArray[row][column] == s.getDeckNum()) {
				if (s.isHorizontal()) {
					for (int i = 0; i < s.getDeckNum(); i++) {
						if (s.getXcor() == row && (s.getYcor() + i) == column
								&& fieldArray[s.getXcor()][s.getYcor() + i] == s.getDeckNum()) {

							if (s.getHealth() > 0) {
								s.setHealth(s.getHealth() - 1);
							} else {
								System.out.println(s.getDeckNum() + " - killed horizontal Field: " + field);
								setFieldAroundHorizontalShip(s, fieldArray);
							}

						}
					}
				}

				else if (!s.isHorizontal()) {
					for (int i = 0; i < s.getDeckNum(); i++) {
						if ((s.getXcor() + i) == row && s.getYcor() == column
								&& fieldArray[s.getXcor() + i][s.getYcor()] == s.getDeckNum()) {

							if (s.getHealth() > 0) {
								s.setHealth(s.getHealth() - 1);
							} else {
								System.out.println(s.getDeckNum() + " - killed vertical Field: " + field);
								setFieldAroundVerticalShip(s, fieldArray);
							}

						}
					}
				}
			}
		}
	}

	private void setFieldAroundHorizontalShip(Ship s, int[][] fieldArray) {

		for (int i = 0; i < s.getDeckNum(); i++) {
			if (s.getXcor() > 0 && fieldArray[s.getXcor() - 1][s.getYcor() + i] == 0)
				fieldArray[s.getXcor() - 1][s.getYcor() + i] = -1;
			if (s.getXcor() < 9 && fieldArray[s.getXcor() + 1][s.getYcor() + i] == 0)
				fieldArray[s.getXcor() + 1][s.getYcor() + i] = -1;

			if (s.getXcor() > 0 && s.getYcor() > 0 && fieldArray[s.getXcor() - 1][s.getYcor() - 1] == 0)
				fieldArray[s.getXcor() - 1][s.getYcor() - 1] = -1;
			if (s.getYcor() > 0 && fieldArray[s.getXcor()][s.getYcor() - 1] == 0)
				fieldArray[s.getXcor()][s.getYcor() - 1] = -1;
			if (s.getXcor() < 9 && s.getYcor() > 0 && fieldArray[s.getXcor() + 1][s.getYcor() - 1] == 0)
				fieldArray[s.getXcor() + 1][s.getYcor() - 1] = -1;
			if (s.getXcor() > 0 && (s.getYcor() + s.getDeckNum()) < 10
					&& fieldArray[s.getXcor() - 1][s.getYcor() + s.getDeckNum()] == 0)
				fieldArray[s.getXcor() - 1][s.getYcor() + s.getDeckNum()] = -1;
			if ((s.getYcor() + s.getDeckNum()) < 10 && fieldArray[s.getXcor()][s.getYcor() + s.getDeckNum()] == 0)
				fieldArray[s.getXcor()][s.getYcor() + s.getDeckNum()] = -1;
			if (s.getXcor() < 9 && (s.getYcor() + s.getDeckNum()) < 10
					&& fieldArray[s.getXcor() + 1][s.getYcor() + s.getDeckNum()] == 0)
				fieldArray[s.getXcor() + 1][s.getYcor() + s.getDeckNum()] = -1;

		}

	}

	private void setFieldAroundVerticalShip(Ship s, int[][] fieldArray) {

		for (int i = 0; i < s.getDeckNum(); i++) {
			if (s.getYcor() > 0 && fieldArray[s.getXcor() + i][s.getYcor() - 1] == 0)
				fieldArray[s.getXcor() + i][s.getYcor() - 1] = -1;
			if (s.getYcor() < 9 && fieldArray[s.getXcor() + i][s.getYcor() + 1] == 0)
				fieldArray[s.getXcor() + i][s.getYcor() + 1] = -1;

			if (s.getXcor() > 0 && s.getYcor() > 0 && fieldArray[s.getXcor() - 1][s.getYcor() - 1] == 0)
				fieldArray[s.getXcor() - 1][s.getYcor() - 1] = -1;
			if (s.getXcor() > 0 && fieldArray[s.getXcor() - 1][s.getYcor()] == 0)
				fieldArray[s.getXcor() - 1][s.getYcor()] = -1;
			if (s.getXcor() > 0 && s.getYcor() < 9 && fieldArray[s.getXcor() - 1][s.getYcor() + 1] == 0)
				fieldArray[s.getXcor() - 1][s.getYcor() + 1] = -1;
			if ((s.getXcor() + s.getDeckNum()) < 10 && s.getYcor() > 0
					&& fieldArray[s.getXcor() + s.getDeckNum()][s.getYcor() - 1] == 0)
				fieldArray[s.getXcor() + s.getDeckNum()][s.getYcor() - 1] = -1;
			if ((s.getXcor() + s.getDeckNum()) < 10 && fieldArray[s.getXcor() + s.getDeckNum()][s.getYcor()] == 0)
				fieldArray[s.getXcor() + s.getDeckNum()][s.getYcor()] = -1;
			if ((s.getXcor() + s.getDeckNum()) < 10 && s.getYcor() < 9
					&& fieldArray[s.getXcor() + s.getDeckNum()][s.getYcor() + 1] == 0)
				fieldArray[s.getXcor() + s.getDeckNum()][s.getYcor() + 1] = -1;
		}

	}

	public int[] getSizeShips() {
		return sizeShips;
	}
}
