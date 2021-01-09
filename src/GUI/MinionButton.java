package GUI;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Mage;
import service.initDim;

import static GUI.View.style;


public class MinionButton extends CardButton {
     JLabel tauntLabel = new JLabel();
     JLabel divineLabel = new JLabel();
     JLabel sleepingLabel = new JLabel();
     JLabel hpLabel= new JLabel();
     JLabel attackLabel= new JLabel();
     
     Minion minion;

	public Minion getMinion() {
		return minion;
	}

	public MinionButton(Minion m, Hero hero) {
 		super(m,hero);
 		this.setBackground(Color.getHSBColor(100, 0, 200));
 		minion=m;
 		boolean taunt=m.isTaunt();
 		boolean divine= m.isDivine();
 		boolean sleeping=m.isSleeping();
 		int hp=m.getMaxHP();
 		int attack =m.getAttack();
 		
 		setActionCommand("Minion");
 		if(taunt) {
 			tauntLabel.setText("<html>  <font color=white >"+"Taunt"+ "</font></html>");	
 		}
 		if(divine) {
 			divineLabel.setText("<html>  <font color=white >"+"Devine"+ "</font></html>");
 		}
 		if(sleeping) {
 			sleepingLabel.setText("<html>  <font color=white >"+"sleeping"+ "</font></html>");
 		}
 		
 		hpLabel.setVisible(true);
 		hpLabel.setText(hp+"");
 		attackLabel.setText(attack+"");
 		tauntLabel.setVisible(true);
 		add(tauntLabel);
		initDim.setdim(tauntLabel,55,130,90,30);
		

 		divineLabel.setVisible(true);
 		add(divineLabel);
 		initDim.setdim(divineLabel,55,120,90,30);
 		sleepingLabel.setVisible(true);
 		add(sleepingLabel);
 		initDim.setdim(sleepingLabel,55,110,90,30);

 		add(hpLabel);

 		attackLabel.setVisible(true);
 		add(attackLabel);
		initDim.setdim(hpLabel,125,140,90,40);
 		initDim.setdim(attackLabel,10,140,90,40);
		attackLabel.setFont(new Font(style,Font.BOLD,18));
		hpLabel.setFont(new Font(style,Font.BOLD,18));

 		setIconn(m.getName());
		this.setHorizontalTextPosition(SwingConstants.CENTER);
 		repaint();
 		revalidate();
 	}

	private void setIconn(String n) {

		this.setIcon(new ImageIcon("Images\\Minion\\"+n+".png"));
	}

	public void setTauntLabel(Boolean taunt) {
		if(taunt) {
			tauntLabel.setText("Taunt");	
		}
	}
	public void setDivineLabel(Boolean divine) {
		if(divine) {
			divineLabel.setText("Divine");
		}
	}
	public void setSleepingLabel(Boolean sleeping) {
		if(sleeping) {
			sleepingLabel.setText("sleeping");
		}
	}
	public void setHpLabel(int hp) {
		hpLabel.setText("hp");
	}
	public static void main(String []args) throws IOException, CloneNotSupportedException {
		 JFrame jf = new JFrame();
		 jf.setSize(1920,1080);
		 jf.setLayout(null);
		 MinionButton cb = new MinionButton(new Minion("ae",3,Rarity.LEGENDARY, 90,98 ,true,true,true),new Mage("bega"));
		 jf.add(cb);
		 
		 cb.setBounds(0, 0, 150, 140);
		 jf.setVisible(true);
		 
		 jf.repaint();
		 jf.revalidate();
	 }
	

}
