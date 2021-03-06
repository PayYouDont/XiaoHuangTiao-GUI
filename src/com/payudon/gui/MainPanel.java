/**   
* @Title: MainPanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月4日 下午5:43:59 
*/
package com.payudon.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.payudon.listener.TopShowMouseAdapter;
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
	private JButton close;
	private JButton add;
	private TopPanel topPanel;
	private TodoPanel todoPanel;
	private DonePanel donePanel;
	private final MainJFrame frame;
	public MainPanel(final MainJFrame frame) {
		this.frame = frame;
		setLayout(null);
		setOpaque(false);
		setName("mainPanel");
		initColseAndAddPanel();
		topPanel = new TopPanel(frame);
		add(topPanel);
		todoPanel = new TodoPanel(frame);
		add(todoPanel);
		donePanel = new DonePanel(frame);
		add(donePanel);
	}
	public void initColseAndAddPanel(){
		Dimension topSize = frame.getSize();
		ImageIcon closeIcon = new ImageIcon(StyleUtil.getIconBasePath()+"close.png");
		close = new JButton(closeIcon);
		close.setLocation(topSize.width-25,5);
		close.setSize(20,20);
		close.setContentAreaFilled(false);//不绘制按钮区域
		close.setBorderPainted(false);//不绘制边框
		close.setEnabled(false);
		close.addMouseListener(new TopShowMouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					close();
				}
			}
		});
		add(close);
		ImageIcon addIcon = new ImageIcon(StyleUtil.getIconBasePath()+"add.png");
		add = new JButton(addIcon);
		add.setLocation(5,5);
		add.setSize(20,20);
		add.setEnabled(false);
		add.setContentAreaFilled(false);//不绘制按钮区域
		add.setBorderPainted(false);//不绘制边框
		add.addMouseListener(new TopShowMouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					new MainJFrame();
				}
			}
		});
		add.grabFocus();
		add(add);
	}
	
	private void refresh() {
		setVisible(false);
		setVisible(true);
	}
	public void refreshlSize(Dimension dimension) {
		todoPanel.refreshlSize(dimension);
		topPanel.refreshlSize(dimension);
		donePanel.refreshlSize(dimension);
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
	public void BorderHide() {
		setBorder(ComponentUtil.getBorder(new Color(0,0,0,0)));
		close.setVisible(false);
		add.setVisible(false);
	}
	public void BorderShow() {
		setBorder(ComponentUtil.getBorder(Color.white));
		close.setVisible(true);
		add.setVisible(true);
	}
	public void setFontSize(Integer size) {
		todoPanel.setInputFontSize(size);
		donePanel.setInputFontSize(size);
	}
	public void setColor(Color color) {
		todoPanel.setInputColor(color);
		donePanel.setInputColor(color);
	}
	public TopPanel getTopPanel() {
		return topPanel;
	}
	public void setTopPanel(TopPanel topPanel) {
		this.topPanel = topPanel;
	}
	public TodoPanel getTodoPanel() {
		return todoPanel;
	}
	public void setTodoPanel(TodoPanel todoPanel) {
		this.todoPanel = todoPanel;
	}
	public DonePanel getDonePanel() {
		return donePanel;
	}
	public void setDonePanel(DonePanel donePanel) {
		this.donePanel = donePanel;
	}
	public boolean hasBorder() {
		return getBorder()!=null&&getBorder().isBorderOpaque();
	}
	
}
