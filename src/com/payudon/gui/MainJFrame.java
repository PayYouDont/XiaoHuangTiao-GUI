package com.payudon.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.payudon.listener.MoveWindowListener;
import com.payudon.listener.MyFocusListener;

/** 
* @ClassName: HttpSendJFrame 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月20日 下午2:54:06 
*  
*/
public class MainJFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void create(JPanel panel) {
		/*String jsonStr = ((JTextArea)getCompentByName(panel, "codeArea")).getText();
		if(!"".equals(jsonStr)) {
			try {
				javaBeanStr = JsonUtils.parseJson2Java(jsonStr,isAnnotate);
				((JTextArea)getCompentByName(panel, "resultArea")).setText(javaBeanStr);
			}catch(Exception e) {
				((JTextArea)getCompentByName(panel, "resultArea")).setText(e.getMessage());
			}
		}else {
			JOptionPane.showMessageDialog(panel, "Json字符串为空！");
		}*/
	}
	public static Component getCompentByName(JPanel panel,String name) {
		Component[] components= panel.getComponents();
		for (int i = 0; i < components.length; i++) {
			Component c = components[i];
			if(c instanceof JScrollPane) {
				JScrollPane pane = (JScrollPane)c;
				c = pane.getViewport().getView();
			}
			if(name.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}
	public static void addFocusListener(JPanel panel){
		Component[] components= panel.getComponents();
		MyFocusListener focusListerner = new MyFocusListener();
		for (int i = 0; i < components.length; i++) {
			Component c = components[i];
			if(c instanceof JScrollPane) {
				JScrollPane pane = (JScrollPane)c;
				c = pane.getViewport().getView();
			}
			c.addFocusListener(focusListerner);
		}
	}
	public static Border getBorder(Color color) {
		Border border = BorderFactory.createLineBorder(color);
		return border;
	}
	
	public static void changeColor(MouseEvent e,JTextField urlText) {
		int index = e.getButton();
		if(index==MouseEvent.BUTTON1) {//鼠标左键按下
			urlText.setBorder(getBorder(new Color(51, 135, 255)));
		}
	}
	public void addMouseAdapterListener(MouseAdapter mouseAdapterListener) {
		this.addMouseListener(mouseAdapterListener);
		this.addMouseMotionListener(mouseAdapterListener);
	}
	public MainJFrame() {
		//设置透明
		setUndecorated(true);
		setBackground(new Color(0,0,0,120));
		//设置最小窗口
		setMinimumSize(new Dimension(335,345));
		//关闭窗体后终止程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dispose();
		add(new MainPanel());
		// 设置界面可见
		setVisible(true);
		// Frame在窗体居中
		setLocationRelativeTo(null); 
		addMouseAdapterListener(new MoveWindowListener());
	}
}
