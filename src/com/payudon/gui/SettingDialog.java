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
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.UIManager;

import com.payudon.listener.MoveListener;
import com.payudon.util.ComponentUtil;
import com.payudon.util.StyleUtil;

/** 
* @ClassName: SettingPanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月11日 上午11:32:57 
*  
*/
public class SettingDialog extends JDialog{

	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	public static boolean isShow = false;
	public static boolean isLogin = false;
	String[] fontSizes = {"14","16","18","20","22","24"};
	String[] fontSpacings = {"1","2","3","4","5"};
	private final MainJFrame parentFrame;
	private final MoveListener moveListener= new MoveListener();
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SettingDialog(MainJFrame parentFrame) {
		this.parentFrame = parentFrame;
		parentFrame.setEnabled(false);
		setLayout(null);
		//设置透明
		setUndecorated(true);
		setBackground(new Color(0,0,0,130));
		//设置最小窗口
		setSize(720,405);
		dispose();
		init();
		// 设置界面可见
		setVisible(true);
		// Frame在窗体居中
		setLocationRelativeTo(null); 
		addMoveListener();
		ImageIcon closeIcon = new ImageIcon(StyleUtil.getIconBasePath()+"close.png");
		JButton close = new JButton(closeIcon);
		close.setLocation(getWidth()-25,5);
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
		close.setEnabled(false);
	}
	public void addMoveListener() {
		addMouseListener(moveListener);
		addMouseMotionListener(moveListener);
		addMouseWheelListener(moveListener);
	}
	
