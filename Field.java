import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Field extends JFrame{
	private JButton cell;
	private JPanel field;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Field fd= new Field();
//		fd.setVisible(true);
//	}
	
	
	Field(){
		this.setTitle("Field");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2));
		makeField();
		makeField();
	}
	public void makeField() {
		field= new JPanel();
		field.setLayout(new GridLayout(10,10));
		
		for(int i=0;i<10;i++) {
			for(int k=0;k<10;k++) {
				cell= new JButton();
				cell.setText(+i+" "+k);
				field.add(cell);
			}
		}
		this.add(field);
	}

}
