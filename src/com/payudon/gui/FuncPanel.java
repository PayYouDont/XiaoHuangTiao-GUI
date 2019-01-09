/**   
* @Title: FuncPanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月7日 下午2:16:38 
*/
package com.payudon.gui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.payudon.util.ComponentUtil;

/** 
* @ClassName: FuncPanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月7日 下午2:16:38 
*  
*/
public class FuncPanel extends JPanel{
	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private ContentText text;
	public FuncPanel(ContentText text) {
		this.text = text;
		setSize(100,30);
		setLocation(text.getSize().width-120,0);
		setLocale(null);
		setOpaque(false);
		ImageIcon topImg = new ImageIcon("src/img/top.png");
		JLabel top = new JLabel(topImg);
		top.setBounds(0,0,100,100);
		top.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(true);
				if(e.getButton()==1) {
					//TextPanel textPanel= (TextPanel) e.getComponent().getParent().getParent().getParent();
					TextPanel textPanel = (TextPanel) ComponentUtil.getParentToClass(e.getComponent(),TextPanel.class);
					
					ImageIcon icon = (ImageIcon) top.getIcon();
					if(icon.toString().indexOf("top")!=-1) {
						icon = new ImageIcon("src/img/unpin.png");
						topText(textPanel);
					}else {
						icon = new ImageIcon("src/img/top.png");
						unpinText(textPanel);
					}
					top.setIcon(icon);
					textPanel.orderText();
					setVisible(false);
					refresh();
				}
			}
			public void mouseEntered(MouseEvent e) {
				setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				 setVisible(false);
			}
		});
		add(top);
		ImageIcon hookImg = new ImageIcon("src/img/hook.png");
		JLabel hook = new JLabel(hookImg);
		hook.setBounds(30,0,100,100);
		add(hook);
		ImageIcon deleteImg = new ImageIcon("src/img/delete.png");
		JLabel delete = new JLabel(deleteImg);
		delete.setBounds(60,0,100,100);
		delete.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					 TextPanel textPanel= (TextPanel) e.getComponent().getParent().getParent().getParent();
					 textPanel.remove(text);
					 ComponentUtil.refresh(text);
				}
			}
			public void mouseEntered(MouseEvent e) {
				setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				 setVisible(false);
			}
		});
		add(delete);
		setVisible(false);
	}
	
	private void topText(TextPanel textPanel) {
		if(text!=null) {
			ImageIcon pointImg = new ImageIcon("src/img/point_y.png");
			for (Component c : text.getComponents()) {
				if(c instanceof JLabel) {
					JLabel point = (JLabel) c;
					point.setIcon(pointImg);
					point.setSize(30,30);
					point.setLocation(10,0);
					text.add(point);
					text.setTop(true);
					textPanel.getTopTexts().add(text);
					break;
				}
			}
			
		}
	}
	private void unpinText(TextPanel textPanel) {
		if(text!=null) {
			ImageIcon pointImg = new ImageIcon("src/img/point.png");
			for (Component c : text.getComponents()) {
				if(c instanceof JLabel) {
					JLabel point = (JLabel) c;
					point.setIcon(pointImg);
					point.setSize(30,30);
					point.setLocation(10,0);
					text.setTop(false);
					text.add(point);
					textPanel.getTopTexts().remove(text);
					return;
				}
			}
			
		}
	}
	private void refresh() {
		setVisible(false);
		setVisible(true);
	}
}
