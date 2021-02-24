
package com.jiuyi.activity.passwordlock.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.control.shared.JiuyiPasswordLockShared;
import com.control.utils.Res;
import com.control.widget.relativeLayout.JiuyiRelativeLayout;
import com.jiuyi.app.JiuyiActivityBase;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************
 * 文件名称 : JiuyiPasswordLockTimeSetActivity
 * 作       者 : zhengss
 * 创建时间:2018/3/26 14:43
 * 文件描述 : 密码锁定时间修改
 *****************************************************************
 */
public class JiuyiPasswordLockTimeSetActivity extends JiuyiActivityBase {

	/** 存放数据列表 */
	private List<LockTimeItem> mList = new ArrayList<LockTimeItem>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	public void initListData() {
		int[] arrayValue= JiuyiPasswordLockShared.getIns().getPasswordLockTimeList();
		if(arrayValue==null || arrayValue.length<1){
			mList = new ArrayList<LockTimeItem>();
		}else{
			mList = new ArrayList<LockTimeItem>();
			LockTimeItem pItem;
			for (int i=0;i<arrayValue.length;i++){
				pItem=new LockTimeItem();
				if(arrayValue[i]==0){
					pItem.setTimeValue("立即");
				}else{
					pItem.setTimeValue(arrayValue[i]+"分钟后");
				}
				if(i== JiuyiPasswordLockShared.getIns().getPasswordLockTimeIndex()){
					pItem.setIsSelected(true);
				}else{
					pItem.setIsSelected(false);
				}
				mList.add(pItem);
			}
		}
	}

	@Override
	public void onInit() {
		mBodyLayout = (JiuyiRelativeLayout) LayoutInflater.from(this).inflate(Res.getLayoutID(this, "jiuyi_v23_activity_passwordlocksettime_layout"), null);
		mBodyLayout.findTitleToolBars(this, this);
		setContentView(mBodyLayout);

		initListData();

		final BaseAdapter simpAdapter = new BaseAdapter() {
			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				convertView = (LinearLayout) LayoutInflater.from(mBodyLayout.getContext()).inflate(Res.getLayoutID(mBodyLayout.getContext(), "jiuyi_v23_passwordlock_setlocktime_listitem"), null);

				TextView acctext = (TextView) convertView.findViewById(Res.getViewID(mBodyLayout.getContext(), "tzt_trendmenu_item"));
				acctext.setText(mList.get(position).getTimeValue());

				ImageView pImageView=(ImageView) convertView.findViewById(Res.getViewID(mBodyLayout.getContext(), "tzt_trendmenu_image"));
				if(mList.get(position).getIsSelected()){
					pImageView.setVisibility(View.VISIBLE);
					acctext.setTextColor(Res.getColor(getApplicationContext(), "tzt_v23_rise_color"));
				}else{
					pImageView.setVisibility(View.INVISIBLE);
					acctext.setTextColor(Res.getColor(getApplicationContext(), "jiuyi_info_color"));
				}

				convertView.setMinimumHeight(Res.Dip2Pix(45));
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return mList.get(position);
			}

			@Override
			public int getCount() {
				return mList.size();
			}
		};
		ListView listview = (ListView) mBodyLayout.findViewById(Res.getViewID(this, "tzt_trendmenu_listView"));
		listview.setAdapter(simpAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				for (int i=0;i<mList.size();i++){
					if(i==arg2){
						mList.get(i).setIsSelected(true);
					}else{
						mList.get(i).setIsSelected(false);
					}
				}
				JiuyiPasswordLockShared.getIns().setPasswordLockTimeIndex(arg2);

				simpAdapter.notifyDataSetChanged();
			}
		});
		simpAdapter.notifyDataSetChanged();

		// 初始化之后才能设置标题，否则空异常
		setTitle();
	}


	public void setTitle() {
		setTitle("选择锁定时间");
	}

	public class LockTimeItem {
		public String mTimeValue;
		public boolean bSelected;//是否选中

		public String getTimeValue(){
			return mTimeValue;
		}
		public void setTimeValue(String sValue){
			mTimeValue = sValue;
		}

		public boolean getIsSelected(){
			return bSelected;
		}
		public void setIsSelected(boolean bValue){
			bSelected = bValue;
		}

	}

}
