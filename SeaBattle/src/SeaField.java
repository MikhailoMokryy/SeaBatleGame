import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JPanel;


public class SeaField extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color DARK_BLUE = Color.BLUE.darker().darker().darker();
	private Font font = new Font("Comic Sans MS",Font.BOLD,15);
	private Image background,shipPart,hitPart,bomb;
	private  Graphics2D g2d;
	private GameLogic game;
	private int x, y;
	private JButton newGameBtn;
	
	protected Ship deck4;
	protected Ship deck3_1;
	protected Ship deck3_2;
	protected Ship deck2_1;
	protected Ship deck2_2;
	protected Ship deck2_3;
	protected Ship deck1_1;
	protected Ship deck1_2;
	protected Ship deck1_3;
	protected Ship deck1_4;
	private enum ShipsOnFirstDeck { deck4,deck3_1,deck3_2,deck2_1,deck2_2,deck2_3,deck1_1,deck1_2,deck1_3,deck1_4       };

	
	
	
	public SeaField() {
		
		
		game = new GameLogic();
		game.startGame();
	
		game.fillField(game.fieldArray1,true);
		game.fillField(game.fieldArray2,true);
		

		

		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseMotionListener());
		setFocusable(true); 
		
		try {
		background = ImageIO.read(new File("Pics/water.png")); //TODO change PATH
		shipPart = ImageIO.read(new File("Pics/gray_sqr.png"));
		hitPart = ImageIO.read(new File("Pics/red_sqr.png"));
		bomb = ImageIO.read(new File("Pics/bomb.png"));
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("Change pictures directory!");
		}
		
		setLayout(null);
		MyActionListener action = new MyActionListener();
        
		newGameBtn = new JButton();
		newGameBtn.setText("New Game");
		newGameBtn.setForeground(DARK_BLUE);
		newGameBtn.setFont(font);
		newGameBtn.setBounds(170, 450, 150, 60);
		newGameBtn.addActionListener(action);
		add(newGameBtn);
		
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
	
		 g2d=(Graphics2D)g;
		 g2d.drawImage(background,0,0,900,600,null);

		g2d.setFont(font.deriveFont(30f));
		g2d.setColor(DARK_BLUE);

		g2d.drawString("Player 1", 190, 50);
		g2d.drawString("Player 2", 590, 50);

		for(int i = 0; i<10; i++) {
			for(int k = 0; k<10; k++) {	
				
				if(game.fieldArray1[i][k]==1) {
					g.drawImage(shipPart, 100 + k * 30, 100 + i * 30, 30, 30, null);	
			
				}
				
				if(game.fieldArray1[i][k]==-1) {
					g.drawImage(bomb, 100 + k * 30, 100 + i * 30, 30, 30, null);				
				}
				
				if(game.fieldArray1[i][k]==-2) {
					g.drawImage(hitPart, 100 + k * 30, 100 + i * 30, 30, 30, null);				
				}
				
				
				if(game.fieldArray2[i][k]==1) {
					g.drawImage(shipPart, 500 + k * 30, 100 + i * 30, 30, 30, null);	
				}
				
				if(game.fieldArray2[i][k]==-1) {
					g.drawImage(bomb, 500 + k * 30, 100 + i * 30, 30, 30, null);				
				}
				
				if(game.fieldArray2[i][k]==-2) {
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
	
	public class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if((e.getButton() == 1)&&(e.getClickCount()==1)){
				x = e.getX();
				y = e.getY();
				
				if((x>100)&&(y>100)&&(x<400)&&(y<400)) {
					
					int i = (y-100)/30;
					int k = (x-100)/30;
					
					if((game.fieldArray1[i][k]==1)) {
						game.fieldArray1[i][k] =-2;
						
					}else if(game.fieldArray1[i][k]==0)
					   game.fieldArray1[i][k]= -1;
					
					repaint();
					revalidate();
				}
				
				
               if((x>500)&&(y>100)&&(x<800)&&(y<400)) {
					
					int i = (y-100)/30;
					int k = (x-500)/30;
					
					
					if((game.fieldArray2[i][k]==1)) {
						game.fieldArray2[i][k] =-2;
						
					}else if(game.fieldArray2[i][k]==0)
					   game.fieldArray2[i][k]= -1;
				
					repaint();
					revalidate();
				}
				
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

			if(((x>100)&&(y>100)&&(x<400)&&(y<400))||((x>500)&&(y>100)&&(x<800)&&(y<400))) 	
		       	setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			else
                 setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}
	
	

	private class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			 if(e.getSource().equals(newGameBtn)) {
			  
         	    game.startGame();
        		    game.fillField(game.fieldArray1,true);
        		    game.fillField(game.fieldArray2,true);
            } 
			 
			 repaint();
			 revalidate(); 
		}
	}
	

}