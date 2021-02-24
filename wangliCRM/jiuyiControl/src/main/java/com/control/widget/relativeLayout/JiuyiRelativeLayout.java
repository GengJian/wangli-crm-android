package com.control.widget.relativeLayout;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.control.Interface.JiuyiICanvasInterface;
import com.control.callback.AjaxWebClientUrlLisCallBack;
import com.control.shared.JiuyiWindowFlagParamShared;
import com.control.utils.AjaxWebClientUrlLis;
import com.control.utils.DialogID;
import com.control.utils.Res;
import com.control.widget.dialog.JiuyiDialogBase;
import com.control.widget.keyboard.JiuyiKeyBoardView;
import com.control.widget.JiuyiTitleBarBase;

/**
 * ****************************************************************
 * 文件名称 : jiuyiRelativeLayout.java
 * 作 者 :   zhengss
 * 创建时间:2018/4/9 15:01
 * 文件描述 : Activit里的RelativeLayout
 * 主要功能 : 1、实现标题栏、工具栏、自定义键盘、进度条、Ajax的回调的同意哦处理
 *****************************************************************
 */
public class JiuyiRelativeLayout extends RelativeLayout {
	private JiuyiICanvasInterface mCanvasInterface;// 主工作区

	private View mMainView;//
    private JiuyiKeyBoardView m_pKeyBoard;//
    
	private RelativeLayout m_vProgressLayout;// 滚动条
	private ProgressBar m_vProgressBar;// 滚动条
    private JiuyiTitleBarBase m_vTitleBarBase;// 标题栏


	private AjaxWebClientUrlLis pAjaxWebClientUrlLis;// Ajax的处理的回调

	protected Context context;
	private Activity pActivity;

	public JiuyiRelativeLayout(Context context) {
		super(context);
		this.context = context;
	}

