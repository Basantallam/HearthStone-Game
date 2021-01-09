package GUI;

import model.heroes.Hero;
import model.heroes.Hunter;
import service.initDim;

import javax.sound.sampled.*;
import javax.swing.*;

import static GUI.View.style;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Winner extends JFrame {
    public Winner(Hero winner)
    {
        super();
        this.setTitle("CONGRATULATIONS");
        Image im = Toolkit.getDefaultToolkit().getImage("images/cursor2.png");
		Point p11 = new Point(0, 0);
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(im, p11, "cursor2");
		this.setCursor(c);

        initDim.size(this,1920,1080);
        this.setLayout(null);ImageIcon img = new ImageIcon("Images\\Winner1.jpg");
        JLabel background;
        background = new JLabel("", img, JLabel.CENTER);
        background.setLayout(null);
        setContentPane(background);

        JLabel label=new JLabel("<html>  <font color=red>CONGRATULATIONS \n"+winner.n+" YOU WON </font> </html>");
        JLabel l2 =new JLabel( "<html>  <font color=red>"+winner.getName() + " is the winner </font> </html>");

		label.setFont(new Font(style,Font.BOLD,40));
		l2.setFont(new Font(style,Font.BOLD,30));

        this.add(label);
        this.add(l2);
        initDim.setdim(label,680,40,800,100);
        initDim.setdim(l2,750,100,700,100);

        playSound("sounds/Fanfare.wav");
        this.setVisible(true);
        this.repaint();
        this.revalidate();


    }
    public void playSound(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            Clip clip=AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        new Winner(new Hunter("Aya"));
    }
}
