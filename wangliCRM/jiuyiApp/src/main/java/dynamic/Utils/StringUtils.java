package dynamic.Utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wanglicrm.android.R;
import com.bobomee.android.mentions.edit.MentionEditText;
import com.bobomee.android.mentions.model.FormatRange;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;
import com.jiuyi.app.JiuyiMainApplication;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import customer.entity.User;

/**
 * 正文中表情，@，话题显示不同颜色以及添加点击事件处理
 * SpannableString为扩展性文字，可以将一些图片，特殊文字样式等拓展内容附着到我们的文本上
 */
public class StringUtils {
    public static SpannableString getDynamicContent(final Context context, TextView tv, String source) {
        //正则表达式匹配
        String regexAt = "\\@=(.*?)\\@";
        String regexClient = "\\$=(.*?)\\$";
        String regexAction = "\\#=(.*?)\\#";
        String regexUrl = "#\\{(.*?)#";

        String regex="";
        Pattern pattern;
        Matcher matcher;
        //正则规则 涉及到group()处理

        SpannableString spannableString = new SpannableString(source);

        regex=regexAt;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(spannableString);

        //给字符串添加点击事件需要这样处理
        if (matcher.find()) {
            //设置该句使文本的超连接起作用
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            matcher.reset();
        }

        while (matcher.find()) {
            //获取匹配的具体字符
            final String atStr = matcher.group(1);
            //非空判断 @部分
            if (atStr != null) {
                int start = matcher.start(1);
                int lastPos=-1;
                lastPos=atStr.lastIndexOf("=");
                if(lastPos<=0){
                    return spannableString;
                }
                final String beanID=atStr.substring(0, lastPos);
                MyClickableSpan clickableSpan = new MyClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        Bundle mBundle=new Bundle();
                        mBundle.putString(JiuyiBundleKey.PARAM_LINKMANBEANID, beanID);
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Normal_ContactsInfo,true);
                    }
                };
                spannableString.setSpan(clickableSpan, start-2, start + atStr.length()+1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ImageSpan imageSpan = new ImageSpan(JiuyiMainApplication.getIns(), R.drawable.replacetext);
                spannableString.setSpan(imageSpan, start-1, start+beanID.length()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


            }
        }
        regex=regexClient;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(spannableString);

        //给字符串添加点击事件需要这样处理
        if (matcher.find()) {
            //设置该句使文本的超连接起作用
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            matcher.reset();
        }