	public JiuyiRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public JiuyiRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
	}

	/**
	 * 切换皮肤
	 */
	public void changeSkinType(){

	}

	public void findTitleToolBars(Activity activity, JiuyiICanvasInterface canvasInterface) {
		findTitleToolBars();
		pActivity = activity;
        this.mCanvasInterface = canvasInterface;
    }

    private void findTitleToolBars() {
		/**
		 * 刚进入activity的时候，如果布局组件有edittext的话，往往edittext会获取焦点，自动弹出软键盘，影响整个界面的视觉效果。
		 * 解决方法如下： 可以在edittext的父布局结构中（例如LinearLayout，RelativeLayout等）添加
		 * 这个两个属性，就可以了。
		 */
		setFocusable(true);
		setFocusableInTouchMode(true);

        View view = findViewById(Res.getViewID(context, "jiuyi_titlebar_layout"));
        if(view != null && view instanceof JiuyiTitleBarBase) {
            m_vTitleBarBase = (JiuyiTitleBarBase) view;
            if (m_vTitleBarBase != null) {
                m_vTitleBarBase.setRelativeLayoutNew(this);
                m_vTitleBarBase.onInit();
            }
        }

		findProgressKeyboard();
	}

	private void findProgressKeyboard() {
		m_pKeyBoard = (JiuyiKeyBoardView) findViewById(Res.getViewID(context, "tzt_v23_keyboard_view"));

		m_vProgressLayout = (RelativeLayout) findViewById(Res.getViewID(context, "jiuyi_progressbarlayout"));
		m_vProgressBar = (ProgressBar) findViewById(Res.getViewID(context, "jiuyi_progressbar"));

		// 每个界面都要求有此回调对象
		onCallBack();

        int nCount = getChildCount();
        for (int i = 0; i < nCount; i++) {
            if(getChildAt(i).getLayoutParams() != null && (getChildAt(i).getLayoutParams().height == LayoutParams.MATCH_PARENT || getChildAt(i).getLayoutParams().height == LayoutParams.MATCH_PARENT)){
                mMainView = getChildAt(i);break;
            }
        }
    }

    /**
     * 处理NavigationBar显示隐藏
     */
    public void dealNavigationBarVisiableChange(int nCurrBodyHeight, int nDeltaChange){
        if(mCanvasInterface != null)
            mCanvasInterface.dealNavigationBarVisiableChange(nCurrBodyHeight, nDeltaChange);
    }

    public void onResume() {
        if (pActivity != null && new JiuyiWindowFlagParamShared().onGetData() == 1) {
            pActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    public void onPause() {


        if(pActivity != null) {
            pActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
	}

	public void onStop() {

	}

	public void onDestroy() {

	}



	public JiuyiTitleBarBase getTitleBar() {
		return m_vTitleBarBase;
	}

    /**
     * 设置标题内容
     * @param sTitleMid 中间标题
     * @param sTitleLeft
     * @param sTitleRight
     */
	public void setTitle(String sTitleMid, String sTitleLeft, String sTitleRight, String titleType) {
        if(m_vTitleBarBase != null){
            m_vTitleBarBase.setTitle(null, sTitleMid, sTitleLeft, sTitleRight, titleType);
        }
	}
	


	public RelativeLayout getProgressLayout() {
		return m_vProgressLayout;
	}

	public ProgressBar getProgressBar() {
		return m_vProgressBar;
	}

	public JiuyiKeyBoardView getKeyBoardView() {
		return m_pKeyBoard;
	}

	public Activity getActivity() {
		return pActivity;
	}

    /**
     * 点击输入框，键盘吧输入框上推
     * 之所以采用mMainView的上推，是因为不想把标题栏一并上推
     * @param x
     * @param y
     */
	public void keyboardScrollTo(int x, int y){
		if(mCanvasInterface == null)
			return;

        if(mMainView != null&& mMainView.getLayoutParams() instanceof RelativeLayout.LayoutParams){
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mMainView.getLayoutParams();
            if (lp != null) {
                lp.topMargin = -y;
                mMainView.setLayoutParams(lp);
            }
        }else {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getLayoutParams();
            if (lp != null) {
                lp.topMargin = -y;
                setLayoutParams(lp);
            }
        }
    }




	/**
	 * 返回ProgressBar是否再转动
	 * 
	 * @return
	 */
	public boolean isProgressBar() {
		if (m_vProgressBar != null) {
			if (m_vProgressBar.getVisibility() == View.VISIBLE)
				return true;
		}
		return false;
	}

	public void startProgressBar() {
		if (m_vProgressBar != null) {
			m_vProgressBar.setVisibility(View.VISIBLE);
		}
	}

	public void stopProgressBar() {
		if (m_vProgressBar != null) {
			m_vProgressBar.setVisibility(View.GONE);
		}
	}

	/**
	 * 初始化ajax的回调
	 */
	public void onCallBack() {
		pAjaxWebClientUrlLis = new AjaxWebClientUrlLis(new AjaxWebClientUrlLisCallBack() {
			// 设置标题
			public JiuyiICanvasInterface getCanvasInterface() {
				return mCanvasInterface;
			}

            @Override
			public Activity getActivity() {
				return pActivity;
			}



			@Override
			public AjaxWebClientUrlLis getAjaxWebClientUrlLis() {
				return pAjaxWebClientUrlLis;
			}

			@Override
			public JiuyiRelativeLayout getRelativeLayout() {
				return JiuyiRelativeLayout.this;
			}
		});
	}

	/**
	 * 获取主工作区的AjaxWebClientUrlLis对象
	 */
	public AjaxWebClientUrlLis getAjaxWebClientUrlLis() {
		return pAjaxWebClientUrlLis;
	}

	/**
	 * 获取主工作区的LayoutBase的Dd象的mPageType
	 */
	public int getPageType() {
		return mCanvasInterface.getPageType();
	}

	/**
	 * 获取主工作区的LayoutBase对象
	 */
	public JiuyiICanvasInterface getCanvasInterface() {
		return mCanvasInterface;
	}

	/**
	 * 启动退出系统的Dialog
	 */
	public void StartSystemQiutDialog() {
		if (getCanvasInterface() != null) {
			JiuyiDialogBase.DialogStruct mDialogStruct = new JiuyiDialogBase.DialogStruct(Res.getString(null, "exit_action_true"), Res.getString(null, "exit_action_cancel"));
			getCanvasInterface().startDialog(DialogID.DialogSystemQiut, "", Res.getString(null, "exit_content"), JiuyiDialogBase.Dialog_Type_YesNo, mDialogStruct);
        }
	}

	/**
	 * 获取主工作区的view对象
	 */
	public View getMainView() {
		return mMainView;
	}


}
