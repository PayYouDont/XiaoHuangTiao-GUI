/**   
* @Title: TopPanel.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月7日 下午2:01:28 
*/
package com.payudon.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.payudon.listener.TopShowMouseAdapter;
import com.payudon.util.ComponentUtil;
import com.payudon.util.StyleUtil;

/**
 * @ClassName: TopPanel
 * @Description: TODO( )
 * @author peiyongdong
 * @date 2019年1月7日 下午2:01:28
 * 
 */
public class TopPanel extends JPanel {

	/**
	 * @Fields serialVersionUID : TODO( )
	 */
	private static final long serialVersionUID = 1L;
	private JLabel Todo;
	private JLabel Done;
	private JLabel lock;
	private JLabel synchronize;
	private JLabel setting;
	private class TopMouseListener extends TopShowMouseAdapter{}
	public TopPanel(final MainJFrame frame) {
		Dimension topSize = frame.getSize();
		setLayout(null);
		setSize(topSize.width,60);
		setOpaque(false);
		setName("topPanel");
		// todo
		Todo = new JLabel(StyleUtil.getLabelHtml("Todo", 22, true));
		Todo.setName("todo");
		Todo.setForeground(Color.white);
		Todo.setBounds(30, 0, 100, 80);
		add(Todo);
		// done
		Done = new JLabel(StyleUtil.getLabelHtml("Done", 16, true));
		Done.setName("done");
		Done.setForeground(new Color(170, 170, 170));
		Done.setBounds(120,28,70, 30);
		Done.setVisible(false);
		Done.addMouseListener(new TopMouseListener() {
			public void mouseClicked(MouseEvent e) {
				hideOrShow(true);
				if (e.getButton() == 1) {
					MainPanel mainPanel = (MainPanel)ComponentUtil.getParentToClass(e.getComponent(), MainPanel.class);
					String text = Done.getText();
					if(text.indexOf("Done")!=-1) {
						Done.setText(StyleUtil.getLabelHtml("Todo", 16, true));
						Todo.setText(StyleUtil.getLabelHtml("Done", 22, true));
						mainPanel.getTodoPanel().setVisible(false);
						mainPanel.getDonePanel().setVisible(true);
					}else {
						Done.setText(StyleUtil.getLabelHtml("Done", 16, true));
						Todo.setText(StyleUtil.getLabelHtml("Todo", 22, true));
						mainPanel.getTodoPanel().setVisible(true);
						mainPanel.getDonePanel().setVisible(false);
					}
					refresh();
				}
			}
		});
		add(Done);
		// lock
		ImageIcon lockImg = new ImageIcon(StyleUtil.getIconBasePath()+"lock_up.png");
		lock = new JLabel(lockImg);
		lock.setName("lock");
		lock.setSize(30, 30);
		lock.setLocation(topSize.width - 115, 25);
		lock.setVisible(false);
		lock.addMouseListener(new TopMouseListener() {
			public void mouseClicked(MouseEvent e) {
				hideOrShow(true);
				if (e.getButton() == 1) {
					ImageIcon icon = (ImageIcon) lock.getIcon();
					if (icon.toString().indexOf("lock_down") != -1) {
						icon = new ImageIcon(StyleUtil.getIconBasePath()+"lock_up.png");
						frame.notFixed();
					} else {
						icon = new ImageIcon(StyleUtil.getIconBasePath()+"lock_down.png");
						frame.fixed();
					}
					lock.setIcon(icon);
					refresh();
				}
			}
		});
		add(lock);
		// synchronize
		ImageIcon synchronizeImg = new ImageIcon(StyleUtil.getIconBasePath()+"synchronize.png");
		synchronize = new JLabel(synchronizeImg);
		synchronize.setName("synchronize");
		synchronize.setSize(30, 30);
		synchronize.setLocation(topSize.width - 85, 25);
		synchronize.setVisible(false);
		add(synchronize);
		// setting
		ImageIcon settingImg = new ImageIcon(StyleUtil.getIconBasePath()+"setting.png");
		setting = new JLabel(settingImg);
		setting.setName("setting");
		setting.setSize(30, 30);
		setting.setLocation(topSize.width - 55, 25);
		setting.setVisible(false);
		setting.addMouseListener(new TopMouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					if(!SettingDialog.isShow) {
						MainJFrame mainJFrame = (MainJFrame)ComponentUtil.getParentToClass(e.getComponent(), MainJFrame.class);
						new SettingDialog(mainJFrame);
						SettingDialog.isShow = true;
					}
				}
			}
		});
		add(setting);
	}

	public void hideOrShow(boolean isShow) {
		Done.setVisible(isShow);
		lock.setVisible(isShow);
		synchronize.setVisible(isShow);
		setting.setVisible(isShow);
	}
	private void refresh() {
		setVisible(false);
		setVisible(true);
	}
	public void refreshlSize(Dimension dimension) {
		hideOrShow(true);
		setSize(dimension);
		lock.setLocation(dimension.width-115,25);
		synchronize.setLocation(dimension.width-85,25);
		setting.setLocation(dimension.width-55,25);
		refresh();
	}
}
