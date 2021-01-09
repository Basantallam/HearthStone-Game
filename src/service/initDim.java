package service;

import javax.swing.*;
import java.awt.*;

public class initDim {
   private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static final int screenWidth = screenSize.width;
    private static final int screenheight = screenSize.height;

    private static double xCoordinate = (screenWidth/1920.0), yCoordinate = (screenheight)/1080.0, width = xCoordinate, height = yCoordinate;



    public static JButton getjButton(String n, int x1, int y1, int w1, int h1) {
        JButton b = new JButton(n);
        if(n!="") {
        	b.setText(n);
        }
        b.setName(n);
        setdim(b, x1, y1, w1, h1);
        return b;
    }

    public static void setdim(Component l, int x1, int y1, int w1, int h1) {

        l.setBounds((int) (xCoordinate * x1), (int) (yCoordinate * y1), (int) (width * w1), (int) (height * h1));
    }

    public static void size(Component l, int w1, int h1) {

        l.setSize((int) (width * w1), (int) (height * h1));

    }

    public static void prefSize(Component l, int w1, int h1) {

        l.setPreferredSize(new Dimension((int) (width * w1), (int) (height * h1)));
    }


}
