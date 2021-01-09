package model.cards.minions;

public interface MinionListener {
	
	public void onMinionDeath(Minion m);
	
	public void onFieldUpdated();

	public void onDeckUpdated();
	
}
