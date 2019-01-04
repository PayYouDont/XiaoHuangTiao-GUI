
package com.payudon.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextArea;

/**
 * @ClassName: PlaceholderJTextArea
 * @Description: TODO( )
 * @author peiyongdong
 * @date 2018年12月11日 下午3:07:43
 * 
 */
public class PlaceholderJTextArea extends JTextArea {

	/**
	 * @Fields serialVersionUID : TODO( )
	 */
	private static final long serialVersionUID = 1L;
	private String placeholder;

	public String getPlaceholder() {
		return placeholder;
	}

	@Override
	protected void paintComponent(final Graphics pG) {
		super.paintComponent(pG);

		if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
			return;
		}

		final Graphics2D g = (Graphics2D) pG;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.gray);
		g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
	}

	public void setPlaceholder(final String s) {
		placeholder = s;
	}
}
