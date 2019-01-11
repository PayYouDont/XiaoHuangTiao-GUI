/**   
* @Title: FuncPanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月7日 下午2:16:38 
*/
package com.payudon.gui;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.payudon.entity.Note;
import com.payudon.listener.TopShowMouseAdapter;
import com.payudon.util.ComponentUtil;
import com.payudon.util.StyleUtil;

/** 
* @ClassName: FuncPanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月7日 下午2:16:38 
*  
*/
public class FuncTodoPanel extends JPanel{
	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private ContentText text;
	private class TopMouseListener extends TopShowMouseAdapter{}
	public FuncTodoPanel(ContentText text) {
		this.text = text;
		setSize(100,30);
		setName("funcTodoPanel");
		setLocation(text.getSize().width-120,0);
		setLocale(null);
		setOpaque(false);
		ImageIcon topImg = new ImageIcon(StyleUtil.getIconBasePath()+"top.png");
		JLabel top = new JLabel(topImg);
		top.setBounds(0,-2,100,100);
		top.addMouseListener(new TopMouseListener() {
			public void mouseClicked(MouseEvent e) {
				setVisible(true);
				if(e.getButton()==1) {
					TodoPanel textPanel = (TodoPanel) ComponentUtil.getParentToClass(e.getComponent(),TodoPanel.class);
					ImageIcon icon = (ImageIcon) top.getIcon();
					if(icon.toString().indexOf("top")!=-1) {
						icon = new ImageIcon(StyleUtil.getIconBasePath()+"unpin.png");
						topText(textPanel);
					}else {
						icon = new ImageIcon(StyleUtil.getIconBasePath()+"top.png");
						unpinText(textPanel);
					}
					top.setIcon(icon);
					textPanel.orderText();
					setVisible(false);
					refresh();
				}
			}
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				setVisible(false);
			}
		});
		add(top);
		ImageIcon hookImg = new ImageIcon(StyleUtil.getIconBasePath()+"hook.png");
		JLabel hook = new JLabel(hookImg);
		hook.setBounds(30,-2,100,100);
		hook.addMouseListener(new TopMouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					Note note = ComponentUtil.parseContentText(text);
					if(note.getText().trim().isEmpty()) {
			    		return;
			    	}
					MainPanel mainPanel= (MainPanel) ComponentUtil.getParentToClass(e.getComponent(),MainPanel.class);
					mainPanel.getDonePanel().addDoneText(note);
					mainPanel.getTodoPanel().remove(text);
				}
			}
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				setVisible(false);
			}
		});
		add(hook);
		ImageIcon deleteImg = new ImageIcon(StyleUtil.getIconBasePath()+"delete.png");
		JLabel delete = new JLabel(deleteImg);
		delete.setBounds(60,-2,100,100);
		delete.addMouseListener(new TopMouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					TodoPanel textPanel= (TodoPanel) ComponentUtil.getParentToClass(e.getComponent(),TodoPanel.class);
					textPanel.remove(text);
					ComponentUtil.refresh(textPanel);
				}
			}
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				setVisible(false);
			}
		});
		add(delete);
		setVisible(false);
	}
	private void topText(TodoPanel textPanel) {
		if(text!=null) {
			ImageIcon pointImg = new ImageIcon(StyleUtil.getIconBasePath()+"point_y.png");
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
	private void unpinText(TodoPanel textPanel) {
		if(text!=null) {
			ImageIcon pointImg = new ImageIcon(StyleUtil.getIconBasePath()+"point.png");
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
	public void refreshLocation(ContentText text) {
		setLocation(text.getSize().width-120,0);
	}
}
