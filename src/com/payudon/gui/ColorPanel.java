package com.payudon.gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.payudon.util.StyleUtil;

public class ColorPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel arrow;
	public ColorPanel(){
		setLayout(null);
		setSize(70,35);
		setBackground(new Color(0, 0, 0, 90));
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setSize(24,getHeight()-10);
		panel.setLocation((getWidth()-20-panel.getWidth())/2,5);
		add(panel);
		ImageIcon arrowImg = new ImageIcon(StyleUtil.getIconBasePath()+"arrow.png");
		arrow = new JLabel(arrowImg);
		arrow.setSize(20,getHeight());
		arrow.setLocation(getWidth()-20,0);
		add(arrow);
	}
	public JPanel getPanel() {
		return panel;
	}
	public JLabel getArrow() {
		return arrow;
	}
}