	public void close() {
		this.dispose();
		parentFrame.setEnabled(true);
		isShow = false;
	}
	public void init() {
		JRootPane root = getRootPane();
		root.add(row1());
		root.add(row2());
		root.add(row3());
		root.add(row4());
		root.add(row5());
		root.add(row6());
	}
	public JPanel row1() {
		JPanel row = getRowPanel();
		row.setSize(getSize().width-80,70);
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
				if(e.getStateChange()==1) {
					parentFrame.setAlwaysOnTop(false);
					parentFrame.setBackground(new Color(0,0,0,129));
				}
			}
		});
		Color fartherColor = parentFrame.getBackground();
		boolean col2IsSelected = fartherColor.equals(new Color(0,0,0,129));
		col2.setSelected(!parentFrame.isAlwaysOnTop()&&col2IsSelected);
		JRadioButton col3 = getCheck(StyleUtil.getLabelHtml("普通",16, true));
		col3.setSize(140,40);
		col3.setLocation(row.getWidth()*2/5+30,0);
		col3.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
				if(e.getStateChange()==1) {
					parentFrame.setAlwaysOnTop(false);
					parentFrame.setBackground(new Color(0,0,0,130));
				}
			}
		});
		col3.setSelected(!parentFrame.isAlwaysOnTop()&&!col2IsSelected);
		JRadioButton col4 = getCheck(StyleUtil.getLabelHtml("最顶层",16, true));
		col4.setSize(140,40);
		col4.setLocation(row.getWidth()*3/5+30,0);
		col4.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
				if(e.getStateChange()==1) {
					parentFrame.setAlwaysOnTop(true);
					parentFrame.setBackground(new Color(0,0,0,130));
				}
			}
		});
		col4.setSelected(parentFrame.isAlwaysOnTop());
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
		MainPanel mainPanel = (MainPanel)parentFrame.getContentPane();
		col2.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
				if(e.getStateChange()==1) {
					parentFrame.setBackground(new Color(0,0,0,1));
					mainPanel.BorderHide();
				}
			}
		});
		col2.setSelected(mainPanel.hasBorder());
		JRadioButton col3 = getCheck(StyleUtil.getLabelHtml("透明",16, true));
		col3.setSize(140,40);
		col3.setLocation(row.getWidth()*2/5+30,0);
		col3.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
				if(e.getStateChange()==1) {
					parentFrame.setBackground(new Color(0,0,0,130));
					mainPanel.setBorder(null);
				}
			}
		});
		col3.setSelected(!mainPanel.hasBorder());
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
		MainPanel mainPanel = (MainPanel) parentFrame.getContentPane();
		TodoPanel todoPanel = (TodoPanel)ComponentUtil.getCompentByName(mainPanel,"todoPanel");
		Font font = todoPanel.getInputFont();
		JLabel col1 = creatLabel(StyleUtil.getLabelHtml("文字大小",16, true));
		col1.setSize(100,50);
		col1.setLocation(0, -5);
		String fontSize = StyleUtil.getLabelHtml(""+font.getSize(),12, false);
		DropDownPanel<String> col2 = new DropDownPanel<>(formatStrArr(fontSizes),fontSize);
		col2.setLocation(row.getWidth()*1/6,5);
		col2.getBox().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					String sizeStr = e.getItem().toString();
					col2.getLabel().setText(sizeStr);
					col2.refresh();
					Integer size = new Integer(StyleUtil.getLabelHtmlText(sizeStr));
					mainPanel.setFontSize(size);
				}
			}
		});
		JLabel col3 = creatLabel(StyleUtil.getLabelHtml("文字间距",16, true));
		col3.setSize(100,50);
		col3.setLocation(row.getWidth()*2/6,-5);
		DropDownPanel<String> col4 = new DropDownPanel<>(formatStrArr(fontSpacings),"1");
		col4.setLocation(row.getWidth()*3/6,5);
		/*col4.getBox().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String spacingStr = e.getItem().toString();
				col4.getLabel().setText(spacingStr);
				col4.refresh();
				Integer spacingSize = new Integer(StyleUtil.getLabelHtmlText(spacingStr));
				todoPanel.setInputSpacing(spacingSize);
			}
		});*/
		JLabel col5 = creatLabel(StyleUtil.getLabelHtml("文字颜色",16, true));
		col5.setSize(100,50);
		col5.setLocation(row.getWidth()*4/6,-5);
		ColorPanel col6 = new ColorPanel();
		col6.setLocation(row.getWidth()*5/6,5);
		JPanel panel = col6.getPanel();
		panel.setBackground(todoPanel.getInputColor());
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Color bgColor = JColorChooser.showDialog(panel,"",panel.getBackground());
				panel.setBackground(bgColor);
				mainPanel.setColor(bgColor);
			}
		});
		JLabel arrow = col6.getArrow();
		arrow.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Color bgColor = JColorChooser.showDialog(panel,"",panel.getBackground());
				panel.setBackground(bgColor);
				mainPanel.setColor(bgColor);
			}
		});
		row.add(col1);
		row.add(col2);
		row.add(col3);
		row.add(col4);
		row.add(col5);
		row.add(col6);
		return row;
	}
	public JPanel row5() {
		JPanel row = getRowPanel();
		MainPanel mainPanel = (MainPanel) parentFrame.getContentPane();
		TodoPanel todoPanel = (TodoPanel)ComponentUtil.getCompentByName(mainPanel,"todoPanel");
		Font font = todoPanel.getInputFont();		
		JLabel col1 = creatLabel(StyleUtil.getLabelHtml("文字字体",16, true));
		col1.setSize(100,50);
		col1.setLocation(0, -5);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
	    String[] fonts = ge.getAvailableFontFamilyNames();
		DropDownPanel<String> col2 = new DropDownPanel<>(fonts,font.getFontName());
		col2.setSize(160,35);
		col2.getBox().setSize(170,0);
		col2.getLabel().setText(fonts[0]);
		col2.setLocation(row.getWidth()*1/6,5);
		col2.getBox().setSelectedIndex(169);
		col2.getBox().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					String fontStr = e.getItem().toString();
					col2.getLabel().setText(fontStr);
					col2.refresh();
					int i = col2.getBox().getSelectedIndex();
					Font font = ge.getAllFonts()[i];
					Font newFont = new Font(font.getFontName(), font.getStyle(), todoPanel.getInputFont().getSize());
					todoPanel.setInputFont(newFont);
				}
			}
		});
		JLabel col3 = creatLabel(StyleUtil.getLabelHtml("隐藏界面",16, true));
		col3.setSize(100,50);
		col3.setLocation(col2.getX()+col2.getWidth()+60,0);
		JPanel col4 = new JPanel();
		col4.setLayout(null);
		col4.setSize(160,35);
		col4.setBackground(new Color(0, 0, 0, 90));
		col4.setLocation(col3.getX()+col3.getWidth()+20,5);
		JLabel label = new JLabel(StyleUtil.getLabelHtml("点击设置快捷键",14,true));
		label.setForeground(Color.white);
		label.setSize(col4.getWidth(),col4.getHeight());
		label.setLocation(10,0);
		label.requestFocus();
		if(parentFrame.getHideKeyCode()!=null) {
			Integer hideCode = parentFrame.getHideKeyCode();
			label.setText(StyleUtil.getLabelHtml(KeyEvent.getKeyText(hideCode),14,true));
		}
		col4.add(label);
		col4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				col4.requestFocus();
			}
		});
		col4.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if(code>110&&code<124) {
					label.setText(StyleUtil.getLabelHtml(KeyEvent.getKeyText(code),14,true));
				}else {
					label.setText(StyleUtil.getLabelHtml("点击设置快捷键",14,true));
				}
				col4.setVisible(false);
				col4.setVisible(true);
				parentFrame.hideByKey(code);
			}
			
		});
		row.add(col1);
		row.add(col2);
		row.add(col3);
		row.add(col4);
		return row;
	}
	public JPanel row6() {
		JPanel row = getRowPanel();
		row.setLocation(row.getX(),row.getY()+20);
		JRadioButton col1 = getCheck(StyleUtil.getLabelHtml("开机启动本程序",16, true));
		col1.setSize(200,40);
		col1.setLocation(0,0);
		col1.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
				boolean isStartAtLogon = ((JRadioButton)e.getSource()).isSelected();
				try {
					parentFrame.changeStart(isStartAtLogon);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		col1.setSelected(parentFrame.startAtLogon());
		JRadioButton col2 = getCheck(StyleUtil.getLabelHtml("贴近边缘自动隐藏界面",16, true));
		col2.setSize(300,40);
		col2.setLocation(row.getWidth()*2/6,0);
		col2.addItemListener(new CheckListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				super.itemStateChanged(e);
				boolean isAutoHide = ((JRadioButton)e.getSource()).isSelected();
				parentFrame.isWindowAutoHide(isAutoHide);
			}
		});
		col2.setSelected(parentFrame.autoHide());
		row.add(col1);
		row.add(col2);
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
		row.setSize(getSize().width-80,42);
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
	private String[] formatStrArr(String[] arr) {
		String[] font = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			font[i] = StyleUtil.getLabelHtml(arr[i],12, false);
		}
		return font;
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
}
