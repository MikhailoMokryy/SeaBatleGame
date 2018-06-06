import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Field extends JFrame {
	JButton[][] cell;
	JPanel field;

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// Field fd= new Field();
	// fd.setVisible(true);
	// }

	
	Field() {
		this.setTitle("Field");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 2));

		makeField();
		makeField();
		initListners();
	}

	public void makeField() {
		field = new JPanel();
		field.setLayout(new GridLayout(10, 10));
		cell= new JButton[10][10];
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				cell[i][k] = new JButton();
				cell[i][k].setText(+i + " " + k);
				field.add(cell[i][k]);
			}
		}
		this.add(field);
	}

	public void addShips(Ship sh) {
		
		
			cell[sh.Xcor][sh.Ycor].setName("S");
		if(sh.horizontal==true) {
			for(int i = 1;i<sh.deckNum+1;i++) {
				cell[sh.Xcor+i][sh.Ycor].setName("S");
			}
		}
		if(sh.horizontal==false) {
			for(int i = 1;i<sh.deckNum;i++) {
				cell[sh.Xcor][sh.Ycor+i].setName("S");
			}
		}
	}

	public void initListners() {
		
		cell[0][0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cell[0][0].getName().equals(""))
					cell[0][0].setText("X");
				else
					cell[0][0].setText("Xs");
				cell[0][0].setEnabled(false);
					
			}
		});
		cell[0][1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cell[0][1].getName().equals(""))
					cell[0][1].setText("X");
				else
					cell[0][1].setText("Xs");
				cell[0][1].setEnabled(false);
					
			}
		});
			
	}
}
