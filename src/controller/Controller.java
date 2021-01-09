package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import GUI.CardButton;
import GUI.ChooseHero;
import GUI.GameIntro;
import GUI.GameOver;
import GUI.MinionButton;
import GUI.PlayerNames;
import GUI.Winner;
import engine.Game;
import engine.GameListener;
import exceptions.FullHandException;
import model.heroes.Hero;
import service.Service;
import service.TakeActions;

public class Controller implements ActionListener, GameListener {
	private Game game;
	private Service service;
	private TakeActions takeActions;
	private boolean gameover;
	public Controller() throws InterruptedException, FullHandException, CloneNotSupportedException {
		new GameIntro();
		
		PlayerNames pn=new PlayerNames();
		String name1=pn.name1;
		String name2=pn.name2;
		
		ChooseHero ch1 = new ChooseHero(name1);
		ChooseHero ch2 = new ChooseHero(name2);

		game = new Game(ch1.getH(), ch2.getH());
		game.setListener(this);

		Hero currentHero = game.getCurrentHero();
		Hero opponent = game.getOpponent();

		service = new Service(currentHero, opponent);
		takeActions = new TakeActions(game,service);

		service.init();
		setListenerscomp();
		setListeners();

	}

	private void setListenerscomp() {
		service.getPlayMinion().addActionListener(this);
		service.getEndTurn().addActionListener(this);
	}

	private void setListeners() {
		service.getCurrHeroPanel().getHeroButton().addActionListener(this);
		service.getCurrHeroPanel().getHeroPower().addActionListener(this);
		service.getOppHeroPanel().getHeroButton().addActionListener(this);

		for (MinionButton b : service.getcField())
			b.addActionListener(this);

		for (CardButton b : service.getcHand())
			b.addActionListener(this);

		for (MinionButton b : service.getoField())
			b.addActionListener(this);

	}

	@Override
	public void onGameOver(Hero loser, Hero Winner) {

		service.Disapate();
		gameover=true;
		  
        GameOver go =new GameOver(loser,Winner);

//        try {
//            Thread.sleep(3000);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        go.dispose();
//
//        Winner w  = game.getOpponent()!=loser ? new Winner(game.getOpponent()): new Winner(game.getCurrentHero());
    }

	@Override
	public void onHeroUpdated(Hero hero) {
		service.onHeroUpdated(hero);
	}

	@Override
	public void onHandUpdated(Hero hero) {
		service.onHandUpdated();

	}

	@Override
	public void onDeckUpdated(Hero hero) {
		service.onDeckUpdated(hero);

	}

	public void onSwitch(Hero c, Hero o) {
		service.onSwitch(c, o);
		takeActions.onSwitch(game);

	}

	@Override
	public void onFieldUpdated(Hero hero) {
		service.onFieldUpdated(hero);

	}

	public void actionPerformed(ActionEvent e) {
		if (takeActions.takeAction(e)) {
			service.menawel();
			setListeners();
		}

	}
	
	

	public static void main(String[] args) throws InterruptedException, CloneNotSupportedException, FullHandException {
		new Controller();
	}

}
