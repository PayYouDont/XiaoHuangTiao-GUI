
package com.payudon.gui;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/** 
* @ClassName: FileDialog 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月20日 下午5:10:39 
*  
*/
public class FileDialog extends JDialog{

	private static JFileChooser fDialog;
	
	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private File selectFile;
	static {
		try {
			//设置选择文件窗口为当前系统风格界面
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		fDialog = new JFileChooser();
	}
	public FileDialog() {
		//设置默认打开上次选择路径
		fDialog.setCurrentDirectory(fDialog.getSelectedFile());
		fDialog.setDialogTitle("请选择XML文件");
		int returnVal = fDialog.showOpenDialog(null);
		if(JFileChooser.APPROVE_OPTION == returnVal) {
			setSelectFile(fDialog.getSelectedFile());
		}
	}
	public File getSelectFile() {
		return selectFile;
	}
	public void setSelectFile(File selectFile) {
		this.selectFile = selectFile;
	}
	
}
