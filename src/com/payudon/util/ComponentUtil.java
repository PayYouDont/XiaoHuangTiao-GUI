package com.payudon.util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;

import com.payudon.entity.Note;
import com.payudon.gui.ContentText;

public class ComponentUtil {
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
	public static void refresh(Component componet) {
		if(componet !=null) {
			componet.setVisible(false);
			componet.setVisible(true);
		}
	}
	public static Border getBorder(Color color) {
		Border border = BorderFactory.createLineBorder(color);
		return border;
	}
	@SuppressWarnings("unchecked")
	public static <T> T getParentToClass(Component c,Class<T> type) {
		while (c!=null) {
			if(c.getClass()==type) {
				return (T)c;
			}
			c = c.getParent();
		}
		return null;
	}
	/*@SuppressWarnings("unchecked")
	public static <T>List<T> getChildToClass(Component c,Class<T> type,List<T> list) {
		Component[] Childern = null;
		if(c instanceof JComponent) {
			Childern = ((JPanel) c).getComponents();
		}else if(c instanceof JFrame) {
			Childern = ((JFrame) c).getComponents();
		}
		if(Childern!=null) {
			for (int i = 0; i < Childern.length; i++) {
				Component child = Childern[i];
				if(child.getClass()==type) {
					list.add((T)child);
				}else {
					getChildToClass(child, type,list);
				}
			}
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public static <T>T getChildToClass(Component c,Class<T> type) {
		if(c.getClass()==type) {
			return (T)c;
		}else {
			Component[] Childern = null;
			if(c instanceof JComponent) {
				Childern = ((JPanel) c).getComponents();
			}else if(c instanceof JFrame) {
				Childern = ((JFrame) c).getComponents();
			}
			if(Childern!=null) {
				for (int i = 0; i < Childern.length; i++) {
					Component child = Childern[i];
					if(child.getClass()==type) {
						return (T)child;
					}else {
						getChildToClass(child, type);
					}
				}
			}
		}
		return null;
	}*/
	public static Note parseContentText(ContentText text) {
		Note note = new Note();
		note.setTop(text.isTop());
		note.setStatus(Note.STATUS_COMPLETE);
		note.setCompleteTime(new Date());
		for (Component c : text.getComponents()) {
			if(c instanceof JTextArea) {
				JTextArea textArea = (JTextArea) c;
				note.setText(textArea.getText());
				try {
					note.setAreaRect(textArea.modelToView(textArea.getText().length()));
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		return note;
	}
}
