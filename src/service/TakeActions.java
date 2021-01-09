package service;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import GUI.*;
import engine.Game;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.minions.Minion;
import model.cards.spells.*;
import model.heroes.Hero;
import model.cards.*;
import model.heroes.Mage;
import model.heroes.Priest;

public class TakeActions {

    private Game game;
    private JButton temp1;
    Service s;

    public TakeActions(Game game,Service s) {
        this.game = game;
        this.s=s;
    }
    public void NullButton(){
        if (temp1 != null)
            temp1.setIcon(null);
        temp1 = null;
    }

    public boolean takeAction(ActionEvent e) {
        {

            try {
                if (e.getSource()==s.getEndTurn()) {
                    playSound("sounds/click.wav");
                    game.endTurn();
                    NullButton();


                } else if (e.getSource() == temp1) {
                    playSound("sounds/click.wav");
                    NullButton();


                } else if (e.getSource()==s.getCurrHeroPanel().getHeroPower()) {
                	System.out.println("use hero power pressed");
                    playSound("sounds/click.wav");
                    Hero h = game.getCurrentHero();
                    NullButton();

//ezay useHeroPower momken te throw notYourTurnException w ehna 3andena only one useHeroPower button 3and el current bas?
//                    Aya: incase en kont 3amla display lel usehero power el tania
                    if(h instanceof Mage || h instanceof Priest){
                        temp1 = (JButton) e.getSource();
                        temp1.setIcon(new ImageIcon("images/tick.png"));
                    }
                    else{
                    h.useHeroPower();}



                } else if (e.getActionCommand().equals("Hero") ) heroAction(e.getSource());
                else if (e.getActionCommand().equals( "Minion")) {
                    MinionAction(e.getSource());
                } else if (e.getActionCommand().equals("Spell")  && temp1 == null) {
                    SpellAction(e.getSource());


                } else if (temp1 != null && temp1 instanceof MinionButton && e.getSource()==s.getPlayMinion()) {
                    playSound("sounds/play minion.wav");
                    Minion m = ((MinionButton) temp1).getMinion();
                    game.getCurrentHero().playMinion(m);
                    NullButton();



                }
                return temp1 == null;

            } catch (NotSummonedException | FullHandException | CloneNotSupportedException | NotEnoughManaException
                    | HeroPowerAlreadyUsedException | NotYourTurnException | FullFieldException | CannotAttackException
                    | TauntBypassException | InvalidTargetException e1) {
            	if (e1 instanceof FullHandException) {
					playSound("sounds/fire.wav");
					CardBurnedFrame cbf = new CardBurnedFrame((Card) (((FullHandException) e1).getBurned()),
							game.getCurrentHero(),e1);
				} else {
					ExceptionFrame ef = new ExceptionFrame(e1);
					playSound("sounds/exception.wav");
				}
				return false;
            }
        }

        }

    private void SpellAction(Object source) throws NotEnoughManaException, NotYourTurnException {

        Spell s =  (Spell) (((CardButton)source).getSpell());
        if (s instanceof AOESpell) {
            playSound("sounds/spell sound.wav");
            ArrayList<Minion> oppF = game.getOpponent().getField();
            ((CardButton) source).getHero().castSpell((AOESpell) s,oppF);
            ((JButton) source).setIcon(new ImageIcon("images/casted.png"));



        } else if (s instanceof FieldSpell) {
            playSound("sounds/spell sound.wav");
            ArrayList<Minion> curF = game.getCurrentHero().getField();
            ((CardButton) source).getHero().castSpell((FieldSpell) s);
            ((JButton) source).setIcon(new ImageIcon("images/casted.png"));



        } else {
            temp1 = (CardButton) source;
            Tick();

        }

}

    private void Tick() {
        ((CardButton)temp1).getTick().setVisible(true);
    }

    private void MinionAction(Object source) throws InvalidTargetException, NotYourTurnException, TauntBypassException, CannotAttackException, NotSummonedException, NotEnoughManaException, FullFieldException, CloneNotSupportedException, FullHandException, HeroPowerAlreadyUsedException {
        Minion m2 = ((MinionButton) source).getMinion();
        if (temp1!=null && temp1 instanceof CardButton && !(temp1 instanceof MinionButton)) {
            Spell s = (Spell) ((CardButton)temp1).getSpell();

            if (s instanceof MinionTargetSpell|| s instanceof LeechingSpell) {
                if(s instanceof MinionTargetSpell)
                ((CardButton) temp1).getHero().castSpell((MinionTargetSpell) s,m2);
                else
                    ((CardButton) temp1).getHero().castSpell((LeechingSpell) s,m2);
                NullButton();
                playSound("sounds/spell sound.wav");
            }
        } else if (temp1!=null &&temp1 instanceof MinionButton) {

            Minion m1 = ((MinionButton) temp1).getMinion();

            ((MinionButton) temp1).getHero().attackWithMinion(m1, m2);
            playSound("sounds/hit.wav");
            NullButton();

        }else if((temp1 != null) && temp1.getActionCommand().equals("Use Hero Power")){
            Hero h = game.getCurrentHero();
            if ((h instanceof Mage)) {
                ((Mage) h).useHeroPower(m2);
            } else {
                ((Priest) h).useHeroPower(m2);
            }
            NullButton();

        } else if (temp1 == null) {
            temp1 = (CardButton) source;
            Tick();
            playSound("sounds/click.wav");

        }
    }

    private void heroAction(Object source) throws NotYourTurnException, TauntBypassException, NotSummonedException, CannotAttackException, InvalidTargetException, NotEnoughManaException, FullFieldException, CloneNotSupportedException, FullHandException, HeroPowerAlreadyUsedException {
        Hero hero = ((HeroButton)source).getHero();
        if (temp1!=null && !(temp1 instanceof MinionButton) && (temp1 instanceof CardButton)) { //Spell ya3ni
            Spell s = (Spell) ((CardButton)temp1).getSpell();

            if (s instanceof HeroTargetSpell) {
                ((CardButton) temp1).getHero().castSpell(((HeroTargetSpell) s),hero);
               NullButton();
                playSound("sounds/spell sound.wav");

            }
        } else if (temp1!=null && temp1 instanceof MinionButton) {
            Hero h = ((MinionButton) temp1).getHero();
            h.attackWithMinion(((MinionButton) temp1).getMinion(),hero) ;
            NullButton();
            playSound("sounds/hit.wav");
//((MinionButton) source).getMinion())
        }else if((temp1 != null) && temp1.getActionCommand().equals("Use Hero Power")){
                Hero h = game.getCurrentHero();
                if ((h instanceof Mage)) {
                    ((Mage) h).useHeroPower(hero);
                } else {
                    ((Priest) h).useHeroPower(hero);
                }
                NullButton();

        }
    }
	public void onSwitch(Game game) {
		this.game=game;
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

}
