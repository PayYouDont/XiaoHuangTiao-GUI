/**   
* @Title: MainPanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月4日 下午5:43:59 
*/
package com.payudon.gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/** 
* @ClassName: MainPanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月4日 下午5:43:59 
*  
*/
public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton close;
	private TopPanel topPanel;
	private TextPanel textPanel;
	private final MainJFrame frame;

	public MainPanel(final MainJFrame frame) {
		this.frame = frame;
		setLayout(null);
		setOpaque(false);
		initColseAndAddPanel();
		topPanel = new TopPanel(frame);
		add(topPanel);
		textPanel = new TextPanel(frame);
		add(textPanel);
	}
	public void initColseAndAddPanel(){
		Dimension topSize = frame.getSize();
		ImageIcon closeIcon = new ImageIcon("src/img/close.png");
		close = new JButton(closeIcon);
		close.setLocation(topSize.width-25,5);
		close.setSize(20,20);
		close.setContentAreaFilled(false);//不绘制按钮区域
		close.setBorderPainted(false);//不绘制边框
		close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					close();
				}
			}
		});
		add(close);
		ImageIcon addIcon = new ImageIcon("src/img/add.png");
		JButton add = new JButton(addIcon);
		add.setLocation(5,5);
		add.setSize(20,20);
		add.setContentAreaFilled(false);//不绘制按钮区域
		add.setBorderPainted(false);//不绘制边框
		add.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					new MainJFrame();
				}
			}
		});
		add(add);
	}
	
	private void refresh() {
		setVisible(false);
		setVisible(true);
	}
	public void refreshlSize(Dimension dimension) {
		textPanel.refreshlSize(dimension);
		topPanel.refreshlSize(dimension);
		close.setLocation(topPanel.getWidth()-25,5);
		refresh();
	}
	public void close() {
		frame.dispose();
	}
	public void topPanelShow() {
		topPanel.hideOrShow(true);
	}

	public void topPanelHide() {
		topPanel.hideOrShow(false);
	}
	
}
