/**   
* @Title: MainPanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月4日 下午5:43:59 
*/
package com.payudon.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

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
	private JPanel topPanel;
	private JLabel title;
	private JLabel Done;
	private JLabel lock;
	private JLabel synchronize;
	private JLabel setting;
	private JPanel textPanel;
	private final Color transparentColor = new Color(0, 0, 0,0);
	public MainPanel(MainJFrame frame) {
		setLayout(null);
		setBackground(transparentColor);
		initTopPanel(frame);
		initTextPanel(frame);
		
	}
	public void initTopPanel(MainJFrame frame){
		Dimension topSize = frame.getSize();
		topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setSize(topSize);
		topPanel.setBackground(transparentColor);
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
		lock.setSize(30,30);
		lock.setLocation(topSize.width-115,25);
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
					refresh();
				}
			}
			@Override
		    public void mouseEntered(MouseEvent e) {
				topPanelShow();
				refresh();
			}
		});
		topPanel.add(lock);
		//synchronize
		ImageIcon synchronizeImg = new ImageIcon("src/img/synchronize.png");
		synchronize = new JLabel(synchronizeImg);
		synchronize.setName("synchronize");
		synchronize.setSize(30,30);
		synchronize.setLocation(topSize.width-85,25);
		synchronize.setVisible(false);
		add(synchronize);
		//setting
		ImageIcon settingImg = new ImageIcon("src/img/setting.png");
		setting = new JLabel(settingImg);
		setting.setName("setting");
		setting.setSize(30,30);
		setting.setLocation(topSize.width-55,25);
		setting.setVisible(false);
		topPanel.add(setting);
		add(topPanel);
	}
	
	private void refresh() {
		setVisible(false);
		setVisible(true);
	}
	public void initTextPanel(MainJFrame frame) {
		textPanel = new JPanel();
		//textPanel.setBorder(ComponentUtil.getBorder(Color.red));
		textPanel.setBackground(transparentColor);
		textPanel.setLocation(0, 60);
		textPanel.setSize(frame.getWidth()-20,frame.getHeight());
		textPanel.setLayout(null);
		textPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 addInput();
			}
		});
		add(textPanel);
	}
	private void addInput() {
		int count = textPanel.getComponentCount();
		JTextPane jtp = null; 
		if(count>0) {
			Component[] components = textPanel.getComponents();
			jtp = (JTextPane) components[--count];
			String content = ((JTextArea)jtp.getComponents()[1]).getText();
			while(content.isEmpty()) {
				if(count==0) {
					jtp = null;
					break;
				}
				jtp = (JTextPane) components[--count];
				content = ((JTextArea)jtp.getComponents()[1]).getText();
			}
		}
		int TextHeight = 0;
		if(jtp!=null) {
			TextHeight = jtp.getY()+jtp.getHeight();
		}
		JTextPane text = new JTextPane();
		text.setBackground(transparentColor);
		text.setSize(textPanel.getWidth()-20,30);
		text.setLocation(10,TextHeight);
		text.setLayout(null);
		ImageIcon settingImg = new ImageIcon("src/img/point.png");
		JLabel point = new JLabel(settingImg);
		point.setSize(30,30);
		point.setLocation(10,0);
		text.add(point);
		JTextArea input = new JTextArea();
		input.setSelectionColor(transparentColor);
		input.setSize(text.getSize());
		input.setLocation(35,0);
		input.setFont(new Font(null, 0, 20));
		input.setForeground(Color.red);
		input.setLineWrap(true);
		input.setWrapStyleWord(true);
		input.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		input.setCaretColor(Color.red);  
		input.addKeyListener(new KeyAdapter() {
			@Override  
            public void keyReleased(KeyEvent e) {  
                try {  
                    Rectangle rect = input.modelToView(input.getText().length());  
                    Dimension size = new Dimension(textPanel.getWidth()-20, Math.max(30,rect.y + rect.height));
                    text.setSize(size);
                    input.setSize(size); 
                } catch (BadLocationException e1) {  
                    e1.printStackTrace();  
                } 
            }  
		});
		text.add(input);
		textPanel.add(text);
		ComponentUtil.refresh(text);
		ComponentUtil.refresh(textPanel);
		refresh();
	}
	public void refreshlSize(Dimension dimension) {
		topPanelShow();
		topPanel.setSize(dimension);
		textPanel.setSize(dimension.width-20,dimension.height);
		lock.setLocation(dimension.width-115,25);
		synchronize.setLocation(dimension.width-85,25);
		setting.setLocation(dimension.width-55,25);
		refresh();
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
