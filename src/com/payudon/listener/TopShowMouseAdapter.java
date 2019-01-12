/**   
* @Title: TopShowMouseLitener.java 
* @Package com.payudon.listener 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月11日 上午9:20:04 
*/
package com.payudon.listener;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.payudon.gui.MainJFrame;
import com.payudon.gui.MainPanel;
import com.payudon.util.ComponentUtil;

/** 
* @ClassName: TopShowMouseLitener 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月11日 上午9:20:04 
*  
*/
public abstract class TopShowMouseAdapter extends MouseAdapter{
	private MainPanel mainPanel;
    public void mouseEntered(MouseEvent e) {
    	Component c = e.getComponent();
    	MainJFrame mainJFrame = ComponentUtil.getParentToClass(c, MainJFrame.class);
    	mainPanel = (MainPanel) mainJFrame.getContentPane();
    	mainPanel.topPanelShow();
    	if(mainPanel.hasBorder()) {
    		mainPanel.BorderShow();
    	}
    }
    public void mouseExited(MouseEvent e) {
    	if(mainPanel!=null) {
    		mainPanel.topPanelHide();
    	}
    	if(mainPanel.hasBorder()) {
    		mainPanel.BorderHide();
    	}
    }
}
