package com.control.widget.webview;

import java.util.Map;

/**
 * ****************************************************************
 * 文件名称:JiuyiWebViewCERInfoListener.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/16 13:45
 * ****************************************************************
 */
public interface JiuyiWebViewCERInfoListener
{

	String getLocalData(String strKey);
	
    String getSignature(String beforeSign, String tztCertType);
	
	/** getSignature()时若需要补字段,则在此添加 **/
    Map<String, String> getSignatureAddParam(String tztCertType);
	
	String getCertSN(String tztCertType);
	
	String getCertDN(String tztCertType);
	
	String getCertificateBase64String(String tztCertType);
}
