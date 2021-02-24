package com.iflytek.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/**
 * ****************************************************************
 * 文件名称 : IflytekFunc
 * ****************************************************************
 */
public class IflytekFunc {

    /**
     * 字符转化为数字(正整数)
     */
    public static int parseInt(String nStr) {
        int n;
        try {
            n = Integer.parseInt(nStr);
        } catch (Exception e) {
            n = -1;
        }
        return n;
    }

    /**
     * 判断String是不是为null或长度为0
     * @param Str 字符
     * @return 是否为空
     */
    public static boolean IsStringEmpty(String Str) {
        return Str == null || Str.trim().length() <= 0;
    }

    public static void GetMapValue(String strSrc, Map<String, String[]> GridMap, Map<String, String> VlaueMap, String strSplitFlag, boolean bKeyToUpp)
    {
        //zhengss 20160810 Ajax2.0,都是单的&，1.0的还是双的&&
//        if(Rc.cfg.pHqAttrSet.pTztHqAttrSetAjaxEngineWebView.IsSupportAjax20()){
//            if(strSplitFlag.endsWith("&&"))
//                strSplitFlag = "&";
//        }
        int iSubBegin = 0;
        int iBegin = -1;
        iBegin = strSrc.indexOf("<GRID");
        int iGridb = -1;
        String strGrid = "";
        String strGridValue = "";
        int iEnd = -1;
        LinkedList<String> ValueList = new LinkedList<String>();

        if(GridMap != null)
            GridMap.clear();
        if(VlaueMap != null)
            VlaueMap.clear();

        int lCountSize = strSrc.length();

        while (iSubBegin < lCountSize) {
            if(iBegin >= 0)
                iGridb = strSrc.indexOf(">", iBegin);
            if (iGridb >= iSubBegin)
            {
                if(iGridb > iSubBegin)
                {
                    String strValue = strSrc.substring(iSubBegin,iBegin);
                    ValueList.add(strValue);
                }
                strGrid = strSrc.substring(iBegin+1, iGridb);
                iEnd = strSrc.indexOf("</"+strGrid+">", iGridb);
                if (iEnd >= iSubBegin) {
                    if (GridMap != null)
                    {
                        strGridValue = strSrc.substring(iBegin+strGrid.length()+2, iEnd);
                        String[] ayGridValue = strGridValue.split(strSplitFlag);
                        GridMap.put(strGrid, ayGridValue);
                    }
                }
                else
                {
                    String strValue = strSrc.substring(iSubBegin);
                    ValueList.add(strValue);
                    break;
                }
            }
            else
            {
                String strValue = strSrc.substring(iSubBegin);
                ValueList.add(strValue);
                break;
            }
            iSubBegin = iEnd+strGrid.length()+3;
            iBegin = strSrc.indexOf("<GRID",iSubBegin);
        }
        if (VlaueMap != null)
        {
            for (int i = 0 ; i < ValueList.size(); i ++)
            {
                String strValue = ValueList.get(i);
                String[] ayValue = split(strValue, strSplitFlag);
                for (int j = 0; j < ayValue.length; j++)
                {
                    String strKeyValue = ayValue[j];
                    int iKey = strKeyValue.indexOf("=");
                    if(iKey > 0 && iKey < strKeyValue.length())
                    {
                        String finalkey = strKeyValue.substring(0,iKey);
                        if (bKeyToUpp)
                        {
                            finalkey = finalkey.toUpperCase();
                        }
                        //update by zhengss20161009（如果使用decode，则导致多层嵌套10090/10061会出现参数混乱的情况，如果url乱码则应该在webview的loadurl加decode）
//						String finalValue = Pub.URLDecoder(strKeyValue.substring(iKey+1));
                        String finalValue = strKeyValue.substring(iKey+1);
                        VlaueMap.put(finalkey, finalValue);
                    }
                }
            }
        }
    }

    /**
     * 分割字符串，返回分割后的字符串数组
     *
     * @param original
     *            字符串
     * @param regex
     *            分隔符
     * @return str
     */
    public static String[] split(String original, String regex) {
        if (IsStringEmpty(original))
            return null;
        String[] str = null;
        ArrayList<String> AyList = new ArrayList<String>();
        split(original, regex, AyList);
        str = new String[AyList.size()];
        for (int i = 0; i < AyList.size(); i++) {
            str[i] = AyList.get(i);
        }
        return str;
    }

    /**
     * 分割字符串放入ArrayList里
     *
     * @param original
     *            字符串
     * @param regex
     *            分隔符
     * @param AyList
     *            ArrayList
     * @return 空
     */
    public static void split(String original, String regex, ArrayList<String> AyList) {
        int startIndex = 0;
        if (AyList == null)
            AyList = new ArrayList<String>();
        int index = 0;
        startIndex = original.indexOf(regex);
        while (startIndex < original.length() && startIndex != -1) {
            String temp = original.substring(index, startIndex);
            AyList.add(temp);
            index = startIndex + regex.length();
            startIndex = original.indexOf(regex, startIndex + regex.length());
        }
        if (index < original.length())
            AyList.add(original.substring(index));
        return;
    }
}
