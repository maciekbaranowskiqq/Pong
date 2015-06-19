import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;



public class BallPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	
	private boolean earlyGame = true;
	private boolean midGame = false;
	
	//day/night mode done with
	private boolean dayNight = false;
	
	//ball movement
	private int ballX = 250;
	private int ballY = 250;
	private int ballDiameter = 20;
	private int ballDeltaX = -3;
	private int ballDeltaY = 5;
	
	//paddle control
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean wPressed = false;
	private boolean sPressed = false;
	
	
	//paddle movement
	
	private int playerOneX = 25;
	private int playerOneY = 250;
	private int playerOneWidth = 10;
	private int playerOneHeight = 50;
	
	private int playerTwoX = 450;
	private int playerTwoY = 250;
	private int playerTwoWidth = 10;
	private int playerTwoHeight = 50;
	
	private int paddleSpeed = 5; 
	
	//score
	private int playerOneScore = 0;
	private int playerTwoScore = 0;
	
	
	
	public BallPanel()
	{
		setBackground(Color.BLACK);
		
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		
		Timer timer = new Timer(1000/60, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if (earlyGame)
		{
			setBackground(Color.BLACK);
			
			g.setColor(Color.WHITE);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            g.drawString("Pong", 200, 100);

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));

            g.drawString("Press 2 to play.", 175, 300);
            g.drawString("Mouse-click -> DAY/NIGHT mode during the game", 30, 400);
        }
		
		if (midGame){
		
		if (dayNight)
		{
			setBackground(Color.WHITE);
		}
		else { setBackground (Color.BLACK); }
		
		g.setColor(Color.RED);
		int playerOneRight = playerOneX + playerOneWidth;
		int playerTwoLeft = playerTwoX;
		
		//paint the game objects
		g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
		g.setColor(Color.BLUE);
		g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
		g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
		
		//paint the scorescreen

		
		
		if (dayNight){
		g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.WHITE);
		}
		g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
		g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
		
		g.setColor(Color.RED);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
        g.drawString(String.valueOf(playerOneScore), 100, 100);
        g.drawString(String.valueOf(playerTwoScore), 400, 100);
		
	}}

	public void actionPerformed(ActionEvent e) {
		
		step();
		
	}

	public void step() {
		

		
		//PLAYER ONE MOVEMENT
		
        int playerOneRight = playerOneX + playerOneWidth;
        int playerOneTop = playerOneY;
        int playerOneBottom = playerOneY + playerOneHeight;
        
		if (upPressed) { 
			if (playerOneY-paddleSpeed > 0) {playerOneY -= paddleSpeed; }}
        if (downPressed) {
            if (playerOneY + paddleSpeed + playerOneHeight < getHeight()) {
                playerOneY += paddleSpeed;}}
        
        //PLAYER TWO MOVEMENT
        
        int playerTwoLeft = playerTwoX;
        int playerTwoTop = playerTwoY;
        int playerTwoBottom = playerTwoY + playerTwoHeight;
        
        if (wPressed) {
        	if (playerTwoY-paddleSpeed > 0) {playerTwoY -= paddleSpeed; }}
        if (sPressed) {
        	if (playerTwoY + paddleSpeed + playerTwoHeight < getHeight())
        	{ playerTwoY += paddleSpeed;}}
        
        

        //BALL MOVEMENT

        int nextBallLeft = ballX + ballDeltaX;
        int nextBallRight = ballX + ballDiameter + ballDeltaX;
        int nextBallTop = ballY + ballDeltaY;
        int nextBallBottom = ballY + ballDiameter + ballDeltaY;
 
        if (nextBallLeft < playerOneRight) { 
           
            if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {
    
                playerTwoScore++;

                ballX = 250;
                ballY = 250;
            }
            else {
                ballDeltaX *= -1;
            }
        }
        
        if (nextBallRight > playerTwoLeft) {
        	if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {
        		
        		
        		playerOneScore++;
        		
        		ballX =250;
        		ballY =250;
        	}
        	else {
        		ballDeltaX *=-1;
        	}
        }
        if (nextBallTop < 0 || nextBallBottom > getHeight()) {
            ballDeltaY *= -1;
        }
        
        ballX += ballDeltaX;
        ballY += ballDeltaY;
        
        repaint();
	}

	public void keyPressed(KeyEvent e) {
		
		
		if (earlyGame)
		{	
			if (e.getKeyCode() == KeyEvent.VK_2 )
			{
				earlyGame = false;
				midGame = true;
			}	
		}

		
		if (midGame){
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			upPressed = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downPressed = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			wPressed = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			sPressed = true;
		}

	}}

	public void keyReleased(KeyEvent e) {
		
		if (midGame){
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			upPressed = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downPressed = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			wPressed = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			sPressed =  false;
		}
		}

		
		
	}
	public void keyTyped(KeyEvent e) {}	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {
		
	try {
		if (dayNight == true) { 
		dayNight = false;
		} else { 
		dayNight = true;}
		}
	catch (Exception e1)
	{
		System.out.println("This won't work");
	}}

	public void mouseReleased(MouseEvent e) {}
}
