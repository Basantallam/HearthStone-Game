package GUI;

import static GUI.View.style;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import service.initDim;

public class ExceptionFrame extends JFrame implements  ActionListener{

	JLabel label; 
	String s;
	JButton OK;
	KeyListener keylistener;
	public ExceptionFrame(Exception e) {
		super();
		this.setLayout(null);
        s =e.getMessage();
        label= new JLabel(s);
		label.setFont(new Font(style,Font.BOLD,30));

        initDim.setdim(this, 400, 200, 750, 400);
        initDim.size(this, 750, 400);
        initDim.setdim(label, 200, 250, 500, 200);
        
        this.add(label);
        label.setBounds(80,50,500,120);
        
        OK=new JButton("OK");
        OK.setText("OK");
        OK.setBounds(500, 150, 60, 50);
        OK.addActionListener(this);
        keylistener=new KListener(this);
        this.add(OK);
        OK.addKeyListener(keylistener);
//        this.setBackground(Color.getHSBColor(0, 74, 64));
        this.setVisible(true);
        this.repaint();
	}
	
	public static void main(String args[]) {
		ExceptionFrame ef=new ExceptionFrame(new NullPointerException());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("OK"))
			this.dispose();
		
	}
}
