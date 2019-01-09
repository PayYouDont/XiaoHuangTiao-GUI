package com.payudon.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

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
	public static Component getParentToClass(Component c,Class<? extends Component> clazz) {
		while (c!=null&&c.getClass()!=clazz) {
			c = c.getParent();
			if(c==null) {
				break;
			}
		}
		return c;
	}
}
