package GUI;

import static GUI.View.style;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.heroes.Hero;
import model.heroes.Mage;
import service.initDim;
public class ChooseHero extends JFrame implements ActionListener {
	public Hero h;
	private boolean clicked = false;
	JButton Mage;
	JButton Hunter;
	JButton Paladin;
	JButton Priest;
	JLabel label;
	String i;
	JPanel panel;
	public Hero getH() {
		return h;
	}

	public boolean isClicked() {
		return clicked;
	}

	JButton Warlock;

	public ChooseHero(String i) throws InterruptedException {
		this.i=i;
		
		Image im = Toolkit.getDefaultToolkit().getImage("images/cursor2.png");
		Point p11 = new Point(0, 0);
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(im, p11, "cursor2");
		this.setCursor(c);

		ImageIcon img= new ImageIcon("Images\\background4.png");
		JLabel background;
		background = new JLabel("",img, JLabel.CENTER);
		background.setLayout(new BorderLayout());
		setContentPane(background);
		
		this.setSize(1920, 1080);
		panel=new JPanel();
		this.add(panel);
		panel.setSize(1920,1080);
		panel.setLayout(null);
		this.setLayout(null);
		this.setVisible(true);
		
		Mage = new JButton();
		Hunter = new JButton();
		Paladin = new JButton();
		Priest = new JButton();
		Warlock = new JButton();
		Mage.addActionListener(this);
		Hunter.addActionListener(this);
		Paladin.addActionListener(this);
		Priest.addActionListener(this);
		Warlock.addActionListener(this);

		Mage.setIcon(new ImageIcon("images/Mage.png"));
		Hunter.setIcon(new ImageIcon("images/Hunter.png"));
		Paladin.setIcon(new ImageIcon("images/Paladin.png"));
		Priest.setIcon(new ImageIcon("images/Priest.png"));
		Warlock.setIcon(new ImageIcon("images/Warlock.png"));

		panel.add(Mage);
		panel.add(Hunter);
		panel.add(Paladin);
		panel.add(Priest);
		panel.add(Warlock);
		panel.setOpaque(false);
		int x = 180;
		int y = 250;
		int w = 300;
		int h = 450;
		
		Mage.setBounds(x, y, w, h);
		Hunter.setBounds(x + 300, y, w, h);
		Paladin.setBounds(x + 2 * 300, y, w, h);
		Priest.setBounds(x + 3 * 300, y, w, h);
		Warlock.setBounds(x + 4 * 300, y, w, h);
		
		
		label=new JLabel("<html>  <font color=white> "+i+" Choose Your Character! </font> </html>");
		label.setFont(new Font(style,Font.BOLD ,40));
		panel.add(label);
		initDim.setdim(label,200,20,1900,50);
		revalidate();
		repaint();
		while(!this.isClicked())
			Thread.sleep(1);
	}

	public static void main(String[] args) throws InterruptedException {
		ChooseHero ch = new ChooseHero("gv");
		

	}

	public synchronized void actionPerformed(ActionEvent e) {
        playSound("sounds/click.wav");

		if ((JButton) e.getSource() == Mage) {
			try {
//			System.out.println("Mage");
				h = new Mage(i);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if ((JButton) e.getSource() == Hunter) {
			try {
				h = new model.heroes.Hunter(i);
//				System.out.println("Hunter");

			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if ((JButton) e.getSource() == Paladin) {
			try {
				h = new model.heroes.Paladin(i);
//				System.out.println("Paladin");

			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if ((JButton) e.getSource() == Priest) {
			try {
				h = new model.heroes.Priest(i);
//				System.out.println("Priest");

			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if ((JButton) e.getSource() == Warlock) {
			try {
				h = new model.heroes.Warlock(i);
//				System.out.println("Warlock");
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		clicked = true;
		this.dispose();
	}
	public void playSound(String filepath) {
		try {
			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			Clip clip=AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch (UnsupportedAudioFileException |IOException |LineUnavailableException e) {

			e.printStackTrace();
		}
	}

}