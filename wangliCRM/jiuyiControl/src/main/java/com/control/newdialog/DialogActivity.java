package com.control.newdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.control.callback.DialogCallBack;
import com.control.utils.Rc;
import com.control.utils.Res;

/**
 * ****************************************************************
 * 文件名称:DialogActivity.java
 * 作    者:Created by zhengss
 * 创建时间:2018/4/2 17:01
 * 文件描述:
 * ****************************************************************
 */

public class DialogActivity extends FragmentActivity {

        public static  final String TITLE ="title";
        public static  final String CONTENT ="content";
        public static  final String LEFTTEXT ="leftText";
        public static  final String RIGHTTEXT ="rightText";
        public static  final String LEFTACTION ="leftAction";
        public static  final String RIGHTACTION ="rightAction";
        public static  final String CANVASINTERFACE ="CanvasInterface";
        public static  final String CALLBACK = "callback";

        public static final String BUNDLE = "bundle";

        public static DialogCallBack mCallBack;

        AlertDialog dialog = null;
        @Override
        protected void onCreate(Bundle arg0) {
            super.onCreate(arg0);
            onInit();
        }



        public static void startDialog(Activity activity, DialogStuct ds, DialogCallBack callBack){
            mCallBack = callBack;
            startDialog(activity, ds);
        }
        public static void startDialog(Activity activity,DialogStuct ds){
            if(activity == null)
                return;

            Intent intent = new Intent(activity,DialogActivity.class);
            intent.putExtra(BUNDLE, ds);
            activity.startActivity(intent);
        }
        public void onInit() {
            final DialogStuct sd = (DialogStuct) getIntent().getSerializableExtra(BUNDLE);

            View view = View.inflate(Rc.getApplication(), Res.getLayoutID(Rc.getApplication(), "dialog_putforward"), null);
            //使用默认的dialog标题文字
            TextView tvDialogTitle = (TextView) view.findViewById(Res.getViewID(Rc.getApplication(), "tv_dialog_title"));
            tvDialogTitle.setText(Res.getString(null, "tztdefdialogtitle"));

            TextView tvContent = (TextView) view.findViewById(Res.getViewID(Rc.getApplication(), "tv_content"));

            tvContent.setText(sd.content);
            TextView tvLeft = (TextView) view.findViewById(Res.getViewID((Rc.getApplication()), "tv_left"));
            tvLeft.setText(sd.leftText);
            tvLeft.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(sd.leftAction!=0){

                        if(mCallBack!=null){
                            mCallBack.callBack(sd.leftAction, 0);

                        }
                    }
                    finish();
                }
            });

            TextView tvRight = (TextView) view.findViewById(Res.getViewID(Rc.getApplication(), "tv_right"));
            tvRight.setText(sd.rightText);
            tvRight.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(sd.rightAction!=0){

                        if(mCallBack!=null){
                            mCallBack.callBack(sd.rightAction, 1);

                        }
                    }
                    finish();
                }
            });
            setContentView(view);

        }

}
