package com.payudon.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	public void setJPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setBackground(new Color(0,0,0,0));
		//panel.setBackground(Color.black);
		JLabel urlLabel = new JLabel("Todo");
		urlLabel.setBounds(10, 45, 80, 25);
		panel.add(urlLabel);
		//json字符串输入区域
		PlaceholderJTextArea codeArea = new PlaceholderJTextArea();
		codeArea.setPlaceholder("{\"data\":\"test\"}");
		codeArea.setBorder(getBorder(new Color(0F, 0F, 0F, 0F)));
		codeArea.setName("codeArea");
		codeArea.setLineWrap(true);
		JScrollPane codeScrollPane1 = new JScrollPane(codeArea);
		codeScrollPane1.setBounds(100,35, 400, 150);
		codeScrollPane1.setBorder(getBorder(Color.gray));
		panel.add(codeScrollPane1);
		
		JLabel resultLabel = new JLabel("java代码:");
		resultLabel.setBounds(10, 225, 80, 25);
		panel.add(resultLabel);
		//返回结果框
		JTextArea resultArea = new JTextArea();
		resultArea.setBorder(getBorder(new Color(0F, 0F, 0F, 0F)));
		resultArea.setName("resultArea");
		resultArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(resultArea);
		scrollPane.setBounds(100, 225, 400, 150);
		scrollPane.setBorder(getBorder(Color.gray));
		panel.add(scrollPane);
		
		JButton button = new JButton("生成Java实体");
		button.setBounds(100, 385, 150, 25);
		button.setFocusable(false);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				create(panel);
			}
		});
		JButton copyBtn = new JButton("复制内容");
		copyBtn.setBounds(350, 385, 150, 25);
		copyBtn.setFocusable(false);
		panel.add(copyBtn);
		/*copyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard(); 
				Transferable tText = new StringSelection(javaBeanStr);  
				clip.setContents(tText, null);
				JOptionPane.showMessageDialog(panel, "已复制到剪贴板");
			}
		});*/
		addFocusListener(panel);
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
		//创建面板，这个类似于 HTML 的 div 标签
		JPanel div = new JPanel();
		setJPanel(div);
		add(div);
		// 设置界面可见
		setVisible(true);
		// Frame在窗体居中
		setLocationRelativeTo(null); 
		addMouseAdapterListener(new MoveWindowListener());
	}
}
