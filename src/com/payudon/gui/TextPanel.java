/**   
* @Title: TextPanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月7日 下午2:21:54 
*/
package com.payudon.gui;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import com.payudon.util.ComponentUtil;

/** 
* @ClassName: TextPanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月7日 下午2:21:54 
*  
*/
public class TextPanel extends JPanel{

	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	
	private final TopPanel topPanel;
	private final FuncPanel funcPanel;
	public TextPanel(final MainJFrame frame,final TopPanel topPanel,final FuncPanel funcPanel) {
		JScrollPane scroll = new JScrollPane();
		scroll.setOpaque(false);
		this.topPanel = topPanel;
		this.funcPanel = funcPanel;
		setOpaque(false);
		setLocation(0, 60);
		setSize(frame.getWidth()-20,frame.getHeight()-80);
		setLayout(null);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 addInput();
			}
			@Override
		    public void mouseEntered(MouseEvent e) {
				topPanel.hideOrShow(true);
				refresh();
			}
		});
		scroll.setSize(getSize());
		scroll.add(this);
	}
	private void addInput() {
		int count = getComponentCount();
		JPanel text = null;
		int TextHeight = 0;
		if(count==0) {
			text = new JPanel();
		}else {
			Component[] components = getComponents();
			for (int i = 0; i < components.length; i++) {
				JPanel jp = (JPanel) components[i];
				Component[] cs = jp.getComponents();
				TextHeight = jp.getY()+jp.getHeight();
				for (int j = 0; j < cs.length; j++) {
					if(cs[j] instanceof JTextArea) {
						JTextArea tja = (JTextArea)cs[j];
						if(tja.getText().trim().isEmpty()) {
							text = jp;
							return;
						}
					}
				}
			}
		}
		if(text==null) {
			text = new JPanel();
		}
		text.setSize(getWidth()-20,30);
		text.setLocation(10,TextHeight);
		text.setOpaque(false);
		text.setLayout(null);
		addText(text);
	}
	private void addText(JPanel text) {
		ImageIcon pointImg = new ImageIcon("src/img/point.png");
		JLabel point = new JLabel(pointImg);
		point.setSize(30,30);
		point.setLocation(10,0);
		text.add(point);
		JTextArea input = new JTextArea();
		input.setOpaque(false);
		input.setSize(text.getSize());
		input.setLocation(35,0);
		input.setFont(new Font(null, 0, 20));
		input.setForeground(Color.white);
		input.setLineWrap(true);
		input.setWrapStyleWord(true);
		input.setCaretColor(Color.white);
		input.addKeyListener(new KeyAdapter() {
			@Override  
            public void keyReleased(KeyEvent e) {  
				setJTextAreaSize(text, input);
            }  
		});
		input.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				if(input.getText().trim().isEmpty()) {
					remove(text);
				}
				funcPanel.setVisible(false);
			}
			public void mouseEntered(MouseEvent e) {
				topPanel.hideOrShow(true);
				int y = getLocation().y+text.getLocation().y;
				funcPanel.setText(text);
				funcPanel.setLocation(getWidth()-100,y);
				funcPanel.setVisible(true);
				checkJPanel(funcPanel.getText());
				ComponentUtil.refresh(funcPanel);
				refresh();
			}
		});
		text.add(input);
		add(text);
		refresh();
	}
	private void setJTextAreaSize(JPanel text,JTextArea input) {
		funcPanel.setVisible(false);
		try {  
            Rectangle rect = input.modelToView(input.getText().length());  
            Dimension size = new Dimension(getWidth()-20, Math.max(30,rect.y + rect.height));
            text.setSize(size);
            input.setSize(size); 
            checkJPanel(text);
        } catch (BadLocationException e1) {  
            e1.printStackTrace();  
        } 
	}
	private void checkJPanel(JPanel text) {
		Component[] components = getComponents();
		if(components.length>1) {
			int index = 0;
			for (int i = 0; i < components.length; i++) {
				if(components[i].equals(text)) {
					index = i;
					break;
				}
			}
			if(index<components.length-1) {
				Component nextComponent = components[index+1];
				JPanel nextPanel = (JPanel)nextComponent;
				int increment = text.getLocation().y+text.getHeight() - nextPanel.getLocation().y;
				if(increment!=0) {
					for(int i=index+1;i<components.length;i++) {
						Component component = components[i];
						JPanel panel = (JPanel)component;
						panel.setLocation(panel.getLocation().x,panel.getLocation().y+increment);
					}
				}
			}
		}
	}
	private void refresh() {
		setVisible(false);
		setVisible(true);
	}
	public void refreshlSize(Dimension dimension) {
		setSize(dimension.width-20,dimension.height);
		for (Component c : getComponents()) {
			JPanel text = (JPanel)c;
			for(Component component : text.getComponents()) {
				if(component instanceof JTextArea) {
					JTextArea input = (JTextArea)component;
					setJTextAreaSize(text, input);
				}
			}
			
		}
	}
	
}
