
public class Ship {
	int deckNum;
	boolean[] health= new boolean[deckNum];
	int Xcor;//cordinats of first field
	int Ycor;
	boolean horizontal;
	Ship(int deck,int x,int y,boolean horizontal) {
		deckNum=deck;
		Xcor=x;
		Ycor=y;
		this.horizontal=horizontal;
	}
	public boolean killed(){
		for(int i=0;i<deckNum;i++) {
			if(health[i]==true)
				return false;
		}
		return true;
	}

}
