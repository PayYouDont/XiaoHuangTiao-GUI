package com.payudon.listener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import com.payudon.gui.MainJFrame;


/** 
* @ClassName: MyFocusListener 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月20日 下午4:51:27 
*  
*/
public class MyFocusListener implements FocusListener{

	/** 
	 * <p>Title: focusGained</p> 
	 * <p>Description: </p> 
	 * @param e 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月20日 下午4:51:48
	 */
	@Override
	public void focusGained(FocusEvent e) {
		Component c = e.getComponent();
		if(c instanceof JComponent) {
			if(c instanceof JTextArea) {
				c = c.getParent().getParent();
			}
			((JComponent)c).setBorder(MainJFrame.getBorder(new Color(51, 135, 255)));
		}
	}

	/** 
	 * <p>Title: focusLost</p> 
	 * <p>Description: </p> 
	 * @param e 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月20日 下午4:51:48
	 */
	@Override
	public void focusLost(FocusEvent e) {
		Component c = e.getComponent();
		if(c instanceof JComponent) {
			if(c instanceof JTextArea) {
				c = c.getParent().getParent();
			}
			((JComponent)c).setBorder(MainJFrame.getBorder(Color.gray));
		}
	}
	
}
