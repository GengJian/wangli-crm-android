package com.control.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.control.R;
import com.control.utils.Func;
import com.control.utils.JiuyiBundleKey;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**

 */
@SuppressLint("AppCompatCustomView")
public class JiuyiEditTextField extends EditText {
    private Context mContext;
    private Bitmap mClearButton;
    private Paint mPaint;

    private boolean mClearStatus;

    //按钮显示方式
    private ClearButtonMode mClearButtonMode;
    //初始化输入框右内边距
    private int mInitPaddingRight;
    //按钮的左右内边距，默认为3dp
    private int mButtonPadding = dp2px(3);

    ArrayList<LabelValueBean> clientList=new ArrayList<>();
    ArrayList<LabelValueBean> personList=new ArrayList<>();
    ArrayList<LabelValueBean> businessList=new ArrayList<>();


    public String getOriginText() {
       String showText=this.getText().toString();
       if(!Func.IsStringEmpty(showText)){
           if(clientList!=null){
               for(int i=0;i<clientList.size();i++){
                   if(showText.contains(clientList.get(i).name)){
                       if(clientList.get(i).name.indexOf("$")>=0){
                           String newText="$="+clientList.get(i).value+"="+clientList.get(i).name.substring(1,clientList.get(i).name.length());
                           showText=showText.replace(clientList.get(i).name,newText);
                       }

                   }
               }
           }
           if(personList!=null){
               for(int i=0;i<personList.size();i++){
                   if(showText.contains(personList.get(i).name)){
                       if(personList.get(i).name.indexOf("@")>=0){
                           String newText="@="+personList.get(i).value+"="+personList.get(i).name.substring(1,personList.get(i).name.length());
                           showText=showText.replace(personList.get(i).name,newText);
                       }
                   }
               }
           }
       }

       return  showText;
    }

    private String originText="";





    /**
     * 按钮显示方式
     * NEVER   不显示清空按钮
     * ALWAYS  始终显示清空按钮
     * WHILEEDITING   输入框内容不为空且有获得焦点
     * UNLESSEDITING  输入框内容不为空且没有获得焦点
     * */
    public enum ClearButtonMode {
        NEVER, ALWAYS, WHILEEDITING, UNLESSEDITING
    }


    public JiuyiEditTextField(Context context) {
        super(context);
        init(context, null);
    }

