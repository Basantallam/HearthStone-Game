package GUI;

import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;
import service.initDim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class CardBurnedFrame  extends JFrame implements  ActionListener{

	JLabel label; 
	String s;
	JButton OK;
	KeyListener keylistener;
	
	public CardBurnedFrame(Card c, Hero h, Exception e) {
		super();
		this.setLayout(null);
		String s = e.getMessage() + "\n"+ " " + "CARD BURNED";
        label= new JLabel(s);
        
        initDim.setdim(this, 800, 200, 700, 400);
        initDim.size(this, 650, 300);
        initDim.setdim(label, 50, 50, 300, 100);

        CardButton cb = c instanceof Minion ?new MinionButton((Minion) c,h) : new CardButton(c,h);;

        this.add(cb);
        this.add(label);
        label.setBounds(20,20,500,50);
        cb.setBounds(300,50,150,140);
        
        OK=new JButton("OK");
        OK.setText("OK");
        OK.setBounds(500, 150, 60, 50);
        OK.addActionListener(this);
        keylistener=new KListener(this);
        this.add(OK);
        OK.addKeyListener(keylistener);
        this.setVisible(true);
        this.repaint();
	}
	
//public static void main(String args[]) throws IOException, CloneNotSupportedException, IOException {
//	Minion m = new Minion("hamada",7,Rarity.BASIC, 7,9,true,false,true);
//	Hero h = new Hunter();
//CardBurnedFrame cbf =new CardBurnedFrame(m,h);
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("OK"))
			this.dispose();
		
	}
}