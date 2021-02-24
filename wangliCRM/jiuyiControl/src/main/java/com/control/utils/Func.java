package com.control.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.control.widget.canvas.Graphics;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static com.control.utils.Pub.SPLIT_CHAR_COMMA;
import static com.control.utils.Pub.SPLIT_CHAR_EQUAL;
import static com.control.utils.Pub.SPLIT_CHAR_VLINE;
import static com.control.utils.Pub.g_lMulti;


/**
 * ****************************************************************
 * 文件名称:Func.java
 * 作    者:Created by zhengss
 * 创建时间:2018/5/6 10:42
 * 文件描述:一些常用的工具函数的集合
 * ****************************************************************
 */

public class Func {

    public static String getLastString(String str) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            return null;
        }

        String file = url.getFile();
        String[] splitStr = file.split("/");
        int len = splitStr.length;
        String result = splitStr[len-1];
        return result;
    }

    /**
     * 字符串中包含某个字符的数量
     * @param s     字符串
     * @param ch    包含的字符
     * @return 包含某个字符的数量
     */
    public static int CharCount(String s, int ch) {
        if(s == null)
            return 0;
        int i = s.indexOf(ch), j = 0;
        while (i >= 0) {
            j++;
            i = s.indexOf(ch, i + 1);
        }
        return j;
    }
    /**
     * 分割字符串放入ArrayList里
     * @param original 字符串
     * @param regex 分隔符
     * @param AyList ArrayList
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

    /**
     * 分割字符串，返回分割后的字符串数组
     * @param original 字符串
     * @param regex 分隔符
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
     * 表格形字符串转成二维数组
     * @param s1    表格形字符，行数参数row在函数里自己计算，不用外界传入
     * @return 二维数组
     */
    public static String[][] parseDealInfo(String s1) {
        if (s1 == null) {
            return null;
        }

        if (s1.indexOf("\r\n") < 0)
        {
            s1 += "\r\n";
        }
        int row = Func.CharCount(s1, '\r');
        String[] strGridData = s1.split("\r\n");
        int nRealRow = strGridData.length;
        if (nRealRow != row)
        {
            row = nRealRow;
        }

        String dealInfo[][] = new String[strGridData.length][];
        for (int i = 0 ; i < strGridData.length ; i ++)
        {
            String strData = strGridData[i];
            if (IsStringEmpty(strData))
            {
                continue;
            }

            String[] strRowData = Func.split(strData, "|");
            if (strRowData == null || strRowData.length <= 0)
            {
                continue;
            }

            dealInfo[i] = strRowData;
        }
        return dealInfo;
    }

    /**
     * 不直接使用URLDecoder.decode，是因为可能出现异常
     * @param url 原始url
     * @return decode之后的值
     */
    public static String URLDecoder(String url) {
        try {
            return URLDecoder.decode(url);
        } catch (Exception e) {
            return url;
        }
    }

    /**
     * 不直接使用URLDecoder.decode，是因为可能出现异常
     * @param url 原始url
     * @param charsetName
     * @return ecode之后的值
     */
    public static String URLDecoder(String url, String charsetName) {
        try {
            return URLDecoder.decode(url, charsetName);
        } catch (Exception e) {
            return url;
        }
    }

    /**
     * 不直接使用URLEncoder.encode，是因为可能出现异常
     * @param url 原始url
     * @return decode之后的值
     */
    public static String URLEncoder(String url) {
        try {
            return URLEncoder.encode(url);
        } catch (Exception e) {
            return url;
        }
    }
    /**
     * 不直接使用URLEncoder.encode，是因为可能出现异常
     * @param url 原始url
     * @param charsetName
     * @return ecode之后的值
     */
    public static String URLEncoder(String url, String charsetName) {
        try {
            return URLEncoder.encode(url, charsetName);
        } catch (Exception e) {
            return url;
        }
    }

    /**
     * 大于10万才转
     * 如果大于10万，转单位为万，则需要有小数点，bPoint参数忽略
     * 如果小于10万，不转单位，如果bPoint=false，则不保留小数点，否则保留小数点
     * @param lSrc
     * @param Divide
     * @param Decimal
     * @param bPoint
     * @return
     */
    public static String GetFormatVolumeStringByUnit2(double lSrc, int Divide, int Decimal, boolean bPoint) {
        if(Math.abs(lSrc / g_lMulti[Divide]) >= 100000){
            return GetFormatStringByUnit2(lSrc, Divide, Decimal, bPoint);
        }else{
            final DecimalFormat formater = new DecimalFormat();
            if(bPoint) {
                formater.setMaximumFractionDigits(Decimal);
                formater.setMinimumFractionDigits(Decimal);
            }else{
                formater.setMaximumFractionDigits(0);
            }
            formater.setGroupingSize(0);
            formater.setRoundingMode(RoundingMode.HALF_UP);
            return formater.format(lSrc / g_lMulti[Divide]);
        }
    }
    public static String GetFormatStringByUnit2(double lSrc, int Divide, int Decimal, boolean bPoint) {
        String strUnit = "";
        double Data = (double) lSrc / (double) g_lMulti[Divide];
        if(Math.abs(Data) >= 100000){
            if (Math.abs(Data) > g_lMulti[8]) {
                strUnit = "亿";
                Data = (double) lSrc / (double) g_lMulti[Divide + 8];
            } else if (Math.abs(Data) > g_lMulti[4]) {
                strUnit = "万";
                Data = (double) lSrc / (double) g_lMulti[Divide + 4];
            } else {
                Data = (double) lSrc / (double) g_lMulti[Divide];
            }

            final DecimalFormat formater = new DecimalFormat();
            formater.setMaximumFractionDigits(Decimal);
            formater.setMinimumFractionDigits(Decimal);
            formater.setGroupingSize(0);
            formater.setRoundingMode(RoundingMode.HALF_UP);
            return formater.format(Data) + strUnit;
        }else{
            final DecimalFormat formater = new DecimalFormat();
            if(bPoint) {
                formater.setMaximumFractionDigits(Decimal);
                formater.setMinimumFractionDigits(Decimal);
            }else{
                formater.setMaximumFractionDigits(0);
            }
            formater.setGroupingSize(0);
            formater.setRoundingMode(RoundingMode.HALF_UP);
            return formater.format(Data);
        }
    }

    /**
     * 把long数据格式化成缩小10的Divide次方倍,精确到小数点Decimal位的字符串(带单位)
     * 如果大于10万，转单位为万，则需要有小数点，bPoint参数忽略
     * 如果小于10万，不转单位，如果bPoint=false，则不保留小数点，否则保留小数点
     * @param lSrc - 如: 12345
     * @param Divide - 缩小10的Divide次方倍 如: 12345 -> 12.345
     * @param Decimal - 有效的小数位数 如: 12.345 -> 12.35
     * @return 12.35
     */
    public static String GetFormatStringByUnit(long lSrc, int Divide, int Decimal, boolean bPoint) {
        String strUnit = "";
        double Data = (double) lSrc / (double) g_lMulti[Divide];
        if(Math.abs(Data) >= 100000){
            if (Math.abs(Data) > g_lMulti[8]) {
                strUnit = "亿";
                Data = (double) lSrc / (double) g_lMulti[Divide + 8];
            } else if (Math.abs(Data) > g_lMulti[4]) {
                strUnit = "万";
                Data = (double) lSrc / (double) g_lMulti[Divide + 4];
            } else {
                Data = (double) lSrc / (double) g_lMulti[Divide];
            }

            final DecimalFormat formater = new DecimalFormat();
            formater.setMaximumFractionDigits(Decimal);
            formater.setMinimumFractionDigits(Decimal);
            formater.setGroupingSize(0);
            formater.setRoundingMode(RoundingMode.HALF_UP);
            return formater.format(Data) + strUnit;
        }else{
            final DecimalFormat formater = new DecimalFormat();
            if(bPoint) {
                formater.setMaximumFractionDigits(Decimal);
                formater.setMinimumFractionDigits(Decimal);
            }else{
                formater.setMaximumFractionDigits(0);
            }
            formater.setGroupingSize(0);
            formater.setRoundingMode(RoundingMode.HALF_UP);
            return formater.format(Data);
        }
    }
    /**
     * 把long数据格式化成缩小10的Divide次方倍,精确到小数点Decimal位的字符串(无单位)
     *
     * @param lSrc - 如: 12345
     * @param Divide - 缩小10的Divide次方倍 如: 12345 -> 12.345
     * @param Decimal - 有效的小数位数 如: 12.345 -> 12.35
     * @return 12.35
     */
    public static String GetFormatString(long lSrc, int Divide, int Decimal) {
        String fstr = Decimal > 0 ? "0." : "0";
        for (int i = 0; i < Decimal; i++)
            fstr += "0";
        float Data = (float) (lSrc * g_lMulti[Decimal]) / (float) g_lMulti[Divide];
        boolean bFs = Data < 0;
        if (bFs)
            Data = Math.abs(Data);

        //（如果数据超出int的最大值最小值，计算的数据会是错误的）
        String c = new DecimalFormat(fstr).format(Data / g_lMulti[Decimal]);
        if (c.startsWith("."))
            c = "0" + c;
        if (bFs)
            c = "-" + c;

        return c;
    }


    /**
     * int转FloatString
     * @param i 原始数据
     * @param flag false，是否显示"+"号.
     * @param flag1 true，是否可以显示默认“--”
     * @param PointSize 小数点
     * @param StockType 股票类型
     * @return FloatString
     */
    public static final String ToFloatString(long i, boolean flag, boolean flag1, int PointSize, int StockType) {
        if (flag1 && i <= 0) {
            return "--";
        }
        int j = GetPriceScale(StockType);// 表示精度
        String s = LongToString(i, j, PointSize);
        if (flag && i > 0) {
            s = "+" + s;
        }
        return s;
    }

    /**
     * 添加pointSize参数，表示要显示的小数位数
     * @param lSrc - 如: 12345
     * @param Divide - 缩小10的Divide次方倍 如: 12345 -> 12.345
     * @param Decimal - 有效的小数位数 如: 12.345 -> 12.35
     * @return
     */
    public static final String LongToString(long lSrc, int Divide, int Decimal) {
        //return GetFormatString(l1, Scale, pointSize);
        String fstr = Decimal > 0 ? "0." : "0";
        for (int i = 0; i < Decimal; i++)
            fstr += "0";
        float Data = (float) (lSrc * g_lMulti[Decimal]) / (float) g_lMulti[Divide];
        boolean bFs = Data < 0;
        if (bFs)
            Data = Math.abs(Data);

        String c = new DecimalFormat(fstr).format(Data / g_lMulti[Decimal]);
        if (c.startsWith("."))
            c = "0" + c;
        if (bFs)
            c = "-" + c;

        return c;
    }

    /**
     * 添加pointSize参数，表示要显示的小数位数 扩展
     * @param lSrc - 如: 12345
     * @param Divide 精度
     * @param Decimal - 有效的小数位数
     * @return
     */
    public static String LongToStringEx(long lSrc, int Divide, int Decimal) {
        String fstr = Decimal > 0 ? "0." : "0";
        for (int i = 0; i < Decimal; i++)
            fstr += "0";
        float Data = (float) (lSrc * g_lMulti[Decimal]) / (float)Divide;
        boolean bFs = Data < 0;
        if (bFs)
            Data = Math.abs(Data);

        String c = new DecimalFormat(fstr).format(Data /g_lMulti[Decimal]);
        if (c.startsWith("."))
            c = "0" + c;
        if (bFs)
            c = "-" + c;

        return c;
    }

    /**
     * 取得百分比字符串的函数
     * @param i 除数
     * @param j 被除数
     * @param flag flag是否显示"+"号.
     * @return
     */
    public static final String GetPercent(long i, int j, boolean flag) {
        String s;
        if (i != 0 && j != 0) {
            long k = (i * 10000L) / (long) j;
            // long k = ((long) i) / (long) j;
            s = LongToString( k, 2, 2);
            // s = LongToString((long) k, 2, 3);
            if (k > 0 && flag) {
                s = "+" + s;
            }
        } else {
            //s = "--";
            s = "0.00";
        }
        s = s + "%";
        return s;
    }

    /**
     * 根据类型获取小数点精度
     * @param StockType 市场类型
     * @return  小数点精度
     */
    public static final int GetPriceScale(int StockType) // 获取精度
    {
        return (StockType > 80 && StockType < 84 || StockType >= 113 && StockType <= 116 || StockType == 120 || StockType == 121
               ) ? 4 : 3;
    }



    /**
     * 数组是否包含指定的元素（int）
     * @param arr 原始数组
     * @param item 当前元素
     * @return 是否包含
     */
    public static final boolean InArray(int[] arr, int item) {
        if (arr == null || arr.length <= 0)
            return false;
        else {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == item)
                    return true;
            }
            return false;
        }
    }

    /**
     * 数组是否包含指定的元素（String）
     * @param arr 原始数组
     * @param item 当前元素
     * @return 是否包含
     */
    public static final boolean InArray(String[] arr, String item) {
        if (arr == null || arr.length <= 0)
            return false;
        else {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].endsWith(item))
                    return true;
            }
            return false;
        }
    }

    /**
     * 判断Vector是不是为空
     * @param v  原始Vector
     * @return 是不是为空
     */
    public static final boolean IsVectorEmpty(Vector<?> v) {
        return v == null || v.size() <= 0;
    }

    /**
     * 判断String是不是为纯数字
     * @param nNum 数字字符
     * @return  是不是为纯数字
     */
    public static boolean IsNumber(String nNum) {
        if (nNum == null || nNum.length() <= 0) {
            return false;
        }
        for (int i = 0; i < nNum.length(); i++) {
            char c = nNum.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断String是不是为纯数字（包含小数点）
     * @param nNum 数字字符
     * @return  是不是为纯数字（包含小数点）
     */
    public static boolean isEffectiveNumber(String nNum){
        java.util.regex.Pattern p=java.util.regex.Pattern.compile("^\\d+$|^\\d+\\.\\d+$");
        java.util.regex.Matcher m=p.matcher(nNum);
        return m.matches();
    }

    /**
     * 判断String是不是为纯字母数字
     * @param letter 数字字符
     * @return 是不是为纯字母数字
     */
    public static boolean IsNumLetter(String letter) {
        if (letter == null || letter.length() <= 0) {
            return false;
        }
        for (int i = 0; i < letter.length(); i++) {
            char c = letter.charAt(i);
            if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z') && !(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断String是不是为正确的int数字格式
     *
     * @param nNum 数字字符
     * @param bPositive 要求大于0
     * @return
     */
    public static boolean IsIntFormat(String nNum, boolean bPositive) {
        String intformat = "[1-9]\\d*";
        Pattern p = Pattern.compile(intformat);
        Matcher m = p.matcher(nNum);
        boolean bMatche = m.matches();
        boolean bValue = !bPositive || (parseInt(nNum) > 0);
        return bMatche && bValue;
    }

    /**
     * 判断String是不是为正确的Float数字格式
     *
     * @param nNum 数字字符
     * @param bPositive  要求大于0
     * @return
     */
    public static boolean IsFloatFormat(String nNum, boolean bPositive) {
        if (IsIntFormat(nNum, bPositive))
            return true;
        String intformat = "[1-9]\\d*\\.\\d+";
        Pattern p = Pattern.compile(intformat);
        Matcher m = p.matcher(nNum);

        String intformat2 = "[0]\\.\\d+";
        Pattern p2 = Pattern.compile(intformat2);
        Matcher m2 = p2.matcher(nNum);

        boolean bMatche = m.matches() || m2.matches();
        boolean bValue = !bPositive || (parseFloat(nNum) > 0);
        return bMatche && bValue;
    }

    /**
     * 判断String是不是为null或长度为0
     * @param Str 字符
     * @return 是否为空
     */
    public static boolean IsStringEmpty(String Str) {
//		if (Str == null || Str.length() <= 0)
        return Str == null || Str.trim().length() <= 0;
    }

    /**
     * 判断String是不是手机号码
     */
    public static boolean IsPhoneNum(String Str) {
        if (Str == null || Str.length() == 0) {
            return false;
        } else {
            return Str.startsWith("1") && IsNumber(Str);
        }
    }
    /**
     *手机验证
     * @param phoneNum
     * @return
     */
    public static boolean isCellphone(String  phoneNum) {
        if (phoneNum == null || phoneNum.length() <= 0)
            return false;

        Pattern pattern = Pattern.compile("1[0-9]{10}");
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }

    /**
     *邮箱验证
     * @param phoneNum
     * @return
     */
    public static boolean isEmail(String  email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 字符转化为数字(正long)
     */
    public static long parse16StringToLong(String nStr) {
        long n;
        try {
            if(nStr != null){
                if(nStr.toLowerCase().startsWith("0x")){
                    nStr = nStr.substring(2, nStr.length());
                }
            }
            n = Long.parseLong(nStr, 16);
        } catch (Exception e) {
            n = -1;
        }
        return n;
    }
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

    public static long parseLong(String nStr) {
        long n;
        try {
            n = Long.parseLong(nStr);
        } catch (Exception e) {
            n = -1;
        }
        return n;
    }
    public static float parseFloat(String nStr) {
        float n;
        try {
            n = Float.parseFloat(nStr);
        } catch (Exception e) {
            n = 0.0f;
        }
        return n;

    }

    /**
     * float转换long
     * @param nValue
     * @param bNeedRound 是否四舍五入（true是）
     * @return
     */
    public static long floatParseLong(Float nValue,boolean bNeedRound) {
        long n;
        try {
            BigDecimal bigdecimal = new BigDecimal(nValue);
            if(bNeedRound)
                bigdecimal = new BigDecimal(nValue+0.5);
            n=bigdecimal.longValue();
        } catch (Exception e) {
            n = 0;
        }
        return n;
    }

    /**
     * 格式化按钮字符串为菜单字符串
     */
    public static String btnStr2MenuStr(String Str) {
        int m_nIcoCount = CharCount(Str, '|');
        if (m_nIcoCount <= 0)
            return Str;
        int nPos = 0;
        for (int i = 0; i < m_nIcoCount; i++) {
            nPos = Str.indexOf("|", nPos);
            Str = Str.substring(0, nPos + 1) + "\r\n" + Str.substring(nPos + 1, Str.length());
            nPos++;
        }
        return Str;
    }

    /*
     * 处理形如 自动选择联网方式|-1|0| NET(互联网)|2|0| 的字符串到String的二维数组
     */
    public static String[][] parseStr2Array(String s1, String split) {
        if (s1 == null) {
            return null;
        }
        if (s1.indexOf("\r\n") < 0)
        {
            s1 += "\r\n";
        }
        String[] strGridData = s1.split("\r\n");
        String dealInfo[][] = new String[strGridData.length][];
        for (int i = 0 ; i < strGridData.length ; i ++)
        {
            String strData = strGridData[i];
            if (IsStringEmpty(strData))
            {
                continue;
            }

            String[] strRowData = split(strData, split);
            if (strRowData == null || strRowData.length <= 0)
            {
                continue;
            }

            dealInfo[i] = strRowData;
        }
        return dealInfo;
    }
    /*
     * 处理形如 确定,1001|返回,1020|的字符串 到String[][]的数组
     */
    public static String[][] SplitStr2Array(String Str) {
        if (IsStringEmpty(Str))
            return null;
        String[][] tmp = parseStr2Array(btnStr2MenuStr(Str), SPLIT_CHAR_VLINE);
        if (tmp == null || tmp.length <= 0)
            return null;
        String[][] rtn = new String[tmp.length][];
        for (int i = 0; i < rtn.length; i++) {
            rtn[i] = split(tmp[i][0], SPLIT_CHAR_COMMA);
        }
        return rtn;
    }

    /*
     * 处理形如 tab1=0|&&tab2=5|&&tab3=0| 到String[][]的数组
     * GetStringByName
     */
    public static String[][] SplitStr2Array2(String Str) {
        if (IsStringEmpty(Str))
            return null;
        Str = Str.replace("|&&", "|");
        String[][] tmp = parseStr2Array(btnStr2MenuStr(Str), SPLIT_CHAR_VLINE);
        if (tmp == null || tmp.length <= 0)
            return null;
        String[][] rtn = new String[tmp.length][];
        for (int i = 0; i < rtn.length; i++) {
            rtn[i] = split(tmp[i][0], SPLIT_CHAR_EQUAL);
        }
        return rtn;
    }
    /**
     *
     * @param addr
     * @param flag
     * @return
     */
    public static String[] StringToArray(String addr, char flag) {
        Vector<String> Vec = new Vector<String>();
        String str;
        int y = 0/*, i = 0*/;
        int x = addr.indexOf(flag, y);
        while (x >= 0) {
            str = addr.substring(y, x);
            Vec.addElement(str);
            //i++;
            y = x + 1;
            x = addr.indexOf(flag, y);
        }
        if (y < addr.length()) {
            str = addr.substring(y);
            if (str.indexOf("\r") < 0 && str.indexOf("\n") < 0)
                Vec.addElement(str);
        }

        String[] SelOrVol = new String[Vec.size()];
        Vec.copyInto(SelOrVol);
        return SelOrVol;
    }

    public static String GetStringByName(String hs, String name) {
        String str = "";

        int pos = hs.indexOf(name + "=");
        if (pos >= 0) {
            int end = hs.indexOf("\r\n", pos);
            if (end >= 0)
                str = hs.substring(pos + name.length() + 1, end);
            else
                str = hs.substring(pos + name.length() + 1);

        }
        return str;
    }
    /**
     * 数字签名补齐0
     * @param input
     * @return
     */
    public static String IntToStrByAddZero(int input) {
        if (input > 9) {
            return String.valueOf(input);
        } else {
            return "0" + String.valueOf(input);
        }
    }

    /**
     * 根据字符串数组计算每列宽度
     * @param mGraphics
     * @param strArr 要测量宽度的字符串数组
     * @param width     总宽度
     * @param fontsize  字体
     * @param addPadding 是否添加 宽度不够一页特殊处理，当委托false时，宽度width无效
     * @return
     */
    public static int[] getColMaxRow(Graphics mGraphics, String[] strArr, int width, int fontsize, boolean addPadding){
        if(strArr == null || strArr.length<=0)
            return null;

        int[] ColMaxRow = new int[strArr.length];

        int sumlen = 0;
        do{
            sumlen = 0;
            for (int i=0;i<ColMaxRow.length;i++){
                ColMaxRow[i] = (int) mGraphics.stringWidth_zhengss(strArr[i], fontsize);
                sumlen += ColMaxRow[i];
            }
            fontsize--;
        }while(sumlen > width);

        // 宽度不够一页特殊处理
        if(addPadding) {
            if (sumlen < width) {
                int nPadd = (width - sumlen) / ColMaxRow.length;
                for (int j = 0; j < ColMaxRow.length; j++) {
                    ColMaxRow[j] += nPadd;
                }
            }
        }
        return ColMaxRow;
    }

    // 给定的日期减一定的天数
    public static String ReduceDates(int year, int month, int date, int decDay) {
        if (date > decDay) {
            date = date - decDay;
        } else if (month > 1) {
            month = month - 1;
            if (month == 2) {
                date = date + 28 - decDay;
            } else {
                date = date + 31 - decDay;
            }
        } else {
            year = year - 1;
            month = 12;
            date = date + 31 - decDay;
        }
        return String.valueOf(year) + IntToStrByAddZero(month) + IntToStrByAddZero(date);
    }

    /**
     * 十六进制转换字符串
     * @param hexStr str Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr)
    {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++)
        {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }


    /**
     * 从服务器取图片
     *
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setConnectTimeout(0);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);

            is.close();
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));
        }
        return bitmap;
    }


    /**
     * 获取URL内容
     */
    public static String getUrlContent(String path) {
        StringBuffer str = new StringBuffer();
        try {
            URL url = new URL(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                str.append(line);
            }
            // Thread.sleep(2000);
            in.close();
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e) + "\r\n" + path);
        }
        int index = str.toString().indexOf("http://");
        if (index == -1)
            return "";
        String s = new String(str.toString().substring(index, str.toString().length()));
        return s.toString();
    }


    /**
     * 通过url获取param的值
     * @param url
     * @param param
     * @return
     */
    public static String getValueByUrl(String url,String param){
        String value = "";
        if(!Func.IsStringEmpty(url) && url.indexOf(param+"=") >= 0){
            url = url.substring(url.indexOf(param+"=")+(param+"=").length(), url.length());
            int splitpos = url.indexOf("&");
            if(splitpos < 0)
                splitpos = url.length();
            value = url.substring(0, splitpos);
        }
        return value;
    }

    public static double getDouble(String str, double ddefault) {

        double dResult = ddefault;
        try {
            dResult = Double.parseDouble(str);
        } catch (Exception nfe) {
            dResult = ddefault;
        }
        return dResult;
    }

    public static void getMapValue(String strSrc, Map<String, String[]> GridMap, Map<String, String> VlaueMap, String strSplitFlag, boolean bKeyToUpp)
    {
        //都是单的&，1.0的还是双的&&
//        if(Rc.cfg.pHqAttrSet.mJiuyiHqAttrSetAjaxEngineWebView.isSupportAjax20()){
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
        ArrayList<String> ValueList = new ArrayList<String>();

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
                        //（如果使用decode，则导致多层嵌套会出现参数混乱的情况，如果url乱码则应该在webview的loadurl加decode）
//						String finalValue = URLDecoder(strKeyValue.substring(iKey+1));
                        String finalValue = strKeyValue.substring(iKey+1);
                        VlaueMap.put(finalkey, finalValue);
                    }
                }
            }
        }
    }

    public static void getMapValue(String strSrc,Map<String, String[]> GridMap,Map<String, String> VlaueMap)
    {
        getMapValue(strSrc, GridMap, VlaueMap, "\r\n", true);
    }

    /**
     * 扩展 key是否小写
     * @param strSrc
     * @param GridMap
     * @param VlaueMap
     * @param strSplitFlag
     * @param bKeyToLower
     */
    public static void getMapValueEx(String strSrc, Map<String, String[]> GridMap, Map<String, String> VlaueMap, String strSplitFlag, boolean bKeyToLower)
    {
        int iSubBegin = 0;
        int iBegin = -1;
        iBegin = strSrc.indexOf("<GRID");
        int iGridb = -1;
        String strGrid = "";
        String strGridValue = "";
        int iEnd = -1;
        ArrayList<String> ValueList = new ArrayList<String>();

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
                        if (bKeyToLower)
                        {
                            finalkey = finalkey.toLowerCase();
                        }
                        String finalValue = URLDecoder(strKeyValue.substring(iKey+1));
                        VlaueMap.put(finalkey, finalValue);
                    }
                }
            }
        }
    }

    /**
     * 解析数据KeyValue
     * @param strSrc
     * @param GridMap
     * @param VlaueMap
     */
    public static void DealDataMapValue(String strSrc,Map<String, String[]> GridMap,Map<String, String> VlaueMap)
    {
        int iSubBegin = 0;
        int iBegin = -1;
        iBegin = strSrc.indexOf("<GRID");
        int iGridb = -1;
        String strGrid = "";
        String strGridValue = "";
        int iEnd = -1;
        ArrayList<String> ValueList = new ArrayList<String>();
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
                        String[] ayGridValue = strGridValue.split("\r\n");
                        GridMap.put(strGrid.toLowerCase(), ayGridValue);
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
                String[] ayValue = split(strValue, "\r\n");
                for (int j = 0; j < ayValue.length; j++)
                {
                    String strKeyValue = ayValue[j];
                    int iKey = strKeyValue.indexOf("=");
                    if(iKey > 0 && iKey < strKeyValue.length())
                    {
                        VlaueMap.put(strKeyValue.substring(0,iKey).toLowerCase(), strKeyValue.substring(iKey+1));
                    }
                }
            }
        }

    }


    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int getMoveStepValue(String strValue, int nDefaultMoveStep)
    {
//		String strValue = dValue + "";
        int nRetValue = nDefaultMoveStep;
        if (strValue == null || strValue.length() <= 0)
        {
            return nDefaultMoveStep;
        }

        if (strValue.indexOf(".") > 0 && strValue.indexOf(".") < strValue.length())
        {
            int nPowCount = strValue.substring(strValue.indexOf(".") + 1).length();
            nRetValue = (int) Math.pow(10, nPowCount);
        }

        if(nRetValue < nDefaultMoveStep){
            nRetValue = nDefaultMoveStep;
        }
        return nRetValue;
    }

    /**
     * 去除前面的数字序号和点
     */
    public static String substringNumdot(String str){
        if(str != null){
            int nPos = str.indexOf(".");
            if (nPos >= 0 && parseInt(str.substring(0,nPos)) >= 0)
                str = str.substring(nPos + 1,str.length());
        }
        return str;
    }

    /**
     * 根据long的数据 返回直接显示的数字 保留2位小时 为0是显示-- 比如：55.22亿 3.21万等
     *
     * @param lVol
     * @return
     */
    public static String formatVom_ZLL(double lVol) {

        // 如果小于等于0 直接返回0
        if (0 == lVol) {
            return "0";
        }

        String strUnit = "";
        Double strValue = 0.0;

        if (Math.abs(lVol) > 100000000L) {
            strUnit = "亿";
            strValue = lVol / 100000000;
        }
        else if (Math.abs(lVol) > 10000L) {
            strUnit = "万";
            strValue = lVol / 10000;
        }
        else {
            // 没有超过万，亿的情况下 直接返回
            return String.valueOf(lVol);
        }

        // 精确到2位
        DecimalFormat df = new DecimalFormat("###.00");
        return df.format(strValue) + strUnit;
    }
    /**
     *
     * 格式化 去掉.0
     *
     * @param lVol
     * @return String
     */
    public static String formatVom(double lVol) {

        String value = formatVom_ZLL(lVol);

        if (value.endsWith(".0")) {
            return value.substring(0, String.valueOf(lVol).length() - 2);
        }
        else {
            return value;
        }

    }

    /*
     * @author rongbo 格式化输入，比如--%，--,统一格式化为--
     */
    public static String formatInput_RB(String input) {
        if (IsStringEmpty(input))
            return "--";
        if ("--%".equals(input) || "--".equals(input) || "--%".equals(input))
            return "--";
        return input;
    }


    /**
     * 根据日期获得星期
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 是否是在主线程
     * @return
     */
    public static boolean isMainThread(){
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * 根据数字转中文
     * @param index
     * @return
     */
    public static String getNumCH(int index){
        String arrayNumeCH[] = new String[]{"一","二","三","四","五","六","七","八","九","十"};
        if(index<0 || index>=arrayNumeCH.length)
            return "";
        return arrayNumeCH[index];
    }

    public static String parseIntColorToARGB(int color){
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        return String.format("%x%x%x" ,red, green, blue);
    }
    /**
     * 拆分颜色
     */
    public static int[] rgb(int colors[]) {
        if (colors == null)
            return null;

        int[] pRGB = new int[colors.length];
        for (int i1 = 0; i1 < colors.length; i1++) {
            int i = colors[i1];
            int r = ((((i >> 5) % 8) << 3) + ((i >> 5) % 8)) * 4;
            int g = ((((i >> 2) % 8) << 3) + ((i >> 2) % 8)) * 4;
            int b = (((i % 4) << 4) + ((i % 4) << 2) + (i % 4)) * 4;
            pRGB[i1] = getRGBColor(r, g, b);
        }

        return pRGB;

    }

    /**
     * 设置颜色
     * @param r
     * @param g
     * @param b
     * @return
     */
    public static int getRGBColor(int r, int g, int b) {
        //int alpha = 0;
        return android.graphics.Color.rgb(r, g, b);
    }

     /**
     * 获取字符的宽度
     * @param strs
     * @param mFontSize
     * @return
     */
    public static int getStringWidth(String strs, float mFontSize){
        if(IsStringEmpty(strs)){
            return 0;
        }
        float fontScale = 1;
        try {
            Resources resource = Rc.getApplication().getResources();
            Configuration config = resource.getConfiguration();
            fontScale = config.fontScale;//默认为标准字体
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }

        Rect rect = new Rect();
        Paint paint = new Paint();
        paint.setTextSize(Res.Dip2Pix(mFontSize*fontScale));
        paint.getTextBounds(strs,0,strs.length(), rect);
        return rect.width();
    }

    /**
     * 可以根据fontScale变大的高度
     * @param mHeight
     * @return
     */
    public static int getHeightBuyFontScale(float mHeight){
        float fontScale = 1;
        try {
            Resources resource = Rc.getApplication().getResources();
            Configuration config = resource.getConfiguration();
            fontScale = config.fontScale;//默认为标准字体
        } catch (Exception e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
        return (int)(fontScale * mHeight);
    }
    /**
     * 优化了获取导航栏和状态栏的高度的获取方法
     * 原来onWindowFocusChanged里获取高度的方法取消
     * 有的机型是没有status_bar_height和navigation_bar_height资源的
     */
    public static int[] getNaviStatusBarSize(Activity activity)
    {
        int m_nTopStatusHeight = 0;//Res.Dip2Pix(24);
        int m_nBottomStatusHeight = 0;//Res.Dip2Pix(36);
        if(activity != null){
            Resources resources = activity.getResources();
            int rid = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (rid > 0) {
                JiuyiLog.d("ScreenSize", resources.getBoolean(rid) + ""); // 获取导航栏是否显示true or false
            }

            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

            //获取status_bar_height资源的ID
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                m_nTopStatusHeight = resources.getDimensionPixelSize(resourceId);
            }else{
                m_nTopStatusHeight = frame.top;
            }

            resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                m_nBottomStatusHeight = resources.getDimensionPixelSize(resourceId);
            }else{
                m_nBottomStatusHeight = activity.getWindow().getWindowManager().getDefaultDisplay().getHeight() - frame.bottom;
            }
        }
        if(m_nTopStatusHeight <= 0){
            m_nTopStatusHeight = Res.Dip2Pix(24);
        }
        return new int[]{m_nTopStatusHeight, m_nBottomStatusHeight};
    }
    /**
     * 判断某个服务是否在运行
     * @param mContext
     * @param className
     * @return
     */
    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        try {
            ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(100);
            if (!(serviceList != null && serviceList.size() > 0)) {
                return false;
            }
            for (int i = 0; i < serviceList.size(); i++) {
                if (serviceList.get(i).service.getClassName().equals(className) == true) {
                    isRunning = true;
                    break;
                }
            }
        } catch (SecurityException e) {
            JiuyiLog.e("error", JiuyiLog.getStackTraceString(e));//e.printStackTrace();
        }
        return isRunning;
    }

    /**
     * 截屏幕的某个区域保存为图片操作
     * @param activity
     * @return
     */
    public static Bitmap shotPic(Activity activity, int cutX, int cutY, int picWidth, int picHeight) {
        // View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // 去掉标题栏
        Bitmap b = Bitmap.createBitmap(b1, cutX, cutY, picWidth, picHeight);
        view.destroyDrawingCache();
        return b;
    }

    /**
     * 在给定的图片的右上角加上数量。数量用红色表示
     * @param pIamge
     * @param imgName
     * @param numLable
     * @param topmargin
     * @param rightmargin
     * @return
     */
    public static Bitmap addImageViewBadge(ImageView pIamge, String imgName, int numLable, int topmargin, int rightmargin){
        Bitmap icon = BitmapFactory.decodeResource(pIamge.getContext().getResources(), Res.getDrawabelID(pIamge.getContext(), imgName));
        if(numLable <= 0)
            return icon;
        //初始化画布
        int width = icon.getWidth();
        int height = icon.getHeight();
        Bitmap contactIcon=Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(contactIcon);

        //拷贝图片
        Paint iconPaint=new Paint();
        iconPaint.setDither(true);//防抖动
        iconPaint.setFilterBitmap(true);//用来对Bitmap进行滤波处理，这样，当你选择Drawable时，会有抗锯齿的效果
        Rect src=new Rect(0, 0, width, height);
        Rect dst=new Rect(0, 0, width, height);
        canvas.drawBitmap(icon, src, dst, iconPaint);

        if(numLable > 0){
            int fontsize = Res.Dip2Pix(14);
            int r = fontsize;// * 4 / 3;
            //绘制数量的背景
            Paint bgPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DEV_KERN_TEXT_FLAG);
            bgPaint.setColor(Color.RED);
            bgPaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawCircle(width-r-rightmargin, r+topmargin, r, bgPaint);
//int topmargin,int rightmargin
            //启用抗锯齿和使用设备的文本字距
            Paint countPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DEV_KERN_TEXT_FLAG);
            countPaint.setColor(Color.WHITE);
            countPaint.setTextSize(fontsize);
            countPaint.setTextAlign(Paint.Align.CENTER);
            countPaint.setTypeface(Typeface.DEFAULT_BOLD);

            String str = String.valueOf(numLable);
            Rect bounds = new Rect();
            countPaint.getTextBounds(str, 0, str.length(), bounds);

            canvas.drawText(str, width-r-rightmargin, topmargin+(r*2+bounds.height())/2, countPaint);
        }
        return contactIcon;
    }
    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String addComma(String str) {
        if(str.equals("0.0")||str.equals("0.00")||str.equals("0")){
            return "0.00";
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(Double.parseDouble(str));
    }
    /**
     * 格式化数字，用逗号分割
     *
     * @param number 1000000.7569 to 1,000,000.76 or
     * @return
     */
    public static String formatNumberWithCommaSplit(double number) {
        String firstStr = "";//第一个字符
        String middleStr = "";//中间字符
        String endStr = "";//小数后两位
        if (number < 0) {
            firstStr = "-";
        } else if (number != 0 && number < 0.1) {
            return number + "";
        }

        String tempNumberStr = number + "00";
        int endIndex = tempNumberStr.lastIndexOf(".");
        endStr = tempNumberStr.substring(endIndex+1, endIndex + 3);

        String numberStr = Math.abs((long) number) + "";//取正

        int firstIndex = numberStr.length() % 3;
        int bitCount = numberStr.length() / 3;

        if (firstIndex > 0) {
            middleStr += numberStr.substring(0, firstIndex) + ",";
        }
        for (int i = 0; i < bitCount; i++) {
            middleStr += numberStr.substring(firstIndex + i * 3, firstIndex + i * 3 + 3) + ",";
        }
        if (middleStr.length() > 1) {
            middleStr = middleStr.substring(0, middleStr.length() - 1);
        }
        return firstStr + middleStr + "." + endStr;
    }

    /**
     * 当浮点型数据位数超过10位之后，数据变成科学计数法显示。用此方法可以使其正常显示。
     * @param value
     * @return Sting
     */
    public static String formatFloatNumber(double value) {
        if(value != 0.00){
            java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
            return df.format(value);
        }else{
            return "0.00";
        }

    }
    public static String formatFloatNumberChart(double value) {
        if(value != 0.00){
            java.text.DecimalFormat df = new java.text.DecimalFormat("########");
            return df.format(value);
        }else{
            return "0";
        }

    }
    public static String formatFloatNumber(Double value) {
        if(value != null){
            if(value.doubleValue() != 0.00){
                java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
                return df.format(value.doubleValue());
            }else{
                return "0.00";
            }
        }
        return "";
    }

    public static String addCommaChart(String str) {
        if(str.equals("0.0")||str.equals("0.00")||str.equals("0")){
            return "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(Double.parseDouble(str));
    }

    public static float initScaling(float min, float max,int length){
        float scaling;
        int count = 0;
        /**
         * 初步计算刻度值,排除length=0,或scaling=0的情况
         */
        if (length<16&&length!=0){
            scaling = (max-min)/length;
        }else {
            scaling = (max-min)/15;
        }
        if (scaling<0){
            scaling = -scaling;
//            scalAxisSgin = -1;
        }
        //判断刻度值精度
        if (scaling>1){
            while (scaling>10){
                scaling=scaling/10;
                count++;
            }
            scaling = (float) (Math.ceil(scaling)*Math.pow(10,count));
        }else {
            while (0<scaling&&scaling<1){
                scaling=scaling*10;
                count++;
            }
            scaling = (float) (Math.ceil(scaling)*Math.pow(10,-count));
        }
        return scaling;
    }
    /**
     * 是否是合法的Gson字符串
     * @param targetStr
     * @return
     */
    private static boolean isGoodGson(String targetStr,Class clazz) {
        if(IsStringEmpty(targetStr)){
            return false;
        }
        try {
            new Gson().fromJson(targetStr,clazz);
            return true;
        } catch(JsonSyntaxException ex) {
            return false;
        }
    }

    /**
     * 判断是JsonObject
     * @param obj
     * @return
     */
    public static boolean isJsonObject(Object obj){
        String content = obj.toString();
        try {
            JSONObject.parseObject(content);
            if (content.startsWith("{")) {
                return  true;
            }else {
                return  false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是JsonArray
     * @param obj
     * @return
     */
    public static boolean isJsonArray(Object obj){
        String content = obj.toString();
        try {
            JSONArray.parseArray(content);
            if (content.startsWith("[")) {
                return  true;
            }else {
                return  false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static long convertToLong(String value){
        if(value.contains(".")){
            value=value.substring(0,value.indexOf("."));
        }
        return parseLong(value);
    }




}
