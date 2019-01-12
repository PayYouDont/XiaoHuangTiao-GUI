package com.payudon.util;

public class StyleUtil {
	
	public static String getLabelHtml(String text,int size,boolean isBold) {
		if(text.isEmpty()) {
			return "";
		}
		if(isBold) {
			return "<html><font style=\"font-size:"+size+"px;\"  face=\"verdana\"><b>"+text+"</b></font></html>";

		}
		return "<html><font style=\"font-size:"+size+"px;\" face=\"verdana\">"+text+"</font></html>";
	}
	public static String getIconBasePath() {
		return "src\\img\\";
	}
	public static String getLabelHtmlText(String labelHtml) {
		if(labelHtml==null) {
			return null;
		}
		if(labelHtml.trim().isEmpty()||labelHtml.indexOf("<html>")==-1) {
			return null;
		}
		if(labelHtml.indexOf("</b>")==-1) {
			return labelHtml.substring(labelHtml.indexOf("face=\"verdana\">")+15,labelHtml.indexOf("</font>"));
		}else {
			return labelHtml.substring(labelHtml.indexOf("<b>")+4,labelHtml.indexOf("</b>"));
		}
	}
}