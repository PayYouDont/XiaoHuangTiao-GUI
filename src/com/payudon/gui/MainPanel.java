/**   
* @Title: MainPanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月4日 下午5:43:59 
*/
package com.payudon.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.payudon.util.ComponentUtil;
import com.payudon.util.StyleUtil;

/** 
* @ClassName: MainPanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月4日 下午5:43:59 
*  
*/
public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel title;
	private JLabel Done;
	private JLabel lock;
	private JLabel synchronize;
	private JLabel setting;
	public MainPanel(MainJFrame frame) {
		setLayout(null);
		setBackground(new Color(130,130,130,0));
		initTopPanel(frame);
		initTextPanel();
		
	}
	public void initTopPanel(MainJFrame frame){
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBounds(0, 0,400,60);
		topPanel.setBackground(new Color(0,0,0,0));
		//todo
		title = new JLabel(StyleUtil.getLabelHtml("Todo",22,true));
		title.setName("todo");
		title.setForeground(Color.white);
		title.setBounds(30, 0,100,80);
		topPanel.add(title);
		//done
		Done = new JLabel(StyleUtil.getLabelHtml("Done",16,true));
		Done.setName("done");
		Done.setForeground(new Color(170,170,170));
		Done.setBounds(120,3,100,80);
		Done.setVisible(false);
		topPanel.add(Done);
		//lock
		ImageIcon lockImg = new ImageIcon("src/img/lock_up.png");
		lock = new JLabel(lockImg);
		lock.setName("lock");
		lock.setBounds(200,25,30,30);
		lock.setVisible(false);
		lock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				topPanelHideOrShow(true);
				if(e.getButton()==1) {
					ImageIcon icon = (ImageIcon) lock.getIcon();
					if(icon.toString().indexOf("lock_down")!=-1) {
						icon = new ImageIcon("src/img/lock_up.png");
						frame.notFixed();
					}else {
						icon = new ImageIcon("src/img/lock_down.png");
						frame.fixed();
					}
					lock.setIcon(icon);
					setVisible(false);
					setVisible(true);
				}
			}
			@Override
		    public void mouseEntered(MouseEvent e) {
				topPanelShow();
		    	ComponentUtil.refresh(topPanel);
			}
		});
		topPanel.add(lock);
		//synchronize
		ImageIcon synchronizeImg = new ImageIcon("src/img/synchronize.png");
		synchronize = new JLabel(synchronizeImg);
		synchronize.setName("synchronize");
		synchronize.setBounds(230,25,30,30);
		synchronize.setVisible(false);
		add(synchronize);
		//setting
		ImageIcon settingImg = new ImageIcon("src/img/setting.png");
		setting = new JLabel(settingImg);
		setting.setName("setting");
		setting.setBounds(260,25,30,30);
		setting.setVisible(false);
		topPanel.add(setting);
		add(topPanel);
	}
	
	public void initTextPanel() {
		JPanel text = new JPanel();
		text.setBorder(ComponentUtil.getBorder(Color.red));
		text.setBackground(new Color(0,0,0,0));
		text.setBounds(0,60,400,270);
		add(text);
	}
	public void topPanelShow() {
		topPanelHideOrShow(true);
	}
	public void topPanelHide() {
		topPanelHideOrShow(false);
	}
	private void topPanelHideOrShow(boolean isShow) {
    	Done.setVisible(isShow);
    	lock.setVisible(isShow);
    	synchronize.setVisible(isShow);
    	setting.setVisible(isShow);
	}
}
