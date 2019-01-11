/**   
* @Title: SettingPanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月11日 上午11:32:57 
*/
package com.payudon.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.UIManager;

import com.payudon.util.StyleUtil;

/** 
* @ClassName: SettingPanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月11日 上午11:32:57 
*  
*/
public class SettingFrame extends JFrame{

	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	public static boolean isShow = false;
	public static boolean isLogin = false;
	String[] fontSize = {"14","16","18","20","22","24"};
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SettingFrame() {
		setLayout(null);
		//设置透明
		setUndecorated(true);
		setBackground(new Color(0,0,0,130));
		//设置最小窗口
		setSize(720,405);
		//关闭窗体后终止程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dispose();
		init();
		// 设置界面可见
		setVisible(true);
		// Frame在窗体居中
		setLocationRelativeTo(null); 
	}
	public void init() {
		JRootPane root = getRootPane();
		root.add(row1());
		root.add(row2());
		root.add(row3());
		root.add(row4());
	}
	public JPanel row1() {
		JPanel row = getRowPanel();
		row.setSize(getSize().width-80,60);
		//
		JLabel title = creatLabel(StyleUtil.getLabelHtml("设置",24, true));
		title.setLocation(0, 0);
		//
		ImageIcon userIconImg = new ImageIcon(StyleUtil.getIconBasePath()+"user_icon.png");
		JLabel userIcon = new JLabel(userIconImg);
		userIcon.setSize(30,30);
		userIcon.setLocation(row.getWidth()*2/7,10);
		//
		JLabel userName = creatLabel(StyleUtil.getLabelHtml("登陆",16, true));
		userName.setLocation(row.getWidth()*2/7+30,-2);
		//
		ImageIcon exitIconImg = new ImageIcon(StyleUtil.getIconBasePath()+"exit.png");
		JLabel exitIcon = new JLabel(exitIconImg);
		exitIcon.setSize(30,30);
		exitIcon.setLocation(row.getWidth()*5/7+50,10);
		exitIcon.setVisible(false);
		if(isLogin) {
			exitIcon.setVisible(true);
		}
		//
		JLabel exit = creatLabel(StyleUtil.getLabelHtml("退出",16, true));
		exit.setForeground(new Color(108,232,114));
		exit.setSize(100,50);
		exit.setLocation(row.getWidth()*5/7+80, -2);
		exit.setVisible(false);
		if(isLogin) {
			exit.setVisible(true);
		}
		row.add(title);
		row.add(userIcon);
		row.add(userName);
        row.add(exitIcon);
        row.add(exit);
        return row;
	}
	public JPanel row2() {
		JPanel row = getRowPanel();
		JLabel col1 = creatLabel(StyleUtil.getLabelHtml("图层位置",16, true));
		col1.setSize(100,50);
		col1.setLocation(0, -5);
		JRadioButton col2 = getCheck(StyleUtil.getLabelHtml("嵌入桌面",16, true));
		col2.setSize(140,40);
		col2.setLocation(row.getWidth()*1/5,0);
		col2.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
			}
		});
		JRadioButton col3 = getCheck(StyleUtil.getLabelHtml("普通",16, true));
		col3.setSize(140,40);
		col3.setLocation(row.getWidth()*2/5+30,0);
		col3.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
			}
		});
		JRadioButton col4 = getCheck(StyleUtil.getLabelHtml("最顶层",16, true));
		col4.setSize(140,40);
		col4.setLocation(row.getWidth()*3/5+30,0);
		col4.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
			}
		});
		ButtonGroup group = new ButtonGroup();
		group.add(col2);
		group.add(col3);
		group.add(col4);
		row.add(col1);
		row.add(col2);
		row.add(col3);
		row.add(col4);
		return row;
	}
	public JPanel row3() {
		JPanel row = getRowPanel();
		JLabel col1 = creatLabel(StyleUtil.getLabelHtml("界面风格",16, true));
		col1.setSize(100,50);
		col1.setLocation(0, -5);
		JRadioButton col2 = getCheck(StyleUtil.getLabelHtml("极简",16, true));
		col2.setSize(140,40);
		col2.setLocation(row.getWidth()*1/5,0);
		col2.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
			}
		});
		JRadioButton col3 = getCheck(StyleUtil.getLabelHtml("透明",16, true));
		col3.setSize(140,40);
		col3.setLocation(row.getWidth()*2/5+30,0);
		col3.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
			}
		});
		ButtonGroup group = new ButtonGroup();
		group.add(col2);
		group.add(col3);
		row.add(col1);
		row.add(col2);
		row.add(col3);
		return row;
	}
	public JPanel row4() {
		JPanel row = getRowPanel();
		JLabel col1 = creatLabel(StyleUtil.getLabelHtml("文字大小",16, true));
		col1.setSize(100,50);
		col1.setLocation(0, -5);
		JComboBox<String> box = new JComboBox<>(formatFontSize());
		box.setSize(50,50);
		box.setLocation(row.getWidth()*1/5,0);
		box.setSelectedIndex(0);
		box.setFocusable(false);
		box.setOpaque(false);
		box.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		row.add(col1);
		row.add(box);
		return row;
	}
	public JRadioButton getCheck(String text) {
		ImageIcon selectImg = new ImageIcon(StyleUtil.getIconBasePath()+"unselected.png");
		JRadioButton check = new JRadioButton(text);
		check.setSize(140,40);
		check.setIcon(selectImg);
		check.setForeground(Color.white);
		check.setOpaque(false);
		check.setFocusPainted(false);
		return check;
	}
	public JPanel getRowPanel() {
		JPanel row = new JPanel();
		row.setOpaque(false);
		row.setSize(getSize().width-80,40);
		row.setLayout(null);
		int count = getRootPane().getComponentCount();
		Component[] panels = getRootPane().getComponents();
		int y = count>2?panels[count-1].getY()+panels[count-1].getHeight():50;
		row.setLocation(40,y);
		return row;
	}
	public JLabel creatLabel(String text) {
		JLabel label = new JLabel(text);
		label.setForeground(Color.white);
		label.setSize(80,50);
		return label;
	}
	public static void main(String[] args) {
		new SettingFrame();
	}
	private class CheckListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			ImageIcon selectImg;
			JRadioButton box = (JRadioButton) e.getItem();
			if(e.getStateChange()==1) {
				selectImg = new ImageIcon(StyleUtil.getIconBasePath()+"hook.png");
			}else {
				selectImg = new ImageIcon(StyleUtil.getIconBasePath()+"unselected.png");
			}
			box.setIcon(selectImg);
		}
		
	}
	private String[] formatFontSize() {
		String[] font = new String[fontSize.length];
		for (int i = 0; i < fontSize.length; i++) {
			font[i] = StyleUtil.getLabelHtml(fontSize[i],12, false);
		}
		return font;
	}
}
