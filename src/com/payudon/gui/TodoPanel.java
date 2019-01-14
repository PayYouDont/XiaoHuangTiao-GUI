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
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

import com.payudon.entity.Note;
import com.payudon.listener.TopShowMouseAdapter;
import com.payudon.util.ComponentUtil;
import com.payudon.util.StyleUtil;

/** 
* @ClassName: TextPanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月7日 下午2:21:54 
*  
*/
public class TodoPanel extends JPanel{

	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private JPanel scroll;
	private List<Component> topTexts = new ArrayList<>();
	private List<Component> unpinTexts = new ArrayList<>();
	private Font font = new Font(null, 0, 20);
	private Color inputColor = Color.white;
	private class InputMouseListener extends TopShowMouseAdapter{}
	public TodoPanel(final MainJFrame frame) {
		setOpaque(false);
		setLocation(10, 60);
		setName("todoPanel");
		setSize(frame.getWidth()-20,frame.getHeight()-80);
		setLayout(null);
		scroll = new JPanel();
		scroll.setSize(getSize());
		scroll.setOpaque(false);
		scroll.setLocation(0,0);
		scroll.setLayout(null);
		scroll.addMouseListener(new InputMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					 addInput();
				}
			}
			@Override
		    public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				refresh();
			}
		});
		scroll.addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent e){
				if(e.getWheelRotation()==1){//鼠标滚轮向后滑动
					int height = scroll.getY()+scroll.getHeight();
					if(height>getHeight()) {
						scroll.setLocation(scroll.getX(),scroll.getY()-30);
					}
				}else if(e.getWheelRotation()==-1) {//鼠标滚轮向前滑动
					int y = scroll.getY();
					if(y<0) {
						scroll.setLocation(scroll.getX(),scroll.getY()+30); 
					}
				}
			}
		});
		add(scroll);
	}
	public void addTodoText(Note note) {
		ContentText text = createContentText();
		if(text!=null) {
			addText(text,note);
		}
	}
	public void addInput() {
		ContentText text = createContentText();
		if(text!=null) {
			addText(text);
		}
	}
	private ContentText createContentText() {
		int count = scroll.getComponentCount();
		ContentText text = null;
		int TextHeight = 0;
		if(count==0) {
			text = new ContentText();
		}else {
			Component[] components = scroll.getComponents();
			for (int i = 0; i < components.length; i++) {
				if(components[i] instanceof ContentText) {
					ContentText jp = (ContentText) components[i];
					Component[] cs = jp.getComponents();
					TextHeight = jp.getY()+jp.getHeight();
					for (int j = 0; j < cs.length; j++) {
						if(cs[j] instanceof JTextArea) {
							JTextArea tja = (JTextArea)cs[j];
							if(tja.getText().trim().isEmpty()) {
								text = jp;
								return null;
							}
						}
					}
				}
			}
		}
		if(text==null) {
			text = new ContentText();
		}
		text.setSize(scroll.getWidth()-20,30);
		if(TextHeight+30>=getHeight()) {
			scroll.setSize(scroll.getWidth(),scroll.getHeight()+30);
			scroll.setLocation(scroll.getX(),scroll.getY()-30);
		}
		text.setLocation(10,TextHeight);
		text.setOpaque(false);
		text.setLayout(null);
		return text;
	}
	private void addText(ContentText text,Note note) {
		addText(text);
		for (Component component : text.getComponents()) {
			if(component instanceof JTextArea) {
				JTextArea input = (JTextArea)component;
				input.setText(note.getText());
				Rectangle rect = note.getAreaRect();  
		        Dimension size = new Dimension(getWidth()-20, Math.max(30,rect.y + rect.height));
		        text.setSize(size);
		        input.setSize(text.getWidth()-30,text.getHeight()); 
			}
		}
		refresh();
	}
	private void addText(ContentText text) {
		FuncTodoPanel funcPanel = new FuncTodoPanel(text);
		funcPanel.setName("funcPanel");
		text.add(funcPanel);
		ImageIcon pointImg = new ImageIcon(StyleUtil.getIconBasePath()+"point.png");
		JLabel point = new JLabel(pointImg);
		point.setSize(30,30);
		point.setLocation(10,0);
		text.add(point);
		JTextArea input = new JTextArea();
		input.setName("input");
		input.setOpaque(false);
		input.setSize(text.getWidth()-30,text.getHeight());
		input.setLocation(35,0);
		input.setFont(font);
		input.setForeground(inputColor);
		input.setLineWrap(true);
		input.setWrapStyleWord(true);
		input.setCaretColor(inputColor);
		input.addKeyListener(new KeyAdapter() {
			@Override  
            public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==10) {
					try {
						input.getDocument().remove(input.getCaret().getMark()-1,1);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					addInput();
				}
				setJTextAreaSize(text, input);
            }  
		});
		input.addMouseListener(new InputMouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					int clickTimes = e.getClickCount();
				    if (clickTimes == 2) {
				    	Note note = ComponentUtil.parseContentText(text);
				    	if(note.getText().trim().isEmpty()) {
				    		return;
				    	}
						MainPanel mainPanel= (MainPanel) ComponentUtil.getParentToClass(e.getComponent(),MainPanel.class);
						mainPanel.getDonePanel().addDoneText(note);
						remove(text);
				    }
				}
			}
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				funcPanel.setVisible(false);
				if(input.getText().trim().isEmpty()) {
					scroll.remove(text);
				}
			}
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				if(input.getText().trim().isEmpty()) {
					return;
				}
				funcPanel.refreshLocation(text);
				funcPanel.setVisible(true);
			}
		});
		input.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
	            try {
					int line = input.getLineOfOffset(input.getCaretPosition());
					if(line!=0&&scroll.getY()+line*30==0) {
		        		scroll.setLocation(scroll.getX(),scroll.getY()+30);
		            }else if(line*30+text.getY()>getHeight()&&scroll.getY()+scroll.getHeight()>getHeight()) {
		        		scroll.setLocation(scroll.getX(),scroll.getY()-30);
		            }
	            } catch (BadLocationException e1) {
					e1.printStackTrace();
				}

			}
		});
		text.add(input);
		scroll.add(text);
		input.requestFocus();
		refresh();
	}
	private void setJTextAreaSize(ContentText text,JTextArea input) {
		FuncTodoPanel funcPanel = (FuncTodoPanel) ComponentUtil.getCompentByName(text, "funcPanel");
		funcPanel.setVisible(false);
		try {  
            Rectangle rect = input.modelToView(input.getText().length());  
            Dimension size = new Dimension(getWidth()-20, Math.max(30,rect.y + rect.height));
            text.setSize(size);
            input.setSize(text.getWidth()-30,text.getHeight()); 
            if(text.getY()+text.getHeight()>scroll.getHeight()) {
            	scroll.setSize(scroll.getWidth(),scroll.getHeight()+30);
        		scroll.setLocation(scroll.getX(),scroll.getY()-30);
            }
            //输入时检测是否需要扩大大小
            checkJPanel(text);
        } catch (BadLocationException e1) {  
            e1.printStackTrace();  
        } 
	}
	private void checkJPanel(ContentText text) {
		Component[] components = scroll.getComponents();
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
		scroll.setVisible(false);
		scroll.setVisible(true);
	}
	public void refreshlSize(Dimension dimension) {
		setSize(dimension.width-20,dimension.height-80);
		scroll.setSize(getSize());
		for (Component c : scroll.getComponents()) {
			ContentText text = (ContentText)c;
			for(Component component : text.getComponents()) {
				if(component instanceof JTextArea) {
					JTextArea input = (JTextArea)component;
					setJTextAreaSize(text, input);
				}
			}
		}
	}
	public void remove(ContentText text) {
		if(text.isTop()) {
			topTexts.remove(text);
		}
		scroll.remove(text);
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
		Component[] components = scroll.getComponents();
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
	public void setInputFontSize(Integer size) {
		Font font = getFont();
		Font newFont = new Font(font.getFontName(),font.getStyle(), size);
		setInputFont(newFont);
	}
	public void setInputFont(Font font) {
		this.font = font;
		Component[] texts = scroll.getComponents();
		for (int i = 0; i < texts.length; i++) {
			ContentText text = (ContentText) texts[i];
			JTextArea jta = (JTextArea) ComponentUtil.getCompentByName(text,"input");
			jta.setFont(font);
		}
	}
	public Font getInputFont() {
		return font;
	}
	public void setInputColor(Color color) {
		this.inputColor = color;
		Component[] texts = scroll.getComponents();
		for (int i = 0; i < texts.length; i++) {
			ContentText text = (ContentText) texts[i];
			JTextArea jta = (JTextArea) ComponentUtil.getCompentByName(text,"input");
			jta.setForeground(color);
			jta.setCaretColor(inputColor);
		}
	}
	public Color getInputColor() {
		return inputColor;
	}
}
