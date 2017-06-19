package dp.structuralpattern.adapter.defaultadapter;

import java.awt.event.WindowEvent;
import java.util.EventListener;

public interface WindowListener extends EventListener{
	/**
	 * 当视窗第一次变得看得见时
	 */
	public void windowOpened(WindowEvent e);
	
	/**
	 * 当用户试图从视窗的系统菜单关闭视窗时
	 */
	public void windowClosing(WindowEvent e);
	
	/**
	 * 当视窗已经因调用dispose()关闭后
	 */
    public void windowClosed(WindowEvent e);
    
    /**
     * 当视窗被最小化时
     */
    public void windowIconified(WindowEvent e);
    
    /**
     * 当视窗从最小化状态恢复成正常状态
     */
    public void windowDeiconified(WindowEvent e);
    
    /**
     * 当视窗被激活时
     */
    public void windowActivated(WindowEvent e);
    
    /**
     * 当视窗不再是激活状态时
     */
    public void windowDeactivated(WindowEvent e);
}
