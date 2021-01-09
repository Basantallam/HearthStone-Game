package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KListener implements KeyListener {

	ExceptionFrame ef;
	CardBurnedFrame cbf;
	
	public KListener(ExceptionFrame ef) {
		super();
		this.ef=ef;
	}
	public KListener(CardBurnedFrame cbf) {
		super();
		this.cbf=cbf;
	}
	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode()==KeyEvent.VK_ENTER){
	       if(ef!=null)
	    	   ef.dispose();
	       if(cbf!=null)
	    	   cbf.dispose();
	    }
	}

	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
