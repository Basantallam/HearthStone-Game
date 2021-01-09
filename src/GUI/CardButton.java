package GUI;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;
import model.heroes.Hero;
import model.heroes.Mage;
import service.initDim;

import static GUI.View.style;

public class CardButton extends JButton {
	private JLabel rarityLabel = new JLabel();
	private JLabel manaCostLabel = new JLabel();
	private Card spell;
	private JPanel tick;
	public Hero hero;

	public  JPanel getTick(){return tick;}

	public Card getSpell() {
		return spell;
	}

	public Hero getHero() {
		return hero;
	}

	public CardButton(Card c, Hero hero) {
		super();
		this.setOpaque(false);
//		this.setBackground(Color.getHSBColor(70, 70, 800));
		this.spell = c;
		this.hero = hero;
		String name = c.getName();
		Rarity rarity = c.getRarity();
		int manaCost = c.getManaCost();

		setActionCommand("Spell");
		setLayout(null);
		ImageIcon img= new ImageIcon("Images\\tick.png");

		this.tick=new JPanel();
		tick.add(new JLabel("",img, JLabel.CENTER));
		tick.setVisible(false);
		initDim.prefSize(tick,150,170);
		initDim.setdim(tick,0,0,150,170);
		this.add(tick);
		tick.setOpaque(false);
		initDim.prefSize(this, 150, 170);
		initDim.size(this, 150, 170);

		//setText(name);
		setName(name);
		setVisible(true);
		if (rarity.equals(rarity.BASIC))
			rarityLabel.setText("Basic");
		else {
			if (rarity.equals(rarity.COMMON))
				rarityLabel.setText("Common");
			else {
				if (rarity.equals(rarity.RARE))
					rarityLabel.setText("Rare");
				else {
					if (rarity.equals(rarity.EPIC))
						rarityLabel.setText("Epic");
					else
						rarityLabel.setText("Legendary");
				}
			}
		}

		rarityLabel.setVisible(true);
		add(rarityLabel);
		rarityLabel.setText("<html>  <font color=blue >"+rarityLabel.getText()+ "</font></html>");
		rarityLabel.setFont(new Font(style,Font.BOLD,10));
		initDim.setdim(rarityLabel, 55, 150, 100, 20);
		manaCostLabel.setVisible(true);
		manaCostLabel.setText("<html>  <font color=white >"+manaCost + "</font></html>");
		manaCostLabel.setFont(new Font(style,Font.BOLD,18));
		add(manaCostLabel);
		initDim.setdim(manaCostLabel, 7, 3, 50, 30);

//		this.setHorizontalTextPosition(SwingConstants.CENTER);

		if(c instanceof Spell) Iconn();

		repaint();
		revalidate();

	}

	private void Iconn() {
		String n = spell instanceof DivineSpirit? "DivineSpirit":spell instanceof Flamestrike? "Flamestrike":
				spell instanceof CurseOfWeakness?"CurseOfWeakness": spell instanceof HolyNova?"HolyNova" :
				spell instanceof KillCommand?"KillCommand": spell instanceof  SealOfChampions? "sealOfChampions":
				spell instanceof MultiShot? "MultiShot" :spell instanceof Pyroblast ?"Pyroblast":
				spell instanceof LevelUp?"LevelUp": spell instanceof SiphonSoul? "SiphonSoul":
						spell instanceof ShadowWordDeath?"ShadowWordDeath":"TwistingNether";
		this.setIcon(new ImageIcon("Images\\Spell\\"+n +".png"));
	}


	public void setRarityLabel(Rarity rarity) {
		if (rarity.equals(rarity.BASIC))
			rarityLabel.setText("Basic");
		else {
			if (rarity.equals(rarity.COMMON))
				rarityLabel.setText("Common");
			else {
				if (rarity.equals(rarity.RARE))
					rarityLabel.setText("Rare");
				else {
					if (rarity.equals(rarity.EPIC))
						rarityLabel.setText("Epic");
					else
						rarityLabel.setText("Legendary");
				}
			}
		}

	}

	public static void main(String a[]) throws IOException, CloneNotSupportedException {

		JFrame jf = new JFrame();
		jf.setSize(1920, 1080);
		jf.setLayout(null);
		CardButton cb = new CardButton(new Minion("ae", 3, Rarity.LEGENDARY, 90, 98, true, true, true),
				new Mage("bega"));
		jf.add(cb);

		cb.setBounds(0, 0, 150, 140);
		jf.setVisible(true);

		jf.repaint();
		jf.revalidate();
	}

	public void setManaCostLabel(int manaCost) {
		manaCostLabel.setText("manaCost");
	}

}
