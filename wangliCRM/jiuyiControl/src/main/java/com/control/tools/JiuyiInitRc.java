package com.control.tools;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.control.utils.Func;
import com.control.utils.Pub;
import com.control.utils.Rc;
import com.control.utils.Res;


/**
 * ****************************************************************
 * 文件名称 : JiuyiInitRc.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/3 15:01
 * 文件描述 : 初始化Rc类
 * 创建原因 : 软件里可以有多处创建Rc
 *****************************************************************
 */
public class JiuyiInitRc {
	Rc record;
	
	
	public JiuyiInitRc(Application application){
		boolean bRcEmpty = Rc.IsRcEmpty();
        //
        record = Rc.getIns();

		//消息推送未读数量处理
		//软件关闭状态收到推送，未读消息数量不会自动增加的问
		int mNoReadMsgCount = (bRcEmpty || Rc.cfg==null) ? -1 : record.mNoReadMsgCount;

		if(bRcEmpty || Rc.cfg==null){
			//
			if(Rc.getApplication() == null && application instanceof Application)
                Rc.SetApplication(application);

			//
			record.initRc();
		}else{
            //防止设置字体和分辨率
            Res.GetDisplayParam();
            record.initParam();
        }
		//消息推送未读数量处理，软件启动，使用最后一次的的数量
		if(mNoReadMsgCount >= 0){
            record.mNoReadMsgCount = mNoReadMsgCount;
		}
		//Meta
		formatCFromByMeta();
        //屏幕尺寸
        Res.GetDisplayParam();
        //HomePage 是否使用固定的首页，不固定则读文件
		Pub.m_nStartHomePage =Rc.cfg.getRootPageType();
	}
	
	/**
	 * 通过meta的值格式化From
	 * @return
	 */
	public String formatCFromByMeta() {
		try {
			ApplicationInfo appInfo = Rc.getApplication().getPackageManager().getApplicationInfo(Rc.getApplication().getPackageName(), PackageManager.GET_META_DATA);
			if(appInfo==null || appInfo.metaData==null){
				return Rc.cfg.getCFrom();
			}
			String meta = appInfo.metaData.getString("UMENG_CHANNEL");
			return Rc.cfg.getCFrom() + (!Func.IsStringEmpty(meta) ? ("." + meta) : "");
		} catch (NameNotFoundException e) {
			return Rc.cfg.getCFrom();
		}
	}
}
