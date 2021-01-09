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

import service.initDim;

public class GameIntro extends JFrame implements ActionListener{
	JButton Play;
	boolean clicked;
	public GameIntro() throws InterruptedException {
		playSound("sounds\\Intro.wav");
		Image im = Toolkit.getDefaultToolkit().getImage("images/cursor2.png");
		Point p11 = new Point(0, 0);
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(im, p11, "cursor2");
		this.setCursor(c);
		
		ImageIcon img = new ImageIcon("Images\\Intro.png");
		JLabel background;
		background = new JLabel("", img, JLabel.CENTER);
		background.setLayout(new BorderLayout());
		setContentPane(background);

		initDim.size(this, 1920, 1080);
		this.setLayout(null);
		

		Play = new JButton();
		initDim.setdim(Play,830,800,380,150);
		Play.setOpaque(false);
		Play.setIcon(new ImageIcon("Images/Play!.png"));
		Play.setBackground(Color.black);
		Play.addActionListener(this);

		this.add(Play);
		Play.setVisible(true);
		this.setVisible(true);
		this.repaint();
		this.revalidate();


		while(!this.isClicked()) {
			Thread.sleep(10);
		}
	}
	public static void main(String [] args) throws InterruptedException {
		new GameIntro();
	}

	public void playSound(String filepath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {

			this.dispose();
			clicked=true;
		
		
	}
	public boolean isClicked() {
		return clicked;
	}

	
	

}
