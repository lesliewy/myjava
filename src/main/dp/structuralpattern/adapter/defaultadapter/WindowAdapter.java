package dp.structuralpattern.adapter.defaultadapter;

import java.awt.event.WindowEvent;

/**
 * 抽象的适配器类实现接口,此抽象类有要给接口所要求的每一种方法都提供一个空的方法
 * 可以使它的具体子类免于被迫实现空的方法.
 * @author leslie
 *
 */
public abstract class WindowAdapter implements WindowListener{

	public void windowOpened(WindowEvent e){
		
	}
	public void windowClosing(WindowEvent e){
		
	}
    public void windowClosed(WindowEvent e){
    	
    }
    public void windowIconified(WindowEvent e){
    	
    }
    public void windowDeiconified(WindowEvent e){
    	
    }
    public void windowActivated(WindowEvent e){
    	
    }
    public void windowDeactivated(WindowEvent e){
    	
    }
}
