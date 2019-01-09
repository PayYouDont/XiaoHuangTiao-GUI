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
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
		scroll.setBorder(ComponentUtil.getBorder(Color.red));
		add(scroll);
		setVisible(false);
	}
	public void addDoneText(Note note) {
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
								return;
							}
						}
					}
				}
			}
		}
		if(text==null) {
			text = new ContentText();
		}
		text.setSize(scroll.getWidth()-20,100);
		if(TextHeight+30>=getHeight()) {
			scroll.setSize(scroll.getWidth(),scroll.getHeight()+30);
			scroll.setLocation(scroll.getX(),scroll.getY()-30);
		}
		text.setLocation(10,TextHeight);
		text.setOpaque(false);
		text.setLayout(null);
		addText(text,note);
	}
	private void addText(ContentText text,Note note) {
		FuncDonePanel funcPanel = new FuncDonePanel(text);
		funcPanel.setName("funcPanel");
		text.add(funcPanel);
		JLabel date = new JLabel(StyleUtil.getLabelHtml(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),12,true));
		date.setBounds(10, 0,text.getWidth(),20);
		date.setForeground(Color.white);
		text.add(date);
		ImageIcon pointImg = new ImageIcon("src/img/point.png");
		JLabel point = new JLabel(pointImg);
		point.setSize(30,30);
		point.setLocation(10,20);
		text.add(point);
		JTextArea input = new JTextArea();
		input.setOpaque(false);
		//input.setSize(text.getWidth()-30,text.getHeight());
		input.setLocation(35,20);
		input.setFont(new Font(null, 0, 20));
		input.setForeground(Color.white);
		input.setLineWrap(true);
		input.setWrapStyleWord(true);
		text.add(input);
		scroll.add(text);
		refresh();
	}
	private void refresh() {
		scroll.setVisible(false);
		scroll.setVisible(true);
	}
	/**
	 * Rectangle rect;
		try {
			rect = input.modelToView(input.getText().length());
	        Dimension size = new Dimension(getWidth()-20, Math.max(30,rect.y + rect.height));
	        text.setSize(size.getSize().width, size.getSize().height+date.getHeight());
            input.setSize(text.getWidth()-point.getWidth(),text.getHeight()-date.getHeight()); 
		} catch (BadLocationException e) {
			e.printStackTrace();
		}  
	 */
}
