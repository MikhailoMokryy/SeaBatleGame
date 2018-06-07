import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Panel;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import java.awt.Color;

public class TestField extends JFrame {

	private JPanel contentPane;
	private JPanel[][] cell1;
	private JPanel[][] cell2;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestField frame = new TestField();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestField() {
		getContentPane().setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		
		
		
		
	    cell1= new JPanel[10][10];
	    GridBagConstraints gbc_panel1 = new GridBagConstraints();
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {		
			cell1[i][k] = new JPanel();
			Border titled = BorderFactory.createLineBorder(Color.BLACK);
			cell1[i][k].setBorder(titled);
			cell1[i][k].setBackground(Color.LIGHT_GRAY);
			gbc_panel1.ipady = 15;
			gbc_panel1.ipadx = 15;
			gbc_panel1.gridx = i;
			gbc_panel1.gridy = k;	
			getContentPane().add(cell1[i][k], gbc_panel1);

	
			}
		}
		getContentPane().add(Box.createRigidArea(new Dimension(50, 0)));
	
		
		
		 cell2= new JPanel[10][10];
			GridBagConstraints gbc_panel2 = new GridBagConstraints();
			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < 10; k++) {		
				cell2[i][k] = new JPanel();
				Border titled = BorderFactory.createLineBorder(Color.BLACK);
				cell2[i][k].setBorder(titled);
				cell2[i][k].setBackground(Color.LIGHT_GRAY);
				gbc_panel2.ipady = 15;
				gbc_panel2.ipadx = 15;
				gbc_panel2.gridx =11+ i;
				gbc_panel2.gridy = k;	
				getContentPane().add(cell2[i][k], gbc_panel2);
			
				}
			}
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(300, 300, 590, 292);
	}

}
