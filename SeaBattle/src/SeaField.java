import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private JButton newGamePVE, newGamePVE2, newGamePVE3, newGamePVP, goToMenuBtn, exitBtn, playBtn, randomBtn1,
			randomBtn2, nextBtn;
	private Comp2 robot;
	private int turn;
	private String pl;
	private boolean field1Vis;
	private boolean field2Vis;
	private boolean isMenu, fieldEdible;
	private boolean isFieldSet1, isFieldSet2, isField1, isField2, flag, isPVP;
	private int hits1;
	private int hits2;

	public SeaField() {

		game = new GameLogic();
		game.startGame();

		robot = new Comp2(game.fieldArray2, 2);
		turn = 1;
		pl = "<=";
		field1Vis = true;
		field2Vis = true;

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

		// Sound.playSound("Sounds/Wot2.wav").play();


	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, 900, 600, null);

		g2d.setFont(font.deriveFont(30f));
		g2d.setColor(DARK_BLUE);

		if (!isMenu()) {

			if (isFieldSet2())
				g2d.drawString("Player 1", 190, 50);
			if (isFieldSet1())
				g2d.drawString("Player 2", 590, 50);

			if (isField1() && isField2())
				g2d.drawString(pl, 440, 50);

			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < 10; k++) {

					if (field1Vis) {
						if (isField1()) {
							if (game.fieldArray1[i][k] >= 1 && game.fieldArray1[i][k] <= 4) {
								g.drawImage(shipPart, 100 + k * 30, 100 + i * 30, 30, 30, null);

							}
						}
					}
					if (game.fieldArray1[i][k] == -1) {
						g.drawImage(bomb, 100 + k * 30, 100 + i * 30, 30, 30, null);
					}

					if (game.fieldArray1[i][k] == -2) {
						g.drawImage(hitPart, 100 + k * 30, 100 + i * 30, 30, 30, null);
						g.drawImage(bomb, 100 + k * 30, 100 + i * 30, 30, 30, null);
					}

					if (field2Vis) {
						if (isField2()) {
							if (game.fieldArray2[i][k] >= 1 && game.fieldArray2[i][k] <= 4) {
								g.drawImage(shipPart, 500 + k * 30, 100 + i * 30, 30, 30, null);
							}
						}
					}

					if (game.fieldArray2[i][k] == -1) {
						g.drawImage(bomb, 500 + k * 30, 100 + i * 30, 30, 30, null);
					}

					if (game.fieldArray2[i][k] == -2) {
						g.drawImage(hitPart, 500 + k * 30, 100 + i * 30, 30, 30, null);
						g.drawImage(bomb, 500 + k * 30, 100 + i * 30, 30, 30, null);
					}

				}

			}

		}
		g2d.setColor(DARK_BLUE);

		if (!isFieldSet2() && isFieldSet1() && flag) {
			for (int i = 0; i < 8; i++) {
				for (int k = 0; k < 7; k++) {
					if (game.chooserArray[i][k] >= 1 && game.chooserArray[i][k] <= 4) {
						g.drawImage(shipPart, 100 + k * 30, 100 + i * 30, 30, 30, null);

					}
				}
			}
		}
		if (isFieldSet2() && !isFieldSet1() && !isField1() && !flag) {
			for (int i = 0; i < 8; i++) {
				for (int k = 0; k < 7; k++) {
					if (game.chooserArray[i][k] >= 1 && game.chooserArray[i][k] <= 4) {
						g.drawImage(shipPart, 500 + k * 30, 100 + i * 30, 30, 30, null);

					}
				}
			}
		}


		for (int i = 0; i <= 10; i++) {
			if (isFieldSet2()) {
				g2d.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
				g2d.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
			}
			if (isFieldSet1()) {
				g2d.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
				g2d.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
			}
		}

		g2d.setFont(font.deriveFont(20f));
		g2d.setColor(DARK_BLUE);

		for (int i = 1; i <= 10; i++) {
			String numb = Integer.toString(i);
			if (isFieldSet2()) {
				g2d.drawString(numb, 73, 93 + i * 30);
				g2d.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
			}
			if (isFieldSet1()) {
				g2d.drawString(numb, 473, 93 + i * 30);
				g2d.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);

			}
		}
	}

	/**
	 * Метод відображення основного меню.
	 */
	private void mainMenuFrame() {

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
		devInfo.setFont(font.deriveFont(Font.PLAIN, 11f));
		devInfo.setBackground(new Color(255, 255, 255));
		devInfo.setBounds(622, 547, 300, 37);
		add(devInfo);

		setFieldSet1(false);
		setFieldSet2(false);
		setFieldEdible(false);
		setField1(false);
		setMenu(true);

	}

	private void gameFrame() {

		playBtn.setVisible(false);
		randomBtn2.setVisible(false);

		setVisible(true);
		setLayout(null);

		goToMenuBtn = new JButton();
		goToMenuBtn.setText("To menu");
		goToMenuBtn.setForeground(DARK_BLUE);
		goToMenuBtn.setFont(font.deriveFont(20f));
		goToMenuBtn.setBounds(375, 460, 150, 60);
		goToMenuBtn.addActionListener(action);
		add(goToMenuBtn);

		setField2(true);
		setField1(true);
		setMenu(false);
		setFieldSet1(true);
		setFieldSet2(true);
		setFieldEdible(true);
		setField1(true);

	}

	private void setField2Frame() {

		newGamePVP.setVisible(false);
		newGamePVE.setVisible(false);
		newGamePVE2.setVisible(false);
		newGamePVE3.setVisible(false);
		exitBtn.setVisible(false);

		setVisible(true);
		setLayout(null);

		playBtn = new JButton();
		playBtn.setText("Play");
		playBtn.setForeground(DARK_BLUE);
		playBtn.setFont(font.deriveFont(20f));
		playBtn.setBounds(500, 460, 150, 60);
		playBtn.addActionListener(action);
		add(playBtn);

		randomBtn2 = new JButton();
		randomBtn2.setText("Random");
		randomBtn2.setForeground(DARK_BLUE);
		randomBtn2.setFont(font.deriveFont(20f));
		randomBtn2.setBounds(300, 460, 150, 60);
		randomBtn2.addActionListener(action);
		add(randomBtn2);

		game.cleanEditorField();
		game.fillField(game.fieldArray2, false);

		flag = true;
		setMenu(false);
		setFieldSet1(true);
		setFieldSet2(false);

	}

	private void setFieldFrame() {

		newGamePVP.setVisible(false);
		newGamePVE.setVisible(false);
		newGamePVE2.setVisible(false);
		newGamePVE3.setVisible(false);
		exitBtn.setVisible(false);

		setVisible(true);
		setLayout(null);

		nextBtn = new JButton();
		nextBtn.setText("Next");
		nextBtn.setForeground(DARK_BLUE);
		nextBtn.setFont(font.deriveFont(20f));
		nextBtn.setBounds(500, 460, 150, 60);
		nextBtn.addActionListener(action);
		add(nextBtn);

		randomBtn1 = new JButton();
		randomBtn1.setText("Random");
		randomBtn1.setForeground(DARK_BLUE);
		randomBtn1.setFont(font.deriveFont(20f));
		randomBtn1.setBounds(300, 460, 150, 60);
		randomBtn1.addActionListener(action);
		add(randomBtn1);

		game.cleanEditorField();
		game.fillField(game.fieldArray1, false);

		flag = false;

		setMenu(false);
		setFieldSet1(false);
		setFieldSet2(true);

	}

	public class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (isFieldSet1() && isFieldSet2() && isFieldEdible()) {
				if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
					if (turn % 2 != 0) {

						x = e.getX();
						y = e.getY();

						if ((x > 100) && (y > 100) && (x < 400) && (y < 400)) {

							int i = (y - 100) / 30;
							int k = (x - 100) / 30;

							if (game.fieldArray1[i][k] >= 1 && game.fieldArray1[i][k] <= 4) {
								game.sendShoot(i, k, game.fieldArray1);
								game.fieldArray1[i][k] = -2;
								hits1++;
								pl = "<=";
								turn += 2;
								Sound.playSound("Sounds/exp.wav").play();

							} else if (game.fieldArray1[i][k] == 0) {
								game.fieldArray1[i][k] = -1;
								Sound.playSound("Sounds/bubble.wav").play();
								pl = "=>";
								if (!isPVP) {
									pl = "<=";
									turn++;
									robot.tryShot();
									i = robot.getX();
									k = robot.getY();
									if (game.fieldArray2[i][k] >= 1 && game.fieldArray2[i][k] <= 4) {
										while (game.fieldArray2[i][k] >= 1 && game.fieldArray2[i][k] <= 4) {
											hits2++;
											game.sendShoot(i, k, game.fieldArray2);
											game.fieldArray2[i][k] = -2;
											if (hits2 == 20)
												break;
											robot.tryShot();
											i = robot.getX();
											k = robot.getY();
											if (game.fieldArray2[i][k] == 0) {
												game.fieldArray2[i][k] = -1;
												Sound.playSound("Sounds/bubble.wav").join(); //TODO change delay
											}
											repaint();
											revalidate();
											Sound.playSound("Sounds/exp.wav").join();
										}
									} else if (game.fieldArray2[i][k] == 0)
										game.fieldArray2[i][k] = -1;
									Sound.playSound("Sounds/bubble.wav").play();
								}
								turn++;

							}
							repaint();
							revalidate();
						}

					} else {
						if (isPVP) {
							if ((x > 500) && (y > 100) && (x < 800) && (y < 400)) {

								int i = (y - 100) / 30;
								int k = (x - 500) / 30;

								if (game.fieldArray2[i][k] >= 1 && game.fieldArray2[i][k] <= 4) {
									game.sendShoot(i, k, game.fieldArray2);
									game.fieldArray2[i][k] = -2;
									hits2++;
									pl = "=>";
									Sound.playSound("Sounds/exp.wav").play();
									turn += 2;

								} else if (game.fieldArray2[i][k] == 0) {
									game.fieldArray2[i][k] = -1;
									Sound.playSound("Sounds/bubble.wav").play();
									pl = "<=";

									if (!isPVP) {
										pl = "=>";
										turn++;
										robot.tryShot();
										i = robot.getX();
										k = robot.getY();
										if (game.fieldArray1[i][k] >= 1 && game.fieldArray1[i][k] <= 4) {
											while (game.fieldArray1[i][k] >= 1 && game.fieldArray1[i][k] <= 4) {
												hits1++;
												game.sendShoot(i, k, game.fieldArray1);
												game.fieldArray1[i][k] = -2;
												if (hits1 == 20)
													break;
												robot.tryShot();
												i = robot.getX();
												k = robot.getY();
												if (game.fieldArray1[i][k] == 0) {
													game.fieldArray1[i][k] = -1; 								
													Sound.playSound("Sounds/bubble.wav").play();
												}
												repaint();
												revalidate();						
												Sound.playSound("Sounds/bubble.wav").play();
											}
										} else if (game.fieldArray1[i][k] == 0) {
											game.fieldArray1[i][k] = -1;
											Sound.playSound("Sounds/bubble.wav").play();

										}
									}

									turn++;
								}

								repaint();
								revalidate();
							}

						}
					}
				}

			}

			if (hits1 == 20 && isFieldEdible()) {
				setFieldEdible(false);

				Sound.playSound("Sounds/win.wav").play();
				JOptionPane.showMessageDialog(null, "Player 1 win!!!", "Message", JOptionPane.INFORMATION_MESSAGE);

				field1Vis = true;
				field2Vis = true;
				repaint();
				revalidate();

			}
			if (hits2 == 20 && isFieldEdible()) {
				;
				setFieldEdible(false);
				if (isPVP) {
					Sound.playSound("Sounds/win.wav").play();
					JOptionPane.showMessageDialog(null, "Player 2 win!!!", "Message", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Sound.playSound("Sounds/defeat.wav").play();
					JOptionPane.showMessageDialog(null, "You loose!!!", "Message", JOptionPane.INFORMATION_MESSAGE);

				}
				field1Vis = true;
				field2Vis = true;
				repaint();
				revalidate();
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {

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

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(900, 600);
	}

	private class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Sound.playSound("Sounds/pressBtn.wav").play(true);

			if (e.getSource().equals(newGamePVE)) {
				setField2Frame();
				game.startGame();
				game.fillField(game.fieldArray1, true);
				game.fillField(game.fieldArray2, false);
				hits1 = 0;
				hits2 = 0;
				turn = 1;
				pl = "<=";
				robot.setCleverLvl(1);
				isPVP = false;
				field1Vis = true;
				field2Vis = false;

			}
			if (e.getSource().equals(newGamePVE2)) {
				setField2Frame();
				game.startGame();
				game.fillField(game.fieldArray1, true);
				game.fillField(game.fieldArray2, false);
				hits1 = 0;
				hits2 = 0;
				turn = 1;
				pl = "<=";
				robot.setCleverLvl(2);
				isPVP = false;
				field1Vis = true;
				field2Vis = false;
			}
			if (e.getSource().equals(newGamePVE3)) {
				setField2Frame();
				game.startGame();
				game.fillField(game.fieldArray1, true);
				game.fillField(game.fieldArray2, false);
				hits1 = 0;
				hits2 = 0;
				turn = 1;
				pl = "<=";
				robot.setCleverLvl(3);
				isPVP = false;
				field1Vis = true;
				field2Vis = false;
			}
			if (e.getSource().equals(newGamePVP)) {

				setFieldFrame();
				game.startGame();
				game.fillField(game.fieldArray1, false);
				game.fillField(game.fieldArray2, false);
				hits1 = 0;
				hits2 = 0;
				turn = 1;
				pl = "<=";
				isPVP = true;
				field1Vis = true;
				field2Vis = true;

			}

			if (e.getSource().equals(nextBtn)) {
				setField2Frame();
				nextBtn.setVisible(false);
				randomBtn1.setVisible(false);
				field1Vis = false;

				field2Vis = false;

			}

			if (e.getSource().equals(goToMenuBtn)) {
				mainMenuFrame();
				goToMenuBtn.setVisible(false);
				game.cleanEditorField();
				game.startGame();

			}

			if (e.getSource().equals(playBtn)) {
				gameFrame();
				field1Vis = false;
				if (isPVP)
					field2Vis = false;
				else
					field2Vis = true;
				// field1Vis=true;
				// field2Vis=true;
			}

			if (e.getSource().equals(randomBtn2)) {
				field2Vis = true;
				setField2(true);
				setField1(false);
				setFieldSet2(false);
				setFieldSet1(true);
				game.cleanEditorField();
				game.cleanField(game.fieldArray2);
				game.fillField(game.fieldArray2, true);
				if (!isPVP) {
					game.cleanField(game.fieldArray1);
					game.fillField(game.fieldArray1, true);
				}
			}

			if (e.getSource().equals(randomBtn1)) {
				setField1(true);
				setField2(false);
				setFieldSet2(true);
				setFieldSet1(false);
				game.cleanEditorField();
				game.cleanField(game.fieldArray1);
				game.fillField(game.fieldArray1, true);

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
	 * @param isMenu
	 *            the isMenu to set
	 */
	public void setMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}

	public boolean isFieldSet1() {
		return isFieldSet1;
	}

	public void setFieldSet1(boolean isFieldSet) {
		this.isFieldSet1 = isFieldSet;
	}

	public boolean isFieldSet2() {
		return isFieldSet2;
	}

	public void setFieldSet2(boolean isFieldSet2) {
		this.isFieldSet2 = isFieldSet2;
	}

	public boolean isFieldEdible() {
		return fieldEdible;
	}

	public void setFieldEdible(boolean fieldEdible) {
		this.fieldEdible = fieldEdible;
	}

	public boolean isField1() {
		return isField1;
	}

	public void setField1(boolean isField) {
		this.isField1 = isField;
	}

	public boolean isField2() {
		return isField2;
	}

	public void setField2(boolean isField2) {
		this.isField2 = isField2;
	}

}