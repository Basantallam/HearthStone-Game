package engine;

import model.heroes.Hero;

public interface GameListener {
	public void onGameOver(Hero l, Hero w);

//MINION

//HERO

	public void onHeroUpdated(Hero hero);
	
	public void onSwitch(Hero c,Hero o);

	public void onHandUpdated(Hero hero);

	public void onDeckUpdated(Hero hero);

	public void onFieldUpdated(Hero hero);
}