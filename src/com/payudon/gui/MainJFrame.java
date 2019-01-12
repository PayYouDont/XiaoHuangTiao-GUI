package com.payudon.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.payudon.listener.MoveWindowListener;
import com.payudon.listener.TopShowMouseAdapter;

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
		addMouseListener(new TopShowMouseAdapter() {});
	}
}
