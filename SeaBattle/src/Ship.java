
public class Ship {
	int deckNum;
	int health;
	int Xcor;//cordinats of first field
	int Ycor;
	boolean horizontal;
	Ship(){}
	Ship(int deck,int x,int y,boolean horizontal) {
		deckNum=deck;
		health=deck;
		Xcor=x-1;
		Ycor=y-1;
		this.horizontal=horizontal;
	}
	public boolean isKilled(){
		
			if(health>0)
				return false;
		
		return true;
	}
	public void shoot() {
		health--;
	}

}
