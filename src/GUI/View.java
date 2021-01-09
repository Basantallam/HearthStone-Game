package GUI;

import service.initDim;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame{
	private JPanel current;
	private JPanel opponent;
	private JPanel currField;
	private JPanel oppField;
	private JPanel currHand;
	public static  String style="Verdana Pro Cond Black";
	public View() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(false);

		initDim.size(this,1920,1080);
		
		Image i = Toolkit.getDefaultToolkit().getImage("images/cursor2.png");
		Point p11 = new Point(0, 0);
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(i, p11, "cursor2");
		this.setCursor(c);

		ImageIcon img= new ImageIcon("Images\\b1.png");
		JLabel background;
		background = new JLabel("",img, JLabel.CENTER);
		background.setLayout(new BorderLayout());
		setContentPane(background);
		
		this.current = new JPanel();
		this.opponent = new JPanel();
		
		oppField = new JPanel();
		currField =new JPanel();
		currHand = new JPanel();
		
		this.setVisible(true);
		
		initDim.prefSize(opponent,1920,400);
		initDim.prefSize(current,1920,600);
		initDim.setdim(currField,100,0,1800,200);
		initDim.setdim(currHand,50,420,1900,200);

		current.setLayout(null);
    	current.add(currField);
		current.add(currHand);
		
		opponent.setLayout(null);

		initDim.setdim(oppField,100,220,1800,200);
		opponent.add(oppField);

		current.setOpaque(false);
		opponent.setOpaque(false);
		oppField.setOpaque(false);
		currField.setOpaque(false);
		currHand.setOpaque(false);
		
//		oppField.setBackground(Color.black);
//		currHand.setBackground(Color.black);
//		current.setBackground(new Color(ColorSpace.CS_PYCC));
//		currField.setBackground(new Color(ColorSpace.TYPE_5CLR));
		
		this.add(opponent,BorderLayout.NORTH);
		this.add(current,BorderLayout.SOUTH);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		
	}

	public JPanel getCurrent() {
		return current;
	}
	

	public JPanel getCurrField() {
		return currField;
	}


	public JPanel getOppField() {
		return oppField;
	}

	public void setOppField(JPanel oppField) {
		this.oppField = oppField;
	}

	public JPanel getCurrHand() {
		return currHand;
	}

	public void setCurrHand(JPanel currHand) {
		this.currHand = currHand;
	}

	public void setCurrent(JPanel current) {
		this.current = current;
	}

	public JPanel getOpponent() {
		return opponent;
	}

	public void setOpponent(JPanel opponent) {
		this.opponent = opponent;
	}

//	public static void main(String[] args) {
//		View j = new View();
//	
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int screenHeight = screenSize.height;
//		int screenWidth = screenSize.width;
//		System.out.println(screenWidth +"*"+ screenHeight);
//		
//		
//	}
}
