/**   
* @Title: Note.java 
* @Package com.payudon.entity 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月9日 下午3:57:18 
*/
package com.payudon.entity;

import java.awt.Rectangle;
import java.util.Date;

/** 
* @ClassName: Note 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月9日 下午3:57:18 
*  
*/
public class Note {
	private Integer id;
	private String text;
	/*
	 * 0:(完成)
	 * 1:(未完成)
	 */
	private Integer status;
	private boolean isTop;
	private Date createTime;
	private Date completeTime;
	private Rectangle areaRect;
	public static final int STATUS_COMPLETE = 0;
	public static final int STATUS_UNDONE = 1;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public boolean isTop() {
		return isTop;
	}
	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public Rectangle getAreaRect() {
		return areaRect;
	}
	public void setAreaRect(Rectangle areaRect) {
		this.areaRect = areaRect;
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", text=" + text + ", status=" + status + ", isTop=" + isTop + ", createTime="
				+ createTime + ", completeTime=" + completeTime + ", areaRect=" + areaRect + "]";
	}
	
}
