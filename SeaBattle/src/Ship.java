
public class Ship {

	private int deckNum;
	private int health;
	private int Xcor;// cordinats of first field
	private int Ycor;
	private int[][] shipCor1;
	private int[][] shipCor2;
	private boolean horizontal;

	Ship() {
	}

	Ship(int x, int y, int numOfDeck, boolean alighment) {
		this.Xcor = x;
		this.Ycor = y;
		this.deckNum = numOfDeck;
		this.health = numOfDeck - 1;
		this.horizontal = alighment;
		// setShipCor(x, y,numOfDeck, alighment, field);
	}

	public boolean isDead() {
		if (this.health >= 0)
			return false;
		else
			return true;
	}

	/**
	 * @return the shipCor
	 */
	public int[][] getShipCor1() {
		return shipCor1;
	}

	/**
	 * @return the shipCor
	 */
	public int[][] getShipCor2() {
		return shipCor2;
	}

	/**
	 * @param shipCor
	 *            the shipCor to set
	 */
	public void setShipCor(int x, int y, int deckNum, boolean horizontal, int field) {
		System.out.print(field + "№ Field ");
		if (field == 1) {
			System.out.println("|  DeckNum: " + deckNum + "  Horizontal " + horizontal);
			if (horizontal) {
				shipCor1 = new int[x + 1][y + deckNum];
				for (int i = 0; i < deckNum; i++) {
					shipCor1[x][y + i] = 1;
					System.out.println("Coord: " + (x + 1) + " " + (char) ('A' + y + i));
				}
			}
			if (!horizontal) {
				shipCor1 = new int[x + deckNum][y + 1];
				for (int i = 0; i < deckNum; i++) {
					shipCor1[x + i][y] = 1;
					System.out.println("Coord: " + (x + i + 1) + " " + (char) ('A' + y));
				}
			}
		} else if (field == 2) {
			System.out.println("|  DeckNum: " + deckNum + "  Horizontal " + horizontal);
			if (horizontal) {
				shipCor2 = new int[x + 1][y + deckNum];
				for (int i = 0; i < deckNum; i++) {
					shipCor2[x][y + i] = 1;
					System.out.println("Coord: " + (x + 1) + " " + (char) ('A' + y + i));
				}
			}
			if (!horizontal) {
				shipCor2 = new int[x + deckNum][y + 1];
				for (int i = 0; i < deckNum; i++) {
					shipCor2[x + i][y] = 1;
					System.out.println("Coord: " + (x + i + 1) + " " + (char) ('A' + y));
				}
			}

		}
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health
	 *            the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the xcor
	 */
	public int getXcor() {
		return Xcor;
	}

	/**
	 * @param xcor
	 *            the xcor to set
	 */
	public void setXcor(int xcor) {
		Xcor = xcor;
	}

	/**
	 * @return the ycor
	 */
	public int getYcor() {
		return Ycor;
	}

	/**
	 * @param ycor
	 *            the ycor to set
	 */
	public void setYcor(int ycor) {
		Ycor = ycor;
	}

	/**
	 * @return the horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * @param horizontal
	 *            the horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * @return the deckNum
	 */
	public int getDeckNum() {
		return deckNum;
	}

	/**
	 * @param deckNum
	 *            the deckNum to set
	 */
	public void setDeckNum(int deckNum) {
		this.deckNum = deckNum;
	}

}
