package com.payudon.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.payudon.listener.MoveWindowListener;

/** 
* @ClassName: HttpSendJFrame 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月20日 下午2:54:06 
*  
*/
public class MainJFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private final MoveWindowListener moveWindowListener = new MoveWindowListener();
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public static void addFocusListener(JPanel panel){
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
	}*/
	
	/*public static void changeColor(MouseEvent e,JTextField urlText) {
		int index = e.getButton();
		if(index==MouseEvent.BUTTON1) {//鼠标左键按下
			urlText.setBorder(getBorder(new Color(51, 135, 255)));
		}
	}*/
	public void addMouseAdapterListener(MouseAdapter mouseAdapterListener) {
		addMouseListener(mouseAdapterListener);
		addMouseMotionListener(mouseAdapterListener);
		addMouseWheelListener(mouseAdapterListener);
	}
	public void removeMouseAdapterListener(MouseAdapter mouseAdapterListener) {
		removeMouseListener(mouseAdapterListener);
		removeMouseMotionListener(mouseAdapterListener);
		removeMouseWheelListener(mouseAdapterListener);
	}
	public void notFixed() {
		addMouseAdapterListener(moveWindowListener);
	}
	public void fixed() {
		removeMouseAdapterListener(moveWindowListener);
	}
	public MainJFrame() {
		setLayout(null);
		//设置透明
		setUndecorated(true);
		setBackground(new Color(0,0,0,130));
		//设置最小窗口
		setMinimumSize(new Dimension(318,338));
		//关闭窗体后终止程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dispose();
		setContentPane(new MainPanel(this));
		// 设置界面可见
		setVisible(true);
		// Frame在窗体居中
		setLocationRelativeTo(null); 
		addMouseAdapterListener(moveWindowListener);
	}
}
