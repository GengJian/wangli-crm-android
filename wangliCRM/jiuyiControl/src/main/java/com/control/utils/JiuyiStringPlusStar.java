
package com.control.utils;


/**
 * ****************************************************************
 * 文件名称 : JiuyiStringPlusStar
 * 作       者 : zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述 : 字符串添加*显示
 *****************************************************************
 */
public class JiuyiStringPlusStar {
    /** 替换长度 */
	public static int mRelaceLength = 2;
    /** 前后保留位数 */
	public static int mUnRelaceLength = 3;
	
	/**
	 * 按照中间替换*长度计算：按照*长度计算位置
	 * @param sVlue 字符串
	 * @return 返回带星号的字符串
	 */
	public static String onStartReplace(String sVlue)
	{
		if(Func.IsStringEmpty(sVlue))
			return sVlue;
		if(sVlue.length()<= mRelaceLength)
			return sVlue;
		double nRelaceIndex=(Math.floor((double)(sVlue.length())/2));
		
		double nStart=nRelaceIndex-Math.ceil((double) mRelaceLength /2);//向上取整
		double nEnd=nRelaceIndex+Math.floor((double) mRelaceLength /2);//向下取整
		if(nStart< mUnRelaceLength)
		{
			nStart= mUnRelaceLength;
			nEnd=nStart+(mRelaceLength -1);
		}
		if((nEnd+ mUnRelaceLength)>=(sVlue.length()-1))
			nEnd=(sVlue.length()-1)- mUnRelaceLength;
		String newValue="";
		for (int i=0;i<sVlue.length();i++)
		{
			if(i>=nStart&&i<=nEnd)
			{
				newValue+="*";
			}
			else
				newValue+=sVlue.substring(i, i+1);
		}
		
		
		return newValue;
	}
	
	/**
	 * 按照前后保留位数处理：掐头去尾，中间剩余长度不超过mRelaceLength则按照剩余字符串长度显示*，否则按照mRelaceLength显示*长度
	 * @param sVlue 字符串
	 * @return 返回带星号的字符串
	 */
	public static String onStartReplaceEx(String sVlue)
	{
		if(Func.IsStringEmpty(sVlue))
			return sVlue;
		if(sVlue.length()<=(mUnRelaceLength *2))
			return sVlue;
		String newValue1=sVlue.substring(0, mUnRelaceLength);
		String newValue2="";
		String newValue3=sVlue.substring(sVlue.length()- mUnRelaceLength, sVlue.length());
		String newValueMid=sVlue.substring(mUnRelaceLength,sVlue.length()- mUnRelaceLength);
		int nStartCount=0;
		if(newValueMid.length()< mRelaceLength)
			nStartCount=newValueMid.length();
		else{
			nStartCount= mRelaceLength;
		}
		for (int i=0;i<nStartCount;i++)
		{
			newValue2+="*";
		}
		
		return newValue1+newValue2+newValue3;
	}
	
	/**
	 * 客户姓名
	 * @param sVlue
	 * @return 返回带星号的字符串
	 */
	public static String onStartReplaceName(String sVlue)
	{
		if(Func.IsStringEmpty(sVlue))
			return sVlue;
		if(sVlue.length()<=1)
			return sVlue;
	
		
		return sVlue.substring(0, 1)+"**";
	}
}