        while (matcher.find()) {
            //获取匹配的具体字符
            final String atStr = matcher.group(1);
            //非空判断 @部分
            if (atStr != null) {
                int start = matcher.start(1);
                int lastPos=-1;
                lastPos=atStr.lastIndexOf("=");
                if(lastPos<=0){
                    return spannableString;
                }
                final String beanID=atStr.substring(0, lastPos);
                MyClickableSpan clickableSpan = new MyClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        Bundle mBundle=new Bundle();
                        long customerids[]=new long[1];
                        customerids[0]=Long.parseLong(beanID);
                        mBundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerids);
                        mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Long.parseLong(beanID));
                        Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_main,true);

                    }
                };
                spannableString.setSpan(clickableSpan, start-2, start + atStr.length()+1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ImageSpan imageSpan = new ImageSpan(JiuyiMainApplication.getIns(), R.drawable.replacetext);
                spannableString.setSpan(imageSpan, start-1, start+beanID.length()+1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
      /*  regex=regexAction;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(spannableString);

        //给字符串添加点击事件需要这样处理
        if (matcher.find()) {
            //设置该句使文本的超连接起作用
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            matcher.reset();
        }

        while (matcher.find()) {
            //获取匹配的具体字符
            final String atStr = matcher.group(1);
            //非空判断 @部分
            if (atStr != null) {
                int start = matcher.start(1);
                int startPos=-1,lastPos=-1;
                startPos=atStr.indexOf("http");
                lastPos=atStr.lastIndexOf("}");
                if(lastPos<=0){
                    return spannableString;
                }
                final String beanID=atStr.substring(startPos, lastPos);
                final String name=atStr.substring(lastPos+1, atStr.length());
                MyClickableSpan clickableSpan = new MyClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        String url=beanID;
                        if(!Func.IsStringEmpty(url)){
                            url=url.replace("*","#");
                            url+="&token="+ Rc.id_tokenparam;
                            Bundle mBundle=new Bundle();
                            mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                            String title="";
                            if(name.length()>10){
                                title=name.substring(0,10);
                                title+="...";
                            }else{
                                title=name;
                            }
                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE,title);
                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, 10061,true);
                        }
                    }
                };
                spannableString.setSpan(clickableSpan, start-2, start + atStr.length()+1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ImageSpan imageSpan = new ImageSpan(JiuyiMainApplication.getIns(), R.drawable.replacetext);
                spannableString.setSpan(imageSpan, start-1, start+atStr.length()-name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }*/


        regex=regexUrl;
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(spannableString);

        //给字符串添加点击事件需要这样处理
        if (matcher.find()) {
            //设置该句使文本的超连接起作用
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            matcher.reset();
        }

        while (matcher.find()) {
            //获取匹配的具体字符
            final String atStr = matcher.group(1);
            //非空判断 @部分
            if (atStr != null) {
                int start = matcher.start(1);
                int startPos=-1,lastPos=-1;
                startPos=atStr.indexOf("http");
                lastPos=atStr.lastIndexOf("}");
                if(lastPos<=0){
                    return spannableString;
                }
                final String beanID=atStr.substring(startPos, lastPos);
                final String name=atStr.substring(lastPos+1, atStr.length());
                MyClickableSpan clickableSpan = new MyClickableSpan(context) {
                    @Override
                    public void onClick(View widget) {
                        String url=beanID;
                        if(!Func.IsStringEmpty(url)){
                            url=url.replace("*","#");
                            url+="&token="+ Rc.id_tokenparam;
                            Bundle mBundle=new Bundle();
                            mBundle.putString(JiuyiBundleKey.PARAM_HTTPServer, url);
                            String title="";
                            if(name.length()>10){
                                title=name.substring(0,10);
                                title+="...";
                            }else{
                                title=name;
                            }
                            mBundle.putString(JiuyiBundleKey.PARAM_TITLE,title);
                            Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, 10061,true);
                        }
                    }
                };
                spannableString.setSpan(clickableSpan, start-2, start + atStr.length()+1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ImageSpan imageSpan = new ImageSpan(JiuyiMainApplication.getIns(), R.drawable.replacetext);
                spannableString.setSpan(imageSpan, start-1, start+atStr.length()-name.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }

    public static void insertSelectedContent(final Context context, MentionEditText tv, String source) {
        Editable editable = tv.getText();
        String remainText = source;
        Object[][] output = getUrlFromJDP(source);

        int urlCount = 0;
        if((output == null || output.length == 0 || output[0]==null || output[0].length==0)){
            editable.append(remainText);
            return;
        }

        int lastStart = 0;//截取到一部分后截掉部分的长度
        if(output!=null){
            urlCount = output[0].length;
            for(int i=0;i<urlCount;i++){
                String blueText = (String) output[0][i];//带下划线的文字
                final String href = (String) output[1][i];//下划线文字所对应的url连接
                int start = (int) output[2][i];//<a>标签在源字符串的起始位置
                int end = (int) output[3][i];//<a>标签在源字符串的结束位置

                User user = new User(href+"",blueText.replace("@",""));
                CharSequence charSequence = user.charSequence();
                 editable = tv.getText();
                int start2 = start;
                if(i>0){
                    for(int j=0;j<i;j++){
                        start2-= output[1][j].toString().length();
                        start2-=2;
                    }
                }
                int end2 = start2 + charSequence.length();
                FormatRange.FormatData format = user.formatData();
                FormatRange range = new FormatRange(start2, end2);
                range.setConvert(format);
                range.setRangeCharSequence(charSequence);
                tv.getRangeManager().add(range);
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
                remainText = remainText.substring(end-lastStart,remainText.length());//剩下的部分
                lastStart = end;
                if(front.length()>0) {
                    editable.append(front);
                }
                editable.append(spannableString);
            }

        }
        if(remainText!=null && remainText.length()>0) {
            editable.append(remainText);
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());//响应点击事件
    }



    public static Object[][]  getUrlFromJDP(String source){
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



    //ClickableSpan默认有自定的颜色跟下划线，我们只需要一个蓝色字体就行，所以需要重写它
    static class MyClickableSpan extends ClickableSpan {

        private Context context;

        public MyClickableSpan(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View widget) {
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(context.getResources().getColor(R.color.jiuyi_blue));
            ds.setUnderlineText(false);
        }
    }
}
