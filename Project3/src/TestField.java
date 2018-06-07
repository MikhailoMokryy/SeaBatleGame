import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class TestField extends JPanel{

	public TestField(){
	}

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override	
 public void paintComponent(Graphics g){

  super.paintComponent(g);

//g.drawImage(fon,0,0,900,600,null);

g.setFont(new Font("Monaco",3,40));
g.setColor(Color.BLUE);

//Выведение надписей
g.drawString("Компьютер", 150, 50);
g.drawString("Игрок", 590, 50);

g.setColor(Color.BLUE);
for (int i = 0; i <= 10; i++){
    g.drawLine(100+i*30, 100, 100+i*30, 400);
    g.drawLine(100, 100+i*30, 400, 100+i*30);
    g.drawLine(500+i*30, 100, 500+i*30, 400);
    g.drawLine(500, 100+i*30, 800, 100+i*30);
}


      g.setFont(new Font("Monaco",0,20));

g.setColor(Color.RED);

for (int i = 1; i <= 10; i++){
	
g.drawString(""+i, 73, 93+i*30);

g.drawString(""+i, 473, 93+i*30);

g.drawString(""+(char)('A'+i-1), 78+i*30, 93);

g.drawString(""+(char)('A'+i-1), 478+i*30, 93);

		}
	}

}