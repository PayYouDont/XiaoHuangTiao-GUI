/**   
* @Title: MoveWindowListener.java 
* @Package com.payudon.listener 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月4日 下午5:04:28 
*/
package com.payudon.listener;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.payudon.gui.MainPanel;
import com.payudon.util.ComponentUtil;

/**
 * @ClassName: MoveWindowListener
 * @Description: TODO( )
 * @author peiyongdong
 * @date 2019年1月4日 下午5:04:28
 * 
 */
public class MoveWindowListener extends MouseAdapter {
	private Point lastPoint = null;
	private boolean N = false;//北
    private boolean S = false;//南
    private boolean E = false;//东
    private boolean W = false;//西
    private boolean NW = false;//西北
    private boolean NE = false;//东北
    private boolean SW = false;//西南
    private boolean SE = false;//东南
	@Override
	public void mousePressed(MouseEvent e) {
		lastPoint = e.getLocationOnScreen();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		JFrame c = (JFrame) e.getComponent();
		if(isResetSize(e)) {
			MainPanel mainPanel = (MainPanel)c.getContentPane();
			mainPanel.refreshlSize(c.getSize());
			return;
		}
		Point point = e.getLocationOnScreen();
		int offsetX = point.x - lastPoint.x;
		int offsetY = point.y - lastPoint.y;
		Rectangle bounds = c.getBounds();
		bounds.x += offsetX;
		bounds.y += offsetY;
		c.setBounds(bounds);
		lastPoint = point;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JFrame MainFrame = (JFrame) e.getComponent();
		MainPanel MainPanel = (MainPanel) MainFrame.getContentPane();
		MainPanel.topPanelShow();
		ComponentUtil.refresh(MainPanel);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JFrame MainFrame = (JFrame) e.getComponent();
		MainPanel MainPanel = (MainPanel) MainFrame.getContentPane();
		MainPanel.topPanelHide();
		ComponentUtil.refresh(MainPanel);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Component c = e.getComponent();
		int x =e.getX();
		int y = e.getY();
		int width = c.getWidth();
		int height = c.getHeight();
		if (y==0&&x>2&&x<width-2) {
			c.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
			N = true;
		} else if (height-y<=1&&x>2&&x<width-2) {
			c.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
			S = true;
		} else if (x==0&&y>2&&y<height-2) {
			c.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
			W = true;
		} else if (width-x<=1&&y>2&&y<height-2) {
			c.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
			E = true;
		} else if (x<=2&&y<=2) {
			c.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
			NW = true;
			N = false;
			W = false;
		} else if (width-x<=2&&height-y<=2) {
			c.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
			SE = true;
			S = false;
			E = false;
		} else if (width-x<=2&&y<=2) {
			c.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
			NE = true;
			N = false;
			E = false;
		} else if (x<=2&&height-y<=2) {
			c.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
			SW = true;
			S = false;
			W = false;
		} else {
			c.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			N = false;//北
		    S = false;//南
		    E = false;//东
		    W = false;//西
		    NW = false;//西北
		    NE = false;//东北
		    SW = false;//西南
		    SE = false;//东南
		}
	}
	private boolean isResetSize(MouseEvent e) {
		Component c = e.getComponent();
	 	Dimension dimension = c.getSize();
		if(N) {
			dimension.setSize(c.getWidth(),c.getHeight()-e.getY());
			c.setLocation(c.getLocationOnScreen().x, c.getLocationOnScreen().y + e.getY());
		}else if(S) {
			dimension.setSize(c.getWidth(),e.getY());
		}else if(E) {
			dimension.setSize(e.getX(),c.getHeight());
		}else if(W) {
			dimension.setSize(c.getWidth()-e.getX(),c.getHeight());
			c.setLocation(c.getLocationOnScreen().x+e.getX(), c.getLocationOnScreen().y);
		}else if(NW) {
			dimension.setSize(c.getWidth()-e.getX(),c.getHeight()-e.getY());
			c.setLocation(c.getLocationOnScreen().x+e.getX(),c.getLocationOnScreen().y + e.getY());
		}else if(NE) {
			dimension.setSize(e.getX(),c.getHeight()-e.getY());
			c.setLocation(c.getLocationOnScreen().x, c.getLocationOnScreen().y + e.getY());
		}else if(SW) {
			dimension.setSize(c.getWidth()-e.getX(),e.getY());
			c.setLocation(c.getLocationOnScreen().x+e.getX(), c.getLocationOnScreen().y);
		}else if(SE) {
			dimension.setSize(e.getX(),e.getY());
		}
		c.setSize(dimension);
		return N||S||E||W||NW||NE||SW||SE;
	}
}
