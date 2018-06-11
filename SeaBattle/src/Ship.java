
public class Ship {

	private int deckNum;
	private int health;
	private int Xcor;// cordinats of first field
	private int Ycor;
	private boolean horizontal;

	Ship() {
	}

	Ship(int x, int y, int numOfDeck, boolean alighment) {
		this.Xcor = x;
		this.Ycor = y;
		this.deckNum = numOfDeck;
		this.health = numOfDeck;
		this.horizontal = alighment;
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
