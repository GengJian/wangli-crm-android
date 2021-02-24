package com.control.widget.webview;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.control.utils.Func;
import com.control.utils.Res;
import com.control.shared.JiuyiWebTextSizeShared;

public class JiuyiPopWndModifyWebTextSize extends LinearLayout
{
	PopupWindow pKeyBoardPop;
	private com.control.widget.webview.JiuyiWebView jiuyiWebView;
	/**
	 * 菜单popupWindow
	 */
	PopupWindow menuPop;

	public JiuyiPopWndModifyWebTextSize(Context context, com.control.widget.webview.JiuyiWebView jiuyiWebView, int nHeight)
	{
		super(context);

		this.jiuyiWebView = jiuyiWebView;
		setPopMenu(nHeight);
	}

	/**
	 * 初始化更多选项的View
	 */
	public void initMenuView(int nHeight)
	{
		this.setOrientation(LinearLayout.VERTICAL);

		LinearLayout layoutMenu = new LinearLayout(getContext());
		layoutMenu.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, nHeight));
		layoutMenu.setGravity(Gravity.CENTER);
		layoutMenu.setOrientation(LinearLayout.VERTICAL);

		LinearLayout layoutLine = new LinearLayout(getContext());
		layoutLine.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layoutLine.setGravity(Gravity.CENTER);
		layoutLine.setOrientation(LinearLayout.HORIZONTAL);

		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		layoutParams.weight = 1;
		layoutParams.setMargins(0, 20, 0, 20);

		ImageView img = new ImageView(layoutLine.getContext());
		img.setLayoutParams(layoutParams);
		img.setImageResource(Res.getDrawabelID(getContext(), "tztwebtextsizesmall"));
		img.setTag("SMALL");
		img.setOnClickListener(onClickListener);
		layoutLine.addView(img);

		img = new ImageView(layoutLine.getContext());
		img.setLayoutParams(layoutParams);
		img.setImageResource(Res.getDrawabelID(getContext(), "tztwebtextsizemiddle"));
		img.setTag("MIDDLE");
		img.setOnClickListener(onClickListener);
		layoutLine.addView(img);

		img = new ImageView(layoutLine.getContext());
		img.setLayoutParams(layoutParams);
		img.setImageResource(Res.getDrawabelID(getContext(), "tztwebtextsizebig"));
		img.setTag("BIG");
		img.setOnClickListener(onClickListener);
		layoutLine.addView(img);

		layoutMenu.addView(layoutLine);
		addView(layoutMenu);
		layoutMenu.setBackgroundColor(Res.getColor(getContext(), "title_background_color"));
	}

	public View.OnClickListener onClickListener = new View.OnClickListener() {
		public void onClick(View v) {
			if (v == null)
			{
				return;
			}
			if(menuPop != null && menuPop.isShowing())
			{
				menuPop.dismiss();
			}

			String sTag = (String) v.getTag();
			if (Func.IsStringEmpty(sTag))
			{
				return;
			}

			TextSize nTextSize = WebSettings.TextSize.NORMAL;
			int nSaveTextSize = 100;
			if (sTag.equals("SMALL"))
			{
				nTextSize = WebSettings.TextSize.SMALLER;
				nSaveTextSize = 75;
			}
			else if (sTag.equals("MIDDLE"))
			{
				nTextSize = WebSettings.TextSize.NORMAL;
				nSaveTextSize = 100;
			}
			else if (sTag.equals("BIG"))
			{
				nTextSize = WebSettings.TextSize.LARGER;
				nSaveTextSize = 125;
			}

			if (jiuyiWebView != null)
			{
				jiuyiWebView.setWebTextSize(nTextSize);
                JiuyiWebTextSizeShared.getIns().onSaveData(getContext(), nSaveTextSize);
			}
		}
	};

	public void SetPopWnd(PopupWindow popupWindow)
	{
		if (popupWindow != null)
		{
			menuPop = popupWindow;
		}
		else
		{
			menuPop = null;
		}
	}

	private void setPopMenu(int nHeight)
	{
		initMenuView(nHeight);
	}

}
