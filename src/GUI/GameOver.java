package GUI;

import model.heroes.Hero;
import model.heroes.Hunter;
import service.initDim;

import javax.sound.sampled.*;
import javax.swing.*;

import static GUI.View.style;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameOver extends JFrame implements ActionListener {
	private Hero Winner;
	public GameOver(Hero loser, Hero Winner)
	{
		super();
		Image im = Toolkit.getDefaultToolkit().getImage("images/cursor2.png");
		Point p11 = new Point(0, 0);
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(im, p11, "cursor2");
		this.setCursor(c);

		this.Winner=Winner;
		this.setTitle("GAME OVER");
		initDim.size(this,1920,1080);
		this.setLayout(null);
		ImageIcon img = new ImageIcon("Images\\GameOver2.jpg");
		JLabel background;
		background = new JLabel("", img, JLabel.CENTER);
		background.setLayout(null);
		this.setBackground(Color.BLACK);

		setContentPane(background);

		JLabel label=new JLabel("<html>  <font color=white>GAME OVER! "+loser.n+" YOU LOST! </font> </html> ");

		JLabel label2 =new JLabel( "<html>  <font color=white> "+ loser.getName() + " is the loser </font> </html>");
		label.setFont(new Font(style,Font.BOLD,30));
		label2.setFont(new Font(style,Font.BOLD,30));

		JButton b = new JButton("<html>  <font color=white> Display the Winner </font> </html>") ;
		b.setFont(new Font(style,Font.BOLD,30));
		initDim.setdim(b,600,850,700,100);
		b.setBackground(Color.BLACK);
		b.addActionListener(this);
		this.add(b);



		initDim.setdim(label,680,40,730,50);
		this.add(label);

		initDim.setdim(label2,750,100,700,50);
		this.add(label2);


        playSound("sounds/GAMEOVER.wav");
		this.setVisible(true);
		this.repaint();
		this.revalidate();


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

	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		new GameOver(new Hunter("Omar"), new Hunter("Aya"));
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		this.dispose();
		new Winner(Winner);

	}
}
