package model.heroes;

import java.util.ArrayList;

import exceptions.FullHandException;
import model.cards.Card;
import model.cards.minions.Minion;

public interface HeroListener {
	//MINION
	public void onHeroDeath(Hero hero);

	public void damageOpponent(int amount);
	
	public void endTurn() throws FullHandException, CloneNotSupportedException;
	


	//HERO
	
	public void onHeroUpdated(Hero hero);
	
	public void onDeckUpdated(Hero hero);
	
	public void onHandUpdated(Hero hero);

	public void onFieldUpdated(Hero hero);
		
}
