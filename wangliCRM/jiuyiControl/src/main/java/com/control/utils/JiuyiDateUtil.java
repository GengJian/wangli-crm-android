package com.control.utils;

import android.text.format.DateUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * ****************************************************************
 * 文件名称:JiuyiDateUtil.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述:时间格式转换
 * 注意事项:
 * ****************************************************************
 */

public class JiuyiDateUtil {

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime2(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime3(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String getBeforeTime(int day){
        Calendar  calendar =Calendar. getInstance();
        calendar.add( Calendar. DATE, day); //向前走一天
        Date date= calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime4(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前月份
     * @return
     */
    public static String getNowMonth(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    /**
     * 获取当前月份
     * @return
     */
    public static String getNextMonth(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +1);
        String monthAgo = simpleDateFormat.format(calendar.getTime());
        return monthAgo;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    /**
     * 获取时间戳
     *
     * @return 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }
    /**
     * 时间转换为时间戳
     * @param time:需要转换的时间
     * @return
     */
    public static String datetimeToStamp(String time)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        return String.valueOf(ts);
    }

    /**
     * 时间转换为时间戳
     * @param time:需要转换的时间
     * @return
     */
    public static String dateToStamp(String time)  {

        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
//        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;
    }

    /**
     * 时间戳转换成字符窜
     * @param milSecond
     * @param pattern
     * @return
     */
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public  static String getDateToString(Date  date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    /**
     * 将字符串转为时间戳
     * @param dateString
     * @param pattern
     * @return
     */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }



    /**
     * 时间戳转换为字符串
     * @param time:时间戳
     * @return
     */
    public static String times(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }
    /**
     *获取距现在某一小时的时刻
     * @param hour hour=-1为上一个小时，hour=1为下一个小时
     * @return
     */
    public static String getLongTime(int hour){
        Calendar c = Calendar.getInstance(); // 当时的日期和时间
        int h; // 需要更改的小时
        h = c.get(Calendar.HOUR_OF_DAY) - hour;
        c.set(Calendar.HOUR_OF_DAY, h);
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Log.v("time",df.format(c.getTime()));
        return df.format(c.getTime());
    }
    /**
     * 比较时间大小
     * @param str1：要比较的时间
     * @param str2：要比较的时间
     * @return
     */
    public static boolean isDateOneBigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }
    /**
     * 比较时间大小
     * @param str1：要比较的时间
     * @param str2：要比较的时间
     * @return
     */
    public static boolean isDateOneBigger2(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }
    /**
     * 当地时间 ---> UTC时间
     * @return
     */
    public static String Local2UTC(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("gmt"));
        String gmtTime = sdf.format(new Date());
        return gmtTime;
    }

    /**
     * UTC时间 ---> 当地时间
     * @param utcTime   UTC时间
     * @return
     */
    public static String utc2Local(String utcTime) {
        SimpleDateFormat utcFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//UTC时间格式
        utcFormater.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gpsUTCDate = null;
        try {
            gpsUTCDate = utcFormater.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//当地时间格式
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(gpsUTCDate.getTime());
        return localTime;
    }

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public String longToString(long currentTime, String formatType)
            throws java.text.ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws java.text.ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public Date longToDate(long currentTime, String formatType)
            throws java.text.ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public  long  stringToLong(String strTime, String formatType)
            throws java.text.ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public long dateToLong(Date date) {
        return date.getTime();
    }

    public String numToDate(long number, String formatType) {
        Date date = new Date(number);
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(date);
    }

    public static boolean TimeCompare2(String nowDate, String noalterDate){
        //格式化时间
        SimpleDateFormat CurrentTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            Date beginTime=CurrentTime.parse(nowDate.toString());
            Date endTime=CurrentTime.parse(noalterDate.toString());
            //判断是否大于三天
            if(beginTime.getTime() - endTime.getTime()>0) {
                return true;
            }else{
                return false;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }
    public static boolean DateCompare(String nowDate, String noalterDate){
        //格式化时间
        SimpleDateFormat CurrentTime= new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date beginTime=CurrentTime.parse(nowDate.toString());
            Date endTime=CurrentTime.parse(noalterDate.toString());
            //判断是否大于
            if(beginTime.getTime() - endTime.getTime()>0) {
                return true;
            }else{
                return false;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    public static boolean TimeCompare(String nowDate, String noalterDate){
        //格式化时间
        SimpleDateFormat CurrentTime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {

            Date beginTime=CurrentTime.parse(nowDate.toString());
            Date endTime=CurrentTime.parse(noalterDate.toString());
            //判断是否大于三天
            if(((beginTime.getTime() - endTime.getTime())/(24*60*60*1000))>=3) {
                return true;
            }else{
                return false;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 处理创建时间
     *
     * @param
     * @return String 创建时间字符串
     */
    public static String handleCreateDate(String createdDateStr) {
        if(Func.IsStringEmpty(createdDateStr)){
            return "";
        }
        try {
            Date createdDate=stringToDate(createdDateStr,"yyyy-mm-dd hh:mm:ss");

            Calendar cal = Calendar.getInstance();
            // 当前年份
            int currYear = cal.get(Calendar.YEAR);
            long currTimeInMillis = cal.getTimeInMillis();
            // 创建日期
            cal.setTime(createdDate);
            int createYear = cal.get(Calendar.YEAR);
            long createTimeInMillis = cal.getTimeInMillis();
            System.out.println(currTimeInMillis + "  " + createTimeInMillis);
            long minSencod = (currTimeInMillis - createTimeInMillis);

            Date currentDate = stringToDate(getDateToString(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
            Date createdDateTemp = stringToDate(getDateToString(createdDate, "yyyy-MM-dd"), "yyyy-MM-dd");

            Integer integer = subtractDays(currentDate, createdDateTemp, 24 * 60 * 60 * 1000);
            if (integer >= 1) {
                if (integer > 5) {
                    if (currYear > createYear) {
                        return getStrOfDate(createdDate, "yyyy年MM月dd日",true);
                    } else {
                        String pattern = "MM月dd日";
                        return getStrOfDate(createdDate, pattern,true);
                    }
                } else {
                    return integer + "天前";
                }
            } else {
                int hour2 = cal.get(Calendar.HOUR_OF_DAY);
                String pattern = "HH:mm";
                if (hour2 < 10) {
                    pattern = pattern.replace("HH", "H");
                }
                return getStrOfDate(createdDate, pattern,true);
            }



        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }




    }

    public  static Integer subtractDays(Date endDate, Date beginDate, Integer numerator) {
        long entTime = endDate.getTime();
        long beginTime = beginDate.getTime();
        long time = entTime - beginTime;
        return Integer.valueOf((time / numerator) + "");
    }

    public static String getStrOfDate(Date date, String pattern, boolean excludeZero) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (excludeZero) {
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            if (month < 9) {
                pattern = pattern.replace("MM", "M");
            }
            if (day < 10) {
                pattern = pattern.replace("dd", "d");
            }
        }
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    public static long getTimeExpend(String startTime, String endTime){
        //传入字串类型
        long longStart = getTimeMillis(startTime); //获取开始时间毫秒数
        long longEnd = getTimeMillis(endTime);  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差
        long days = longExpend / (1000 * 60 * 60 * 24);
        long hours = (longExpend-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
        long minutes = (longExpend-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
        long longMinutes=days*24*60+hours*60+minutes;
        return longMinutes;
    }
    public static long getTimeMillis(String strTime) {
        long returnMillis = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnMillis;
    }
}
