package GUI;

import model.heroes.*;
import service.initDim;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static GUI.View.style;

public class HeroButton  extends JButton {

    private Hero hero;



    public HeroButton(Hero hero) {
        super(hero.getName());
        this.hero = hero;
        initDim.setdim(this,0,0,200,200);
        String n ="<html>  <font color=white > "+hero.getName()+"</font> </html>";
//        n.setBorder(new LineBorder(Color.black,6));
        this.setText(n);
        this.setFont(new Font(style,Font.BOLD,14));
        this.setBorderPainted(true);
        this.setHorizontalTextPosition(SwingConstants.CENTER);

        this.setActionCommand("Hero");
        Iconn();
        repaint();
        revalidate();

    }

    private void Iconn() {
        String n = hero instanceof Mage? "Mage":hero instanceof Hunter? "Hunter" :hero instanceof Paladin? "Paladin"
                :hero instanceof Priest? "Priest":"Warlock";

         this.setIcon(new ImageIcon("Images\\Hero\\"+n +".png"));
    }

    public Hero getHero() {
        return hero;
    }

    public static void main(String[] args) {

    }
}
