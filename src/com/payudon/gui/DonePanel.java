/**   
* @Title: DonePanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月9日 下午3:50:03 
*/
package com.payudon.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import com.payudon.entity.Note;
import com.payudon.util.ComponentUtil;
import com.payudon.util.StyleUtil;

/** 
* @ClassName: DonePanel 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月9日 下午3:50:03 
*  
*/
public class DonePanel extends JPanel{

	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private JPanel scroll;
	public DonePanel(final MainJFrame frame) {
		setOpaque(false);
		setLocation(10, 60);
		setSize(frame.getWidth()-20,frame.getHeight()-80);
		setLayout(null);
		scroll = new JPanel();
		scroll.setSize(getSize());
		scroll.setOpaque(false);
		scroll.setLocation(0,0);
		scroll.setLayout(null);
		scroll.addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent e){
				if(e.getWheelRotation()==1){//滚轮向后滑动
					int height = scroll.getY()+scroll.getHeight();
					if(height>getHeight()) {
						scroll.setLocation(scroll.getX(),scroll.getY()-30);
					}
				}else if(e.getWheelRotation()==-1) {//滚轮向前滑动
					int y = scroll.getY();
					if(y<0) {
						scroll.setLocation(scroll.getX(),scroll.getY()+30); 
					}
				}
			}
		});
		add(scroll);
		setVisible(false);
	}
	public void addDoneText(Note note) {
		JPanel textPanel =  createDateLabel(note);
		int count = textPanel.getComponentCount();
		ContentText text = new ContentText();;
		int TextHeight = 0;
		if(count>1) {
			Component[] components = textPanel.getComponents();
			for (int i = 0; i < components.length; i++) {
				if(components[i] instanceof ContentText) {
					ContentText ct = (ContentText) components[i];
					TextHeight = ct.getY()+ct.getHeight();
				}
			}
		}
		if(TextHeight+30>=getHeight()) {
			scroll.setSize(scroll.getWidth(),scroll.getHeight()+30);
			scroll.setLocation(scroll.getX(),scroll.getY()-30);
		}
		text.setOpaque(false);
		text.setLayout(null);
		addText(textPanel,text,note);
	}
	private void addText(JPanel textPanel,ContentText text,Note note) {
		FuncDonePanel funcPanel = new FuncDonePanel(text);
		funcPanel.setName("funcPanel");
		text.add(funcPanel);
		ImageIcon pointImg = new ImageIcon("src/img/point.png");
		JLabel point = new JLabel(pointImg);
		point.setSize(30,30);
		point.setLocation(10,0);
		text.add(point);
		JTextArea input = new JTextArea(note.getText());
		input.setName("input");
		input.setOpaque(false);
		input.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				funcPanel.refreshLocation(text);
				funcPanel.setVisible(true);
				refresh();
			}
			public void mouseExited(MouseEvent e) {
				funcPanel.setVisible(false);

			}
		});
		Rectangle rect = note.getAreaRect();  
        Dimension size = new Dimension(getWidth()-20, Math.max(30,rect.y + rect.height));
        text.setSize(size);
        textPanel.setSize(size.width,textPanel.getHeight()+text.getHeight());
        input.setSize(text.getWidth()-30,text.getHeight()); 
		input.setLocation(35,0);
		input.setFont(new Font(null, 0, 20));
		input.setForeground(Color.white);
		input.setLineWrap(true);
		input.setWrapStyleWord(true);
		input.setEditable(false);
		text.add(input);
		textPanel.add(text);
		refresh();
		orderTexts();
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
	private void setJTextAreaSize(ContentText text,JTextArea input) {
		FuncDonePanel funcPanel = (FuncDonePanel) ComponentUtil.getCompentByName(text, "funcPanel");
		funcPanel.setVisible(false);
		try {  
            Rectangle rect = input.modelToView(input.getText().length());  
            Dimension size = new Dimension(getWidth()-20, Math.max(30,rect.y + rect.height));
            text.setSize(size);
            input.setSize(text.getWidth()-30,text.getHeight()); 
        } catch (BadLocationException e1) {  
            e1.printStackTrace();  
        } 
	}
	public void remove(ContentText text,Note note) {
		JPanel panel = createDateLabel(note);
		panel.remove(text);
		if(panel.getComponentCount()==1) {
			panel.remove(0);
			scroll.remove(panel);
		}
		orderTexts();
	}
	private void orderTexts(){
		Component[] components = scroll.getComponents();
		if(components.length>0) {
			for (int i = 0; i < components.length; i++) {
				JPanel textPanel = (JPanel) components[i];
				if(i==0) {
					textPanel.setLocation(textPanel.getX(),0);
				}else {
					JPanel beforeText = (JPanel) components[i-1];
					textPanel.setLocation(textPanel.getX(),beforeText.getY()+beforeText.getHeight());
				}
				Component[] texts = textPanel.getComponents();
				if(texts.length>1) {
					for (int j = texts.length-1; j >0 ; j--) {
						Component text = texts[j];
						if(j==texts.length-1) {
							text.setLocation(text.getX(),20);
						}else {
							text.setLocation(text.getX(),texts[j+1].getY()+texts[j+1].getHeight());
						}
					}
				}
			}
		}
	}
	private JPanel createDateLabel(Note note) {
		Date noteDate = note.getCompleteTime();
		String noteDateStr = new SimpleDateFormat("yyyy-MM-dd").format(noteDate);
		if(scroll.getComponentCount()>0) {
			for (Component datePanel : scroll.getComponents()) {
				JPanel panel = (JPanel)datePanel;
				JLabel dateLabel = (JLabel) ComponentUtil.getCompentByName(panel,"date");
				String dateText = dateLabel.getText();
				if(dateText.indexOf(noteDateStr)!=-1) {
					return panel;
				}
			}
		}
		JPanel datePanel = new JPanel();
		datePanel.setOpaque(false);
		datePanel.setSize(20,20);
		datePanel.setLocation(0,0);
		datePanel.setLayout(null);
		JLabel date = new JLabel(StyleUtil.getLabelHtml(noteDateStr,12,true));
		date.setBounds(10, 0,120,20);
		date.setName("date");
		date.setForeground(Color.white);
		datePanel.add(date);
		scroll.add(datePanel);
		return datePanel;
	}
}