    public JiuyiEditTextField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public JiuyiEditTextField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }





    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.EditTextField);

        switch (typedArray.getInteger(R.styleable.EditTextField_clearButtonMode, 0)) {
            case 1:
                mClearButtonMode = ClearButtonMode.ALWAYS;
                break;
            case 2:
                mClearButtonMode = ClearButtonMode.WHILEEDITING;
                break;
            case 3:
                mClearButtonMode = ClearButtonMode.UNLESSEDITING;
                break;
            default:
                mClearButtonMode = ClearButtonMode.NEVER;
                break;
        }

        int clearButton = typedArray.getResourceId(R.styleable.EditTextField_clearButtonDrawable, R.mipmap.close);
        typedArray.recycle();

        //按钮的图片
        mClearButton = ((BitmapDrawable) getDrawableCompat(clearButton)).getBitmap();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mInitPaddingRight = getPaddingRight();
    }



    /**
     * 按钮状态管理
     * @param canvas onDraw的Canvas
     */
    private void buttonManager(Canvas canvas) {
        switch (mClearButtonMode) {
            case ALWAYS:
                drawBitmap(canvas, getRect(true));
                break;
            case WHILEEDITING:
                drawBitmap(canvas, getRect(hasFocus() && getText().length() > 0));
                break;
            case UNLESSEDITING:
                break;
            default:
                drawBitmap(canvas, getRect(false));
                break;
        }
    }



    /**
     * 设置输入框的内边距
     * @param isShow  是否显示按钮
     */
    private void setPadding(boolean isShow) {
        int paddingRight = mInitPaddingRight + (isShow ? mClearButton.getWidth() + mButtonPadding + mButtonPadding : 0);

        setPadding(getPaddingLeft(), getPaddingTop(), paddingRight, getPaddingBottom());
    }



    /**
     * 取得显示按钮与不显示按钮时的Rect
     * @param isShow  是否显示按钮
     */
    private Rect getRect(boolean isShow) {
        int left, top, right, bottom;

        right   = isShow ? getMeasuredWidth() + getScrollX() - mButtonPadding - mButtonPadding : 0;
        left    = isShow ? right - mClearButton.getWidth() : 0;
        top     = isShow ? (getMeasuredHeight() - mClearButton.getHeight())/2 : 0;
        bottom  = isShow ? top + mClearButton.getHeight() : 0;


        //更新输入框内边距
        setPadding(isShow);


        return new Rect(left, top, right, bottom);
    }



    /**
     * 绘制按钮图片
     * @param canvas onDraw的Canvas
     * @param rect   图片位置
     */
    private void drawBitmap(Canvas canvas, Rect rect) {
        if (rect != null) {
            canvas.drawBitmap(mClearButton, null, rect, mPaint);
        }
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        buttonManager(canvas);

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //判断是否点击到按钮所在的区域
                if (event.getX() - (getMeasuredWidth() - getPaddingRight()) >= 0) {
                    setError(null);
                    this.setText("");
                }
                break;
        }

        return super.onTouchEvent(event);
    }






    /**
     * 获取Drawable
     * @param resourseId  资源ID
     */
    private Drawable getDrawableCompat(int resourseId) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            return getResources().getDrawable(resourseId, mContext.getTheme());
        } else {
            return getResources().getDrawable(resourseId);
        }
    }

    /**
     * 设置按钮左右内边距
     * @param buttonPadding 单位为dp
     */
    public void setButtonPadding(int buttonPadding) {
        this.mButtonPadding = dp2px(buttonPadding);
    }

    /**
     * 设置按钮显示方式
     * @param clearButtonMode 显示方式
     */
    public void setClearButtonMode(ClearButtonMode clearButtonMode) {
        this.mClearButtonMode = clearButtonMode;
    }

    public boolean isShowing() {
        return mClearStatus;
    }

    public int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    public void setText(String text){
        clientList.clear();
        personList.clear();
        super.setText("");
        if(text==null || text.length()==0) {
            return;
        }
        String remainText = text;
        Object[][] output = getUrlFromJDP(text);
        Object[][] output2 = getUrlFromJDP2(text);
        Object[][] output3 = getUrlFromJDP3(text);
        int urlCount = 0,urlCount2 = 0,urlCount3 = 0;
        if((output == null || output.length == 0 || output[0]==null || output[0].length==0)&&
                (output2 == null || output2.length == 0 || output2[0]==null || output2[0].length==0)
                &&
                (output3 == null || output3.length == 0 || output3[0]==null || output3[0].length==0)
                ){
            super.setText(text);return;
        }
        int lastStart = 0;//截取到一部分后截掉部分的长度
        if(output!=null){
            urlCount = output[0].length;
            for(int i=0;i<urlCount;i++){
                String blueText = (String) output[0][i];//带下划线的文字
                final String href = (String) output[1][i];//下划线文字所对应的url连接
                LabelValueBean labelValueBean=new LabelValueBean(blueText,href);
                clientList.add(labelValueBean);
                int start = (int) output[2][i];//<a>标签在源字符串的起始位置
                int end = (int) output[3][i];//<a>标签在源字符串的结束位置
                SpannableString spannableString = new SpannableString(blueText);
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
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

        if(output2!=null){
            urlCount2 = output2[0].length;
            for(int i=0;i<urlCount2;i++){
                String blueText = (String) output2[0][i];//带下划线的文字
                final String href = (String) output2[1][i];//下划线文字所对应的url连接
                LabelValueBean labelValueBean=new LabelValueBean(blueText,href);
                personList.add(labelValueBean);
                int start = (int) output2[2][i];//<a>标签在源字符串的起始位置
                int end = (int) output2[3][i];//<a>标签在源字符串的结束位置
                SpannableString spannableString = new SpannableString(blueText);
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
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
        if(output3!=null){
            urlCount3 = output3[0].length;
            for(int i=0;i<urlCount3;i++){
                String blueText = (String) output3[0][i];//带下划线的文字
                final String href = (String) output3[1][i];//下划线文字所对应的url连接
                LabelValueBean labelValueBean=new LabelValueBean(blueText,href);
                businessList.add(labelValueBean);
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

    public void insertPerson(int where,String text, final String id){
        Editable editable = this.getText();
        LabelValueBean labelValueBean=new LabelValueBean(text,id);
        personList.add(labelValueBean);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Bundle mBundle=new Bundle();
                mBundle.putString(JiuyiBundleKey.PARAM_LINKMANBEANID,id+"");
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
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(clickableSpan,0,text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editable.insert(where, spannableString);

    }
    public void insertCustomer(int where,  String text, final String id){
        Editable editable = this.getText();
        LabelValueBean labelValueBean=new LabelValueBean(text,id);
        clientList.add(labelValueBean);
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ClickableSpan() {
            //在这里定义点击下划线文字的点击事件，不一定非要打开浏览器
            @Override
            public void onClick(View widget) {
                Bundle mBundle=new Bundle();
                long customerids[]=new long[1];
                customerids[0]=Long.parseLong(id);
                mBundle.putLongArray(JiuyiBundleKey.PARAM_CUSTOMERIDS, customerids);
                mBundle.putLong(JiuyiBundleKey.PARAM_CUSTOMERID, Long.parseLong(id));
                Rc.getIns().getBaseCallTopCallBack().changePage(mBundle, Pub.Customer_main,true);

            }
        },0,text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editable.insert(where, spannableString);

    }


    public class LabelValueBean {
        public String getName() {
            return name;
        }

        public LabelValueBean(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        private String name;
        private String value;
    }


}