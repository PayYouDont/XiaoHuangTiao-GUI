package com.payudon.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import com.payudon.listener.MoveWindowListener;
import com.payudon.listener.TopShowMouseAdapter;
import com.payudon.listener.WindowAutoHide;

/**
 * @ClassName: HttpSendJFrame
 * @Description: TODO( )
 * @author peiyongdong
 * @date 2018年7月20日 下午2:54:06
 * 
 */
public class MainJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final MoveWindowListener moveWindowListener = new MoveWindowListener();
	private boolean isAutoHide;
	private boolean isStartAtLogon;
	//隐藏键
	private Integer hideKeyCode;
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addMouseAdapterListener(MouseAdapter mouseAdapterListener) {
		addMouseListener(mouseAdapterListener);
		addMouseMotionListener(mouseAdapterListener);
		addMouseWheelListener(mouseAdapterListener);
	}

	public void removeMouseAdapterListener(MouseAdapter mouseAdapterListener) {
		removeMouseListener(mouseAdapterListener);
		removeMouseMotionListener(mouseAdapterListener);
		removeMouseWheelListener(mouseAdapterListener);
	}

	public void notFixed() {
		addMouseAdapterListener(moveWindowListener);
	}

	public void fixed() {
		removeMouseAdapterListener(moveWindowListener);
	}

	public void hideByKey(int keyCode) {
		this.hideKeyCode = keyCode;
		JIntellitype.getInstance().unregisterHotKey(0);
		JIntellitype.getInstance().registerHotKey(0, 0, keyCode);
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			public void onHotKey(int markCode) {
				if (markCode == 0) {
					if (isVisible()) {
						setVisible(false);
					} else {
						setVisible(true);
					}
				}
			}
		});
	}

	/**
	 * Reg 参数说明 /v 所选项之下要添加或删除的值名 /t RegKey 数据类型（reg_sz字符串） /d 要分配给添加的注册表 ValueName
	 * 的数据 /f 不用提示就强行删除
	 */
	public void changeStart(boolean isStartAtLogon) throws IOException {
		this.isStartAtLogon = isStartAtLogon;
		String regKey = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run";
		String myAppName = "XiaoHuangTiao-GUI";
		String exePath = ".\\XiaoHuangTiao-GUI.exe";// 开机启动程序本地目录
		Runtime.getRuntime().exec("reg " + (isStartAtLogon ? "add " : "delete ") + regKey + " /v " + myAppName
				+ (isStartAtLogon ? " /t reg_sz /d " + exePath : " /f"));
	}

	public void isWindowAutoHide(boolean isAutoHide) {
		this.isAutoHide = isAutoHide;
		WindowAutoHide autoHide = new WindowAutoHide(this);
		if (isAutoHide) {
			addWindowStateListener(autoHide);
		} else {
			removeWindowStateListener(autoHide);
		}

	}

	public boolean autoHide() {
		return this.isAutoHide;
	}
	public boolean startAtLogon() {
		return isStartAtLogon;
	}
	public Integer getHideKeyCode() {
		return hideKeyCode;
	}
	public MainJFrame() {
		setLayout(null);
		// 设置透明
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 130));
		// 设置最小窗口
		setMinimumSize(new Dimension(318, 338));
		// 关闭窗体后终止程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dispose();
		setContentPane(new MainPanel(this));
		// 设置界面可见
		setVisible(true);
		// Frame在窗体居中
		setLocationRelativeTo(null);
		addMouseAdapterListener(moveWindowListener);
		addMouseListener(new TopShowMouseAdapter() {
		});
	}
}
