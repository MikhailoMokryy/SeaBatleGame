import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SeaField extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color DARK_BLUE = Color.BLUE.darker().darker().darker();
	private Font font = new Font("Comic Sans MS", Font.BOLD, 15);
	private Image background, shipPart, hitPart, bomb;
	private Graphics2D g2d;
	private MyActionListener action;
	private GameLogic game;
	private int x, y;
	private JLabel devInfo;
	private JButton newGamePVE,newGamePVE2,newGamePVE3,newGamePVP,goToMenuBtn, exitBtn;	
	private Comp2 robot;
	private int turn;
	private String pl;
	private int prog;
	private boolean field1Vis;
	private boolean field2Vis;
	private boolean isMenu;
	private int hits1;
	private int hits2;


	public SeaField() {

		game = new GameLogic();
		game.startGame();
		game.fillField(game.fieldArray1, true);
		game.fillField(game.fieldArray2, true);
		robot = new Comp2(game.fieldArray1, 2);
		turn = 1;
		pl = "=>";
		prog = 2; 
		field1Vis=true;
		field2Vis=true;

		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseMotionListener());
		setFocusable(true);
		
		try {
			background = ImageIO.read(new File("Pics/water.png")); // TODO change PATH
			shipPart = ImageIO.read(new File("Pics/gray_sqr.png"));
			hitPart = ImageIO.read(new File("Pics/red_sqr.png"));
			bomb = ImageIO.read(new File("Pics/bomb.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Change pictures directory!");
		}
		
		setLayout(null);
		action = new MyActionListener();
	
		mainMenuFrame();

	

		
	}

	@Override
	public void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		
		g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, 900, 600, null);
		
		g2d.setFont(font.deriveFont(30f));
		g2d.setColor(DARK_BLUE);
		if(!isMenu()) {
		g2d.drawString("Player 1", 190, 50);
		g2d.drawString("Player 2", 590, 50);
		g2d.drawString(pl, 440, 50);

		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				
				if(field1Vis) {
				if (game.fieldArray1[i][k] >= 1&&game.fieldArray1[i][k] <= 4) {
					g.drawImage(shipPart, 100 + k * 30, 100 + i * 30, 30, 30, null);

				}
				}
				if (game.fieldArray1[i][k] == -1) {
					g.drawImage(bomb, 100 + k * 30, 100 + i * 30, 30, 30, null);
				}

				if (game.fieldArray1[i][k] == -2) {
					g.drawImage(hitPart, 100 + k * 30, 100 + i * 30, 30, 30, null);
				}
				
				if(field2Vis) {
				if (game.fieldArray2[i][k] >= 1&&game.fieldArray2[i][k] <= 4) {
					g.drawImage(shipPart, 500 + k * 30, 100 + i * 30, 30, 30, null);
				}
				}
				
				if (game.fieldArray2[i][k] == -1) {
					g.drawImage(bomb, 500 + k * 30, 100 + i * 30, 30, 30, null);
				}

				if (game.fieldArray2[i][k] == -2) {
					g.drawImage(hitPart, 500 + k * 30, 100 + i * 30, 30, 30, null);
				}

			}
			
		}

		g2d.setColor(DARK_BLUE);
		for (int i = 0; i <= 10; i++) {
			g2d.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
			g2d.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
			g2d.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
			g2d.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
		}

		g2d.setFont(font.deriveFont(20f));

		g2d.setColor(DARK_BLUE);
		for (int i = 1; i <= 10; i++) {
			String numb = Integer.toString(i);
			g2d.drawString(numb, 73, 93 + i * 30);
			g2d.drawString(numb, 473, 93 + i * 30);
			g2d.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
			g2d.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);

		}
		}
		
	}
	
	
	
	/**
	* Метод відображення основного меню.
	*/
	private void mainMenuFrame()  {
	
		setLayout(null);
		setVisible(true);
	
		
		newGamePVE = new JButton();
		newGamePVE.setText("Low");
		newGamePVE.setForeground(DARK_BLUE);
		newGamePVE.setFont(font.deriveFont(20f));
		newGamePVE.setBounds(350, 120, 200, 60);
		newGamePVE.addActionListener(action);
		add(newGamePVE);
		newGamePVE2 = new JButton();
		newGamePVE2.setText("Medium");
		newGamePVE2.setForeground(DARK_BLUE);
		newGamePVE2.setFont(font.deriveFont(20f));
		newGamePVE2.setBounds(350, 200, 200, 60);
		newGamePVE2.addActionListener(action);
		add(newGamePVE2);
		newGamePVE3 = new JButton();
		newGamePVE3.setText("Hard");
		newGamePVE3.setForeground(DARK_BLUE);
		newGamePVE3.setFont(font.deriveFont(20f));
		newGamePVE3.setBounds(350, 280, 200, 60);
		newGamePVE3.addActionListener(action);
		add(newGamePVE3);
		newGamePVP = new JButton();
		newGamePVP.setText("With Player");
		newGamePVP.setForeground(DARK_BLUE);
		newGamePVP.setFont(font.deriveFont(20f));
		newGamePVP.setBounds(350, 360, 200, 60);
		newGamePVP.addActionListener(action);
		add(newGamePVP);
		exitBtn = new JButton();
		exitBtn.setText("Exit");
		exitBtn.setForeground(DARK_BLUE);
		exitBtn.setFont(font.deriveFont(20f));
		exitBtn.setBounds(350, 440, 200, 60);
		exitBtn.addActionListener(action);
		add(exitBtn);
		
		devInfo = new JLabel("<html>Made by Mikhailo Mokryy and Andry Kovalenko, 2018</html>");
		devInfo.setForeground(new Color(255, 255, 255));
		devInfo.setFont(font.deriveFont(Font.PLAIN,11f));
		devInfo.setBackground(new Color(255, 255, 255));
		devInfo.setBounds(622, 547, 300, 37);
		
		add(devInfo);
	
		setMenu(true);
			
	}
	
	private void gameFrame() {
		newGamePVP.setVisible(false);
		newGamePVE.setVisible(false);
		newGamePVE2.setVisible(false);
		newGamePVE3.setVisible(false);
		exitBtn.setVisible(false);
		
		setVisible(true);
		setLayout(null);

		goToMenuBtn = new JButton();
		goToMenuBtn.setText("To menu");
		goToMenuBtn.setForeground(DARK_BLUE);
		goToMenuBtn.setFont(font.deriveFont(20f));
		goToMenuBtn.setBounds(375, 460, 150, 60);
		goToMenuBtn.addActionListener(action);
		add(goToMenuBtn);
		
		
		setMenu(false);
		
		
		setVisible(true);
		

	}

	public class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
				if (turn % 2 == 0) {
					if (prog == 1) {

						x = e.getX();
						y = e.getY();

						if ((x > 100) && (y > 100) && (x < 400) && (y < 400)) {

							int i = (y - 100) / 30;
							int k = (x - 100) / 30;

							if (game.fieldArray1[i][k] >= 1&&game.fieldArray1[i][k] <= 4) {
								game.sendShoot(i, k, game.fieldArray1);
								game.fieldArray1[i][k] = -2;
								hits1++;
								turn++;
								pl = "<=";
								turn++;
								

							} else if (game.fieldArray1[i][k] == 0) {
								game.fieldArray1[i][k] = -1;
								pl = "=>";
								turn++;
								
							}
							repaint();
							revalidate();
						}
					}
				} else {

					if ((x > 500) && (y > 100) && (x < 800) && (y < 400)) {

						int i = (y - 100) / 30;
						int k = (x - 500) / 30;

						if (game.fieldArray2[i][k] >= 1&&game.fieldArray2[i][k] <= 4) {
							game.sendShoot(i, k, game.fieldArray2);
							game.fieldArray2[i][k] = -2;
							hits2++;
							pl = "=>";

							turn+=2;

						} else if (game.fieldArray2[i][k] == 0) {
							game.fieldArray2[i][k] = -1;
							pl = "<=";
							if (prog == 2) {
								pl = "=>";
								turn++;
								robot.tryShot();
								i = robot.getX();
								k = robot.getY();
								if (game.fieldArray1[i][k] >= 1&&game.fieldArray1[i][k] <= 4) {
									while(game.fieldArray1[i][k] >= 1&&game.fieldArray1[i][k] <= 4) {
										hits1++;
										game.sendShoot(i, k, game.fieldArray1);
										game.fieldArray1[i][k] = -2;
										if(hits1==20)
											break;
										robot.tryShot();
										i = robot.getX();
										k = robot.getY();
										if (game.fieldArray1[i][k] == 0) {
											game.fieldArray1[i][k] = -1;
										}
										System.out.println("start");
								    	try {
											Thread.sleep(3000);
											
											System.out.println("shot!");
											
										
								    	} catch (InterruptedException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
								    	System.out.println("end");
								    	
										repaint();
										revalidate();
								    	
									}
								} else if (game.fieldArray1[i][k] == 0)
									game.fieldArray1[i][k] = -1;
							}
							turn++;
						}
						
						repaint();
						revalidate();
					}

				}

			}
		
			if(hits1==20) {
				JOptionPane.showMessageDialog(null,
						"Player 1 wins!!!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if(hits2==20) {
				JOptionPane.showMessageDialog(null,
						"Player 2 wins!!!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class MyMouseMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			if (((x > 100) && (y > 100) && (x < 400) && (y < 400))
					|| ((x > 500) && (y > 100) && (x < 800) && (y < 400)))
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			else
				setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	private class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			
			
			if (e.getSource().equals(newGamePVE)) {
				gameFrame();
				game.startGame();
				game.fillField(game.fieldArray1, true);
				game.fillField(game.fieldArray2, true);
				hits1=0;
				hits2=0;
				turn = 1;
				pl = "=>";
				prog = 2;
				robot.setCleverLvl(1);
				field1Vis=true;
				field2Vis=true;
			}
			if (e.getSource().equals(newGamePVE2)) {
				gameFrame();
				game.startGame();
				game.fillField(game.fieldArray1, true);
				game.fillField(game.fieldArray2, true);
				hits1=0;
				hits2=0;
				turn = 1;
				pl = "=>";
				prog = 2;
				robot.setCleverLvl(2);
				field1Vis=true;
				field2Vis=true;
			}
			if (e.getSource().equals(newGamePVE3)) {
				gameFrame();
				game.startGame();
				game.fillField(game.fieldArray1, true);
				game.fillField(game.fieldArray2, true);
				hits1=0;
				hits2=0;
				turn = 1;
				pl = "=>";
				prog = 2;
				robot.setCleverLvl(3);
				field1Vis=true;
				field2Vis=true;
			}
			if (e.getSource().equals(newGamePVP)) {
				gameFrame();
				game.startGame();
				game.fillField(game.fieldArray1, true);
				game.fillField(game.fieldArray2, true);
				hits1=0;
				hits2=0;
				turn = 1;
				pl = "=>";
				prog = 1;
				field1Vis=true;
				field2Vis=true;
			}
			
			if (e.getSource().equals(goToMenuBtn)) {
				mainMenuFrame();
				goToMenuBtn.setVisible(false);
				
			}
			
			
			if (e.getSource().equals(exitBtn)) {
				System.exit(0);
				
			}
			
			repaint();
			revalidate();
		}
	}

	/**
	 * @return the isMenu
	 */
	public boolean isMenu() {
		return isMenu;
	}

	/**
	 * @param isMenu the isMenu to set
	 */
	public void setMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}


}