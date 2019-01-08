/**   
* @Title: ContentText.java 
* @Package com.payudon.gui 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月8日 下午4:44:38 
*/
package com.payudon.gui;

import javax.swing.JPanel;

/** 
* @ClassName: ContentText 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月8日 下午4:44:38 
*  
*/
public class ContentText extends JPanel{
	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private boolean isTop;
	private Integer id;
	private Integer status;
	public boolean isTop() {
		return isTop;
	}
	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
