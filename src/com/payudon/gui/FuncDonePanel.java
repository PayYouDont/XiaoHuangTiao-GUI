/**   
* @Title: FuncDonePanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月9日 下午4:15:21 
*/
package com.payudon.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.payudon.util.ComponentUtil;

/** 
* @ClassName: FuncDonePanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月9日 下午4:15:21 
*  
*/
public class FuncDonePanel extends JPanel{
	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private ContentText text;
	public FuncDonePanel(ContentText text) {
		this.text = text;
		setSize(70,30);
		setLocation(text.getSize().width-120,0);
		setLocale(null);
		setOpaque(false);
		ImageIcon revokeImg = new ImageIcon("src/img/revoke.png");
		JLabel revoke = new JLabel(revokeImg);
		revoke.setBounds(0,0,25,25);
		revoke.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(true);
				if(e.getButton()==1) {
					System.out.println(e);
				}
			}
			public void mouseEntered(MouseEvent e) {
				setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				 setVisible(false);
			}
		});
		ImageIcon deleteImg = new ImageIcon("src/img/delete.png");
		JLabel delete = new JLabel(deleteImg);
		delete.setBounds(30,0,25,25);
		delete.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					TodoPanel textPanel= (TodoPanel) ComponentUtil.getParentToClass(e.getComponent(), TodoPanel.class);
					 if(text.isTop()) {
							textPanel.getTopTexts().remove(text);
					 }
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
}