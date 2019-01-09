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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private List<Component> topTexts = new ArrayList<Component>();
	private List<Component> unpinTexts = new ArrayList<Component>();
	public TextPanel(final MainJFrame frame) {
		setOpaque(false);
		setLocation(10, 60);
		setSize(frame.getWidth()-20,frame.getHeight()-80);
		setLayout(null);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 addInput();
			}
			@Override
		    public void mouseEntered(MouseEvent e) {
				refresh();
			}
		});
	}
	private void addInput() {
		int count = getComponentCount();
		ContentText text = null;
		int TextHeight = 0;
		if(count==0) {
			text = new ContentText();
		}else {
			Component[] components = getComponents();
			for (int i = 0; i < components.length; i++) {
				ContentText jp = (ContentText) components[i];
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
			text = new ContentText();
		}
		text.setSize(getWidth()-20,30);
		text.setLocation(10,TextHeight);
		text.setOpaque(false);
		text.setLayout(null);
		addText(text);
	}
	private void addText(ContentText text) {
		FuncPanel funcPanel = new FuncPanel(text);
		funcPanel.setName("funcPanel");
		text.add(funcPanel);
		ImageIcon pointImg = new ImageIcon("src/img/point.png");
		JLabel point = new JLabel(pointImg);
		point.setSize(30,30);
		point.setLocation(10,0);
		text.add(point);
		JTextArea input = new JTextArea();
		input.setOpaque(false);
		input.setSize(text.getWidth()-30,text.getHeight());
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
				funcPanel.setVisible(false);
				if(input.getText().trim().isEmpty()) {
					remove(text);
				}
			}
			public void mouseEntered(MouseEvent e) {
				if(input.getText().trim().isEmpty()) {
					return;
				}
				funcPanel.setVisible(true);
				refresh();
			}
		});
		text.add(input);
		text.setBorder(ComponentUtil.getBorder(Color.red));
		add(text);
		input.requestFocus();
		refresh();
	}
	private void setJTextAreaSize(ContentText text,JTextArea input) {
		FuncPanel funcPanel = (FuncPanel) ComponentUtil.getCompentByName(text, "funcPanel");
		funcPanel.setVisible(false);
		try {  
            Rectangle rect = input.modelToView(input.getText().length());  
            Dimension size = new Dimension(getWidth()-20, Math.max(30,rect.y + rect.height));
            text.setSize(size);
            input.setSize(text.getWidth()-30,text.getHeight()); 
            //输入时检测是否需要扩大大小
            checkJPanel(text);
        } catch (BadLocationException e1) {  
            e1.printStackTrace();  
        } 
	}
	private void checkJPanel(ContentText text) {
		Component[] components = getComponents();
		if(components.length>1) {
			int index = 0;
			if(text.isTop()) {
				for (int i = topTexts.size()-1; i>=0; i--) {
					if(topTexts.get(i).equals(text)) {
						index = i;
						break;
					}
				}
				sortTopText(index);
				orderUnpinTexts();
			}else {
				unpinTexts = new ArrayList<>();
				for (int i = 0; i < components.length; i++) {
					ContentText ct = (ContentText) components[i];
					if(!ct.isTop()) {
						unpinTexts.add(ct);
					}
				}
				for (int i = 0; i < unpinTexts.size(); i++) {
					if(unpinTexts.get(i).equals(text)) {
						index = i;
						break;
					}
				}
				sortUnpinTexts(index);
			}
		}
	}
	private void sortTopText(Integer index) {
		List<Component> list = topTexts;
		if(index>0&&index<list.size()) {
			Component text = list.get(index);
			Component next = list.get(index-1);
			int increment = text.getY()+text.getHeight() - next.getY();
			if(increment!=0) {
				for(int i=index-1;i>=0;i--) {
					Component component = list.get(i);
					component.setLocation(component.getX(),component.getY()+increment);
				}
			}
		}
	}
	private void sortUnpinTexts(Integer index) {
		List<Component> list = unpinTexts;
		if(index<list.size()-1) {
			Component text = list.get(index);
			Component next = list.get(index+1);
			int increment = text.getY()+text.getHeight() - next.getY();
			if(increment!=0) {
				for(int i=index+1;i<list.size();i++) {
					Component component = list.get(i);
					component.setLocation(component.getX(),component.getY()+increment);
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
			ContentText text = (ContentText)c;
			for(Component component : text.getComponents()) {
				if(component instanceof JTextArea) {
					JTextArea input = (JTextArea)component;
					setJTextAreaSize(text, input);
				}
			}
		}
	}
	public void remove(JPanel text) {
		super.remove(text);
		orderText();
	}
	public void orderText() {
		orderTopTexts();
		orderUnpinTexts();
	}
	private void orderTopTexts() {
		if(topTexts.size()>0) {
			Component lastText = topTexts.get(topTexts.size()-1);
			lastText.setLocation(lastText.getX(),0);
			for (int i = topTexts.size()-2; i>=0; i--) {
				Component topText = topTexts.get(i);
				Component beforeTop = topTexts.get(i+1);
				topText.setLocation(topText.getX(),beforeTop.getY()+beforeTop.getHeight());
			}
		}
	}
	private void orderUnpinTexts(){
		if(unpinTexts.size()==0) {
			return;
		}
		Component[] components = getComponents();
		unpinTexts = new ArrayList<>();
		for (int i = 0; i < components.length; i++) {
			ContentText ct = (ContentText) components[i];
			if(!ct.isTop()) {
				unpinTexts.add(ct);
			}
		}
		if(unpinTexts.size()>0) {
			Component firstText = unpinTexts.get(0);
			int y = topTexts.size()>0?topTexts.get(0).getY()+topTexts.get(0).getHeight():0;
			firstText.setLocation(firstText.getX(),y);
			for (int i = 1; i < unpinTexts.size(); i++) {
				ContentText text = (ContentText) unpinTexts.get(i);
				text.setLocation(text.getX(),unpinTexts.get(i-1).getY()+unpinTexts.get(i-1).getHeight());
			}
		}
	}
	public List<Component> getTopTexts() {
		return topTexts;
	}
	public void setTopTexts(List<Component> topTexts) {
		this.topTexts = topTexts;
	}
	
}
