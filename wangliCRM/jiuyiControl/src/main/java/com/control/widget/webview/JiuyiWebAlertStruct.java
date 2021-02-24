package com.control.widget.webview;

import com.control.utils.Func;

import java.util.HashMap;

/**
 * ****************************************************************
 * 文件名称 : TztWebAlertStruct.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述 : Webview的alert的重复的内容不重复提示
 *****************************************************************
 */
public class JiuyiWebAlertStruct {
	/**
	 * 已经弹出的未关闭的Dialog
	 */
	private HashMap<String,String> m_pAlertedStack = new HashMap<String,String>();


    /**
     * 删除关键字
     * @param dialogMessage dialog显示的信息
     */
	public void delectAlertStack(String dialogMessage){
		if(dialogMessage == null)
			return;
		if(m_pAlertedStack!=null && m_pAlertedStack.size()>0){
			m_pAlertedStack.remove(dialogMessage);
		}
	}

    /**
     * 添加关键字
     * @param belongtoactivity 当前的activity
     * @param dialogMessage dialog显示的信息
     */
	public void addAlertStack(String belongtoactivity,String dialogMessage){
		if(dialogMessage == null || belongtoactivity == null)
			return;
		m_pAlertedStack.put(dialogMessage, belongtoactivity);
	}

    /**
     * 在当前activity里还有未关闭的的activity，不在弹出
     * @param belongtoactivity 当前的activity
     * @param dialogMessage dialog显示的信息
     * @return 是否可以弹出dialog
     */
	public boolean checkAlertStack(String belongtoactivity,String dialogMessage){
		if(dialogMessage == null || belongtoactivity == null)
			return false;
		if(m_pAlertedStack!=null && m_pAlertedStack.size()>0){
			String tmpactivity = m_pAlertedStack.get(dialogMessage);
			if(!Func.IsStringEmpty(tmpactivity)){
				if(tmpactivity.equals(belongtoactivity)){
					return false;
				}
			}
		}
		return true;
	}


    /**
     * 弹出Dialog后必须实现删除关键字
     */
	public interface JsAlertinterface{
		void delectAlertStack(int nAction,int nKeyCode, String dialogMessage);
	}
}
