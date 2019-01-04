/**   
* @Title: MoveWindowListener.java 
* @Package com.payudon.listener 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月4日 下午5:04:28 
*/
package com.payudon.listener;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** 
* @ClassName: MoveWindowListener 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月4日 下午5:04:28 
*  
*/
public class MoveWindowListener extends MouseAdapter{
	private Point lastPoint = null; 
    @Override
    public void mousePressed(MouseEvent e) {
        lastPoint = e.getLocationOnScreen();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	Component c = e.getComponent();
        Point point = e.getLocationOnScreen();
        int offsetX = point.x - lastPoint.x;
        int offsetY = point.y - lastPoint.y;
        Rectangle bounds = c.getBounds();
        bounds.x += offsetX;
        bounds.y += offsetY;
        c.setBounds(bounds);
        lastPoint = point;
    }
}
