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
	/*public static Component getCompentByType(JPanel panel,Class<? extends Component> clazz) {
		Component[] components= panel.getComponents();
		for (int i = 0; i < components.length; i++) {
			Component c = components[i];
			if(c.getClass()==clazz) {
				return c;
			}else if(c instanceof JPanel ) {
				c = getCompentByType((JPanel)c, clazz);
			}
			if(c.getClass()==clazz) {
				return c;
			}
		}
		return null;
	}*/
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
	public static Component getParentToClass(Component c,Class<? extends Component> clazz) {
		while (c!=null&&c.getClass()!=clazz) {
			c = c.getParent();
			if(c==null) {
				break;
			}
		}
		return c;
	}
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
