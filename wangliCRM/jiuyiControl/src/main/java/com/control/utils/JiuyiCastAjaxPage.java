package com.control.utils;


/**
 * ****************************************************************
 * 文件名称 : JiuyiCastAjaxPage.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述 : 处理页面号和软件的页面号的转换相关的操作
 *****************************************************************
 */
public class JiuyiCastAjaxPage {
	

	/**
	 * 功能号和软件的功能号对应
	 * @param nPage
	 * @return
	 */
	public static int getAjaxPage(int nPage){
		switch(nPage){
		case 10001: return Pub.m_nStartHomePage;
		case 10002: return Pub.Doback;
		default:return nPage;
		}
	}
}
