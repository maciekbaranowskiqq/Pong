import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		
		JFrame frame1 = new JFrame("PONG");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BallPanel panel1 = new BallPanel();
		frame1.add(panel1);
		
		frame1.setVisible(true);
		frame1.setSize(500,500);
		

	}

}
