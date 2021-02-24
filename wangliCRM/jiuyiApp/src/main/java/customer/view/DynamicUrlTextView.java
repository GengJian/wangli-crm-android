package customer.view;

/**
 * ****************************************************************
 * 文件名称:DynamicUrlTextView.java
 * 作    者:Created by zhengss
 * 创建时间:2018/11/15 17:08
 * 文件描述:
 * 注意事项:
 * 版权声明:Copyright (C) 2018-2025 上海九翊软件科技有限公司
 * 修改历史:2018/11/15 1.00 初始版本
 * ****************************************************************
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressLint("AppCompatCustomView")
public class DynamicUrlTextView extends TextView{

    public DynamicUrlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setText(String text){
        super.setText("");
        if(text==null || text.length()==0) {
            return;
        }

        String remainText = text;
        Object[][] output = getUrlFromJDP(text);
        Object[][] output2 = getUrlFromJDP2(text);
        Object[][] output3 = getUrlFromJDP3(text);
        Object[][] output4 = getUrlFromJDP4(text);
        int urlCount = 0,urlCount2 = 0,urlCount3 = 0,urlCount4 = 0;
        if((output == null || output.length == 0 || output[0]==null || output[0].length==0)&&
                (output2 == null || output2.length == 0 || output2[0]==null || output2[0].length==0)
                &&
                (output3 == null || output3.length == 0 || output3[0]==null || output3[0].length==0)
                &&
                (output4 == null || output4.length == 0 || output4[0]==null || output4[0].length==0)
        ){
            super.setText(text);return;
        }




        int lastStart = 0;//截取到一部分后截掉部分的长度
        if(output2!=null){
            urlCount2 = output2[0].length;
            for(int i=0;i<urlCount2;i++){
                String blueText = (String) output2[0][i];//带下划线的文字
                final String href = (String) output2[1][i];//下划线文字所对应的url连接
                int start = (int) output2[2][i];//<a>标签在源字符串的起始位置
                int end = (int) output2[3][i];//<a>标签在源字符串的结束位置
                SpannableString spannableString = new SpannableString(blueText);
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        //下面是打开系统默认浏览器的方法
                        Bundle mBundle=new Bundle();
                        mBundle.putString(JiuyiBundleKey.PARAM_LINKMANBEANID,href+"");
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_ContactsInfo,true);
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Res.getColor(null, "jiuyi_blue"));
                        ds.setUnderlineText(false);
                        ds.clearShadowLayer();
                    }
                };
                spannableString.setSpan(clickableSpan,0,blueText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                int subStart = start - lastStart;
                String front = remainText.substring(0,subStart);//截取出一段文字+一段url
//            Log.i("Alex","起始位置"+(end-lastStart));
                remainText = remainText.substring(end-lastStart,remainText.length());//剩下的部分
                lastStart = end;
                if(front.length()>0) {
                    append(front);
                }
                append(spannableString);
            }

        }

        if(output!=null){
            urlCount = output[0].length;
            for(int i=0;i<urlCount;i++){
                String blueText = (String) output[0][i];//带下划线的文字
                final String href = (String) output[1][i];//下划线文字所对应的url连接
                int start = (int) output[2][i];//<a>标签在源字符串的起始位置
                int end = (int) output[3][i];//<a>标签在源字符串的结束位置
                SpannableString spannableString = new SpannableString(blueText);
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        //下面是打开系统默认浏览器的方法
                        Bundle mBundle=new Bundle();
                        long customerids[]=new long[1];
                        customerids[0]=Long.parseLong(href);
                        mBundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerids);
                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Long.parseLong(href));
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_main,true);
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Res.getColor(null, "jiuyi_blue"));
                        ds.setUnderlineText(false);
                        ds.clearShadowLayer();
                    }
                };
                spannableString.setSpan(clickableSpan,0,blueText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                int subStart = start - lastStart;
                String front = remainText.substring(0,subStart);//截取出一段文字+一段url
                Log.i("Alex","起始位置"+(end-lastStart));
                remainText = remainText.substring(end-lastStart,remainText.length());//剩下的部分
                lastStart = end;
                Log.i("Alex","front是"+front);
                Log.i("Alex","spann是"+spannableString);
                Log.i("Alex","remain是"+remainText);
                if(front.length()>0) {
                    append(front);
                }
                append(spannableString);
            }

        }

        if(output3!=null){
            urlCount3 = output3[0].length;
            for(int i=0;i<urlCount3;i++){
                String blueText = (String) output3[0][i];//带下划线的文字
                final String href = (String) output3[1][i];//下划线文字所对应的url连接
                int start = (int) output3[2][i];//<a>标签在源字符串的起始位置
                int end = (int) output3[3][i];//<a>标签在源字符串的结束位置
                SpannableString spannableString = new SpannableString(blueText);
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        int pagetype=0,pos=-1,posEnd=-1;
                        long id=0;
                        if(href.toUpperCase().contains("ACTION:")){
                            pos=href.indexOf(":");
                            posEnd=href.indexOf("-");
                            if(pos>=0 && posEnd>=0){
                                pagetype= Integer.parseInt(href.substring(pos+1,posEnd));
                                id=Long.parseLong(href.substring(posEnd+1,href.length()));
                                Bundle mBundle=new Bundle();
                                mBundle.putString(JiuyiBundleKey.PARAM_LINKMANBEANID,id+"");
                                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, pagetype,true);
                            }
                        }
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Res.getColor(null, "jiuyi_blue"));
                        ds.setUnderlineText(false);
                        ds.clearShadowLayer();
                    }
                };
                spannableString.setSpan(clickableSpan,0,blueText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                int subStart = start - lastStart;
                String front = remainText.substring(0,subStart);//截取出一段文字+一段url
                remainText = remainText.substring(end-lastStart,remainText.length());//剩下的部分
                lastStart = end;
                if(front.length()>0) {
                    append(front);
                }
                append(spannableString);
            }

        }

        if(output4!=null){
            urlCount4 = output4[0].length;
            for(int i=0;i<urlCount4;i++){
                String blueText = (String) output4[0][i];//带下划线的文字
                final String href = (String) output4[1][i];//下划线文字所对应的url连接
                int start = (int) output4[2][i];//<a>标签在源字符串的起始位置
                int end = (int) output4[3][i];//<a>标签在源字符串的结束位置
                SpannableString spannableString = new SpannableString(blueText);
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        int pagetype=0,pos=-1,posEnd=-1;
                        long id=0;
                        if(href.toUpperCase().contains("ACTION:")){
                            pos=href.indexOf(":");
                            posEnd=href.indexOf("-");
                            if(pos>=0 && posEnd>=0){
                                pagetype= Integer.parseInt(href.substring(pos+1,posEnd));
                                String url="";
                                url=href.substring(posEnd+1,href.length());
                                if(!Func.IsStringEmpty(url)){
                                    url+="&token="+ Rc.id_tokenparam;
                                    Bundle mBundle=new Bundle();
                                    mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                                    String title="";
                                    if(blueText.length()>10){
                                        title=blueText.substring(0,10);
                                        title+="...";
                                    }else{
                                        title=blueText;
                                    }
                                    mBundle.putString(JiuyiBundleKey.PARAM_TITLE,title);
                                    Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, pagetype,true);
                                }

                            }
                        }
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(Res.getColor(null, "jiuyi_blue"));
                        ds.setUnderlineText(false);
                        ds.clearShadowLayer();
                    }
                };
                spannableString.setSpan(clickableSpan,0,blueText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                int subStart = start - lastStart;
                String front = remainText.substring(0,subStart);//截取出一段文字+一段url
                remainText = remainText.substring(end-lastStart,remainText.length());//剩下的部分
                lastStart = end;
                if(front.length()>0) {
                    append(front);
                }
                append(spannableString);
            }

        }


        if(remainText!=null && remainText.length()>0) {
            append(remainText);
        }
        setMovementMethod(LinkMovementMethod.getInstance());//响应点击事件
    }

    public static Object[][]  getUrlFromJDP(String source){
        ArrayList<String> hosts = new ArrayList<>(4);
        ArrayList<String> urls = new ArrayList<>(4);
        ArrayList<Integer> starts = new ArrayList<>(4);
        ArrayList<Integer> ends = new ArrayList<>(4);

        Pattern pattern = Pattern.compile("\\$=(.*?)\\$");//首先将a标签分离出来
        Matcher matcher = pattern.matcher(source);
        int i=0;
        while(matcher.find()){
            String raw = matcher.group(0);
            Pattern url_pattern = Pattern.compile("=(.*?)=");//将href分离出来
            Matcher url_matcher = url_pattern.matcher(raw);
            try {
                if(url_matcher.find()){
                    String url = url_matcher.group(1);
                    Log.i("Alex","真实url是"+url);//括号里面的
                    urls.add(i,url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String host = null;//将要显示的文字分离出来
            try {
                host = matcher.group(0);
                int lastPos=-1;
                lastPos=host.lastIndexOf("=");
                if(lastPos>0){
                    host="$"+host.substring(lastPos+1,host.length());
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Log.i("Alex","蓝色文字是"+host);//括号里面的
            hosts.add(i,host);
            starts.add(i,matcher.start());
            ends.add(i,matcher.end());
            Log.i("Alex","字符串起始下标是"+matcher.start()+"结尾下标是"+matcher.end());//匹配出的字符串在源字符串的位置
            i++;
        }
        if(hosts.size()==0){
            Log.i("Alex","没有发现url");
            return null;
        }
        Object[][] outputs = new Object[4][hosts.size()];//第一个下标是内容的分类，第二个下标是url的序号
        outputs[0] = hosts.toArray(new String[hosts.size()]);//下标0是蓝色的文字
        outputs[1] = urls.toArray(new String[urls.size()]);//下标1是url
        outputs[2] = starts.toArray(new Integer[starts.size()]);//下标2是<a>标签起始位置
        outputs[3] = ends.toArray(new Integer[ends.size()]);//下标3是<a>标签结束位置
        return outputs;
    }

    public static Object[][]  getUrlFromJDP2(String source){
        ArrayList<String> hosts = new ArrayList<>(4);
        ArrayList<String> urls = new ArrayList<>(4);
        ArrayList<Integer> starts = new ArrayList<>(4);
        ArrayList<Integer> ends = new ArrayList<>(4);

        Pattern pattern = Pattern.compile("\\@=(.*?)\\@");//首先将a标签分离出来
        Matcher matcher = pattern.matcher(source);
        int i=0;
        while(matcher.find()){
            String raw = matcher.group(0);
            Pattern url_pattern = Pattern.compile("=(.*?)=");//将href分离出来
            Matcher url_matcher = url_pattern.matcher(raw);
            try {
                if(url_matcher.find()){
                    String url = url_matcher.group(1);
                    Log.i("Alex","真实url是"+url);//括号里面的
                    urls.add(i,url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String host = null;//将要显示的文字分离出来
            try {
                host = matcher.group(0);
                int lastPos=-1;
                lastPos=host.lastIndexOf("=");
                if(lastPos>0){
                    host="@"+host.substring(lastPos+1,host.length());
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Log.i("Alex","蓝色文字是"+host);//括号里面的
            hosts.add(i,host);
            starts.add(i,matcher.start());
            ends.add(i,matcher.end());
            Log.i("Alex","字符串起始下标是"+matcher.start()+"结尾下标是"+matcher.end());//匹配出的字符串在源字符串的位置
            i++;
        }
        if(hosts.size()==0){
            Log.i("Alex","没有发现url");
            return null;
        }
        Object[][] outputs = new Object[4][hosts.size()];//第一个下标是内容的分类，第二个下标是url的序号
        outputs[0] = hosts.toArray(new String[hosts.size()]);//下标0是蓝色的文字
        outputs[1] = urls.toArray(new String[urls.size()]);//下标1是url
        outputs[2] = starts.toArray(new Integer[starts.size()]);//下标2是<a>标签起始位置
        outputs[3] = ends.toArray(new Integer[ends.size()]);//下标3是<a>标签结束位置
        return outputs;
    }

    public static Object[][]  getUrlFromJDP3(String source){
        ArrayList<String> hosts = new ArrayList<>(4);
        ArrayList<String> urls = new ArrayList<>(4);
        ArrayList<Integer> starts = new ArrayList<>(4);
        ArrayList<Integer> ends = new ArrayList<>(4);

        Pattern pattern = Pattern.compile("\\#=(.*?)\\#");//首先将a标签分离出来
        Matcher matcher = pattern.matcher(source);
        int i=0;
        while(matcher.find()){
            String raw = matcher.group(0);
            Pattern url_pattern = Pattern.compile("=(.*?)=");//将href分离出来
            Matcher url_matcher = url_pattern.matcher(raw);
            try {
                if(url_matcher.find()){
                    String url = url_matcher.group(1);
                    Log.i("Alex","真实url是"+url);//括号里面的
                    urls.add(i,url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String host = null;//将要显示的文字分离出来
            try {
                host = matcher.group(0);
                int lastPos=-1;
                lastPos=host.lastIndexOf("=");
                if(lastPos>0){
                    host="#"+host.substring(lastPos+1,host.length());
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Log.i("Alex","蓝色文字是"+host);//括号里面的
            hosts.add(i,host);
            starts.add(i,matcher.start());
            ends.add(i,matcher.end());
            Log.i("Alex","字符串起始下标是"+matcher.start()+"结尾下标是"+matcher.end());//匹配出的字符串在源字符串的位置
            i++;
        }
        if(hosts.size()==0){
            Log.i("Alex","没有发现url");
            return null;
        }
        Object[][] outputs = new Object[4][hosts.size()];//第一个下标是内容的分类，第二个下标是url的序号
        outputs[0] = hosts.toArray(new String[hosts.size()]);//下标0是蓝色的文字
        outputs[1] = urls.toArray(new String[urls.size()]);//下标1是url
        outputs[2] = starts.toArray(new Integer[starts.size()]);//下标2是<a>标签起始位置
        outputs[3] = ends.toArray(new Integer[ends.size()]);//下标3是<a>标签结束位置
        return outputs;
    }

    public static Object[][]  getUrlFromJDP4(String source){
        ArrayList<String> hosts = new ArrayList<>(4);
        ArrayList<String> urls = new ArrayList<>(4);
        ArrayList<Integer> starts = new ArrayList<>(4);
        ArrayList<Integer> ends = new ArrayList<>(4);

        Pattern pattern = Pattern.compile("\\#\\{(.*?)\\#");//首先将a标签分离出来
        Matcher matcher = pattern.matcher(source);
        int i=0;
        while(matcher.find()){
            String raw = matcher.group(0);
            Pattern url_pattern = Pattern.compile("\\{(.*?)\\}");//将href分离出来
            Matcher url_matcher = url_pattern.matcher(raw);
            try {
                if(url_matcher.find()){
                    String url = url_matcher.group(1);
                    url=url.replace("*","#");
                    Log.i("Alex","真实url是"+url);//括号里面的
                    urls.add(i,url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String host = null;//将要显示的文字分离出来
            try {
                host = matcher.group(0);
                int lastPos=-1;
                lastPos=host.lastIndexOf("}");
                if(lastPos>0){
                    host="#"+host.substring(lastPos+1,host.length());
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Log.i("Alex","蓝色文字是"+host);//括号里面的
            hosts.add(i,host);
            starts.add(i,matcher.start());
            ends.add(i,matcher.end());
            Log.i("Alex","字符串起始下标是"+matcher.start()+"结尾下标是"+matcher.end());//匹配出的字符串在源字符串的位置
            i++;
        }
        if(hosts.size()==0){
            Log.i("Alex","没有发现url");
            return null;
        }
        Object[][] outputs = new Object[4][hosts.size()];//第一个下标是内容的分类，第二个下标是url的序号
        outputs[0] = hosts.toArray(new String[hosts.size()]);//下标0是蓝色的文字
        outputs[1] = urls.toArray(new String[urls.size()]);//下标1是url
        outputs[2] = starts.toArray(new Integer[starts.size()]);//下标2是<a>标签起始位置
        outputs[3] = ends.toArray(new Integer[ends.size()]);//下标3是<a>标签结束位置
        return outputs;
    }



}
