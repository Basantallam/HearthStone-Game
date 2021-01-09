package GUI;


import model.heroes.Hero;
import service.initDim;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static GUI.View.style;

public class HeroPanel extends JPanel {

	private HeroButton heroButton ;
	private JButton heroPower;
	private JLabel currentHP= new JLabel();
	private int totalManaCrystals;
	private int currentManaCrystals;
	private JLabel ManaCrystals= new JLabel();

	
	public HeroPanel(Hero h) {

		super();
		Image i = Toolkit.getDefaultToolkit().getImage("images/cursor2.png");
		Point p11 = new Point(0, 0);
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(i, p11, "cursor2");
		this.setCursor(c);

		int currentHP = h.getCurrentHP();
		int currentManaCrystals = h.getCurrentManaCrystals();
		int totalManaCrystals = h.getTotalManaCrystals();
		this.heroButton = new HeroButton(h);
		heroPower=initDim.getjButton("",280,20,225,84);
		heroPower.setBackground(Color.orange);
		heroPower.setIcon(new ImageIcon("Images/HeroPower.png"));
		heroPower.setOpaque(false);
		this.currentHP.setSize(600,100);
		this.currentHP.setFont(new Font(style,Font.BOLD,30));
		setCurrentHP(currentHP);
		this.totalManaCrystals = totalManaCrystals;
		this.currentManaCrystals = currentManaCrystals;
		this.ManaCrystals.setFont(new Font(style,Font.BOLD,20));
		setCurrentManaCrystals(currentManaCrystals);
		this.setLayout(null);
		initDim.size(this,900,200);
		add(heroButton);
		add(heroPower);
		add(this.currentHP);
		add(ManaCrystals);
		initDim.setdim(ManaCrystals,440,180,50,20);
		initDim.setdim(this.currentHP,210,150,50,50);
	
	}


	public HeroButton getHeroButton() {
		return heroButton;
	}


	public JButton getHeroPower() {
		return heroPower;
	}

	public int getCurrentHP() {
		return Integer.parseInt(currentHP.getText());
	}
	public void setCurrentHP(int currentHP) {
		String s="<html>  <font color=white> "+currentHP+" </font> </html>";
		this.currentHP.setFont(new Font("TimesRoman",Font.BOLD,30));
		this.currentHP.setText(s);
	}
		
	public JLabel getManaCrystals() {
		return ManaCrystals;
	}
	public void setCurrentManaCrystals(int n) {
		String s="<html>  <font color=white>"+n+"/"+totalManaCrystals+"</font> </html>";
		this.ManaCrystals.setFont(new Font("TimesRoman",Font.BOLD,20));

		this.ManaCrystals.setText(s);
	}

	public static void main(String[] args) {
//		JFrame aya= new JFrame();
//		HeroPanel b = new HeroPanel("Aya", 1, 20, 30);
//		aya.add(b);
//		aya.setVisible(true);
	}
	
	

}
