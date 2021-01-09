package service;

import static GUI.View.style;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.CardButton;
import GUI.HeroPanel;
import GUI.MinionButton;
import GUI.View;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Service {
	private Hero currentHero;
	private Hero opponentHero;
	private ArrayList<MinionButton> cField = new ArrayList<MinionButton>();
	private ArrayList<MinionButton> oField = new ArrayList<MinionButton>();
	private ArrayList<CardButton> cHand = new ArrayList<CardButton>();
	private JLabel oppDeck = new JLabel();
	private JLabel currDeck = new JLabel();
	private JLabel oHand = new JLabel();
	private View view;
	private HeroPanel currHeroPanel;
	private JButton play;
	private JButton endTurn;
	private boolean gameover;
	private HeroPanel oppHeroPanel;

	public Service(Hero currentHero, Hero opponentHero) {
		this.currentHero = currentHero;
		this.opponentHero = opponentHero;
		
	}

	public void init() {
		
		view = new View();

		currHeroPanel = initHeroPanel(currentHero);
		oppHeroPanel = initHeroPanel(opponentHero);
		changeOppHeroParams();

		view.getOpponent().add(oppHeroPanel);
		view.getCurrent().add(currHeroPanel);

		onHandUpdated();

		setViewComponents();

		view.setVisible(true);
		view.revalidate();
		view.repaint();

	}

	private HeroPanel initHeroPanel(Hero hero) {
		HeroPanel heroPanel = new HeroPanel(hero);
		initDim.setdim(heroPanel, 860, 180, 1000, 200);
		heroPanel.setOpaque(false);
		return heroPanel;
	}
	public JButton getPlayMinion() {
		return play;
	}
	private void changeOppHeroParams() {
		initDim.setdim(oppHeroPanel, 860, 5, 1000, 200);
		initDim.setdim(oppHeroPanel.getManaCrystals(), 410, 0, 50, 50);
		initDim.setdim(oppHeroPanel.getHeroButton(), 0, 40, 200, 180);
		oppHeroPanel.getHeroPower().setVisible(false);
	}

	private void setViewComponents() {

		endTurn = initDim.getjButton("", 1600, 350, 200, 50);
		endTurn.setBackground(Color.ORANGE);
		endTurn.setIcon(new ImageIcon("Images/endTurn.png"));
		endTurn.setOpaque(false);
		view.getOpponent().add(endTurn);

		play = initDim.getjButton("", 350, 180, 225, 90);
		play.setBackground(Color.PINK);
		play.setIcon(new ImageIcon("Images/PlayMinion.png"));
		play.setOpaque(false);
		view.getCurrent().add(play);
		
		oppDeck.setFont(new Font(style,Font.BOLD,25));
		String s="<html>  <font color=white> Deck Cards :" + opponentHero.getDeck().size() + " </font> </html>";

		oppDeck.setText(s);
		
		
		initDim.setdim(oppDeck, 1600, 150, 200, 200);
		view.getOpponent().add(oppDeck);

		oHand.setFont(new Font(style,Font.BOLD,25));
		oHand.setText("<html>  <font color=white>Hand Cards :" + opponentHero.getHand().size() +" </font> </html>");
		initDim.setdim(oHand, 300, 10, 200, 200);
		view.getOpponent().add(oHand);

		String s1="<html>  <font color=white> Deck Cards :" + currentHero.getDeck().size() + " </font> </html>";
		currDeck.setText(s1);
		currDeck.setFont(new Font(style,Font.BOLD,25));
		initDim.setdim(currDeck, 1600, 50, 200, 200);

		view.getCurrent().add(currDeck);
	}

	public void onHandUpdated() {
		cHand.clear();
		view.getCurrHand().removeAll();
		ArrayList<Card> hand = currentHero.getHand();
		for (Card currCard : hand) {
			CardButton cb;
			if (currCard instanceof Minion) {
				Minion m = (Minion) currCard;
				cb = new MinionButton(m, currentHero);
				cb.hero=currentHero;
			} else {// Spell
				cb = new CardButton(currCard, currentHero);
				cb.hero=currentHero;
			}
			cHand.add(cb);
			view.getCurrHand().add(cb);
		}

		oHand.setText("<html>  <font color=white>Hand Cards :" + opponentHero.getHand().size() +" </font> </html>");

		view.repaint();
		view.revalidate();
	}

	public void menawel() {
		if(gameover)
			return;
		onHeroUpdated(currentHero);
		onHeroUpdated(opponentHero);

		onHandUpdated();

		onFieldUpdated(currentHero);
		onFieldUpdated(opponentHero);
		
		currHeroPanel.setCurrentHP(currentHero.getCurrentHP());
		currHeroPanel.setCurrentManaCrystals(currentHero.getCurrentManaCrystals());
		
		String s="<html>  <font color=white> Deck Cards :" + opponentHero.getDeck().size() + " </font> </html>";
		oppDeck.setText(s);
		
		String s1="<html>  <font color=white> Deck Cards :" + currentHero.getDeck().size() + " </font> </html>";
		currDeck.setText(s1);
		
		oHand.setText("<html>  <font color=white>Hand Cards :" + opponentHero.getHand().size() +" </font> </html>");

     
		view.setVisible(true);
		view.revalidate();
		view.repaint();

	}

	public void onDeckUpdated(Hero hero) {
		String s="<html>  <font color=white> Deck Cards :" + opponentHero.getDeck().size() + " </font> </html>";

		oppDeck.setText(s);
		
		String s1="<html>  <font color=white> Deck Cards :" + currentHero.getDeck().size() + " </font> </html>";
		currDeck.setText(s1);

	}

	public void onSwitch(Hero curr,Hero opp) {
		currentHero=curr;
		opponentHero=opp;
		menawel();
	}
	public void onHeroUpdated(Hero hero) {
		
		if (hero == currentHero) {
			view.getCurrent().remove(currHeroPanel);
			currHeroPanel = initHeroPanel(currentHero);
			view.getCurrent().add(currHeroPanel);
			currHeroPanel.setVisible(true);

		} else {
			view.getOpponent().remove(oppHeroPanel);
			oppHeroPanel = initHeroPanel(hero);
			changeOppHeroParams();
			view.getOpponent().add(oppHeroPanel);
			oppHeroPanel.setVisible(true);

		}
		view.repaint();
		view.revalidate();
	}

	public void onFieldUpdated(Hero hero) {

		JPanel f;
		ArrayList<MinionButton> A;
		if (hero == currentHero) {
			f = view.getCurrField();
			A = cField;
		} else {
			f = view.getOppField();
			A = oField;
		}
		A.clear();
		f.removeAll();
		ArrayList<Minion> field = hero.getField();
		for (Minion m : field) {
			MinionButton mb = new MinionButton(m, hero);
			mb.hero=hero;
			A.add(mb);
			f.add(mb);
		}
		view.repaint();
		view.revalidate();
	}

	public void Disapate() {
		view.dispose();
		gameover=true;		
	}

	public HeroPanel getCurrHeroPanel() {
		return currHeroPanel;
	}

	public HeroPanel getOppHeroPanel() {
		return oppHeroPanel;
	}

	public ArrayList<MinionButton> getcField() {
		return cField;
	}

	public ArrayList<MinionButton> getoField() {
		return oField;
	}

	public ArrayList<CardButton> getcHand() {
		return cHand;
	}


	public JButton getEndTurn() {
		return endTurn;
	}

}
