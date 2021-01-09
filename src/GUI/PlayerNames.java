package GUI;

import static GUI.View.style;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import service.initDim;

public class PlayerNames extends JFrame implements ActionListener{

	JTextField p1;
	JTextField p2;
	JButton Start;
	boolean clicked;
	public String name1="Player 1";
	public String name2="Player 2";
	public PlayerNames() throws InterruptedException {
		this.setLayout(null);
		Image i = Toolkit.getDefaultToolkit().getImage("images/cursor2.png");
		Point p11 = new Point(0, 0);
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(i, p11, "cursor2");
		this.setCursor(c);

		ImageIcon img = new ImageIcon("Images\\Background.jpg");
		JLabel background;
		Start= new JButton();
		Start.setBackground(Color.black);
		Start.setIcon(new ImageIcon("Images/Start.png"));

		Start.setFont(new Font(style,Font.BOLD,30));
		Start.addActionListener(this);
		background = new JLabel("", img, JLabel.CENTER);
		background.setLayout(new BorderLayout());
		setContentPane(background);
	
		initDim.size(this, 1920, 1080);
		JPanel panel=new JPanel();
		this.add(panel);
		
		p1= new JTextField("Player 1");
		p2= new JTextField("Player 2");
		p1.setFont(new Font(style,Font.BOLD,30));
		p2.setFont(new Font(style,Font.BOLD,30));

		p1.addActionListener(this);
		p2.addActionListener(this);
		Start.setOpaque(false);
		p1.setBounds(800, 300, 240, 60);
		p2.setBounds(800, 400, 240, 60);
		panel.add(Start);
		Start.setBounds(850,520,240,110);
//		Start.setBackground(Color.getHSBColor(0, 74, 64));
		panel.setSize(600, 600);
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setVisible(true);
		panel.add(p1);
		panel.add(p2);
		p1.setVisible(true);
		p2.setVisible(true);
		this.setVisible(true);
		this.repaint();
		this.revalidate();
		while(!this.isClicked()) {
			Thread.sleep(10);
			name1=p1.getText();
			name2=p2.getText();
		}
	}
	public boolean isClicked() {
		return clicked;
	}
	public static void main(String [] args) throws InterruptedException {
		new PlayerNames();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			this.dispose();
			clicked=true;
		
	}
}
