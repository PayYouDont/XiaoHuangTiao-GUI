package com.payudon.util;

public class StyleUtil {
	
	public static String getLabelHtml(String text,int size,boolean isBold) {
		if(text.isEmpty()) {
			return "";
		}
		if(isBold) {
			return "<html><font style=\"font-size:"+size+"px;\" face=\"verdana\"><b>"+text+"</b></font></html>";

		}
		return "<html><font style=\"font-size:"+size+"px;\" face=\"verdana\">"+text+"</font></html>";
	}
	
}
