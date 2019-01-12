package com.payudon.gui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.payudon.util.StyleUtil;

public class DropDownPanel<E> extends JPanel{
	private static final long serialVersionUID = 1L;
	private JComboBox<?> box;
	private JLabel label;
	private JLabel arrow;
	public DropDownPanel(E[] e,E entity) {
		setLayout(null);
		super.setSize(70,35);
		setBackground(new Color(0, 0, 0, 90));
		box = new JComboBox<>(e);
		box.setSize(getWidth(),0);
		box.setLocation(0,getHeight());
		box.setSelectedItem(entity);
		box.setFocusable(false);
		box.setOpaque(false);
		box.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					label.setText(e.getItem().toString());
					refresh();
				}
			}
		});
		add(box);
		label = new JLabel(StyleUtil.getLabelHtml(box.getSelectedItem().toString(),26, true));
		label.setForeground(Color.white);
		label.setSize(getWidth()-20,getHeight());
		label.setLocation(10,0);
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				box.showPopup();
			}
		});
		add(label);
		ImageIcon arrowImg = new ImageIcon(StyleUtil.getIconBasePath()+"arrow.png");
		arrow = new JLabel(arrowImg);
		arrow.setSize(20,getHeight());
		arrow.setLocation(getWidth()-20,0);
		arrow.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				box.showPopup();
			}
		});
		add(arrow);
	}
	@Override
	public void setSize(int width,int height) {
		super.setSize(width, height);
		label.setSize(width-20, height);
		arrow.setLocation(width-20,0);
	}
	public JComboBox<?> getBox() {
		return box;
	}
	public void setBox(JComboBox<?> box) {
		this.box = box;
	}
	public JLabel getLabel() {
		return label;
	}
	public void setLabel(JLabel label) {
		this.label = label;
	}
	public void refresh() {
		setVisible(false);
		setVisible(true);
	}
}
